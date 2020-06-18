package program.controller;

import program.model.Score;
import program.model.Shape;
import program.view.BoardView;

import javax.swing.*;
import java.awt.*;

public class ControllerBoard {
    private BoardView boardView;
    private Shape actualFigure;
    private Timer timer;
    private Shape.Figure[] board;
    private Score score;
    private int timeAcceleration = 10;
    private int boardWidth;
    private int boardHeight;
    private int actualX = 0;
    private int actualY = 0;
    private boolean isPaused = false;
    private boolean isStarted = false;
    private boolean isDoneFalling = false;

    /***
     * Konstruktor tworzący i uruchamiający timer, przypisujący wielkości planszy do zmiennych, czyszczący planszę, tworzący klasę punkty
     * @param boardWidth - parametr z szerokością planszy
     * @param boardHeight - parametr z wysokością planszy
     * @param boardView - widok planszy
     */
    public ControllerBoard (int boardWidth, int boardHeight, BoardView boardView) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardView = boardView;
        actualFigure = new Shape();
        timer = new Timer(500, boardView);
        timer.start();
        board = new Shape.Figure[boardWidth * boardHeight];
        clearBoard();
        score = new Score();
    }

    /***
     * Funkcja malująca planszę i określająca, w którym miejscu jaką figurę należy zakolorować
     * @param graph - standardowy argument
     * @param width - wysokość planszy
     * @param height - szerokość planszy
     */
    public void paint(Graphics graph, double width, double height) {
        int squareWidth = (int) width/boardWidth;
        int squareHeight = (int) height/boardHeight;
        int topOfBoard = (int) height - boardHeight * squareHeight;
        for (int i=0; i<boardHeight; ++i) {
            for (int j=0; j<boardWidth; ++j) {
                Shape.Figure fig = figurePosition(j, boardHeight-i-1);
                if (fig != Shape.Figure.lackF)
                    boardView.drawFigure(graph, j*squareWidth, topOfBoard+i*squareHeight, fig);
            }
        }
        if (actualFigure.getFigure() != program.model.Shape.Figure.lackF) {
            for (int i= 0; i <= 3; ++i) {
                int x = actualX + actualFigure.x(i);
                int y = actualY - actualFigure.y(i);
                boardView.drawFigure(graph, x*squareWidth, topOfBoard+(boardHeight-y-1)*squareHeight, actualFigure.getFigure());
            }
        }
    }

    /***
     * Funkcja uruchamiająca grę, generująca figurę, rozpoczyna timer
     */
    public void start() {
        if (isPaused) return;
        isStarted = true;
        isDoneFalling = false;
        clearBoard();
        generateNewFigure();
        timer.start();
    }

    /***
     * Funkcja zatrzymująca grę
     */
    public void pause() {
        if (!isStarted)
            return;
        if (!isPaused) {
            timer.stop();
        } else {
            timer.start();
        }
        isPaused = !isPaused;
        boardView.repaint();
    }

    /***
     * Funkcja zwracajaca informację o tym czy figura nie jest "pusta"
     * @return - zwracana informacja
     */
    public boolean isNoShape() {
        return (actualFigure.getFigure() == Shape.Figure.lackF);
    }

    /***
     * Funkcja zwracajaca informację o tym czy gra jest zatrzymana
     * @return - zwracana informacja
     */
    public boolean isPaused() {
        return isPaused;
    }

    /***
     * Funkcja zwracajaca informację o tym czy gra się rozpoczęła
     * @return - zwracana informacja
     */
    public boolean isStarted() {
        return isStarted;
    }

    /***
     * Funkcja sprawdzająca czy element już upadł
     */
    public void fall() {
        if (isDoneFalling) {
            isDoneFalling = false;
            generateNewFigure();
        } else
            putLineDown();
    }

    /***
     * Funcja opuszczająca w dół o jedna linię element
     */
    public void putLineDown() {
        if (!tryMove(actualFigure, actualX, actualY-1))
            dropFigure();
    }

    /***
     * Funkcja, która zauważa upadek figury i generuje nową figurę
     */
    private void dropFigure(){
        for (int i=0; i<4; ++i) {
            int x = actualX + actualFigure.x(i);
            int y = actualY - actualFigure.y(i);
            board[(y*boardWidth)+x] = actualFigure.getFigure();
        }
        removeLines();
        if (!isDoneFalling)
            generateNewFigure();
    }

    /***
     * Funkcja usuwająca linie
     */
    private void removeLines()
    {
        int num=0;
        for (int i=boardHeight-1; i>=0; --i) {
            boolean toRemove = true;
            for (int j=0; j<boardWidth; ++j) {
                if (figurePosition(j, i) == Shape.Figure.lackF) {
                    toRemove = false;
                    break;
                }
            }
            if (toRemove) {
                num += 1;
                for(int k=i; k<boardHeight-1; ++k) {
                    for (int j=0; j<boardWidth; ++j)
                        board[(k*boardWidth)+j] = figurePosition(j, k+1);
                }
            }
        }
        if (num>0) {
            if(num==4) {
                score.addFourPoints();
                if(timer.getDelay()>=150)
                    timer.setDelay(timer.getDelay()-timeAcceleration);
            } else {
                score.addPoint();
                if(timer.getDelay()>=150)
                    timer.setDelay(timer.getDelay()-timeAcceleration);
            }
            isDoneFalling = true;
            actualFigure.setNewShape(Shape.Figure.lackF);
            boardView.repaint();
        }
    }

    /***
     * Funkcja czyszcząca planszę
     */
    private void clearBoard(){
        for (int i=0; i<boardHeight*boardWidth; ++i)
            board[i] = Shape.Figure.lackF;
    }

    /***
     * Funkcja generująca nową figurę, jeżeli jest to niemożliwe to zwracana=y jest wynik użytkownika
     */
    private void generateNewFigure() {
        actualFigure.setRandomShape();
        actualX = boardWidth/2 - 1;
        actualY = boardHeight - 1 + actualFigure.minY();
        if (!tryMove(actualFigure, actualX, actualY)) {
            actualFigure.setNewShape(Shape.Figure.lackF);
            timer.stop();
            isStarted = false;
            JOptionPane.showMessageDialog(boardView,"You lost!\nYour result: "  + score.getFinalResult());
        }
    }

    /***
     * Funkcja zwracająca "pozycję" figury - obszar w którym się ona znajduje
     * @param x - współrzędna x
     * @param y - współrzędna y
     * @return - pole w którym figura się znajduje
     */
    private Shape.Figure figurePosition(int x, int y) {
        return board[(y*boardWidth)+x];
    }

    /***
     * Funkcja zwracająca wartość boolean czy możliwe jest umieszczenie figury w danej pozycji
     * @param newFigure - figura
     * @param newX - współrzędna x, w której ma się znaleźć figura
     * @param newY - współrzędna y, w której ma się znaleźć figura
     * @return - zwracan wartość
     */
    private boolean tryMove(Shape newFigure, int newX, int newY){
        for (int i=0; i<=3; ++i) {
            int x = newX + newFigure.x(i);
            int y = newY - newFigure.y(i);
            if (x<0 || y<0 || x>=boardWidth || y>=boardHeight)
                return false;
            if (figurePosition(x, y) != Shape.Figure.lackF)
                return false;
        }
        actualFigure = newFigure;
        actualX = newX;
        actualY = newY;
        boardView.repaint();
        return true;
    }

    /***
     * Funkcja przesuwająca figurę w lewo
     */
    public void shiftLeft() {
        tryMove(actualFigure, actualX-1, actualY);
    }

    /***
     * Funkcja przesuwająca figurę w prawo
     */
    public void shiftRight() {
        tryMove(actualFigure, actualX+1, actualY);
    }

    /***
     * Funkcja obracająca figurę w lewo
     */
    public void rotateLeft() {
        tryMove(actualFigure.rotateLeft(), actualX, actualY);
    }

    /***
     * Funkcja obracająca figurę w prawo
     */
    public void rotateRight() {
        tryMove(actualFigure.rotateRight(), actualX, actualY);
    }

    /***
     * Funkcja przesuwająca element najniżej jak to możliwe
     */
    public void putDown() {
        for (int i=0; i<100; ++i) {
            if (!tryMove(actualFigure, actualX, actualY - 1)) {
                dropFigure();
                break;
            }
        }
    }
}