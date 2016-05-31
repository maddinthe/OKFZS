package GUI.Werkzeug;

import Datenhaltung.Aktion;
import Datenhaltung.KFZ;
import Datenhaltung.Person;
import Datenhaltung.Sonderausstattung;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 */
public class AktionEditor extends Ansicht {

    public AktionEditor(OKFZS okfzsInstanz, KFZ kfz) {
        super(okfzsInstanz);

              //      KFZ DATEN
        JFrame jfAktionEdit = new JFrame("Aktionen-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));


        //Aktion

        JPanel jpAktion = new JPanel();
        jpAktion.setLayout(new BoxLayout(jpAktion, BoxLayout.Y_AXIS));

        JPanel jpAktionen = new JPanel();
        jpAktionen.setBorder(new TitledBorder("Aktionen"));
        jpAktionen.setLayout(new BoxLayout(jpAktionen, BoxLayout.Y_AXIS));

        JPanel jpDatumAktion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDatumAktion = new JLabel("Datum: * ");
        JTextField jtDatumAktion = new JTextField(20);
        jpDatumAktion.add(jlDatumAktion);
        jpDatumAktion.add(jtDatumAktion);

        JPanel jpDurchfuehrender = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDurchfuehrender = new JLabel("Durchführender: * ");
        Person[] persons=null;

        try {
            List<Person> personList=okfzsInstanz.getDatenbank().allePersonenSortiert();
            persons=personList.toArray(new Person[personList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComboBox<Person> jcDurchfuehrender = new JComboBox<>(persons);

        jpDurchfuehrender.add(jlDurchfuehrender);
        jpDurchfuehrender.add(jcDurchfuehrender);

        JPanel jpBeschreibung = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlBeschreibung = new JLabel("Beschreibung: * ");
        JTextArea jtBeschreibung = new JTextArea(20, 20);

        jpBeschreibung.add(jlBeschreibung);
        jpBeschreibung.add(jtBeschreibung);

        jpAktionen.add(jpDatumAktion);
        jpAktionen.add(jpDurchfuehrender);
        jpAktionen.add(jpBeschreibung);

        jpAktion.add(jpAktionen);

//        Actionlistener

        ActionListener alAktionSpeichern = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                  Aktion a=new Aktion(KFZEditor.umwandeln(jtDatumAktion.getText()),(Person)jcDurchfuehrender.getSelectedItem(),jtBeschreibung.getText(),kfz);

                    okfzsInstanz.getDatenbank().insertOrUpdateAktion(a);
                    System.out.println("test");

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
                okfzsInstanz.anzeigen("autoAend");
                jfAktionEdit.dispose();


            }
        };


        //        Button

        JButton jbAktionSave = new JButton("Speichern");
        jbAktionSave.addActionListener(alAktionSpeichern);

        JPanel jpButtonLeisteSouth = new JPanel();
        jpButtonLeisteSouth.add(jbAktionSave);

        jfAktionEdit.add(jpAktion, BorderLayout.CENTER);
        jfAktionEdit.add(jpButtonLeisteSouth, BorderLayout.SOUTH);

        //JFrame jf Größe mitgeben
        jfAktionEdit.setSize(400, 300);


        //JFrame jf auf Bildschirm plazieren
        jfAktionEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfAktionEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfAktionEdit.setVisible(true);




    }

//    public AktionEditor(KFZ kfz, OKFZS okfzsInstanz) {
//        super(okfzsInstanz);
//        //      KFZ DATEN
//
//        JFrame jfAktionEdit = new JFrame("Aktionen-Editor");
//        JPanel jpKFZ = new JPanel();
//        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));
//
//        JPanel jpKfzAngaben = new JPanel();
//        jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
//        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpTypAngaben = new JPanel();
//        jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
//        jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpSonstigeAngaben = new JPanel();
//        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
//        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlFin = new JLabel("Fin:");
//        JTextField jtFin = new JTextField(20);
//        jtFin.setText(kfz.getFin());
//        jtFin.setEditable(false);
//        jpFin.add(jlFin);
//        jpFin.add(jtFin);
//
//        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlHersteller = new JLabel("Hersteller:");
//        JTextField jtHersteller = new JTextField(20);
//        jtHersteller.setText(kfz.getHersteller());
//        jtHersteller.setEditable(false);
//        jpHersteller.add(jlHersteller);
//        jpHersteller.add(jtHersteller);
//
//        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlModell = new JLabel("Modell:");
//        JTextField jtModell = new JTextField(20);
//        jtModell.setText(kfz.getModell());
//        jtModell.setEditable(false);
//        jpModell.add(jlModell);
//        jpModell.add(jtModell);
//
//        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
//        JTextField jtKfzBriefNr = new JTextField(20);
//        jtKfzBriefNr.setText(kfz.getKfzBriefNr());
//        jtKfzBriefNr.setEditable(false);
//        jpKfzBriefNr.add(jlKfzBriefNr);
//        jpKfzBriefNr.add(jtKfzBriefNr);
//
//        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
//        JTextField jtLeistungInKw = new JTextField(20);
//        jtLeistungInKw.setText(String.valueOf(kfz.getLeistungInKw()));
//        jtLeistungInKw.setEditable(false);
//        jpLeistungInKw.add(jlLeistungInKw);
//        jpLeistungInKw.add(jtLeistungInKw);
//
//        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlFarbe = new JLabel("Farbe:");
//        JTextField jtFarbe = new JTextField(20);
//        jtFarbe.setText(kfz.getFarbe());
//        jtFarbe.setEditable(false);
//        jpFarbe.add(jlFarbe);
//        jpFarbe.add(jtFarbe);
//
//        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlEZ = new JLabel("Erstzulassung:");
//        JTextField jtEZ = new JTextField(20);
//        jtEZ.setText(String.valueOf(kfz.getEz()));
//        jtEZ.setEditable(false);
//        jpEZ.add(jlEZ);
//        jpEZ.add(jtEZ);
//
//        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
//        JTextField jtUmweltplakette = new JTextField(20);
//        jtUmweltplakette.setText(String.valueOf(kfz.getUmweltPlakette()));
//        jtUmweltplakette.setEditable(false);
//        jpUmweltplakette.add(jlUmweltplakette);
//        jpUmweltplakette.add(jtUmweltplakette);
//
//        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
//        JTextField jtKraftstoff = new JTextField(20);
//        jtKraftstoff.setText(kfz.getKraftstoff());
//        jtKraftstoff.setEditable(false);
//        jpKraftstoff.add(jlKraftstoff);
//        jpKraftstoff.add(jtKraftstoff);
//
//
//        JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
//        JTextField jtSonderausstattungen = new JTextField(20);
//        String[] ausstattung = {"Leder", "Navigation", "PDC", "Xenon", "Tempomat"};
//        JComboBox jcAusstattungListe = new JComboBox(ausstattung);
//        jcAusstattungListe.setSelectedItem(kfz.getSonderausstattung());
//        jcAusstattungListe.setEditable(false);
//        jcAusstattungListe.setSelectedIndex(4);
//        jcAusstattungListe.setEditable(true);
//
//        jpSonderausstattungen.add(jlSonderausstattungen);
//        jpSonderausstattungen.add(jcAusstattungListe);
//
//        jpKfzAngaben.add(jpFin);
//        jpKfzAngaben.add(jpKfzBriefNr);
//        jpKfzAngaben.add(jpEZ);
//        jpKfzAngaben.add(jpLeistungInKw);
//
//        jpTypAngaben.add(jpHersteller);
//        jpTypAngaben.add(jpModell);
//        jpTypAngaben.add(jpFarbe);
//        jpTypAngaben.add(jpSonderausstattungen);
//
//        jpSonstigeAngaben.add(jpKraftstoff);
//        jpSonstigeAngaben.add(jpUmweltplakette);
//
//
//        jpKFZ.add(jpTypAngaben);
//        jpKFZ.add(jpKfzAngaben);
//        jpKFZ.add(jpSonstigeAngaben);
//
//        //Aktion
//
//        JPanel jpAktion = new JPanel();
//        jpAktion.setLayout(new BoxLayout(jpAktion, BoxLayout.Y_AXIS));
//
//        JPanel jpAktionen = new JPanel();
//        jpAktionen.setBorder(new TitledBorder("Aktionen"));
//        jpAktionen.setLayout(new BoxLayout(jpAktionen, BoxLayout.Y_AXIS));
//
//        JPanel jpDatumAktion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlSchaeden = new JLabel("Datum:");
//        JTextField jtSchaeden = new JTextField(20);
//        jpDatumAktion.add(jlSchaeden);
//        jpDatumAktion.add(jtSchaeden);
//
//        JPanel jpDurchfuehrender = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlDurchfuehrender = new JLabel("Durchführender:");
//        JTextField jtDurchfuehrender = new JTextField(20);
//        jpDurchfuehrender.add(jlDurchfuehrender);
//        jpDurchfuehrender.add(jtDurchfuehrender);
//
//        JPanel jpBeschreibung = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlKennzeichen = new JLabel("Beschreibung:");
//        JTextArea jtKennzeichen = new JTextArea(20, 20);
//
//        jpBeschreibung.add(jlKennzeichen);
//        jpBeschreibung.add(jtKennzeichen);
//
//        jpAktionen.add(jpDatumAktion);
//        jpAktionen.add(jpDurchfuehrender);
//        jpAktionen.add(jpBeschreibung);
//
//        jpAktion.add(jpAktionen);
//
//        jfAktionEdit.add(jpKFZ, BorderLayout.WEST);
//        jfAktionEdit.add(jpAktion, BorderLayout.EAST);
//
//
//        //JFrame jf Größe mitgeben
//        jfAktionEdit.setSize(1024, 768);
//
//
//        //JFrame jf auf Bildschirm plazieren
//        jfAktionEdit.setLocation(200, 400);
//
//        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
//        jfAktionEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        //JFrame jf anzeigen
//        jfAktionEdit.setVisible(true);
//
//
//    }

    public AktionEditor(Aktion aktion, OKFZS okfzsInstanz) {
        super(okfzsInstanz);


        //      KFZ DATEN
        JFrame jfAktionEdit = new JFrame("Aktionen-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        //Aktion

        JPanel jpAktion = new JPanel();
        jpAktion.setLayout(new BoxLayout(jpAktion, BoxLayout.Y_AXIS));

        JPanel jpAktionen = new JPanel();
        jpAktionen.setBorder(new TitledBorder("Aktionen"));
        jpAktionen.setLayout(new BoxLayout(jpAktionen, BoxLayout.Y_AXIS));

        JPanel jpDatumAktion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDatumAktion = new JLabel("Datum: * ");
        JTextField jtDatumAktion = new JTextField(aktion.getDurchfuehrung().toString(),20);
        jpDatumAktion.add(jlDatumAktion);
        jpDatumAktion.add(jtDatumAktion);

        JPanel jpDurchfuehrender = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDurchfuehrender = new JLabel("Durchführender: * ");
        Person[] persons=null;

        try {
            List<Person> personList=okfzsInstanz.getDatenbank().allePersonenSortiert();
            persons=personList.toArray(new Person[personList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComboBox<Person> jcDurchfuehrender = new JComboBox<>(persons);
        jcDurchfuehrender.setSelectedItem(aktion.getDurchfuehrender());
        jpDurchfuehrender.add(jlDurchfuehrender);
        jpDurchfuehrender.add(jcDurchfuehrender);

        JPanel jpBeschreibung = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlBeschreibung = new JLabel("Beschreibung: * ");
        JTextArea jtBeschreibung = new JTextArea(aktion.getBeschreibung(),20, 20);

        jpBeschreibung.add(jlBeschreibung);
        jpBeschreibung.add(jtBeschreibung);

        jpAktionen.add(jpDatumAktion);
        jpAktionen.add(jpDurchfuehrender);
        jpAktionen.add(jpBeschreibung);

        jpAktion.add(jpAktionen);

//        Actionlistener

        ActionListener alAktionSpeichern = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Aktion a=new Aktion(KFZEditor.umwandeln(jtDatumAktion.getText()),(Person)jcDurchfuehrender.getSelectedItem(),jtBeschreibung.getText(),aktion.getKfz());

                    okfzsInstanz.getDatenbank().insertOrUpdateAktion(a);
                    System.out.println("test");

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
                okfzsInstanz.anzeigen("autoAend");
                jfAktionEdit.dispose();


            }
        };




        //        Button

        JButton jbAktionSave = new JButton("Speichern");
        jbAktionSave.addActionListener(alAktionSpeichern);

        JPanel jpButtonLeisteSouth = new JPanel();
        jpButtonLeisteSouth.add(jbAktionSave);

        jfAktionEdit.add(jpAktion, BorderLayout.CENTER);
        jfAktionEdit.add(jpButtonLeisteSouth, BorderLayout.SOUTH);

        //JFrame jf Größe mitgeben
        jfAktionEdit.setSize(400, 300);


        //JFrame jf auf Bildschirm plazieren
        jfAktionEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfAktionEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfAktionEdit.setVisible(true);



    }



//    public Aktion getAktion() {
//        return Aktion();
//    }

    public void setNeueAktion(){
        JFrame jfKfzEdit = new JFrame("Ausstattungs-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("Ausstattung hinzufügen"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpAustattungHinzu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField jtFin = new JTextField(20);
        jpAustattungHinzu.add(jtFin);

        JButton jbSave = new JButton("Speichern");
        jpAustattungHinzu.add(jbSave);

        jpKfzAngaben.add(jpAustattungHinzu);

        jpKFZ.add(jpKfzAngaben);
        jfKfzEdit.add(jpKFZ, BorderLayout.CENTER);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(300, 200);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);

    }

}
