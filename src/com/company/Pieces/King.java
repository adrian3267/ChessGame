package com.company.Pieces;


import com.company.Blank;
import com.company.GameController;

import java.util.ArrayList;

public class King extends Piece {

    public King(GameController game, int player, Blank place){
        this.game = game;
        this.tabuleiro = game.tabuleiro;
        this.player = player;
        this.myPlace = place;
        this.type = "King";
        if(player == 2){
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\king_black.png";
        }else{
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\king_white.png";
        }
    }

    @Override
    public ArrayList<Blank> possibleMoves() {
        ArrayList<Blank> possiblePlaces = new ArrayList<>();
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY-1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY-1));
        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(Blank place: possiblePlaces){
            if(place!= null) {
                if (!game.autoCheck(this, place, player)) {
                    if (place.thereIsPiece) {
                        if (place.piece.player != this.player) { //Peça de mesmo jogador
                            possiblePlacesFinal.add(place);
                        }
                    } else {
                        possiblePlacesFinal.add(place);
                    }
                }
            }
        }
        return possiblePlacesFinal;
    }

    @Override
    public ArrayList<Blank> possibleMovesNoRecursion() {
        ArrayList<Blank> possiblePlaces = new ArrayList<>();
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY-1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY+1));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY));
        possiblePlaces.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY-1));
        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(Blank place: possiblePlaces){
            if(place!= null){
                if(place.thereIsPiece) {
                    if (place.piece.player != this.player) { //Peça de mesmo jogador
                        possiblePlacesFinal.add(place);
                    }
                }else{
                    possiblePlacesFinal.add(place);
                }
            }
        }
        return possiblePlacesFinal;
    }
}
