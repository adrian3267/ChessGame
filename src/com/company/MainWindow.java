package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class MainWindow {

    private final JPanel gui = new JPanel(new BorderLayout(2, 1));
    public JLabel dialogo = new JLabel("O jogo iniciou");
    private static final String COLS = "01234567";//"ABCDEFGH";
    GameController game;

    MainWindow() {
        game = new GameController(dialogo);
        iniciarGui();
    }


    public final void iniciarGui() {
        //Header da página
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        JButton newGameBnt = new JButton("Novo Jogo");
        tools.add(newGameBnt);
        tools.addSeparator();
        tools.add(dialogo);

        //Painel
        JPanel painel = new JPanel(new GridLayout(0, 9));
        painel.setBorder(new LineBorder(Color.BLUE));
        gui.add(tools, BorderLayout.PAGE_START);
        gui.add(painel);

        game.start();

        newGameBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        } );

        //Montar tela principal
        painel.add(new JLabel(""));
        // Adicionar coluna com as letras
        for (int ii = 0; ii < 8; ii++) {
            painel.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
        }

        //Preencher as colunas e linhas da tela principal
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        painel.add(new JLabel("" + (ii), SwingConstants.CENTER));
                    default:
                        painel.add(game.tabuleiro.position(jj,ii));
                }
            }
        }
    }


    JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable thread = new Runnable() {

            @Override
            public void run() {
                MainWindow tela = new MainWindow();

                JFrame f = new JFrame("Xadrez");
                f.add(tela.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack(); //Garantir que a tela tenha o minimo tamanho para exibir tudo
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(thread);
    }
}