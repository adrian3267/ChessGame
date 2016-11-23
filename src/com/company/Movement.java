package com.company;

import com.company.Pieces.Piece;
import javafx.util.Pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movement implements ActionListener {
    private Piece piece;
    private Blank placeFrom;
    private Blank placeTo;
    private Tabuleiro tabuleiro;
    private GameController game;
    private Piece pieceKilled;


    public Movement(Piece piece, Blank placeTo){
        this.piece = piece;
        this.placeFrom = piece.myPlace;
        this.placeTo = placeTo;
        this.game = piece.game;
        this.tabuleiro = game.tabuleiro;

    }

    public void move(){
        if(placeTo.thereIsPiece){
            switch(placeTo.piece.player){
                case 1:
                    tabuleiro.whitePieces.remove(placeTo.piece);
                    break;
                case 2:
                    tabuleiro.blackPieces.remove(placeTo.piece);
                    break;
            }
            this.pieceKilled = placeTo.piece;
            placeTo.removePiece();
        }
        placeTo.setPiece(piece);
        placeFrom.removePiece();

    }

    //Refazer o movimento
    void moveBack(){
        placeFrom.setPiece(piece);
        if (pieceKilled != null) {
            placeTo.setPiece(pieceKilled);
            switch(placeTo.piece.player){
                case 1:
                    tabuleiro.whitePieces.add(pieceKilled);
                    break;
                case 2:
                    tabuleiro.blackPieces.add(pieceKilled);
                    break;
            }
        }else{
            placeTo.removePiece();
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(placeTo.selected && piece.tabuleiro.game.isMyTurn(piece.player)){
            move();
            game.moveList.add(new Pair<>(new Position(placeFrom.positionX, placeFrom.positionY), new Position(placeTo.positionX, placeTo.positionY))); //Lista de movimentos do jogo
            piece.firstMove = false;
            tabuleiro.cancelPreview();
            game.nextTurn();
        }

    }
}
