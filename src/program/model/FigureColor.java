package program.model;

import java.awt.*;

public class FigureColor {
    private Color leftLF;
    private Color rightLF;
    private Color leftZF;
    private Color rightZF;
    private Color squareF;
    private Color tF;
    private Color lineF;
    private Color actualColor;
    public boolean settingUsed = false;

    /***
     * Funkcja inicjalizująca kolory
     */
    public void init() {
        leftLF = new Color(0, 255, 0);
        rightLF = new Color(0, 0, 255);
        leftZF = new Color(255, 0, 0);
        rightZF = new Color(255, 255, 0);
        squareF = new Color(0, 255, 255);
        tF = new Color(255, 0, 255);
        lineF = new Color(255, 255, 255);
    }

    /***
     * Funkcja pobierająca kolor dla danego elementu
     * @param i - numer elemetu, dla którego ma zostać pobrany kolor
     * @return - zwracany kolor
     */
    public Color getColor(int i) {
        switch(i) {
            case 0:
                return leftLF;
            case 1:
                return rightLF;
            case 2:
                return leftZF;
            case 3:
                return rightZF;
            case 4:
                return squareF;
            case 5:
                return tF;
            case 6:
                return lineF;
        }
        return actualColor;
    }

    /***
     * Funkcja zmieniająca kolor danego elemntu
     * @param i - numer elementu, którego kolor użytkownik chce zmienić
     * @param string - kolor, na który ma zostać zmieniony element
     */
    public void setColor(int i, String string) {
        settingUsed = true;
        switch (string) {
            case "Yellow":
                actualColor = new Color(255, 255, 0);
                break;
            case "Blue":
                actualColor = new Color(0, 0, 255);;
                break;
            case "Red":
                actualColor = new Color(255, 0, 0);
                break;
            case "Green":
                actualColor = new Color(0, 255, 0);
                break;
            case "Pink":
                actualColor = new Color(0, 255, 255);
                break;
            case "Purple":
                actualColor = new Color(255, 0, 255);
                break;
            case "White":
                actualColor = new Color(255, 255, 255);
                break;
        }
        switch(i) {
            case 0:
                this.leftLF = actualColor;
                break;
            case 1:
                this.rightLF = actualColor;;
                break;
            case 2:
                this.leftZF = actualColor;;
                break;
            case 3:
                this.rightZF = actualColor;;
                break;
            case 4:
                this.squareF = actualColor;;
                break;
            case 5:
                this.tF = actualColor;;
                break;
            case 6:
                this.lineF = actualColor;;
                break;
        }
    }
}
