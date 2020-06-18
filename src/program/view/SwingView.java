package program.view;

import program.controller.ControllerSwing;

import javax.swing.*;
import java.awt.event.*;

public class SwingView extends JFrame {
    private JButton bStart = new JButton("Start game");
    private JButton bSettings = new JButton("Settings");
    private ControllerSwing controllerSwing;

    /***
     * Konstruktor tworzący kontroler głównego widoku
     */
    public SwingView() {
        controllerSwing = new ControllerSwing();
    }

    /***
     * Funkcja inicjalizująca główny widok, a także dodająca listenery do dwóch przycisków
     */
    public void init() {
        setLayout(null);
        setSize(600, 350);
        setLocationRelativeTo(null);
        add(bStart);
        add(bSettings);
        bStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controllerSwing.runGame();
                setEnabled(false);
            }
        });
        bSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controllerSwing.runSettings();
                setEnabled(true);
            }
        });
        bStart.setBounds(100, 50, 150, 75);
        bSettings.setBounds(350, 50, 150, 75);
        setTitle("TETRIS");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}