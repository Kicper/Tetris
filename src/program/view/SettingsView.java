package program.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import program.controller.ControllerSettings;
import program.model.FigureColor;

public class SettingsView extends JFrame {
    private JLabel settings;
    private JComboBox figBoxes[] = new JComboBox[7];
    private JLabel label[] = new JLabel[7];
    private JButton change;
    private FigureColor figureColor;
    private String[] figColors = {"Yellow", "Blue", "Red", "Pink", "Green", "Purple", "White"};
    private String[] figShapes = {"Lefl L", "Right L", "Left Z", "Right Z", "Square", "T", "Line"};
    private ControllerSettings controllerSettings;

    /***
     * Konstruktor tworzący kontroler do widoku ustawień
     * @param figureColor - obiekt przekazywany do kontrolera dla spójności danych pomiędzy ustawieniami a grą właściwą
     */
    public SettingsView(FigureColor figureColor) {
        this.figureColor = figureColor;
        settings = new JLabel("SETTINGS");
        controllerSettings = new ControllerSettings(figureColor);
    }

    /***
     * Funkcja inicjalizująca widok ustawień
     */
    public void init() {
        setLayout(null);
        setSize(300, 700);
        setLocationRelativeTo(null);
        setTitle("SETTINGS");
        for(int i=0; i<7; ++i) {
            figBoxes[i] = new JComboBox(figColors);
            label[i] = new JLabel(figShapes[i]);
            label[i].setBounds(50, 45+75*i, 80, 30);
            figBoxes[i].setBounds(150, 45+75*i, 100, 30);
            add(label[i]);
            add(figBoxes[i]);
        }
        change = new JButton("Save changes");
        change.setBounds(50,550,200,70);
        add(change);
        setVisible(true);
        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controllerSettings.asignColors(figBoxes);
            }
        });
    }
}
