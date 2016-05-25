package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by tkertz on 23.05.2016.
 */
public class Hilfe extends Ansicht{
    private String hilfeAnmelden = "Um sich anzumelden nutzen Sie bitte Ihren Nutzernamen und das Passwort";
    private String hilfeAutos = "Um sich anzumelden nutzen Sie bitte Ihren Nutzernamen und das Passwort";
    private String hilfetext3;
    private String hilfetext4;


    public Hilfe(OKFZS okfzsInstanz){
        super(okfzsInstanz);
        JFrame jfHilfe = new JFrame("Ostsee KFZ Hilfe");
        JPanel jpHilfe = new JPanel();
        jpHilfe.setLayout(new BoxLayout(jpHilfe, BoxLayout.Y_AXIS));

        JPanel jpHilfeAnmeldenUeberschrift = new JPanel();
        jpHilfeAnmeldenUeberschrift.setBorder(new TitledBorder("Anmelden"));
        jpHilfeAnmeldenUeberschrift.setLayout(new BoxLayout(jpHilfeAnmeldenUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfeAnmeldung = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea jtHilfeAnmeldung = new JTextArea(20,20);
        jtHilfeAnmeldung.setText(hilfeAnmelden);
        jtHilfeAnmeldung.setEditable(false);
        jpHilfeAnmeldung.add(jtHilfeAnmeldung);

        JPanel jpHilfeAutoUeberschrift = new JPanel();
        jpHilfeAutoUeberschrift.setBorder(new TitledBorder("Autos"));
        jpHilfeAutoUeberschrift.setLayout(new BoxLayout(jpHilfeAutoUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfeAutos = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextArea jtHilfeAutos = new JTextArea(20,20);
        jtHilfeAutos.setText(hilfeAutos);
        jtHilfeAutos.setEditable(false);
        jpHilfeAutos.add(jtHilfeAnmeldung);

        jpHilfeAnmeldenUeberschrift.add(jpHilfeAnmeldung);
        jpHilfeAutoUeberschrift.add(jpHilfeAutos);

        jfHilfe.add(jpHilfeAnmeldenUeberschrift, BorderLayout.WEST);
        jfHilfe.add(jpHilfeAutoUeberschrift, BorderLayout.WEST);

        //JFrame jf Größe mitgeben
        jfHilfe.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfHilfe.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfHilfe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfHilfe.setVisible(true);
    }

    public static void main(String[] args) {
        Hilfe hil = new Hilfe(null);
    }

}
