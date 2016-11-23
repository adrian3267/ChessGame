package com.company.IA;

import com.company.Movement;
import com.company.Tabuleiro;


public class CPU {
    Tabuleiro tabuleiro;
    Pastor pastor;
    int jogada;

    public CPU(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        pastor = new Pastor();
        jogada = 1;
    }

    public boolean makeMove(){
        Movement movement = pastor.move(tabuleiro, jogada);
        if(movement != null){
            movement.move();
            ++jogada;
            return true;
        }else{
            return false;
        }

    }
}


