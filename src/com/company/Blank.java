package com.company;

import com.company.Pieces.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Blank extends JButton {
    public GameController game;
    public Tabuleiro tabuleiro;
    public Piece piece;
    public boolean thereIsPiece;
    public boolean selected;
    public int positionX;
    public int positionY;
    private Blank mySpace = this;


    public Blank(GameController game, int positionX, int positionY){
        this.game = game;
        this.tabuleiro = game.tabuleiro;
        this.positionX = positionX;
        this.positionY = positionY;
        thereIsPiece = false;
        selected = false;
        setIcon(null);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!thereIsPiece){
                    game.tabuleiro.cancelPreview();
                }
            }
        } );
    }




    public void setPiece(Piece piece){
        thereIsPiece = true;
        this.piece = piece;
        this.piece.setPlace(this);
        setIcon(new ImageIcon(piece.pathImage));
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(thereIsPiece && game.isMyTurn(piece.player) && !game.gameOver){
                    ActionController action = new ActionController(game, mySpace);
                    action.getPossibleMovements();
                }

            }
        } );
    }

    public void removePiece(){
        thereIsPiece = false;
        selected = false;
        piece = null;
        setIcon(null);
    }

    //Caso seja selecionado para a peça mover para esse espaço(Blank)
    public void moveIfSelected(Piece piece){
        Blank placeTo = this;
        addActionListener(new Movement(piece, placeTo));
    }
}
