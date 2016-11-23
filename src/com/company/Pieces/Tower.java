package com.company.Pieces;

import com.company.Blank;
import com.company.GameController;

import java.util.ArrayList;

public class Tower extends Piece{

    public Tower(GameController game, int player, Blank place){
        this.game = game;
        this.tabuleiro = game.tabuleiro;
        this.player = player;
        this.myPlace = place;
        this.type = "Tower";
        if(player == 2){
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\tower_b.png";
        }else{
            pathImage = "C:\\Users\\thale\\IdeaProjects\\Automatizados\\src\\resources\\tower_w.png";
        }
    }

    @Override
    public ArrayList<Blank> possibleMoves() {
        ArrayList<Blank> path1 = new ArrayList<>();
        ArrayList<Blank> path2 = new ArrayList<>();
        ArrayList<Blank> path3 = new ArrayList<>();
        ArrayList<Blank> path4 = new ArrayList<>();
        for(int i = 1; i < 8; i++){
            path1.add(tabuleiro.position(myPlace.positionX+i, myPlace.positionY));
            path2.add(tabuleiro.position(myPlace.positionX-i, myPlace.positionY));
        }
        for(int j = 1; j < 8; j++){
            path3.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+j));
            path4.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-j));
        }
        ArrayList<ArrayList<Blank>> possiblePlaces = new ArrayList<>();
        possiblePlaces.add(path1);
        possiblePlaces.add(path2);
        possiblePlaces.add(path3);
        possiblePlaces.add(path4);

        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(ArrayList<Blank> arrayPlaces: possiblePlaces){
            for(Blank place: arrayPlaces){
                if(place!= null){
                    if(!game.autoCheck(this, place, player)) {
                        if (place.thereIsPiece) {
                            if (place.piece.player != this.player) { //Peça de mesmo jogador
                                possiblePlacesFinal.add(place);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            possiblePlacesFinal.add(place);
                        }
                    }
                }else{
                    break;
                }
            }
        }

        return possiblePlacesFinal;
    }

    @Override
    public ArrayList<Blank> possibleMovesNoRecursion() {
        ArrayList<Blank> path1 = new ArrayList<>();
        ArrayList<Blank> path2 = new ArrayList<>();
        ArrayList<Blank> path3 = new ArrayList<>();
        ArrayList<Blank> path4 = new ArrayList<>();
        for(int i = 1; i < 8; i++){
            path1.add(tabuleiro.position(myPlace.positionX+i, myPlace.positionY));
            path2.add(tabuleiro.position(myPlace.positionX-i, myPlace.positionY));
        }
        for(int j = 1; j < 8; j++){
            path3.add(tabuleiro.position(myPlace.positionX, myPlace.positionY+j));
            path4.add(tabuleiro.position(myPlace.positionX, myPlace.positionY-j));
        }
        ArrayList<ArrayList<Blank>> possiblePlaces = new ArrayList<>();
        possiblePlaces.add(path1);
        possiblePlaces.add(path2);
        possiblePlaces.add(path3);
        possiblePlaces.add(path4);

        //Adicionar somente movimentos possiveis
        ArrayList<Blank> possiblePlacesFinal = new ArrayList<>();
        for(ArrayList<Blank> arrayPlaces: possiblePlaces){
            for(Blank place: arrayPlaces){
                if(place!= null ){
                    if(place.thereIsPiece) {
                        if (place.piece.player != this.player) { //Peça de mesmo jogador
                            possiblePlacesFinal.add(place);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        possiblePlacesFinal.add(place);
                    }
                }else{
                    break;
                }
            }
        }

        return possiblePlacesFinal;
    }
}
