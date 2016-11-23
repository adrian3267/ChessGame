package com.company.Pieces;

import com.company.Blank;
import com.company.GameController;
import com.company.Tabuleiro;
import java.util.ArrayList;

public abstract class Piece {
    public GameController game;
    public int player;
    public String type;
    public Tabuleiro tabuleiro;
    public Blank myPlace;
    public String pathImage;
    public boolean firstMove = true;

    public abstract ArrayList<Blank> possibleMoves();
    public abstract ArrayList<Blank> possibleMovesNoRecursion();

    public void setPlace(Blank newPlace){
        this.myPlace = newPlace;
    }

}
