package com.company.Pieces;

import com.company.Blank;
import com.company.GameController;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(GameController game, int player, Blank place){
        this.game = game;
        this.tabuleiro = game.tabuleiro;
        this.player = player;
        this.myPlace = place;
        this.type = "Pawn";
        if(player == 2){
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\pawn_b.png";
        }else{
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\pawn_w.png";
        }
    }

    @Override
    public ArrayList<Blank> possibleMoves() {
        ArrayList<Blank> possiblePlaces = new ArrayList<>();
        ArrayList<Blank> possiblePlacesAttack = new ArrayList<>();
        if(player == 1){
            possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY-1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY-1));
            if(firstMove){
                possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-2));
            }
        }else{
            possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY+1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY+1));
            if(firstMove){
                possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+2));
            }
        }
        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(Blank place: possiblePlaces){
            if(place!= null){
                if(!game.autoCheck(this, place, player)){
                    if(place.thereIsPiece) {
                        break;
                    }else{
                        possiblePlacesFinal.add(place);
                    }
                }else{
                    break;
                }
            }else{
                break;
            }
        }
        //Lugares possiveis onde o peao pode somente atacar se tiver uma peça
        for(Blank place: possiblePlacesAttack){
            if(place!= null){
                if(!game.autoCheck(this, place, player)) {
                    if (place.thereIsPiece) {
                        if (place.piece.player != this.player) { //Peça de mesmo jogador
                            possiblePlacesFinal.add(place);
                        }
                    }
                }
            }
        }
        return possiblePlacesFinal;
    }

    @Override
    public ArrayList<Blank> possibleMovesNoRecursion() {
        ArrayList<Blank> possiblePlaces = new ArrayList<>();
        ArrayList<Blank> possiblePlacesAttack = new ArrayList<>();
        if(player == 1){
            possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY-1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY-1));
            if(firstMove){
                possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-2));
            }
        }else{
            possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX+1, myPlace.positionY+1));
            possiblePlacesAttack.add(tabuleiro.position(myPlace.positionX-1, myPlace.positionY+1));
            if(firstMove){
                possiblePlaces.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+2));
            }
        }
        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(Blank place: possiblePlaces){
            if(place!= null){
                if(place.thereIsPiece) {
                    break;
                }else{
                    possiblePlacesFinal.add(place);
                }
            }else{
                break;
            }
        }
        //Lugares possiveis onde o peao pode somente atacar se tiver uma peça
        for(Blank place: possiblePlacesAttack){
            if(place!= null) {
                if (place.thereIsPiece ) {
                    if (place.piece.player != this.player) { //Peça de mesmo jogador
                        possiblePlacesFinal.add(place);
                    }
                }
            }
        }
        return possiblePlacesFinal;
    }
}
