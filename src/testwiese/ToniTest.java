package testwiese;

import Datenhaltung.*;
import GUI.Werkzeug.KFZEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created by tkertz on 24.05.2016.
 */
public class ToniTest {
    public static void main(String[] args) {
//Spieldaten
        java.util.List<Vorgang> vorgaenge = new LinkedList<>();
        Verkaeufer[] verkaufer={
                new Verkaeufer("maddin","madinspw",new Person("Herr", "Theilen"),true,true),
                new Verkaeufer("toni","tonispw",new Person("Herr", "Kertz"),true,true),
                new Verkaeufer("turner","turnerspw",new Person("Herr", "Dreher"),true,true)
        };
        KFZ[] kfzs = {new KFZ("12345678912345678", "Nissan", "Micra", "J124456", 100, "Rot", new Date(2015, 5, 1), (byte) 4, "Diesel", new LinkedList<Aktion>(), new LinkedList<Sonderausstattung>()),
                new KFZ("123453478912345678", "Honda", "Civic", "J127856", 101, "Rot", new Date(2014, 5, 1), (byte) 4, "Diesel", new LinkedList<Aktion>(), new LinkedList<Sonderausstattung>()),
                new KFZ("1234538912345678", "VW", "Golf", "J12786", 102, "Gelb", new Date(2013, 5, 1), (byte) 4, "Benzin", new LinkedList<Aktion>(), new LinkedList<Sonderausstattung>()),
                new KFZ("1232378912345678", "Skoda", "Octavia", "J12378", 103, "Schwarz", new Date(2012, 5, 1), (byte) 4, "Benzin", new LinkedList<Aktion>(), new LinkedList<Sonderausstattung>())};

        vorgaenge.add(new Vorgang(kfzs[0],verkaufer[0],9500));
        vorgaenge.add(new Vorgang(kfzs[1],verkaufer[0],19500));
        vorgaenge.add(new Vorgang(kfzs[2],verkaufer[0],29500));
        vorgaenge.add(new Vorgang(kfzs[3],verkaufer[0],39500));

        java.util.List<Person> personen=new LinkedList<>();
        personen.add(verkaufer[0].getPerson());
        personen.add(verkaufer[1].getPerson());
        personen.add(verkaufer[2].getPerson());


        //Spieldaten ende

//        KFZEditor editor = new KFZEditor(vorgaenge.get(0));
//        KFZEditor editor2 = new KFZEditor(kfzs[0]);

        KFZ k = new KFZ();
        k.getSonderausstattung();



    }
}
