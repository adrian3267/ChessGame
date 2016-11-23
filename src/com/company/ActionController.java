package com.company;


import com.company.Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class ActionController {
    public GameController game;
    public Tabuleiro tabuleiro;
    private Blank fromSpace;
    private Piece movingPiece;
    private ArrayList<Blank> possibleSpaces;

    public ActionController(GameController game, Blank fromSpace){
        this.game = game;
        this.tabuleiro = game.tabuleiro;
        this.fromSpace = fromSpace;
        this.movingPiece = fromSpace.piece;
        possibleSpaces = new ArrayList<>(movingPiece.possibleMoves());
    }

    public void getPossibleMovements(){
        tabuleiro.cancelPreview();
        if(fromSpace.thereIsPiece && game.isMyTurn(fromSpace.piece.player)) {
            for (Blank space : possibleSpaces) {
                //Mudar a cor para identificar os lugares onde a peça pode movimentar
                if(fromSpace.piece.player == 1){
                    if(space.thereIsPiece){
                        space.setBackground(Color.RED);
                    }else{
                        space.setBackground(Color.CYAN);
                    }
                }else{
                    if(space.thereIsPiece){
                        space.setBackground(Color.RED);
                    }else {
                        space.setBackground(Color.YELLOW);
                    }
                }

                space.selected = true;
                space.moveIfSelected(this.movingPiece);
            }
        }

    }


}
