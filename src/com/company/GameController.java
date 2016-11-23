package com.company;

import com.company.IA.CPU;
import com.company.Pieces.Piece;
import javafx.util.Pair;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController {
    private JLabel dialogo;
    private int turnPlayer;
    public Tabuleiro tabuleiro;
    boolean gameOver;
    boolean cpuEnable = false;
    CPU cpu;
    ArrayList<Pair<Position, Position>> moveList;

    public GameController(JLabel dialogo){
        this.dialogo = dialogo;
        gameOver = false;
        moveList = new ArrayList<>();
        tabuleiro = new Tabuleiro(this);
    }

    public GameController(){
        dialogo = new JLabel();
        gameOver = false;
        moveList = new ArrayList<>();
        tabuleiro = new Tabuleiro(this);

    }


    void nextTurn(){
        if(cpuEnable){
            if(!gameOver){
                if(turnPlayer == 1){
                    turnPlayer = 2;
                    if(cpu.makeMove()){
                        nextTurn();
                    }else{
                        dialogo.setForeground(Color.RED);
                        dialogo.setText("A CPU desiste");
                    }
                }else if(turnPlayer == 2){
                    turnPlayer = 1;
                    dialogo.setForeground(Color.BLACK);
                    dialogo.setText("Sua vez, Jogador 1");
                }
            }
            if(isPlayerInCheck(tabuleiro, turnPlayer)){
                dialogo.setForeground(Color.RED);
                if(turnPlayer == 1) {
                    dialogo.setText("Você está em cheque, Jogador 1");
                } else {
                    dialogo.setText("Você está em cheque, Jogador 2");
                }
            }
        }else{
            if(!gameOver){
                if(turnPlayer == 1){
                    turnPlayer = 2;
                    dialogo.setForeground(Color.BLACK);
                    dialogo.setText("Sua vez, Jogador 2");
                }else if(turnPlayer == 2){
                    turnPlayer = 1;
                    dialogo.setForeground(Color.BLACK);
                    dialogo.setText("Sua vez, Jogador 1");
                }
            }
            if(isPlayerInCheck(tabuleiro, turnPlayer)){
                dialogo.setForeground(Color.RED);
                if(turnPlayer == 1) {
                    dialogo.setText("Você está em cheque, Jogador 1");
                } else {
                    dialogo.setText("Você está em cheque, Jogador 2");
                }
            }
        }



    }

    boolean isMyTurn(int player){
        return player == this.turnPlayer;
    }

    void start(){
        Object stringArray[] = { "Player 1 Vs CPU", "Player 1 Vs Player 2" };
        int resposta = JOptionPane.showOptionDialog(null, "Qual modo você deseja jogar?",
                "Xadrez: Modo de Jogo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, stringArray, stringArray[0]);
        if (resposta == JOptionPane.NO_OPTION) {
            System.out.println("Multiplayer");
            turnPlayer = 1;
            gameOver = false;
            moveList = new ArrayList<>();
            dialogo.setForeground(Color.BLACK);
            dialogo.setText("Podes começar o jogo, Jogador 1");
            tabuleiro.clear();
            tabuleiro.organizar();
        } else if (resposta == JOptionPane.YES_OPTION) {
            cpuEnable = true;
            System.out.println("Singleplayer");
            turnPlayer = 1;
            gameOver = false;
            moveList = new ArrayList<>();
            dialogo.setForeground(Color.BLACK);
            dialogo.setText("Podes começar o jogo, Jogador 1");
            tabuleiro.clear();
            tabuleiro.organizar();
            cpu = new CPU(tabuleiro);
        }else{
            System.exit(0);
        }

    }

    private boolean isPlayerInCheck(Tabuleiro tabuleiro, int player){
        ArrayList<Piece> enemyPieces = new ArrayList<>();
        switch (player){
            case 1:
                enemyPieces = tabuleiro.blackPieces;
                break;
            case 2:
                enemyPieces = tabuleiro.whitePieces;
                break;
        }
        for(Piece enemyPiece: enemyPieces){
            for (Blank placeToKill : enemyPiece.possibleMovesNoRecursion()) {
                if(placeToKill.thereIsPiece) {
                    if(placeToKill.piece.type.equals("King")){
                        return true;
                    }

                }

            }
        }
        return false;

    }


    private boolean isPlayerInCheckMate(int player){
        GameController gameTest = new GameController();
        gameTest.tabuleiro.organizar();
        gameTest.tabuleiro.executeListOfMoves(this.moveList);
        gameTest.tabuleiro.printTabuleiro();

        ArrayList<Piece> myPieces = new ArrayList<>();
        switch (player){
            case 1:
                myPieces = gameTest.tabuleiro.whitePieces;
                break;
            case 2:
                myPieces = gameTest.tabuleiro.blackPieces;
                break;
        }
        System.out.println(myPieces.size());
        for(Piece myPiece: myPieces){
            System.out.println("Type:"+myPiece.myPlace.positionX+"-"+myPiece.myPlace.positionY+":"+myPiece.type);
            System.out.println("Quantos:"+myPiece.possibleMovesNoRecursion().size());
            for(Blank placeToMove: myPiece.possibleMovesNoRecursion()) {
                Movement movement = new Movement(myPiece, placeToMove);
                movement.move();
                System.out.println("To:"+placeToMove.positionX+"-"+placeToMove.positionY);
                if (!isPlayerInCheck(gameTest.tabuleiro, myPiece.player)){
                    gameTest.tabuleiro.printTabuleiro();
                    return false;
                }
                movement.moveBack();
            }
        }

        dialogo.setForeground(Color.RED);
        if (player == 1) {
            dialogo.setText("Game Over, Jogador 2 venceu!!!");
        } else {
            dialogo.setText("Game Over, Jogador 1 venceu!!!");
        }
        return true;

    }

    // Somente para o jogador não se dar check
    public boolean autoCheck(Piece piece, Blank placeToMove, int player){
        GameController gameTest = new GameController();
        gameTest.tabuleiro.organizar();
        gameTest.tabuleiro.executeListOfMoves(this.moveList);
        Piece testPiece = gameTest.tabuleiro.position(piece.myPlace.positionX, piece.myPlace.positionY).piece;
        Blank testPlaceToMove = gameTest.tabuleiro.position(placeToMove.positionX, placeToMove.positionY);
        Movement movement = new Movement(testPiece, testPlaceToMove);
        movement.move();
        return isPlayerInCheck(gameTest.tabuleiro, player);

    }



}
