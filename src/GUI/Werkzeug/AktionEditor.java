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
 * Created by  on 23.05.2016.
 * Der Aktioneneditor erweitert die Ansicht und stellt die Möglichkeit bereit, eine Fahrzeugaktion
 * z.B. Probefahrt hinzuzufügen
 * @author tkertz
 */
public class AktionEditor extends Ansicht {

    /**
     * Dieser Aktionenkonstruktor stellt die Möglichkeit zur Verfügung für eine übergebene Instanz nebst KFZ eine Aktion
     * zu erstellen
     * @param okfzsInstanz
     * @param kfz
     */
    public AktionEditor(OKFZS okfzsInstanz, KFZ kfz) {
        super(okfzsInstanz);

        /*
         * Panel für die Ansicht als Popup Fenster wird generiert
         */

        JFrame jfAktionEdit = new JFrame("Aktionen-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        /*
         * Panelrahmen und Beschriftung im JFrame Popup
         */

        JPanel jpAktion = new JPanel();
        jpAktion.setLayout(new BoxLayout(jpAktion, BoxLayout.Y_AXIS));

        JPanel jpAktionen = new JPanel();
        jpAktionen.setBorder(new TitledBorder("Aktionen"));
        jpAktionen.setLayout(new BoxLayout(jpAktionen, BoxLayout.Y_AXIS));

        /*
         * Panel für Datumseingabe und Beschriftung über ein Label
         */
        JPanel jpDatumAktion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDatumAktion = new JLabel("Datum: * ");
        JTextField jtDatumAktion = new JTextField(20);
        jpDatumAktion.add(jlDatumAktion);
        jpDatumAktion.add(jtDatumAktion);

        /*
         * Dropdownmenü für den Durchführenden der Aktion. Der Durchführende kann ein potentieller Käufer sein, oder
         * eben eine unternehmensnahe Person, wie z.B. Mechaniker, Aufbereiter etc. pp.
         */
        JPanel jpDurchfuehrender = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDurchfuehrender = new JLabel("Durchführender: * ");
        Person[] persons=null;

        /*
         * Personenliste (Inhalt)für das Dropdownmenü erstellt und an die JComboBox übergebne
         */
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

        /*
          Actionlistener der die erstellte Aktion an die DB übertragt
         */

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

        /*
          Speichern-Button und Vervollständigung
         */

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


    /**
     * Aktioneneditor der die Möglichkeit bietet eine bestehende Aktion zu einem Fahrzeug zu bearbeiten
     * @param aktion
     * @param okfzsInstanz
     */

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

    /**
     * Methode die das eigentliche Popup Fenster generiert und die Eingaben: Datum, Durchführender und Beschreibung ermöglicht
     */

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
