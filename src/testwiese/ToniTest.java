package testwiese;

import Datenhaltung.*;
import GUI.OKFZS;
import GUI.Statistik;
import GUI.Werkzeug.AktionEditor;
import GUI.Werkzeug.KFZEditor;
import GUI.Werkzeug.PersonenEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by tkertz on 24.05.2016.
 */
public class ToniTest {
    public static void main(String[] args) throws SQLException {
//Spieldaten
        OKFZS okfzs = new OKFZS();
//        KFZEditor editor2 = new KFZEditor(okfzs,okfzs.getDatenbank().einKfz("12311"));

//        PersonenEditor p = new PersonenEditor(okfzs, okfzs.getDatenbank().einePerson(1));


//        java.util.List<Vorgang> vorgaenge = new LinkedList<>();
//        Verkaeufer[] verkaufer={
//                new Verkaeufer("maddin","madinspw",new Person("Herr", "Theilen", new Date()),true,true),
//                new Verkaeufer("toni","tonispw",new Person("Herr", "Kertz", new Date()),true,true),
//                new Verkaeufer("turner","turnerspw",new Person("Herr", "Dreher", new Date()),true,true)
//        };
//        KFZ[] kfzs = {new KFZ("12345678912345678", "Nissan", "Micra", "J124456", 100, "Rot", new Date(2015, 5, 1), (byte) 4, "Diesel"),
//                new KFZ("123453478912345678", "Honda", "Civic", "J127856", 101, "Rot", new Date(2014, 5, 1), (byte) 4, "Diesel"),
//                new KFZ("1234538912345678", "VW", "Golf", "J12786", 102, "Gelb", new Date(2013, 5, 1), (byte) 4, "Benzin"),
//                new KFZ("1232378912345678", "Skoda", "Octavia", "J12378", 103, "Schwarz", new Date(2012, 5, 1), (byte) 4, "Benzin")};
//
//        vorgaenge.add(new Vorgang(kfzs[0],verkaufer[0],9500));
//        vorgaenge.add(new Vorgang(kfzs[1],verkaufer[0],19500));
//        vorgaenge.add(new Vorgang(kfzs[2],verkaufer[0],29500));
//        vorgaenge.add(new Vorgang(kfzs[3],verkaufer[0],39500));
//
//        java.util.List<Person> personen=new LinkedList<>();
//        personen.add(verkaufer[0].getPerson());
//        personen.add(verkaufer[1].getPerson());
//        personen.add(verkaufer[2].getPerson());
//
//        java.util.List<Aktion> aktionen = new LinkedList<>();
//        Verkaeufer[] Personen={
//                new Verkaeufer("maddin","madinspw",new Person("Herr", "Theilen", new Date()),true,true),
//                new Verkaeufer("toni","tonispw",new Person("Herr", "Kertz", new Date()),true,true),
//                new Verkaeufer("turner","turnerspw",new Person("Herr", "Dreher", new Date()),true,true)
//        };
//
//        aktionen.add(new Aktion(new Date(), personen.get(0), "Waschen",kfzs[0]));
////        aktionen.add(new Vorgang(kfzs[1],verkaufer[0],19500));
////
////
////        //Spieldaten ende
////
//
////
////        KFZ k = new KFZ();
//////        k.getSonderausstattung();
////
////       new AktionEditor(aktionen.get(0),null);

    }
}
