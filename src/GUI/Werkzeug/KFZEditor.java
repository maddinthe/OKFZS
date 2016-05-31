package GUI.Werkzeug;

import Datenhaltung.*;
import GUI.Ansicht;
import GUI.KFZListe;
import GUI.OKFZS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 */
public class KFZEditor extends Ansicht {
    private Vorgang vorgang;
    public KFZEditor(OKFZS okfzsInstanz, Vorgang vorgang) {
        super(okfzsInstanz);
        KFZ k=vorgang.getKfz();
        this.vorgang=vorgang;
        try {
            //      KFZ DATEN
            List<KFZ> kfzs=okfzsInstanz.getDatenbank().alleKfz();
            List<Sonderausstattung> ausstattungen=okfzsInstanz.getDatenbank().ausstattungslisteSortiert();
            this.setLayout(new BorderLayout());
            JPanel jfKfzEdit = this;
            JPanel jpKfzWest = new JPanel();
            jpKfzWest.setLayout(new BoxLayout(jpKfzWest, BoxLayout.Y_AXIS));

            JPanel jpKfzAngaben = new JPanel();
            jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
            jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

            JPanel jpTypAngaben = new JPanel();
            jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
            jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

            JPanel jpSonstigeAngaben = new JPanel();
            jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
            jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

            JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlFin = new JLabel("Fin: * ");
            JTextField jtFin = new JTextField(20);
            jtFin.setText(k.getFin());
            jtFin.setEditable(false);
            jpFin.add(jlFin);
            jpFin.add(jtFin);

            JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlHersteller = new JLabel("Hersteller: * ");
            JTextField jtHersteller = new JTextField(20);
            jtHersteller.setText(k.getHersteller());
            jtHersteller.setEditable(false);
            jpHersteller.add(jlHersteller);
            jpHersteller.add(jtHersteller);

            JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlModell = new JLabel("Modell: * ");
            JTextField jtModell = new JTextField(20);
            jtModell.setText(k.getModell());
            jtModell.setEditable(false);
            jpModell.add(jlModell);
            jpModell.add(jtModell);

            JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.: * ");
            JTextField jtKfzBriefNr = new JTextField(20);
            jtKfzBriefNr.setText(k.getKfzBriefNr());
            jtKfzBriefNr.setEditable(false);
            jpKfzBriefNr.add(jlKfzBriefNr);
            jpKfzBriefNr.add(jtKfzBriefNr);

            JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlLeistungInKw = new JLabel("Leistung in KW: * ");
            JTextField jtLeistungInKw = new JTextField(20);
            jtLeistungInKw.setText(String.valueOf(k.getLeistungInKw()));
            jtLeistungInKw.setEditable(false);
            jpLeistungInKw.add(jlLeistungInKw);
            jpLeistungInKw.add(jtLeistungInKw);

            JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlFarbe = new JLabel("Farbe: * ");
            JTextField jtFarbe = new JTextField(20);
            jtFarbe.setText(k.getFarbe());
            jtFarbe.setEditable(false);
            jpFarbe.add(jlFarbe);
            jpFarbe.add(jtFarbe);

            JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEZ = new JLabel("Erstzulassung: * ");
            JTextField jtEZ = new JTextField(20);
            jtEZ.setText(k.getEz().toString());
            jtEZ.setEditable(false);
            jpEZ.add(jlEZ);
            jpEZ.add(jtEZ);

            JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlUmweltplakette = new JLabel("Umweltplakette: * ");
            JTextField jtUmweltplakette = new JTextField(20);
            jtUmweltplakette.setText(String.valueOf(k.getUmweltPlakette()));
            jtUmweltplakette.setEditable(false);
            jpUmweltplakette.add(jlUmweltplakette);
            jpUmweltplakette.add(jtUmweltplakette);

            JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKraftstoff = new JLabel("Kraftstoff: * ");
            JTextField jtKraftstoff = new JTextField(20);
            jtKraftstoff.setText(k.getKraftstoff());
            jtKraftstoff.setEditable(false);
            jpKraftstoff.add(jlKraftstoff);
            jpKraftstoff.add(jtKraftstoff);



            JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAktionen = new JLabel("Aktionen:");
//            JTextArea jtAktionen = new JTextArea(3,20);
            JList jtAktionen = new JList(k.getAktionen().toArray(new Aktion[k.getAktionen().size()]));
            jtAktionen.setSize(20,3);

//            jtAktionen.setEditable(false);
            jtAktionen.setOpaque(false);
            jtAktionen.setBorder(new TitledBorder(""));
            jpAktionen.add(jlAktionen);
            jpAktionen.add(jtAktionen);

            jpKfzAngaben.add(jpFin);
            jpKfzAngaben.add(jpKfzBriefNr);
            jpKfzAngaben.add(jpEZ);
            jpKfzAngaben.add(jpLeistungInKw);

            jpTypAngaben.add(jpHersteller);
            jpTypAngaben.add(jpModell);
            jpTypAngaben.add(jpFarbe);

            jpSonstigeAngaben.add(jpKraftstoff);
            jpSonstigeAngaben.add(jpUmweltplakette);
            jpSonstigeAngaben.add(jpAktionen);

            jpKfzWest.add(jpTypAngaben);
            jpKfzWest.add(jpKfzAngaben);
            jpKfzWest.add(jpSonstigeAngaben);


            //JPanel Center

            JPanel jpKfzCenter = new JPanel();
            jpKfzCenter.setLayout(new BoxLayout(jpKfzCenter, BoxLayout.Y_AXIS));

            JPanel jpVKAngaben = new JPanel();
            jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
            jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

            JPanel jpMerkmale = new JPanel();
            jpMerkmale.setBorder(new TitledBorder("KFZ-Merkmale"));
            jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

            JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEinkaeufer = new JLabel("Einkäufer:");
            JTextField jtEinkaeufer = new JTextField(20);
           jtEinkaeufer.setText(vorgang.getEinkaeufer().getAnmeldeName());
            jtEinkaeufer.setEditable(false);
            jpEinkaeufer.add(jlEinkaeufer);
            jpEinkaeufer.add(jtEinkaeufer);

            JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEK = new JLabel("Einkaufspreis: * ");
            JTextField jtEK = new JTextField(20);
            jtEK.setText(String.valueOf(vorgang.getePreis()));
            jtEK.setEditable(false);
            jpEK.add(jlEK);
            jpEK.add(jtEK);

            JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlVKP = new JLabel("Verkaufspreis:");
            JTextField jtVKP = new JTextField(20);
            if(vorgang.getvPreis()==0.0) {
                jtVKP.setText(String.valueOf(vorgang.getePreis()*1.2));
            }
            jtVKP.setEditable(false);
            jpVKP.add(jlVKP);
            jpVKP.add(jtVKP);

            JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEKDat = new JLabel("Einkaufsdatum:");
            JTextField jtEKDat = new JTextField(20);
            jtEKDat.setText(vorgang.getEinkaufsDatum().toString());
            jtEKDat.setEditable(false);
            jpEKDat.add(jlEKDat);
            jpEKDat.add(jtEKDat);

            JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlSchaeden = new JLabel("Schäden:");
            JTextArea jtSchaeden = new JTextArea(3, 20);
            jtSchaeden.setText(vorgang.getSchaeden());
            jtSchaeden.setEditable(false);
            jtSchaeden.setOpaque(false);
            jtSchaeden.setBorder(new TitledBorder(""));
            jpSchaeden.add(jlSchaeden);
            jpSchaeden.add(jtSchaeden);


            JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlTuev = new JLabel("TÜV:");
            JTextField jtTuev = new JTextField(20);
            jtTuev.setText(String.valueOf(vorgang.getTuev()));
            jtTuev.setEditable(false);
            jpTuev.add(jlTuev);
            jpTuev.add(jtTuev);

            JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKennzeichen = new JLabel("Kennzeichen:");
            JTextField jtKennzeichen = new JTextField(20);
            jtKennzeichen.setText(vorgang.getKennzeichen());
            jtKennzeichen.setEditable(false);
            jpKennzeichen.add(jlKennzeichen);
            jpKennzeichen.add(jtKennzeichen);

            JPanel jpKm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKm = new JLabel("KM:");
            JTextField jtKm = new JTextField(20);
            jtKm.setText(String.valueOf(vorgang.getKilometer()));
            jtKm.setEditable(false);
            jpKm.add(jlKm);
            jpKm.add(jtKm);

            jpVKAngaben.add(jpEinkaeufer);
            jpVKAngaben.add(jpEKDat);
            jpVKAngaben.add(jpEK);
            jpVKAngaben.add(jpVKP);

            jpMerkmale.add(jpSchaeden);
            jpMerkmale.add(jpTuev);
            jpMerkmale.add(jpKennzeichen);
            jpMerkmale.add(jpKm);

            jpKfzCenter.add(jpMerkmale);
            jpKfzCenter.add(jpVKAngaben);

//        JPanel East
            JPanel jpKfzEast = new JPanel();
            jpKfzEast.setLayout(new BoxLayout(jpKfzEast, BoxLayout.Y_AXIS));

            JPanel jpSonderausstattung = new JPanel();
            jpSonderausstattung.setBorder(new TitledBorder("Sonderausstattung"));
            jpSonderausstattung.setLayout(new BoxLayout(jpSonderausstattung, BoxLayout.Y_AXIS));

            DefaultListModel ausstattungsListModel = new DefaultListModel();
            JCheckBoxList list = new JCheckBoxList();
            list.setModel(ausstattungsListModel);


            for(Sonderausstattung s:ausstattungen){
                JCheckboxWithObject jcbwo=new JCheckboxWithObject(s);
                if (k.getSonderausstattung().contains(s)){
                    jcbwo.setSelected(true);
                }else
                jcbwo.setSelected(false);
                ausstattungsListModel.addElement(jcbwo);
            }
            JPanel jpAutoliste = new JPanel();
            jpAutoliste.setBorder(new TitledBorder("Fahrzeugbestand"));
            jpAutoliste.setLayout(new BoxLayout(jpAutoliste, BoxLayout.Y_AXIS));
            JPanel jpSonderAusstattungsListe = new JPanel(new GridLayout(2,1));
            JComponent jList = list;
            list.setVisibleRowCount(20);
            JScrollPane jsp = new JScrollPane(jList);
            jpSonderAusstattungsListe.add(jsp);

            JPanel jpListFahrzeugbestand = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JList jtFahrzeugbestand = new JList(kfzs.toArray(new KFZ[kfzs.size()]));
            jtFahrzeugbestand.setVisibleRowCount(20);
            jpListFahrzeugbestand.add(jtFahrzeugbestand);

            jpSonderausstattung.add(jpSonderAusstattungsListe);
            jpAutoliste.add(jpListFahrzeugbestand);

            jpKfzEast.add(jpSonderausstattung);
            jpKfzEast.add(jpAutoliste);


            jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
            jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
            jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);

// South Buttonleiste
            JPanel jpButton = new JPanel();
            JButton jbFahrzeugSave = new JButton("Fahrzeug speichern");
            JButton jbFahrzeugBearbeiten = new JButton("Fahrzeug bearbeiten");
            JButton jbNeueAktion = new JButton("Aktion hinzufügen");
            JButton jbAktionEdit = new JButton("Aktion bearbeiten");
            JButton jbNeueAusstattung = new JButton("Fahrzeugausstattung hinzufügen");
            JButton jbVerkauf = new JButton("Fahrzeugverkauf vorbereiten");



// Actionlistener

            //todo Daten der AL in DB speichern
            ActionListener alFahrzeugSave = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jtFin.setEditable(false);
                    jtHersteller.setEditable(false);
                    jtModell.setEditable(false);
                    jtKfzBriefNr.setEditable(false);
                    jtLeistungInKw.setEditable(false);
                    jtFarbe.setEditable(false);
                    jtEZ.setEditable(false);
                    jtUmweltplakette.setEditable(false);
                    jtKraftstoff.setEditable(false);
//                    jtAktionen.setEditable(false);
                    jtAktionen.setOpaque(false);
                    jtSchaeden.setEditable(false);
                    jtSchaeden.setOpaque(false);
                    jtTuev.setEditable(false);
                    jtKennzeichen.setEditable(false);
                    jtKm.setEditable(false);
                    jtEinkaeufer.setEditable(false);
                    jtEKDat.setEditable(false);
                    jtEK.setEditable(false);
                    jtVKP.setEditable(false);


                    try {

                        KFZ kfz = new KFZ(jtFin.getText(),jtHersteller.getText(),jtModell.getText(),jtKfzBriefNr.getText(),Integer.parseInt(jtLeistungInKw.getText()),jtFarbe.getText(),umwandeln(jtEZ.getText()),Byte.parseByte(jtUmweltplakette.getText()),jtKraftstoff.getText());
                        for(Object o:list.getSelectedObjects()){
                            kfz.addSonderstattung((Sonderausstattung)o);
                        }
                        okfzsInstanz.getDatenbank().insertOrUpdateKfz(kfz);

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            };

            ActionListener alFahrzeugBearbeiten = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jtFin.setEditable(true);
                    jtHersteller.setEditable(true);
                    jtModell.setEditable(true);
                    jtKfzBriefNr.setEditable(true);
                    jtLeistungInKw.setEditable(true);
                    jtFarbe.setEditable(true);
                    jtEZ.setEditable(true);
                    jtUmweltplakette.setEditable(true);
                    jtKraftstoff.setEditable(true);
//                    jtAktionen.setEditable(true);
                    jtAktionen.setOpaque(true);
                    jtSchaeden.setEditable(true);
                    jtSchaeden.setOpaque(true);
                    jtTuev.setEditable(true);
                    jtKennzeichen.setEditable(true);
                    jtKm.setEditable(true);
                    jtEinkaeufer.setEditable(true);
                    jtEKDat.setEditable(true);
                    jtEK.setEditable(true);
                    jtVKP.setEditable(true);
                }
            };

            ActionListener alNeueAusstattung = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setNeueAustattung(okfzsInstanz);
                }
            };

            ActionListener alNeueAktion = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AktionEditor ae = new AktionEditor(okfzsInstanz,k);

                }
            };

            ActionListener alAktionEdit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  AktionEditor ae = new AktionEditor((Aktion)jtAktionen.getSelectedValue(),okfzsInstanz);
                }
            };

            jbFahrzeugSave.addActionListener(alFahrzeugSave);
            jbFahrzeugBearbeiten.addActionListener(alFahrzeugBearbeiten);
            jbNeueAktion.addActionListener(alNeueAktion);
            jbAktionEdit.addActionListener(alAktionEdit);
            jbNeueAusstattung.addActionListener(alNeueAusstattung);

            jpButton.add(jbFahrzeugSave);
            jpButton.add(jbFahrzeugBearbeiten);
            jpButton.add(jbNeueAktion);
            jpButton.add(jbAktionEdit);
            jpButton.add(jbNeueAusstattung);
            jpButton.add(jbVerkauf);

            jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
            jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
            jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);
            jfKfzEdit.add(jpButton, BorderLayout.SOUTH);


            //JFrame jf Größe mitgeben
            jfKfzEdit.setSize(1024, 768);


            //JFrame jf auf Bildschirm plazieren
            jfKfzEdit.setLocation(200, 400);

            //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind

            //JFrame jf anzeigen
            jfKfzEdit.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public KFZEditor(OKFZS okfzsInstanz, KFZ kfz) {
        super(okfzsInstanz);
        List<Sonderausstattung> ausstattungen= null;
        try {
            ausstattungen = okfzsInstanz.getDatenbank().ausstattungslisteSortiert();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//      KFZ DATEN
        this.setLayout(new BorderLayout());
        JPanel jfKfzEdit = this;
        JPanel jpKfzWest = new JPanel();
        jpKfzWest.setLayout(new BoxLayout(jpKfzWest, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpTypAngaben = new JPanel();
        jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
        jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFin = new JLabel("Fin: * ");
        JTextField jtFin = new JTextField(20);
        jtFin.setText(kfz.getFin());
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller: * ");
        JTextField jtHersteller = new JTextField(20);
        jtHersteller.setText(kfz.getHersteller());
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell: * ");
        JTextField jtModell = new JTextField(20);
        jtModell.setText(kfz.getModell());
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.: * ");
        JTextField jtKfzBriefNr = new JTextField(20);
        jtKfzBriefNr.setText(kfz.getKfzBriefNr());
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW: * ");
        JTextField jtLeistungInKw = new JTextField(20);
        jtLeistungInKw.setText(String.valueOf(kfz.getLeistungInKw()));
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe: * ");
        JTextField jtFarbe = new JTextField(20);
        jtFarbe.setText(kfz.getFarbe());
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung: * ");
        JTextField jtEZ = new JTextField(20);
        jtEZ.setText(String.valueOf(kfz.getEz()));
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette: * ");
        JTextField jtUmweltplakette = new JTextField(20);
        jtUmweltplakette.setText(String.valueOf(kfz.getUmweltPlakette()));
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff: * ");
        JTextField jtKraftstoff = new JTextField(20);
        jtKraftstoff.setText(kfz.getKraftstoff());
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextArea jtAktionen = new JTextArea(3,20);
        jtAktionen.setText(String.valueOf(kfz.getAktionen()));
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKfzWest.add(jpTypAngaben);
        jpKfzWest.add(jpKfzAngaben);
        jpKfzWest.add(jpSonstigeAngaben);


        //JPanel Center

        JPanel jpKfzCenter = new JPanel();
        jpKfzCenter.setLayout(new BoxLayout(jpKfzCenter, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("KFZ-Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis: * ");
        JTextField jtEK = new JTextField(20);
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Schäden:");
        JTextArea jtSchaeden = new JTextArea(3,20);
        jpSchaeden.add(jlSchaeden);
        jpSchaeden.add(jtSchaeden);

        JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTuev = new JLabel("TÜV:");
        JTextField jtTuev = new JTextField(20);
        jpTuev.add(jlTuev);
        jpTuev.add(jtTuev);

        JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKennzeichen = new JLabel("Kennzeichen:");
        JTextField jtKennzeichen = new JTextField(20);
        jpKennzeichen.add(jlKennzeichen);
        jpKennzeichen.add(jtKennzeichen);

        JPanel jpKm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKm = new JLabel("KM:");
        JTextField jtKm = new JTextField(20);
        jpKm.add(jlKm);
        jpKm.add(jtKm);

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);
        jpMerkmale.add(jpKm);


        jpKfzCenter.add(jpMerkmale);
        jpKfzCenter.add(jpVKAngaben);

//        JPanel East
        JPanel jpKfzEast = new JPanel();
        jpKfzEast.setLayout(new BoxLayout(jpKfzEast, BoxLayout.Y_AXIS));

        JPanel jpSonderausstattung = new JPanel();
        jpSonderausstattung.setBorder(new TitledBorder("Sonderausstattung"));
        jpSonderausstattung.setLayout(new BoxLayout(jpSonderausstattung, BoxLayout.Y_AXIS));

        DefaultListModel ausstattungsListModel = new DefaultListModel();
        JCheckBoxList list = new JCheckBoxList();
        list.setVisibleRowCount(15);
        list.setModel(ausstattungsListModel);

        for(Sonderausstattung s:ausstattungen){
            ausstattungsListModel.addElement(new JCheckboxWithObject(s));
        }

        JPanel jpAutoliste = new JPanel();
        jpAutoliste.setBorder(new TitledBorder("Fahrzeugbestand"));
        jpAutoliste.setLayout(new BoxLayout(jpAutoliste, BoxLayout.Y_AXIS));
        JPanel jpSonderAusstattungsListe = new JPanel(new GridLayout(2,1));
        JComponent jList = list;
        JScrollPane jsp = new JScrollPane(jList);
        jpSonderAusstattungsListe.add(jsp);

        JPanel jpListFahrzeugbestand = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea jtFahrzeugbestand = new JTextArea(20, 20);
        jpListFahrzeugbestand.add(jtFahrzeugbestand);

        jpSonderausstattung.add(jpSonderAusstattungsListe);
        jpAutoliste.add(jpListFahrzeugbestand);

        jpKfzEast.add(jpSonderausstattung);
        jpKfzEast.add(jpAutoliste);


        jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
        jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
        jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);


        // South Buttonleiste
        JPanel jpButton = new JPanel();
        JButton jbFahrzeugSave = new JButton("Fahrzeug speichern");
        JButton jbFahrzeugBearbeiten = new JButton("Fahrzeug bearbeiten");
        JButton jbNeueAktion = new JButton("Fahrzeugaktion hinzufügen");
        JButton jbNeueAusstattung = new JButton("Fahrzeugausstattung hinzufügen");
        JButton jbVerkauf = new JButton("Fahrzeugverkauf vorbereiten");

// Actionlistener

        //todo Daten der AL in DB speichern
        ActionListener alFahrzeugSave = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    KFZ kfz = new KFZ(jtFin.getText(),jtHersteller.getText(),jtModell.getText(),jtKfzBriefNr.getText(),Integer.parseInt(jtLeistungInKw.getText()),jtFarbe.getText(),umwandeln(jtEZ.getText()),Byte.parseByte(jtUmweltplakette.getText()),jtKraftstoff.getText());
                    okfzsInstanz.getDatenbank().insertOrUpdateKfz(kfz);
                    Vorgang v=new Vorgang(kfz,okfzsInstanz.getBenutzer(),Double.parseDouble(jtEK.getText()));
                    if (jtKm.getText().length()>0)
                    v.setKilometer(Integer.parseInt(jtKm.getText()));
                    okfzsInstanz.getDatenbank().insertOrUpdateVorgang(v);
                    vorgang=v;
                    okfzsInstanz.anzeigen(new KFZEditor(okfzsInstanz, v));

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        };

        ActionListener alFahrzeugBearbeiten = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtFin.setEditable(true);
                jtHersteller.setEditable(true);
                jtHersteller.setEditable(true);
                jtKfzBriefNr.setEditable(true);
                jtLeistungInKw.setEditable(true);
                jtFarbe.setEditable(true);
                jtEZ.setEditable(true);
                jtUmweltplakette.setEditable(true);
                jtKraftstoff.setEditable(true);
            }
        };

        ActionListener alNeueAusstattung = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNeueAustattung(okfzsInstanz);
            }
        };



        jbFahrzeugSave.addActionListener(alFahrzeugSave);
        jbFahrzeugBearbeiten.addActionListener(alFahrzeugBearbeiten);
//        jbNeueAktion.addActionListener(alNeueAktion);
        jbNeueAusstattung.addActionListener(alNeueAusstattung);

        jpButton.add(jbFahrzeugSave);
        jpButton.add(jbFahrzeugBearbeiten);
        jpButton.add(jbNeueAktion);
        jpButton.add(jbNeueAusstattung);
        jpButton.add(jbVerkauf);

        jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
        jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
        jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);
        jfKfzEdit.add(jpButton, BorderLayout.SOUTH);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);

    }

    public KFZEditor(OKFZS okfzsInstanz) {
        super(okfzsInstanz);
        List<KFZ> kfzs= null;
        try {
            kfzs = okfzsInstanz.getDatenbank().alleKfz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Sonderausstattung> ausstattungen= null;
        try {
            ausstattungen = okfzsInstanz.getDatenbank().ausstattungslisteSortiert();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//      KFZ DATEN
        this.setLayout(new BorderLayout());
        JPanel jfKfzEdit = this;
        JPanel jpKfzWest = new JPanel();
        jpKfzWest.setLayout(new BoxLayout(jpKfzWest, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpTypAngaben = new JPanel();
        jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
        jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFin = new JLabel("Fin: * ");
        JTextField jtFin = new JTextField(20);
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller: * ");
        JTextField jtHersteller = new JTextField(20);
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell: * ");
        JTextField jtModell = new JTextField(20);
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.: * ");
        JTextField jtKfzBriefNr = new JTextField(20);
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW: * ");
        JTextField jtLeistungInKw = new JTextField(20);
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe: * ");
        JTextField jtFarbe = new JTextField(20);
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung: * ");
        JTextField jtEZ = new JTextField(20);
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette: * ");
        JTextField jtUmweltplakette = new JTextField(20);
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff: * ");
        JTextField jtKraftstoff = new JTextField(20);
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextArea jtAktionen = new JTextArea(3,20);
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKfzWest.add(jpTypAngaben);
        jpKfzWest.add(jpKfzAngaben);
        jpKfzWest.add(jpSonstigeAngaben);


        //JPanel Center

        JPanel jpKfzCenter = new JPanel();
        jpKfzCenter.setLayout(new BoxLayout(jpKfzCenter, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("KFZ-Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis: * ");
        JTextField jtEK = new JTextField(20);
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Schäden:");
        JTextArea jtSchaeden = new JTextArea(3,20);
        jpSchaeden.add(jlSchaeden);
        jpSchaeden.add(jtSchaeden);

        JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTuev = new JLabel("TÜV:");
        JTextField jtTuev = new JTextField(20);
        jpTuev.add(jlTuev);
        jpTuev.add(jtTuev);

        JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKennzeichen = new JLabel("Kennzeichen:");
        JTextField jtKennzeichen = new JTextField(20);
        jpKennzeichen.add(jlKennzeichen);
        jpKennzeichen.add(jtKennzeichen);

        JPanel jpKm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKm = new JLabel("KM:");
        JTextField jtKm = new JTextField(20);
        jpKm.add(jlKm);
        jpKm.add(jtKm);

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);
        jpMerkmale.add(jpKm);


        jpKfzCenter.add(jpMerkmale);
        jpKfzCenter.add(jpVKAngaben);

//        JPanel East
        JPanel jpKfzEast = new JPanel();
        jpKfzEast.setLayout(new BoxLayout(jpKfzEast, BoxLayout.Y_AXIS));

        JPanel jpSonderausstattung = new JPanel();
        jpSonderausstattung.setBorder(new TitledBorder("Sonderausstattung"));
        jpSonderausstattung.setLayout(new BoxLayout(jpSonderausstattung, BoxLayout.Y_AXIS));

        DefaultListModel ausstattungsListModel = new DefaultListModel();
        JCheckBoxList list = new JCheckBoxList();
        list.setModel(ausstattungsListModel);

        for(Sonderausstattung s:ausstattungen){
            ausstattungsListModel.addElement(new JCheckboxWithObject(s));
        }
        JPanel jpSonderAusstattungsListe = new JPanel(new BorderLayout());
        JComponent jList = list;
        list.setVisibleRowCount(12);
        JScrollPane jsp = new JScrollPane(jList);
        jpSonderAusstattungsListe.add(jsp);
        jpSonderausstattung.add(jpSonderAusstattungsListe,BorderLayout.CENTER);

        JPanel jpAutoliste = new JPanel();
        jpAutoliste.setBorder(new TitledBorder("Fahrzeugbestand"));
        jpAutoliste.setLayout(new BoxLayout(jpAutoliste, BoxLayout.Y_AXIS));

        JPanel jpListFahrzeugbestand = new JPanel(new BorderLayout());
        JList jtFahrzeugbestand = new JList(kfzs.toArray(new KFZ[kfzs.size()]));
        jtFahrzeugbestand.setVisibleRowCount(12);
        JScrollPane jspFahrzeugBestand = new JScrollPane(jtFahrzeugbestand);
        jpListFahrzeugbestand.add(jspFahrzeugBestand);
        jpAutoliste.add(jpListFahrzeugbestand, BorderLayout.CENTER);

        JButton jbKfzDatenLaden = new JButton("KFZ Daten Laden");

        ActionListener alKfzDatenladen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KFZ k = (KFZ) jtFahrzeugbestand.getSelectedValue();
                jtHersteller.setText(k.getHersteller());
                jtModell.setText(k.getModell());
                jtFarbe.setText(k.getFarbe());
                jtFin.setText(k.getFin());
                jtKfzBriefNr.setText(k.getKfzBriefNr());
                jtEZ.setText(String.valueOf(k.getEz()));
                jtLeistungInKw.setText(String.valueOf(k.getLeistungInKw()));
                jtKraftstoff.setText(k.getKraftstoff());
                jtUmweltplakette.setText(String.valueOf(k.getUmweltPlakette()));

            }
        };

        jbKfzDatenLaden.addActionListener(alKfzDatenladen);
        jpListFahrzeugbestand.add(jbKfzDatenLaden,BorderLayout.SOUTH);

        jpKfzEast.add(jpSonderausstattung);
        jpKfzEast.add(jpAutoliste);


        jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
        jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
        jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);


        // South Buttonleiste
        JPanel jpButton = new JPanel();
        JButton jbFahrzeugSave = new JButton("Fahrzeug speichern");
        JButton jbFahrzeugBearbeiten = new JButton("Fahrzeug bearbeiten");
        JButton jbNeueAktion = new JButton("Fahrzeugaktion hinzufügen");
        JButton jbNeueAusstattung = new JButton("Fahrzeugausstattung hinzufügen");
        JButton jbVerkauf = new JButton("Fahrzeugverkauf vorbereiten");

// Actionlistener

        //todo Daten der AL in DB speichern
        ActionListener alFahrzeugSave = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    KFZ kfz = new KFZ(jtFin.getText(),jtHersteller.getText(),jtModell.getText(),jtKfzBriefNr.getText(),Integer.parseInt(jtLeistungInKw.getText()),jtFarbe.getText(),umwandeln(jtEZ.getText()),Byte.parseByte(jtUmweltplakette.getText()),jtKraftstoff.getText());
                    okfzsInstanz.getDatenbank().insertOrUpdateKfz(kfz);
                    Vorgang v=new Vorgang(kfz,okfzsInstanz.getBenutzer(),Double.parseDouble(jtEK.getText()));
                    v.setKilometer(Integer.parseInt(jtKm.getText()));
                    okfzsInstanz.getDatenbank().insertOrUpdateVorgang(v);
                    vorgang=v;
                    okfzsInstanz.anzeigen("autoAend");

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        };

        ActionListener alFahrzeugBearbeiten = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtFin.setEditable(true);
                jtHersteller.setEditable(true);
                jtHersteller.setEditable(true);
                jtKfzBriefNr.setEditable(true);
                jtLeistungInKw.setEditable(true);
                jtFarbe.setEditable(true);
                jtEZ.setEditable(true);
                jtUmweltplakette.setEditable(true);
                jtKraftstoff.setEditable(true);
            }
        };

        ActionListener alNeueAusstattung = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNeueAustattung(okfzsInstanz);
            }
        };



        jbFahrzeugSave.addActionListener(alFahrzeugSave);
        jbFahrzeugBearbeiten.addActionListener(alFahrzeugBearbeiten);
//        jbNeueAktion.addActionListener(alNeueAktion);
        jbNeueAusstattung.addActionListener(alNeueAusstattung);

        jpButton.add(jbFahrzeugSave);
        jpButton.add(jbFahrzeugBearbeiten);
        jpButton.add(jbNeueAktion);
        jpButton.add(jbNeueAusstattung);
        jpButton.add(jbVerkauf);

        jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
        jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
        jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);
        jfKfzEdit.add(jpButton, BorderLayout.SOUTH);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);

    }

    public String getKFZ(String kfz) {
        return kfz;
    }

    public void setNeueAustattung(OKFZS okfzsInstanz){
        JFrame jfKfzEdit = new JFrame("Ausstattungs-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("Ausstattung hinzufügen"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpAustattungHinzu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField jtAusstattungHinzu = new JTextField(20);
        jpAustattungHinzu.add(jtAusstattungHinzu);

        JButton jbAusstattungSave = new JButton("Speichern");
        jpAustattungHinzu.add(jbAusstattungSave);

        ActionListener alAusstattungSave = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Sonderausstattung sonderausstattung = new Sonderausstattung(jtAusstattungHinzu.getText());
                    okfzsInstanz.getDatenbank().insertOrUpdateSonderausstattung(sonderausstattung);
                    System.out.println("test");

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
                okfzsInstanz.anzeigen("autoAend");
                jfKfzEdit.dispose();

            }
        };

        jbAusstattungSave.addActionListener(alAusstattungSave);

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

//    public void setNeueAktion(OKFZS okfzsInstanz){
//        JFrame jfKfzEdit = new JFrame("Aktionen-Editor");
//        JPanel jpKFZ = new JPanel();
//        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));
//
//        JPanel jpKfzAngaben = new JPanel();
//        jpKfzAngaben.setBorder(new TitledBorder("Aktion hinzufügen"));
//        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpAktionHinzu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JTextField jtAktionHinzu = new JTextField(20);
//        jpAktionHinzu.add(jtAktionHinzu);
//
//        JButton jbAktionSave = new JButton("Speichern");
//        jpAktionHinzu.add(jbAktionSave);
//
//        ActionListener alAktionSave = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//
//                    Aktion aktion = new Aktion(jtAktionHinzu.getText());
//                    okfzsInstanz.getDatenbank().insertOrUpdateSonderausstattung(aktion);
//                    System.out.println("test");
//
//                } catch (SQLException e1) {
//                    System.out.println(e1.getMessage());
//                }
////                okfzsInstanz.anzeigen("autoAend");
//                jfKfzEdit.dispose();
//
//            }
//        };
//
//        jbAusstattungSave.addActionListener(alAusstattungSave);
//
//        jpKfzAngaben.add(jpAustattungHinzu);
//        jpKFZ.add(jpKfzAngaben);
//        jfKfzEdit.add(jpKFZ, BorderLayout.CENTER);
//
//        //JFrame jf Größe mitgeben
//        jfKfzEdit.setSize(300, 200);
//
//
//        //JFrame jf auf Bildschirm plazieren
//        jfKfzEdit.setLocation(200, 400);
//
//        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
//        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        //JFrame jf anzeigen
//        jfKfzEdit.setVisible(true);
//
//    }

    public static java.sql.Date umwandeln(String datum) {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(datum);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        return sDate;
    }

    public Vorgang getVorgang() {
        return vorgang;
    }


    /**
     * Klasse ist nach Anleitung gebaut und entsprechend abgeändert.
     * Quelle: http://blog.mynotiz.de/programmieren/java-checkbox-in-jlist-1061/
     */

    private class JCheckBoxList extends JList {

            public JCheckBoxList() {
                setCellRenderer(new CellRenderer());
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        int index = locationToIndex(e.getPoint());
                        if (index != -1) {
                            JCheckBox checkbox = (JCheckBox) getModel().getElementAt(
                                    index);
                            checkbox.setSelected(!checkbox.isSelected());
                            repaint();
                        }
                    }
                });
                setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }

            protected class CellRenderer implements ListCellRenderer {
                public Component getListCellRendererComponent(JList list, Object value,
                                                              int index, boolean isSelected, boolean cellHasFocus) {
                    JCheckBox checkbox = (JCheckBox) value;

                    if (isSelected) {
                        checkbox.setBorderPainted(true);
                        checkbox.setForeground(UIManager.getColor("List.selectionForeground"));
                        checkbox.setBackground(UIManager.getColor("List.selectionBackground"));

                    } else {
                        checkbox.setBorderPainted(false);
                        checkbox.setForeground(UIManager.getColor("List.foreground"));
                        checkbox.setBackground(UIManager.getColor("List.background"));
                    }
                    return checkbox;
                }
            }
            public List<Object> getSelectedObjects(){
                List<Object> ret =new ArrayList<>();
                int size=this.getModel().getSize();
                for (int i = 0; i < size ; i++) {
                    if(((JCheckboxWithObject)this.getModel().getElementAt(i)).isSelected())
                        ret.add(((JCheckboxWithObject)this.getModel().getElementAt(i)).getObject());
                }
                return ret;
            }

            public void selectAll() {
                int size = this.getModel().getSize();
                for (int i = 0; i < size; i++) {
                    JCheckBox checkbox = (JCheckboxWithObject) this.getModel()
                            .getElementAt(i);
                    checkbox.setSelected(true);
                }
                this.repaint();
            }

            public void deselectAll() {
                int size = this.getModel().getSize();
                for (int i = 0; i < size; i++) {
                    JCheckBox checkbox = (JCheckboxWithObject) this.getModel()
                            .getElementAt(i);
                    checkbox.setSelected(false);
                }
                this.repaint();
            }

//        public void sortieren(List<Sonderausstattung> liste){
//            Sonderausstattung[] sonderausstattungArray= liste.toArray(new Sonderausstattung[liste.size()]);
//            JList<Sonderausstattung> sListe = new JList<>(sonderausstattungArray);
//
//            for (int i = 0; i < sonderausstattungArray.length; i++) {
//                sonderausstattungArray[i].get
//            }
//
//            }
//
//        }

    }

    /**
     * Klasse ist nach Anleitung gebaut und entsprechend abgeändert.
     * Quelle: http://blog.mynotiz.de/programmieren/java-checkbox-in-jlist-1061/
     */
        private class JCheckboxWithObject extends JCheckBox{
            private Object object;

            public JCheckboxWithObject (Object object){
                this.object = object;
                this.setText(object.toString());
            }

            public Object getObject() {
                return object;
            }

            public void setObject(Object object) {
                this.object = object;
                this.setText(object.toString());
            }

        @Override
        public String toString() {
            return object.toString();
        }
    }
    }



