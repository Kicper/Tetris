package program.controller;

import program.model.FigureColor;
import program.view.GameView;
import program.view.SettingsView;

public class ControllerSwing {
    private FigureColor figureColor = new FigureColor();

    /***
     * Funkcja uruchamiająca grę, a dokładniej jej widok
     */
    public void runGame() {
        GameView gameView = new GameView(figureColor);
        gameView.init();
        return;
    }

    /***
     * Funkcja uruchamiająca widok z ustawieniami
     */
    public void runSettings() {
        SettingsView settingsView = new SettingsView(figureColor);
        settingsView.init();
        return;
    }
}
