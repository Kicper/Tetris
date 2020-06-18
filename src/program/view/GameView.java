package program.view;

import program.model.FigureColor;

import javax.swing.*;

public class GameView extends JFrame {
    private JLabel game;
    private BoardView boardView;
    private FigureColor figureColor;

    /***
     * Konstruktor przekazujący jeden argument i tworzący widok planszy
     * @param figureColor - obiekt figureColor przekazywany dalej
     */
    public GameView(FigureColor figureColor) {
        game = new JLabel("GAME");
        this.figureColor = figureColor;
        boardView = new BoardView(figureColor);
    }

    /***
     * Funkcja inicjalizująca widok gry
     */
    public void init() {
        setLayout(null);
        setSize(300, 800);
        boardView.setBounds(0,0,300,750);
        setLocationRelativeTo(null);
        setTitle("GAME");
        add(boardView);
        boardView.start();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
