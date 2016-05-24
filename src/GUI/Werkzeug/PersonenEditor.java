package GUI.Werkzeug;

import Datenbank.Datenbank;
import Datenhaltung.Person;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;


/**
 * Created by cdreher on 16.05.2016.
 */
//todo:stub
public class PersonenEditor  {

//    public PersonenEditor(OKFZS okfzsInstanz, Person person) {
//        super(okfzsInstanz);
//        Datenbank db = okfzsInstanz.getDatenbank();
//        JFrame jfKfzEdit = new JFrame("KFZ-Editor");
//        JPanel jp = new JPanel();
//        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
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
//        jpFin.add(jlFin);
//        jpFin.add(jtFin);
//
//        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlHersteller = new JLabel("Hersteller:");
//        JTextField jtHersteller = new JTextField(20);
//        jtHersteller.setText(kfz.getHersteller());
//        jpHersteller.add(jlHersteller);
//        jpHersteller.add(jtHersteller);
//
//        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlModell = new JLabel("Modell:");
//        JTextField jtModell = new JTextField(20);
//        jtModell.setText(kfz.getModell());
//        jpModell.add(jlModell);
//        jpModell.add(jtModell);
//
//        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
//        JTextField jtKfzBriefNr = new JTextField(20);
//        jtKfzBriefNr.setText(kfz.getKfzBriefNr());
//        jpKfzBriefNr.add(jlKfzBriefNr);
//        jpKfzBriefNr.add(jtKfzBriefNr);
//
//        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
//        JTextField jtLeistungInKw = new JTextField(20);
//        jtLeistungInKw.setText(""+kfz.getLeistungInKw());
//        jpLeistungInKw.add(jlLeistungInKw);
//        jpLeistungInKw.add(jtLeistungInKw);
//
//        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlFarbe = new JLabel("Farbe:");
//        JTextField jtFarbe = new JTextField(20);
//        jtFarbe.setText(kfz.getFarbe());
//        jpFarbe.add(jlFarbe);
//        jpFarbe.add(jtFarbe);
//
//        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlEZ = new JLabel("Erstzulassung:");
//        JTextField jtEZ = new JTextField(20);
//        jtEZ.setText(""+kfz.getEz());
//        jpEZ.add(jlEZ);
//        jpEZ.add(jtEZ);
//
//        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
//        JTextField jtUmweltplakette = new JTextField(20);
//        jtUmweltplakette.setText(""+kfz.getUmweltPlakette());
//        jpUmweltplakette.add(jlUmweltplakette);
//        jpUmweltplakette.add(jtUmweltplakette);
//
//        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
//        JTextField jtKraftstoff = new JTextField(20);
//        jtKraftstoff.setText(kfz.getKraftstoff());
//        jpKraftstoff.add(jlKraftstoff);
//        jpKraftstoff.add(jtKraftstoff);
//
//        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlAktionen = new JLabel("Aktionen:");
//        JTextField jtAktionen = new JTextField(20);
//        jtAktionen.setText(""+kfz.getAktionen());
//        jpAktionen.add(jlAktionen);
//        jpAktionen.add(jtAktionen);
//
//        JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
//        JTextField jtSonderausstattungen = new JTextField(20);
//        String[] ausstattung = { "Leder", "Navigation", "PDC", "Xenon", "Tempomat" };
//        JComboBox jcAusstattungListe = new JComboBox(ausstattung);
//        jcAusstattungListe.setSelectedItem(kfz.getSonderausstattung());
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
//        jpSonstigeAngaben.add(jpAktionen);
//
//        jp.add(jpTypAngaben);
//        jp.add(jpKfzAngaben);
//        jp.add(jpSonstigeAngaben);
//        jfKfzEdit.add(jp, BorderLayout.WEST);
//
//
//
//        //JFrame jf Größe mitgeben
//        jfKfzEdit.setSize(1024, 768);
//
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
//        try {
//            if (okfzsInstanz.getBenutzer().istAdmin())
//            db.insertOrUpdatePersonAdmin(person);
//            else
//            db.insertOrUpdatePerson(person);
//            db.printTable("t_Person");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//
//    }
//    public PersonenEditor(OKFZS okfzsInstanz) throws SQLException {
//        super(okfzsInstanz);
//       Datenbank db = okfzsInstanz.getDatenbank();
//        JFrame jfPersonEdit = new JFrame("Personen-Editor");
//        JPanel jp = new JPanel();
//        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
//
//        JPanel jpPersonAngaben = new JPanel();
//        jpPersonAngaben.setBorder(new TitledBorder("Personen-Angaben"));
//        jpPersonAngaben.setLayout(new BoxLayout(jpPersonAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpErwAngaben = new JPanel();
//        jpErwAngaben.setBorder(new TitledBorder("Erweiterte-Angaben"));
//        jpErwAngaben.setLayout(new BoxLayout(jpErwAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpSonstigeAngaben = new JPanel();
//        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
//        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));
//
//        JPanel jpPid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlPid = new JLabel("PID:");
//        JTextField jtPid = new JTextField(20);
//        jpPid.add(jlPid);
//        jpPid.add(jtPid);
//
//        JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlAnrede = new JLabel("Anrede:");
//        JTextField jtAnrede = new JTextField(20);
//        jpAnrede.add(jlAnrede);
//        jpAnrede.add(jtAnrede);
//
//        JPanel jpName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlName = new JLabel("Name:");
//        JTextField jtName = new JTextField(20);
//        jpName.add(jlName);
//        jpName.add(jtName);
//
//        JPanel jpVorname = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlVorname = new JLabel("Vorname:");
//        JTextField jtVorname = new JTextField(20);
//        jpVorname.add(jlVorname);
//        jpVorname.add(jtVorname);
//
//        JPanel jpGeburtstag = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlGeburtstag = new JLabel("Geburtstag:");
//        JTextField jtGeburtstag = new JTextField(20);
//        jpGeburtstag.add(jlGeburtstag);
//        jpGeburtstag.add(jtGeburtstag);
//
//        JPanel jpAnschrift = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlAnschrift = new JLabel("Anschrift:");
//        JTextField jtAnschrift = new JTextField(20);
//        jpAnschrift.add(jlAnschrift);
//        jpAnschrift.add(jtAnschrift);
//
//        JPanel jpPlz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlPlz = new JLabel("Postleitzahl:");
//        JTextField jtPlz = new JTextField(20);
//        jpPlz.add(jlPlz);
//        jpPlz.add(jtPlz);
//
//        JPanel jpOrt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlOrt = new JLabel("Ort:");
//        JTextField jtOrt = new JTextField(20);
//        jpOrt.add(jlOrt);
//        jpOrt.add(jtOrt);
//
//        JPanel jpUst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlUst = new JLabel("UST-ID:");
//        JTextField jtUst = new JTextField(20);
//        jpUst.add(jlUst);
//        jpUst.add(jtUst);
//
//        JPanel jpNotizen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlNotizen = new JLabel("Notizen:");
//        JTextField jtNotizen = new JTextField(20);
//        jpNotizen.add(jlNotizen);
//        jpNotizen.add(jtNotizen);
//
//        JPanel jpErreichbarkeit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel jlErreichbarkeit = new JLabel("Sonderausstattung:");
//        JTextField jtErreichbarkeit = new JTextField(20);
//        String[] Erreichbarkeit = { "Telefon", "Handy", "E-Mail", "Details"};
//        JComboBox jcErreichbarkeitsListe = new JComboBox(Erreichbarkeit);
//        jcErreichbarkeitsListe.setSelectedIndex(4);
//        jcErreichbarkeitsListe.setEditable(true);
//
//        jpErreichbarkeit.add(jlErreichbarkeit);
//        jpErreichbarkeit.add(jcErreichbarkeitsListe);
//
//
//
//        jpPersonAngaben.add(jpPid);
//        jpPersonAngaben.add(jpVorname);
//        jpPersonAngaben.add(jpPlz);
//        jpPersonAngaben.add(jpGeburtstag);
//
//        jpErwAngaben.add(jpAnrede);
//        jpErwAngaben.add(jpName);
//        jpErwAngaben.add(jpAnschrift);
//        jpErwAngaben.add(jpErreichbarkeit);
//
//        jpSonstigeAngaben.add(jpUst);
//        jpSonstigeAngaben.add(jpOrt);
//        jpSonstigeAngaben.add(jpNotizen);
//
//        jp.add(jpErwAngaben);
//        jp.add(jpPersonAngaben);
//        jp.add(jpSonstigeAngaben);
//        jfPersonEdit.add(jp, BorderLayout.WEST);
//
//
//
//        //JFrame jf Größe mitgeben
//        jfPersonEdit.setSize(1024, 768);
//
//
//
//        //JFrame jf auf Bildschirm plazieren
//        jfPersonEdit.setLocation(200, 400);
//
//        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
//        jfPersonEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        //JFrame jf anzeigen
//        jfPersonEdit.setVisible(true);
//        try {
//           // db.insertPerson(new Person());
//            db.printTable("t_Person");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    public Person getPerson(){
//        return null;
//    }
//


}
