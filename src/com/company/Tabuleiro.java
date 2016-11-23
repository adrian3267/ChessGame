package com.company;


import com.company.Pieces.*;
import javafx.util.Pair;
import java.awt.*;
import java.util.ArrayList;

public class Tabuleiro{
    public GameController game;
    public ArrayList<Piece> whitePieces;
    public ArrayList<Piece> blackPieces;
    public Blank[][] tabuleiro;

    Tabuleiro(GameController game){
        this.game = game;
        tabuleiro = new Blank[8][8];
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for (int ii = 0; ii < tabuleiro.length; ii++) {
            for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
                Blank bnt = new Blank(game, jj, ii);
                bnt.setPreferredSize(new Dimension(45, 45));
                //ImageIcon icon = new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB));
                //b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    bnt.setBackground(Color.WHITE);
                } else {
                    bnt.setBackground(Color.DARK_GRAY);
                }
                tabuleiro[jj][ii] = bnt;
            }
        }

    }


    void clear(){
        for (int ii = 0; ii < tabuleiro.length; ii++) {
            for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
                if (tabuleiro[jj][ii].thereIsPiece) {
                    tabuleiro[jj][ii].removePiece();
                }
            }
        }
    }

    void organizar(){
        //Pawns
        for(int i = 0; i < 8; i++){
            tabuleiro[i][1].setPiece(new Pawn(game, 2, tabuleiro[i][1]));
            tabuleiro[i][6].setPiece(new Pawn(game, 1, tabuleiro[i][6]));
        }
        //Knights
        tabuleiro[1][0].setPiece(new Knight(game, 2, tabuleiro[1][0]));
        tabuleiro[6][0].setPiece(new Knight(game, 2, tabuleiro[6][0]));
        tabuleiro[1][7].setPiece(new Knight(game, 1, tabuleiro[1][7]));
        tabuleiro[6][7].setPiece(new Knight(game, 1, tabuleiro[6][7]));
        //Towers
        tabuleiro[0][0].setPiece(new Tower(game, 2, tabuleiro[0][0]));
        tabuleiro[7][0].setPiece(new Tower(game, 2, tabuleiro[7][0]));
        tabuleiro[0][7].setPiece(new Tower(game, 1, tabuleiro[0][7]));
        tabuleiro[7][7].setPiece(new Tower(game, 1, tabuleiro[7][7]));
        //Bishop
        tabuleiro[2][0].setPiece(new Bishop(game, 2, tabuleiro[2][0]));
        tabuleiro[5][0].setPiece(new Bishop(game, 2, tabuleiro[5][0]));
        tabuleiro[2][7].setPiece(new Bishop(game, 1, tabuleiro[2][7]));
        tabuleiro[5][7].setPiece(new Bishop(game, 1, tabuleiro[5][7]));
        //Queen
        tabuleiro[3][0].setPiece(new Queen(game, 2, tabuleiro[3][0]));
        tabuleiro[3][7].setPiece(new Queen(game, 1, tabuleiro[3][7]));
        //King
        tabuleiro[4][0].setPiece(new King(game, 2, tabuleiro[4][0]));
        tabuleiro[4][7].setPiece(new King(game, 1, tabuleiro[4][7]));

        separetePieces();

    }


    private void separetePieces(){
        //Agrupar peças da mesma cor
        for (int ii = 0; ii < tabuleiro.length; ii++) {
            for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
                if(tabuleiro[jj][ii].thereIsPiece){
                    if(tabuleiro[jj][ii].piece.player == 1){
                        whitePieces.add(tabuleiro[jj][ii].piece);
                    }else{
                        blackPieces.add(tabuleiro[jj][ii].piece);
                    }
                }
            }
        }
    }

    void cancelPreview(){
        for (int ii = 0; ii < tabuleiro.length; ii++) {
            for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    tabuleiro[jj][ii].setBackground(Color.WHITE);
                    tabuleiro[jj][ii].selected = false;
                } else {
                    tabuleiro[jj][ii].setBackground(Color.DARK_GRAY);
                    tabuleiro[jj][ii].selected = false;
                }
            }
        }
    }

    public Blank position(int x, int y){
        if(x>7 || x<0 || y>7 || y<0){ //Fora do limites
            return null;
        }
        return this.tabuleiro[x][y];
    }


    void executeListOfMoves(ArrayList<Pair<Position, Position>> moveList){
        for (Pair<Position, Position> move : moveList) {
            int fromX = move.getKey().x;
            int fromY = move.getKey().y;
            int toX = move.getValue().x;
            int toY = move.getValue().y;
            Movement movement = new Movement(position(fromX,fromY).piece, position(toX,toY));
            movement.move();
        }

    }

    void printTabuleiro(){
        for (int ii = 0; ii < tabuleiro.length; ii++) {
            for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
                printPiece(tabuleiro[jj][ii]);
            }
            System.out.println("");
        }
    }

    void printPiece(Blank place){
        if(place.thereIsPiece){
            String name = "";
            if(place.piece.type.equals("Bishop")){
                name = "Bi";
            }else if(place.piece.type.equals("King")){
                name = "Ki";
            }else if(place.piece.type.equals("Knight")){
                name = "Kn";
            }else if(place.piece.type.equals("Pawn")){
                name = "pa";
            }else if(place.piece.type.equals("Queen")){
                name = "Qu";
            }else if(place.piece.type.equals("Tower")){
                name = "To";
            }
            System.out.print("["+name+"]");
        }else{
            System.out.print("[  ]");
        }
    }


}
