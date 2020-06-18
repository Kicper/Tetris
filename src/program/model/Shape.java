package program.model;

import java.util.Random;

public class Shape {
    private int[][] coordinates;
    private int[][][] coordinatesFigure;
    private Figure newFigure;

    public enum Figure {
        lackF, leftLF, rightLF, leftZF, rightZF, squareF, tF, lineF
    }

    /***
     * Konstruktor tworzący tablicę wszystkich kształtów i ustawiający kształ początkowy na jego brak
     */
    public Shape() {
        coordinates = new int[4][2];
        coordinatesFigure = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}}, {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
                {{0, 0}, {1, 0}, {0, 1}, {0, 2}}, {{1, 0}, {1, 1}, {0, 1}, {0, 2}},
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
                {{0, 0}, {0, 1}, {1, 1}, {0, 2}}, {{0, 0}, {0, 1}, {0, 2}, {0, 3}}
        };
        setNewShape(Figure.lackF);
    }

    /***
     * Funkcja, która przypisuje do nowej figury wylosowany kształt
     * @param newFigure - przekazany kształt
     */
    public void setNewShape(Figure newFigure) {
        for (int i=0; i<=3; ++i) {
            System.arraycopy(coordinatesFigure[newFigure.ordinal()][i], 0, coordinates[i], 0, 2);
        }
        this.newFigure = newFigure;
    }

    /***
     * Funkcja losująca nowy kształt
     */
    public void setRandomShape() {
        Random random = new Random();
        int counter = (Math.abs(random.nextInt()) % 7) + 1;
        Figure[] chosenShape = Figure.values();
        setNewShape(chosenShape[counter]);
    }

    /***
     * Funkcja zwracająca figurę
     * @return - zwracana figura
     */
    public Figure getFigure() {
        return newFigure;
    }

    /***
     * Funkcja zwracająca minimum współrzędnych figury
     * @return - zwracane minimum
     */
    public int minY() {
        int min = coordinates[0][1];
        for (int i=0; i<=3; ++i)
            min = Math.min(min, coordinates[i][1]);
        return min;
    }

    /***
     * Funkcja zwracajaca konkretną wartość dla współrzędnej
     * @param i - współrzędna
     * @return - wartość
     */
    public int x(int i) {
        return coordinates[i][0];
    }

    /***
     * Funkcja zwracajaca konkretną wartość dla współrzędnej
     * @param i - współrzędna
     * @return - wartość
     */
    public int y(int i) {
        return coordinates[i][1];
    }

    /***
     * Obrót figury w lewo
     * @return - figura otrzymana przez obrót
     */
    public Shape rotateLeft() {
        if(newFigure == Figure.squareF) {
            return this;
        }
        Shape returnShape = new Shape();
        returnShape.newFigure = newFigure;
        for(int i=0; i<=3; ++i) {
            returnShape.coordinates[i][0] = coordinates[i][1];
            returnShape.coordinates[i][1] = -coordinates[i][0];
        }
        return returnShape;
    }

    /***
     * Obrót figury w prawo
     * @return - figura otrzymana przez obrót
     */
    public Shape rotateRight() {
        if (newFigure == Figure.squareF)
            return this;
        Shape returnShape = new Shape();
        returnShape.newFigure = newFigure;
        for (int i=0; i<=3; ++i) {
            returnShape.coordinates[i][0] = -coordinates[i][1];
            returnShape.coordinates[i][1] = coordinates[i][0];
        }
        return returnShape;
    }
}
