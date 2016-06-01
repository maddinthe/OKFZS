package GUI;

import Datenbank.Datenbank;
import Datenhaltung.KFZ;
import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;
import GUI.Werkzeug.KFZEditor;
import GUI.Werkzeug.KaufvertragEditor;
import GUI.Werkzeug.PersonenEditor;
import GUI.Werkzeug.Suche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by mtheilen on 23.05.2016.
 * @author mtheilen
 */
public class OKFZS extends JFrame {
    /**
     * Die Datenbank Instanz
     */
    private Datenbank datenbank;
    /**
     * der angemeldete Verkäufer
     */
    private Verkaeufer benutzer;
    /**
     * Die Programmfensterhöhe (nicht unbedingt nötig)
     */
    private int hoehe = 786;
    /**
     * die Programmfensterbreite (nicht unbedingt nötig)
     */
    private int breite = 1024;
    /**
     * Das Panel in dem Alles Stattfindet
     */
    private JPanel anzeige;
    /**
     * Das Cardlayout des Panels zum umschalten der Views (In aktueller version nicht mehr so notwendig es fehlte jedoch die zeit es wieder auszubauen)
     */
    private CardLayout cards;
    /**
     * Die Aktuelle ansicht wird benötigt um der nachfolgenden ansicht evtl daten übergeben zu können Personenliste zu Personeneditor z.b. die vorhergehende Selektierte Person
     */
    private Ansicht aktuelleAnsicht;

    /**
     * Erstellt das Fenster und sorgt anschließend dafür das alles Richtig angezeigt wird und stellt den ansichten die daten bereit bzw sorgt dafür das die ansichten sich die daten holen können
     */
    public OKFZS() {
        super();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                beenden();
            }
        });
        //einlesen der OKFZ.cfg
        Map<String, String> config = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("OKFZS.cfg"))) {
            String read;
            while ((read = br.readLine()) != null) {
                String[] temp = read.split(":");
                config.put(temp[0], temp[1]);
            }
        } catch (IOException e) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Bitte DB-Server und Port angeben:");
            JTextField server = new JTextField("localhost", 20);
            JTextField port = new JTextField("5432", 5);
            panel.add(label);
            panel.add(server);
            panel.add(port);
            String[] options = new String[]{"OK", "Abbrechen"};
            int option = JOptionPane.showOptionDialog(null, panel, "Config Anlegen",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option == 0) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("OKFZS.cfg")))) {
                    bw.append("dbhost:" + server.getText());
                    bw.newLine();
                    bw.append("dbport:" + port.getText());
                    config.put("dbhost", server.getText());
                    config.put("dbport", port.getText());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (option == 1) {

                }

            }

        }
        //einlesen der Config beendet

        //holen der DB-Instanz
        try {
            String host = config.get("dbhost");
            String port = config.get("dbport");
            datenbank = Datenbank.getInstance((host != null) ? host : "localhost", (port != null) ? Integer.parseInt(port) : 5432);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //db anteil fürs erste ende
        cards = new CardLayout();
        anzeige = new JPanel(cards);

        //nutzer anmelden
        Anmeldung a = new Anmeldung(this);

        String anmeldungID = "Anmeldung";
        anzeige.add(a, anmeldungID);
        cards.show(anzeige, anmeldungID);
        //anmeldung ende

        setTitle("Ostsee KFZ Service");
        setSize(breite, hoehe);
        setJMenuBar(new Menue(this));
        add(anzeige);

        setVisible(true);
    }

    /**
     * Beendet das Programm und gibt Ressourcen frei(z.b. DB-Conn schließen
     */
    public void beenden() {
        System.out.println("ende");
        datenbank.closeDBConnection();

        System.exit(0);
    }


    /**
     * Gibt die Datenbankinstanz zurück die die Aktuelle OKFZS instanz nutzt
     *
     * @return Instanz von Datenbank die die Aktuelle OKFZS instanz nutzt
     */
    public Datenbank getDatenbank() {
        return datenbank;
    }

    /**
     * Gibt den aktuell Angemeldeten benutzer raus
     *
     * @return Verkäufer aktuell Angemeldeter Benutzer
     */
    public Verkaeufer getBenutzer() {
        return benutzer;
    }

    /**
     * Setzt den aktuell angemeldeten Benutzer
     *
     * @param benutzer Verkäufer der den Aktuell angemeldeten benutzer darstellt
     */
    public void setBenutzer(Verkaeufer benutzer) {
        this.benutzer = benutzer;
    }

    /**
     * Lässt die Entsprechende Karte im Fenster anzeigen und lässt die entsprechenden Menüs aktiv/inaktiv werden
     *
     * @param cardID Mögliche Werte["ende","uebersicht","impexp","autoAnz","autoAnl","personAnz","personAnl","personAend","statstik","ueber","hilfe"]
     */
    public void anzeigen(String cardID) {
        Menue menue = (Menue) getJMenuBar();
        menue.menueUmschalten(cardID);
        switch (cardID) {
            case "ende": {
                if (JOptionPane.showConfirmDialog(this, "Wirklich beenden?", "Beenden", JOptionPane.CANCEL_OPTION, JOptionPane.CLOSED_OPTION) == 0)
                    ;
                beenden();
                break;
            }

            case "sucheKFZ": {
                aktuelleAnsicht = new Suche(this, true);
                anzeige.add(aktuelleAnsicht, "sucheKFZ");
                cards.show(anzeige, "sucheKFZ");
                break;
            }
            case "suchePers": {
                aktuelleAnsicht = new Suche(this, false);
                anzeige.add(aktuelleAnsicht, "suchePers");
                cards.show(anzeige, "suchePers");
                break;
            }
            case "uebersicht": {
                java.util.List<Vorgang> vorgangList = null;
                try {
                    vorgangList = datenbank.VorgaengeZuVerkaeufer(benutzer);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                if (vorgangList == null) System.out.println("nullliste");
                aktuelleAnsicht = new Uebersicht(this, vorgangList);
                anzeige.add(aktuelleAnsicht, "uebersicht");
                cards.show(anzeige, "uebersicht");
                break;
            }
            case "autoAnz": {
                java.util.List<Vorgang> vorgList = null;
                java.util.List<KFZ> kfzList = null;
                if (aktuelleAnsicht.getClass().equals(Suche.class)) {
                    if (((Suche) aktuelleAnsicht).getKfzs().size() > 0)
                        kfzList = ((Suche) aktuelleAnsicht).getKfzs();
                    else {
                        anzeigen("autoAnl");
                        break;
                    }
                } else
                    try {
                        vorgList = datenbank.unverkaufteVorgaenge();
                    } catch (SQLException e) {
                    }
                if ((vorgList == null) && kfzList.size() == 0) {
                    anzeigen("autoAnl");
                    break;
                }
                if (vorgList != null && vorgList.size() > 0) {
                    aktuelleAnsicht = new KFZListe(this, vorgList);
                } else if(kfzList!=null)aktuelleAnsicht = new KFZListe(this, kfzList);
                else{
                    anzeigen("autoAnl");
                    break;
                }
                anzeige.add(aktuelleAnsicht, "autoAnz");
                cards.show(anzeige, "autoAnz");

                break;
            }
            case "autoAnl": {
                aktuelleAnsicht = new KFZEditor(this);
                anzeige.add(aktuelleAnsicht, "autoAnl");
                cards.show(anzeige, "autoAnl");
                break;
            }
            case "autoAend": {
                Vorgang v = null;
                if (aktuelleAnsicht.getClass().equals(KFZListe.class)) {
                    v = ((KFZListe) aktuelleAnsicht).getSelectedVorg();
                    if (v == null) {
                        aktuelleAnsicht = new KFZEditor(this, ((KFZListe) aktuelleAnsicht).getSelectedKFZ());
                        anzeige.add("autoAend", aktuelleAnsicht);
                        cards.show(anzeige, "autoAend");
                        break;

                    }
                } else if (aktuelleAnsicht.getClass().equals(KFZEditor.class)) {
                    v = ((KFZEditor) aktuelleAnsicht).getVorgang();
                    try {
                        v = getDatenbank().einVorgang(v.getKfz());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                aktuelleAnsicht = new KFZEditor(this, v);
                anzeige.add("autoAend", aktuelleAnsicht);
                cards.show(anzeige, "autoAend");
                break;

            }

            case "autoVerk": {
                Vorgang v = null;
                if (aktuelleAnsicht.getClass().equals(KFZListe.class)) {
                    v = ((KFZListe) aktuelleAnsicht).getSelectedVorg();
                } else if (aktuelleAnsicht.getClass().equals(KFZEditor.class)) {
                    v = ((KFZEditor) aktuelleAnsicht).getVorgang();
                }
                aktuelleAnsicht = new KaufvertragEditor(this, v);
                anzeige.add("autoVerk", aktuelleAnsicht);
                cards.show(anzeige, "autoVerk");

                break;

            }
            case "personAnz": {
                java.util.List<Person> personList = null;
                if (aktuelleAnsicht.getClass().equals(Suche.class)) {
                    personList = ((Suche) aktuelleAnsicht).getPersonen();
                } else
                    try {
                        personList = datenbank.allePersonen();
                    } catch (SQLException e) {
                    }
                if (personList == null || personList.size() == 0) {
                    anzeigen("personAnl");
                    break;
                }
                aktuelleAnsicht = new PersonenListe(this, personList);
                anzeige.add(aktuelleAnsicht, "personList");
                cards.show(anzeige, "personList");
                break;
            }
            case "personAnl": {
                aktuelleAnsicht = new PersonenEditor(this);
                anzeige.add(aktuelleAnsicht, "personAnl");
                cards.show(anzeige, "personAnl");


                break;
            }
            case "personAend": {
                Person edit = null;
                if (aktuelleAnsicht.getClass() == PersonenListe.class) {
                    edit = ((PersonenListe) aktuelleAnsicht).getSelectedPers();
                } else if (aktuelleAnsicht.getClass() == PersonenEditor.class) {
                    edit = ((PersonenEditor) aktuelleAnsicht).getPerson();
                }
                aktuelleAnsicht = new PersonenEditor(this, edit);
                anzeige.add(aktuelleAnsicht, "personAnl");
                cards.show(anzeige, "personAnl");


                break;
            }
            case "statstik": {
                aktuelleAnsicht = new Statistik(this);
                anzeige.add(aktuelleAnsicht, "statstik");
                cards.show(anzeige, "statstik");
                break;
            }
            case "hilfe": {
                aktuelleAnsicht=new Hilfe(this);
                anzeige.add(aktuelleAnsicht,"hilfe");
                cards.show(anzeige,"hilfe");
                break;
            }
            default:
                System.out.println(cardID);
        }

    }

    /**
     * Lässt eine Vorgegebene ansicht anzeigen und Setzt die OFZS eigenen variablen entsprechen
     *
     * @param a Anzuzeigende ansicht muss instanz von Statistik, PersonenEditor, KFZEditor oder Kaufvertrageditor sein
     */
    public void anzeigen(Ansicht a) {
        Menue menue = (Menue) getJMenuBar();
        switch (a.getClass().getSimpleName()) {
            case "Statistik":{
                aktuelleAnsicht = a;
                menue.menueUmschalten("statstik");
                anzeige.add(aktuelleAnsicht, "statstik");
                cards.show(anzeige, "statstik");
                break;
            }
            case "PersonenEditor": {
                aktuelleAnsicht = a;
                menue.menueUmschalten("personAend");
                anzeige.add(aktuelleAnsicht, "personAend");
                cards.show(anzeige, "personAend");
                break;
            }
            case "KFZEditor": {
                aktuelleAnsicht = a;
                menue.menueUmschalten("autoAend");
                anzeige.add(aktuelleAnsicht, "autoAend");
                cards.show(anzeige, "autoAend");
                break;
            }
            case "KaufvertragEditor": {
                aktuelleAnsicht = a;
                menue.menueUmschalten("autoVerk");
                anzeige.add(aktuelleAnsicht, "autoVerk");
                cards.show(anzeige, "autoVerk");
                break;
            }
        }
    }


    /**
     * Main Methode die ausschließlich new OKFZS() aufruft
     *
     * @param args wird nicht genutzt
     */
    public static void main(String[] args) {
        new OKFZS();

    }
}
