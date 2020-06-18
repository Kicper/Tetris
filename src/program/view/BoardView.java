package program.view;

import program.controller.ControllerBoard;
import program.model.FigureColor;
import program.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardView extends JPanel implements ActionListener {
    private final int WIDTH = 10;
    private final int HEIGHT = 25;
    private ControllerBoard controllerBoard;
    private FigureColor figureColor;

    /***
     * Konstruktor tworzący kontroler, listenery, a także inicjalizujący kolory jeżeli w ustawieniach nie zostało to zrobione
     * @param figureColor - obiekt potrzebny do przekazania kolorów do tej klasy
     */
    BoardView(FigureColor figureColor) {
        setFocusable(true);
        setBackground(Color.BLACK);
        controllerBoard = new ControllerBoard(WIDTH, HEIGHT, this);
        addKeyListener(new TAdapter());
        this.figureColor = figureColor;
        if(figureColor.settingUsed == false) {
            figureColor.init();
        }
    }

    /***
     * Funkcja uruchamiająca kontroler planszy
     */
    void start() {
        controllerBoard.start();
    }

    /***
     * Funkcja odpowiedzialna za malowanie figur - wywołuje malowanie w kontrolerze
     * @param graph - standardowy argument
     */
    public void paint(Graphics graph) {
        super.paint(graph);
        controllerBoard.paint(graph, getSize().getWidth(), getSize().getHeight());
    }

    /***
     * Funkcja zwracająca właściwą szerokość pola figury (kwadratu)
     * @return - szerokość kawadratu
     */
    private int squareWidth() { return (int) getSize().getWidth() / WIDTH; }

    /***
     * Funkcja zwracająca właściwą wysokość pola figury (kwadratu)
     * @return - wysokość kawadratu
     */
    private int squareHeight() { return (int) getSize().getHeight() / HEIGHT; }

    /***
     * Funkcja kolorująca pola
     * @param graph - standardowy argument
     * @param x - współrzędna x, która ma być pokolorowana
     * @param y - współrzędna y, która ma być pokolorowana
     * @param fig - figura do której współrzedna (x, y) należy
     */
    public void drawFigure(Graphics graph, int x, int y, Shape.Figure fig) {
        Color colors[] = {new Color(0, 0, 0), figureColor.getColor(0),
                figureColor.getColor(1), figureColor.getColor(2),
                figureColor.getColor(3), figureColor.getColor(4),
                figureColor.getColor(5), figureColor.getColor(6)
        };
        Color color = colors[fig.ordinal()];
        graph.setColor(color);
        graph.fillRect(x+1, y+1, squareWidth()-2, squareHeight()-2);

        graph.setColor(color.brighter());
        graph.drawLine(x, y+squareHeight()-1, x, y);
        graph.drawLine(x, y, x+squareWidth()-1, y);

        graph.setColor(color.darker());
        graph.drawLine(x+1, y+squareHeight()-1, x+squareWidth()-1, y+squareHeight()-1);
        graph.drawLine(x+squareWidth()-1, y+squareHeight()-1,  x+squareWidth()-1, y+1);
    }

    /***
     * Funkcja wywołująca spadanie
     * @param e - standardowy argument
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerBoard.fall();
    }

    /***
     * Funkcja odczytująca przyciskane przyciski i przesyłająca ich działanie do kontrolera
     */
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if(!controllerBoard.isStarted() || controllerBoard.isNoShape()) {
                return;
            }

            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ESCAPE) {
                controllerBoard.pause();
                return;
            }

            if (controllerBoard.isPaused())
                return;

            switch (key) {
                case KeyEvent.VK_LEFT:
                    controllerBoard.shiftLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    controllerBoard.shiftRight();
                    break;
                case 'a':
                    controllerBoard.rotateLeft();
                    break;
                case 'A':
                    controllerBoard.rotateLeft();
                    break;
                case 'd':
                    controllerBoard.rotateRight();
                    break;
                case 'D':
                    controllerBoard.rotateRight();
                    break;
                case KeyEvent.VK_SPACE:
                    controllerBoard.putDown();
                    break;
                case KeyEvent.VK_DOWN:
                    controllerBoard.putLineDown();
                    break;
            }
        }
    }
}
