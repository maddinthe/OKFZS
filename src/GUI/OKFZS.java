package GUI;

import Datenbank.Datenbank;
import Datenhaltung.Verkaeufer;
import GUI.Werkzeug.PersonenEditor;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: noch nicht fertig
public class OKFZS extends JFrame {
    private Datenbank datenbank;
    private Map<String,Ansicht> ansichten;
    private Verkaeufer benutzer;
    private int hoehe =786;
    private int breite =1024;

    //todo: stub
    public OKFZS(){
        super();
        setTitle("Ostsee KFZ Service");
        setSize(breite, hoehe);

        setVisible(true);
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

    //todo:doku und evtl weitere details
    public static void main(String[] args) {
        new OKFZS();

    }


}
