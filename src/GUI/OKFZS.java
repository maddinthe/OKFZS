package GUI;

import Datenbank.Datenbank;
import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;
import GUI.Werkzeug.KFZEditor;
import GUI.Werkzeug.KaufvertragEditor;
import GUI.Werkzeug.PersonenEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: noch nicht fertig
public class OKFZS extends JFrame {
    private Datenbank datenbank;
    private Map<String, Ansicht> ansichten;
    private Verkaeufer benutzer;
    private int hoehe = 786;
    private int breite = 1024;
    private JPanel anzeige;
    private CardLayout cards;
    private Ansicht aktuelleAnsicht;

    //todo: stub
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
        } catch (IOException e) {/**nichts tun falls nicht da**/}
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
    private void beenden() {
        System.out.println("ende");
        try {
            datenbank.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //todo: schließende sachen tuen;
        System.exit(0);
    }


    public Datenbank getDatenbank() {
        return datenbank;
    }

    public Map<String, Ansicht> getAnsichten() {
        return ansichten;
    }

    public Verkaeufer getBenutzer() {
        return benutzer;
    }

    public int getHoehe() {
        return hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public JPanel getAnzeige() {
        return anzeige;
    }

    public CardLayout getCards() {
        return cards;
    }

    public void setBenutzer(Verkaeufer benutzer) {
        this.benutzer = benutzer;
    }

    /**
     * Lässt die Entsprechende Karte im Fenster anzeigen und lässt die entsprechenden Menüs aktiv/inaktiv werden
     * @param cardID Mögliche Werte["ende","uebersicht","impexp","autoAnz","autoAnl","personAnz","personAnl","personAend","statstik","ueber","hilfe"]
     */
    public void anzeigen(String cardID) {
        Menue menue= (Menue)getJMenuBar();
        menue.menueUmschalten(cardID);
        switch (cardID) {
            case "ende": {
                if(JOptionPane.showConfirmDialog(this, "Wirklich beenden?", "Beenden", JOptionPane.CANCEL_OPTION, JOptionPane.CLOSED_OPTION)==0);
                beenden();
                break;
            }
            case "uebersicht": {
                java.util.List<Vorgang> vorgangList=null;
                try{vorgangList=datenbank.VorgaengeZuVerkaeufer(benutzer);}
                catch (SQLException e){
                }
                aktuelleAnsicht=new Uebersicht(this, vorgangList);
                anzeige.add(aktuelleAnsicht, "uebersicht");
                cards.show(anzeige, "uebersicht");
                break;
            }
            case "impexp": {
                break;
            }
            case "autoAnz": {
                java.util.List<Vorgang> kfzList=null;
                try{kfzList=datenbank.unverkaufteVorgaenge();
                    aktuelleAnsicht=new KFZListe(this, kfzList);
                    anzeige.add(aktuelleAnsicht, "autoAnz");
                    cards.show(anzeige, "autoAnz");}
                catch (SQLException e){
                }
                break;
            }
            case "autoAnl": {
                    aktuelleAnsicht=new KFZEditor(this);
                    anzeige.add(aktuelleAnsicht, "autoAnl");
                    cards.show(anzeige, "autoAnl");


                break;
            }
            case "autoAend":{
                Vorgang v=((KFZListe)aktuelleAnsicht).getSelectedVorg();
                aktuelleAnsicht=new KFZEditor(this,v);
                anzeige.add("autoAend",aktuelleAnsicht);
                cards.show(anzeige,"autoAend");
                break;

            }

            case "autoVerk":{
                Vorgang v=((KFZListe)aktuelleAnsicht).getSelectedVorg();
                aktuelleAnsicht=new KaufvertragEditor(this,v);
                anzeige.add("autoVerk",aktuelleAnsicht);
                cards.show(anzeige,"autoVerk");

                break;

            }
            case "personAnz": {
                java.util.List<Person> personList=null;
                try{personList=datenbank.allePersonen();
                    aktuelleAnsicht=new PersonenListe(this, personList);
                    anzeige.add(aktuelleAnsicht, "personList");
                    cards.show(anzeige, "personList");}
                catch (SQLException e){
                }

                break;
            }
            case "personAnl": {

                try{
                    aktuelleAnsicht=new PersonenEditor(this);
                    anzeige.add(aktuelleAnsicht, "personAnl");
                    cards.show(anzeige, "personAnl");

                }catch(SQLException e){

                }

                break;
            }
            case "personAend":{
                    Person edit=((PersonenListe) aktuelleAnsicht).getSelectedPers();
                    aktuelleAnsicht=new PersonenEditor(this,edit);
                    anzeige.add(aktuelleAnsicht, "personAnl");
                    cards.show(anzeige, "personAnl");


                break;
            }
            case "statstik": {
                java.util.List<Vorgang> vorgangList=null;
                try{vorgangList=datenbank.VorgaengeZuVerkaeufer(benutzer);}
                catch (SQLException e){
                }
                aktuelleAnsicht=new Statistik(this, vorgangList);
                anzeige.add(aktuelleAnsicht, "statstik");
                cards.show(anzeige, "statstik");
                break;
            }
            case "ueber": {
                break;
            }
            case "hilfe":{
                break;
            }
            default:
                System.out.println(cardID);
        }

    }

    //todo:doku und evtl weitere details
    public static void main(String[] args) {
        new OKFZS();

    }
}
