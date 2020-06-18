package program.controller;

import program.model.FigureColor;
import program.view.SettingsView;

import javax.swing.*;

public class ControllerSettings {
    private FigureColor figureColor;
    private String color;

    /***
     * Konstruktor przypisujący obiekt figureColor
     * @param figureColor - obiekt, który będzie używany do zmiany kolorów
     */
    public ControllerSettings(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    /***
     * Funkcja pobierająca wybrane przez użytkownika kolory
     * @param figBoxes - boxy, w których użytkownik wybieral kolor
     */
    public void asignColors(JComboBox[] figBoxes) {
        for(int i=0; i<7; ++i) {
            color = figBoxes[i].getItemAt(figBoxes[i].getSelectedIndex()).toString();
            switch (color) {
                case "Yellow":
                    figureColor.setColor(i, "Yellow");
                    break;
                case "Blue":
                    figureColor.setColor(i, "Blue");
                    break;
                case "Red":
                    figureColor.setColor(i, "Red");
                    break;
                case "Pink":
                    figureColor.setColor(i, "Pink");
                    break;
                case "Green":
                    figureColor.setColor(i, "Green");
                    break;
                case "Purple":
                    figureColor.setColor(i, "Purple");
                    break;
                case "White":
                    figureColor.setColor(i, "White");
                    break;
            }
        }
    }
}
