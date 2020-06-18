package program;

import program.view.SwingView;

public class Main {

    /***
     * Funkcja tworząca główny widok
     * @param args - nie jest istotny
     */
    public static void main(String[] args) {
        SwingView mainView = new SwingView();
        mainView.init();
    }
}
