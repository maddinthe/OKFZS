package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by tkertz on 23.05.2016.
 * @author Toni Kertz
 * Diese Klasse stellt die Programmhilfe zur Verfügung und erweitert die Ansicht
 */
public class Hilfe extends Ansicht{

    /**
     * Initialisierung Hilfetext zur Anmeldung
     */
    private String hilfeAnmelden = "Um sich anzumelden nutzen Sie bitte Ihren Nutzernamen und das Passwort";
    /**
     * Initialisierung Hilfetext zur Fahrzeuganzeige
     */
    private String hilfeAutos = "Lassen Sie sich alle Fahrzeuge und Daten anzeigen";
    /**
     * Initialisierung Hilfetext zum Personeneditor
     */
    private String hilfePerson = "Hier legen Sie Personen an, ändern diese oder sehen ihre Daten";
    /**
     * Initialisierung Hilfetext Statistikanzeige
     */
    private String hilfeStatistik ="Lassen Sie sich verschiedene Statistiken anzeigen";

    /**
     * Objekt Hilfe wird erstellt
     * @param okfzsInstanz - aktuelle Instanz in der die Hilfe angezeigt wird
     */
    public Hilfe(OKFZS okfzsInstanz){
        super(okfzsInstanz);

        JFrame jfHilfe = new JFrame("Ostsee KFZ Hilfe");
        JPanel jpHilfeLinks = new JPanel();
        jpHilfeLinks.setLayout(new BoxLayout(jpHilfeLinks, BoxLayout.Y_AXIS));

        JPanel jpHilfeRechts = new JPanel();
        jpHilfeRechts.setLayout(new BoxLayout(jpHilfeRechts, BoxLayout.Y_AXIS));

        JPanel jpHilfeAnmeldenUeberschrift = new JPanel();
        jpHilfeAnmeldenUeberschrift.setBorder(new TitledBorder("Anmelden"));
        jpHilfeAnmeldenUeberschrift.setLayout(new BoxLayout(jpHilfeAnmeldenUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfeAnmeldung = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextArea jtHilfeAnmeldung = new JTextArea(80,50);
        jtHilfeAnmeldung.setText(hilfeAnmelden);
        jtHilfeAnmeldung.setEditable(false);
        jpHilfeAnmeldung.add(jtHilfeAnmeldung);

        JPanel jpHilfeAutoUeberschrift = new JPanel();
        jpHilfeAutoUeberschrift.setBorder(new TitledBorder("Autos"));
        jpHilfeAutoUeberschrift.setLayout(new BoxLayout(jpHilfeAutoUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfeAutos = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextArea jtHilfeAutos = new JTextArea(80,50);
        jtHilfeAutos.setText(hilfeAutos);
        jtHilfeAutos.setEditable(false);
        jpHilfeAutos.add(jtHilfeAutos);

        JPanel jpHilfePersonUeberschrift = new JPanel();
        jpHilfePersonUeberschrift.setBorder(new TitledBorder("Personen"));
        jpHilfePersonUeberschrift.setLayout(new BoxLayout(jpHilfePersonUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfePerson = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextArea jtHilfePerson = new JTextArea(80,50);
        jtHilfePerson.setText(hilfePerson);
        jtHilfePerson.setEditable(false);
        jpHilfePerson.add(jtHilfePerson);

        JPanel jpHilfeStatistikUeberschrift = new JPanel();
        jpHilfeStatistikUeberschrift.setBorder(new TitledBorder("Statistik"));
        jpHilfeStatistikUeberschrift.setLayout(new BoxLayout(jpHilfeStatistikUeberschrift, BoxLayout.Y_AXIS));

        JPanel jpHilfeStatistik = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextArea jtHilfeStatistik = new JTextArea(80,50);
        jtHilfeStatistik.setText(hilfeStatistik);
        jtHilfeStatistik.setEditable(false);
        jpHilfeStatistik.add(jtHilfeStatistik);

        jpHilfeAnmeldenUeberschrift.add(jpHilfeAnmeldung);
        jpHilfeAutoUeberschrift.add(jpHilfeAutos);
        jpHilfePersonUeberschrift.add(jpHilfePerson);
        jpHilfeStatistikUeberschrift.add(jpHilfeStatistik);

        jpHilfeLinks.add(jpHilfeAnmeldenUeberschrift);
        jpHilfeLinks.add(jpHilfeAutoUeberschrift);
        jpHilfeRechts.add(jpHilfePersonUeberschrift);
        jpHilfeRechts.add(jpHilfeStatistikUeberschrift);

        jfHilfe.add(jpHilfeLinks,BorderLayout.WEST);
        jfHilfe.add(jpHilfeRechts,BorderLayout.CENTER);

        //JFrame jf Größe mitgeben
        jfHilfe.setSize(1024, 768);

        //JFrame jf auf Bildschirm plazieren
        jfHilfe.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfHilfe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfHilfe.setVisible(true);
    }

}
