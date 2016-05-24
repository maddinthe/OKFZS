package GUI.Werkzeug;

import Datenhaltung.KFZ;
import Datenhaltung.Vorgang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by tkertz on 23.05.2016.
 */
public class KFZEditor {

    public KFZEditor(KFZ kfz) {

//      KFZ DATEN

            JFrame jfKfzEdit = new JFrame("KFZ-Editor");
            JPanel jpKFZ = new JPanel();
            jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

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
            JLabel jlFin = new JLabel("Fin:");
            JTextField jtFin = new JTextField(20);
            jtFin.setText(kfz.getFin());
            jpFin.add(jlFin);
            jpFin.add(jtFin);

            JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlHersteller = new JLabel("Hersteller:");
            JTextField jtHersteller = new JTextField(20);
            jtHersteller.setText(kfz.getHersteller());
            jpHersteller.add(jlHersteller);
            jpHersteller.add(jtHersteller);

            JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlModell = new JLabel("Modell:");
            JTextField jtModell = new JTextField(20);
            jtModell.setText(kfz.getModell());
            jpModell.add(jlModell);
            jpModell.add(jtModell);

            JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
            JTextField jtKfzBriefNr = new JTextField(20);
            jtKfzBriefNr.setText(kfz.getKfzBriefNr());
            jpKfzBriefNr.add(jlKfzBriefNr);
            jpKfzBriefNr.add(jtKfzBriefNr);

            JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
            JTextField jtLeistungInKw = new JTextField(20);
            jtLeistungInKw.setText(String.valueOf(kfz.getLeistungInKw()));
            jpLeistungInKw.add(jlLeistungInKw);
            jpLeistungInKw.add(jtLeistungInKw);

            JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlFarbe = new JLabel("Farbe:");
            JTextField jtFarbe = new JTextField(20);
            jtFarbe.setText(kfz.getFarbe());
            jpFarbe.add(jlFarbe);
            jpFarbe.add(jtFarbe);

            JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEZ = new JLabel("Erstzulassung:");
            JTextField jtEZ = new JTextField(20);
            jtEZ.setText(String.valueOf(kfz.getEz()));
            jpEZ.add(jlEZ);
            jpEZ.add(jtEZ);

            JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
            JTextField jtUmweltplakette = new JTextField(20);
            jtUmweltplakette.setText(String.valueOf(kfz.getUmweltPlakette()));
            jpUmweltplakette.add(jlUmweltplakette);
            jpUmweltplakette.add(jtUmweltplakette);

            JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKraftstoff = new JLabel("Kraftstoff:");
            JTextField jtKraftstoff = new JTextField(20);
            jtKraftstoff.setText(kfz.getKraftstoff());
            jpKraftstoff.add(jlKraftstoff);
            jpKraftstoff.add(jtKraftstoff);

            JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAktionen = new JLabel("Aktionen:");
            JTextField jtAktionen = new JTextField(20);
            jtAktionen.setText(String.valueOf(kfz.getAktionen()));
            jpAktionen.add(jlAktionen);
            jpAktionen.add(jtAktionen);

            JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
            JTextField jtSonderausstattungen = new JTextField(20);
            String[] ausstattung = {"Leder", "Navigation", "PDC", "Xenon", "Tempomat"};
            JComboBox jcAusstattungListe = new JComboBox(ausstattung);
            jcAusstattungListe.setSelectedItem(kfz.getSonderausstattung());
            jcAusstattungListe.setSelectedIndex(4);
            jcAusstattungListe.setEditable(true);

            jpSonderausstattungen.add(jlSonderausstattungen);
            jpSonderausstattungen.add(jcAusstattungListe);

            jpKfzAngaben.add(jpFin);
            jpKfzAngaben.add(jpKfzBriefNr);
            jpKfzAngaben.add(jpEZ);
            jpKfzAngaben.add(jpLeistungInKw);

            jpTypAngaben.add(jpHersteller);
            jpTypAngaben.add(jpModell);
            jpTypAngaben.add(jpFarbe);
            jpTypAngaben.add(jpSonderausstattungen);

            jpSonstigeAngaben.add(jpKraftstoff);
            jpSonstigeAngaben.add(jpUmweltplakette);
            jpSonstigeAngaben.add(jpAktionen);

            jpKFZ.add(jpTypAngaben);
            jpKFZ.add(jpKfzAngaben);
            jpKFZ.add(jpSonstigeAngaben);


            //Vorgang

            JPanel jpVorgang = new JPanel();
            jpVorgang.setLayout(new BoxLayout(jpVorgang, BoxLayout.Y_AXIS));

            JPanel jpVKAngaben = new JPanel();
            jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
            jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

            JPanel jpMerkmale = new JPanel();
            jpMerkmale.setBorder(new TitledBorder("Merkmale"));
            jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

            JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEinkaeufer = new JLabel("Einkäufer:");
            JTextField jtEinkaeufer = new JTextField(20);
            jpEinkaeufer.add(jlEinkaeufer);
            jpEinkaeufer.add(jtEinkaeufer);

            JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEK = new JLabel("Einkaufspreis:");
            JTextField jtEK = new JTextField(20);
            jpEK.add(jlEK);
            jpEK.add(jtEK);

            JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
            JTextField jtVKP = new JTextField(20);
            jpVKP.add(jlVKP);
            jpVKP.add(jtVKP);

            JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEKDat = new JLabel("Einkaufsdatum:");
            JTextField jtEKDat = new JTextField(20);
            jpEKDat.add(jlEKDat);
            jpEKDat.add(jtEKDat);

            JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
            JTextField jtSchaeden = new JTextField(20);
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

            jpVKAngaben.add(jpEinkaeufer);
            jpVKAngaben.add(jpEKDat);
            jpVKAngaben.add(jpEK);
            jpVKAngaben.add(jpVKP);

            jpMerkmale.add(jpSchaeden);
            jpMerkmale.add(jpTuev);
            jpMerkmale.add(jpKennzeichen);


            jpVorgang.add(jpMerkmale);
            jpVorgang.add(jpVKAngaben);

            jfKfzEdit.add(jpKFZ, BorderLayout.WEST);
            jfKfzEdit.add(jpVorgang, BorderLayout.EAST);

            //JFrame jf Größe mitgeben
            jfKfzEdit.setSize(1024, 768);


            //JFrame jf auf Bildschirm plazieren
            jfKfzEdit.setLocation(200, 400);

            //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
            jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //JFrame jf anzeigen
            jfKfzEdit.setVisible(true);

    }

    public KFZEditor() {
//      KFZ DATEN
        JFrame jfKfzEdit = new JFrame("KFZ-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

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
        JLabel jlFin = new JLabel("Fin:");
        JTextField jtFin = new JTextField(20);
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller:");
        JTextField jtHersteller = new JTextField(20);
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell:");
        JTextField jtModell = new JTextField(20);
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
        JTextField jtKfzBriefNr = new JTextField(20);
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
        JTextField jtLeistungInKw = new JTextField(20);
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe:");
        JTextField jtFarbe = new JTextField(20);
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung:");
        JTextField jtEZ = new JTextField(20);
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
        JTextField jtUmweltplakette = new JTextField(20);
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
        JTextField jtKraftstoff = new JTextField(20);
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextField jtAktionen = new JTextField(20);
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
        JTextField jtSonderausstattungen = new JTextField(20);
        String[] ausstattung = {"Leder", "Navigation", "PDC", "Xenon", "Tempomat"};
        JComboBox jcAusstattungListe = new JComboBox(ausstattung);
        jcAusstattungListe.setSelectedIndex(4);
        jcAusstattungListe.setEditable(true);

        jpSonderausstattungen.add(jlSonderausstattungen);
        jpSonderausstattungen.add(jcAusstattungListe);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);
        jpTypAngaben.add(jpSonderausstattungen);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKFZ.add(jpTypAngaben);
        jpKFZ.add(jpKfzAngaben);
        jpKFZ.add(jpSonstigeAngaben);


        //Vorgang

        JPanel jpVorgang = new JPanel();
        jpVorgang.setLayout(new BoxLayout(jpVorgang, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis:");
        JTextField jtEK = new JTextField(20);
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
        JTextField jtSchaeden = new JTextField(20);
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

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);


        jpVorgang.add(jpMerkmale);
        jpVorgang.add(jpVKAngaben);

        jfKfzEdit.add(jpKFZ, BorderLayout.WEST);
        jfKfzEdit.add(jpVorgang, BorderLayout.EAST);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);


    }

    public KFZEditor(Vorgang vorgang) {
        //      KFZ DATEN

        JFrame jfKfzEdit = new JFrame("KFZ-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

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
        JLabel jlFin = new JLabel("Fin:");
        JTextField jtFin = new JTextField(20);
        jtFin.setText(vorgang.getKfz().getFin());
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller:");
        JTextField jtHersteller = new JTextField(20);
        jtHersteller.setText(vorgang.getKfz().getHersteller());
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell:");
        JTextField jtModell = new JTextField(20);
        jtModell.setText(vorgang.getKfz().getModell());
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
        JTextField jtKfzBriefNr = new JTextField(20);
        jtKfzBriefNr.setText(vorgang.getKfz().getKfzBriefNr());
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
        JTextField jtLeistungInKw = new JTextField(20);
        jtLeistungInKw.setText(String.valueOf(vorgang.getKfz().getLeistungInKw()));
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe:");
        JTextField jtFarbe = new JTextField(20);
        jtFarbe.setText(vorgang.getKfz().getFarbe());
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung:");
        JTextField jtEZ = new JTextField(20);
        jtEZ.setText(String.valueOf(vorgang.getKfz().getEz()));
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
        JTextField jtUmweltplakette = new JTextField(20);
        jtUmweltplakette.setText(String.valueOf(vorgang.getKfz().getUmweltPlakette()));
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
        JTextField jtKraftstoff = new JTextField(20);
        jtKraftstoff.setText(vorgang.getKfz().getKraftstoff());
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextField jtAktionen = new JTextField(20);
        jtAktionen.setText(String.valueOf(vorgang.getKfz().getAktionen()));
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
        JTextField jtSonderausstattungen = new JTextField(20);
        String[] ausstattung = {"Leder", "Navigation", "PDC", "Xenon", "Tempomat"};
        JComboBox jcAusstattungListe = new JComboBox(ausstattung);
        jtSonderausstattungen.setText(String.valueOf(vorgang.getKfz().getSonderausstattung()));
        jcAusstattungListe.setSelectedIndex(4);
        jcAusstattungListe.setEditable(true);

        jpSonderausstattungen.add(jlSonderausstattungen);
        jpSonderausstattungen.add(jcAusstattungListe);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);
        jpTypAngaben.add(jpSonderausstattungen);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKFZ.add(jpTypAngaben);
        jpKFZ.add(jpKfzAngaben);
        jpKFZ.add(jpSonstigeAngaben);


        //Vorgang

        JPanel jpVorgang = new JPanel();
        jpVorgang.setLayout(new BoxLayout(jpVorgang, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jtEinkaeufer.setText(String.valueOf(vorgang.getEinkaeufer()));
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis:");
        JTextField jtEK = new JTextField(20);
        jtEK.setText(String.valueOf(vorgang.getePreis()));
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jtVKP.setText(String.valueOf(vorgang.getvPreisPlan()));
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jtEKDat.setText(String.valueOf(vorgang.getEinkaufsDatum()));
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
        JTextField jtSchaeden = new JTextField(20);
        jtSchaeden.setText(vorgang.getSchaeden());
        jpSchaeden.add(jlSchaeden);
        jpSchaeden.add(jtSchaeden);

        JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTuev = new JLabel("TÜV:");
        JTextField jtTuev = new JTextField(20);
        jtTuev.setText(String.valueOf(vorgang.getTuev()));
        jpTuev.add(jlTuev);
        jpTuev.add(jtTuev);

        JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKennzeichen = new JLabel("Kennzeichen:");
        JTextField jtKennzeichen = new JTextField(20);
        jtKennzeichen.setText(vorgang.getKennzeichen());
        jpKennzeichen.add(jlKennzeichen);
        jpKennzeichen.add(jtKennzeichen);

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);


        jpVorgang.add(jpMerkmale);
        jpVorgang.add(jpVKAngaben);

        jfKfzEdit.add(jpKFZ, BorderLayout.WEST);
        jfKfzEdit.add(jpVorgang, BorderLayout.EAST);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);
    }

    public String getKFZ(String kfz) {
        return kfz;
    }

}

