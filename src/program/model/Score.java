package program.model;

public class Score {
    private int finalResult;

    /***
     * Konstruktor przypisujący zmiennej finalResult wartość 0
     */
    public Score() {
        finalResult = 0;
    }

    /***
     * Funkcja dodająca punkt
     */
    public void addPoint() {
        finalResult += 1;
    }

    /***
     * Funkcja dodająca cztery punkty
     */
    public void addFourPoints() {
        finalResult += 4;
    }

    /***
     * Funkcja zwracająca wynik
     * @return - zwracany wynik
     */
    public int getFinalResult() {
        return finalResult;
    }
}
