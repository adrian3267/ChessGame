package com.company.IA;


import com.company.Blank;
import com.company.Movement;
import com.company.Pieces.Piece;
import com.company.Tabuleiro;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Pastor {

    public Pastor(){}

    boolean condition(Tabuleiro tabuleiro){
        Blank[][] tab = tabuleiro.tabuleiro;
        return !tab[3][4].thereIsPiece &&
                !tab[4][5].thereIsPiece &&
                !tab[5][3].thereIsPiece &&
                !tab[5][4].thereIsPiece &&
                !tab[5][5].thereIsPiece &&
                tab[5][6].thereIsPiece;

    }

    @Nullable
    Piece getPawn(Tabuleiro tabuleiro){
        for (Piece piece : tabuleiro.blackPieces) {
            if(piece.type.equals("Pawn") && piece.myPlace.positionY == 1 && piece.myPlace.positionX == 4 ){
                return piece;
            }
        }
        return null;
    }

    @Nullable
    Piece getBishop(Tabuleiro tabuleiro){
        for (Piece piece : tabuleiro.blackPieces) {
            if(piece.type.equals("Bishop") && piece.myPlace.positionY == 0 && piece.myPlace.positionX == 5 ){
                return piece;
            }
        }
        System.out.println("null");
        return null;
    }

    @Nullable
    Piece getQueen(Tabuleiro tabuleiro){
        for (Piece piece : tabuleiro.blackPieces) {
            if(piece.type.equals("Queen")){
                return piece;
            }
        }
        return null;
    }

    @Nullable
    Movement move(Tabuleiro tabuleiro, int number){
        if(condition(tabuleiro)){
            switch (number){
                case 1:
                    return new Movement(getPawn(tabuleiro), tabuleiro.position(4,2));
                case 2:
                    return new Movement(getBishop(tabuleiro), tabuleiro.position(2,3));
                case 3:
                    return new Movement(getQueen(tabuleiro), tabuleiro.position(5,2));
                case 4:
                    return new Movement(getQueen(tabuleiro), tabuleiro.position(5,6));
            }
        }
        return null;

    }
}
