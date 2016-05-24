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
public class PersonenEditor extends Ansicht {

    public PersonenEditor(OKFZS okfzsInstanz, Person person) {
        super(okfzsInstanz);
        Datenbank db = okfzsInstanz.getDatenbank();
        JFrame jfPersonEdit = new JFrame("Personen-Editor");
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JPanel jpPersonenAngaben = new JPanel();
        jpPersonenAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
        jpPersonenAngaben.setLayout(new BoxLayout(jpPersonenAngaben, BoxLayout.Y_AXIS));

        JPanel jpErweiterteAngaben = new JPanel();
        jpErweiterteAngaben.setBorder(new TitledBorder("Typ-Angaben"));
        jpErweiterteAngaben.setLayout(new BoxLayout(jpErweiterteAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpPid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlPid = new JLabel("PID:");
        JTextField jtPid = new JTextField(20);
        jtPid.setText(String.valueOf(person.getPid()));
        jpPid.add(jlPid);
        jpPid.add(jtPid);

        JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAnrede = new JLabel("Anrede:");
        JTextField jtAnrede = new JTextField(20);
        jtAnrede.setText(person.getAnrede());
        jpAnrede.add(jlAnrede);
        jpAnrede.add(jtAnrede);

        JPanel jpName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlName = new JLabel("Name:");
        JTextField jtName = new JTextField(20);
        jtName.setText(person.getName());
        jpName.add(jlName);
        jpName.add(jtName);

        JPanel jpVorname = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVorname = new JLabel("Vorname:");
        JTextField jtVorname = new JTextField(20);
        jtVorname.setText(person.getVorname());
        jpVorname.add(jlVorname);
        jpVorname.add(jtVorname);

        JPanel jpGebTag = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlGebTag = new JLabel("Geburtstag:");
        JTextField jtGebTag = new JTextField(20);
        jtGebTag.setText("" + person.getGeburtstag());
        jpGebTag.add(jlGebTag);
        jpGebTag.add(jtGebTag);

        JPanel jpAnschrift = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAnschrift = new JLabel("Anschrift:");
        JTextField jtAnschrift = new JTextField(20);
        jtAnschrift.setText(person.getAnschrift());
        jpAnschrift.add(jlAnschrift);
        jpAnschrift.add(jtAnschrift);

        JPanel jpPlz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlPlz = new JLabel("Postleitzahl:");
        JTextField jtPlz = new JTextField(20);
        jtPlz.setText("" + person.getPostleitzahl());
        jpPlz.add(jlPlz);
        jpPlz.add(jtPlz);

        JPanel jpOrt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlOrt = new JLabel("Ort:");
        JTextField jtOrt = new JTextField(20);
        jtOrt.setText("" + person.getOrt());
        jpOrt.add(jlOrt);
        jpOrt.add(jtOrt);

        JPanel jpUst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUst = new JLabel("Ust-ID:");
        JTextField jtUst = new JTextField(20);
        jtUst.setText(person.getUstID());
        jpUst.add(jlUst);
        jpUst.add(jtUst);

        JPanel jpNotiz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlNotiz = new JLabel("Notiz:");
        JTextField jtNotiz = new JTextField(20);
        jtNotiz.setText("" + person.getNotizen());
        jpNotiz.add(jlNotiz);
        jpNotiz.add(jtNotiz);

        JPanel jpErreichbarkeit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlErreichbarkeit = new JLabel("Erreichbarkeiten:");
        JTextField jtSonderausstattungen = new JTextField(20);
        String[] erreichbarkeit = { "Tel", "Handy", "E-Mail", "Datum"};
        JComboBox jcErreichbarkeitsListe = new JComboBox(erreichbarkeit);
        jcErreichbarkeitsListe.setSelectedItem(person.getErreichbarkeiten());
        jcErreichbarkeitsListe.setSelectedIndex(3);
        jcErreichbarkeitsListe.setEditable(true);

        jpErreichbarkeit.add(jlErreichbarkeit);
        jpErreichbarkeit.add(jcErreichbarkeitsListe);

        jpPersonenAngaben.add(jpPid);
        jpPersonenAngaben.add(jpVorname);
        jpPersonenAngaben.add(jpPlz);
        jpPersonenAngaben.add(jpGebTag);

        jpErweiterteAngaben.add(jpAnrede);
        jpErweiterteAngaben.add(jpName);
        jpErweiterteAngaben.add(jpAnschrift);
        jpErweiterteAngaben.add(jpErreichbarkeit);

        jpSonstigeAngaben.add(jpUst);
        jpSonstigeAngaben.add(jpOrt);
        jpSonstigeAngaben.add(jpNotiz);

        jp.add(jpErweiterteAngaben);
        jp.add(jpPersonenAngaben);
        jp.add(jpSonstigeAngaben);
        jfPersonEdit.add(jp, BorderLayout.WEST);



        //JFrame jf Größe mitgeben
        jfPersonEdit.setSize(1024, 768);



        //JFrame jf auf Bildschirm plazieren
        jfPersonEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfPersonEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfPersonEdit.setVisible(true);

        try {
            if (okfzsInstanz.getBenutzer().istAdmin())
            db.insertOrUpdatePersonAdmin(person);
            else
            db.insertOrUpdatePerson(person);
            db.printTable("t_Person");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
    public PersonenEditor(OKFZS okfzsInstanz) throws SQLException {
        super(okfzsInstanz);
       Datenbank db = okfzsInstanz.getDatenbank();
        JFrame jfPersonEdit = new JFrame("Personen-Editor");
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JPanel jpPersonAngaben = new JPanel();
        jpPersonAngaben.setBorder(new TitledBorder("Personen-Angaben"));
        jpPersonAngaben.setLayout(new BoxLayout(jpPersonAngaben, BoxLayout.Y_AXIS));

        JPanel jpErwAngaben = new JPanel();
        jpErwAngaben.setBorder(new TitledBorder("Erweiterte-Angaben"));
        jpErwAngaben.setLayout(new BoxLayout(jpErwAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpPid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlPid = new JLabel("PID:");
        JTextField jtPid = new JTextField(20);
        jpPid.add(jlPid);
        jpPid.add(jtPid);

        JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAnrede = new JLabel("Anrede:");
        JTextField jtAnrede = new JTextField(20);
        jpAnrede.add(jlAnrede);
        jpAnrede.add(jtAnrede);

        JPanel jpName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlName = new JLabel("Name:");
        JTextField jtName = new JTextField(20);
        jpName.add(jlName);
        jpName.add(jtName);

        JPanel jpVorname = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVorname = new JLabel("Vorname:");
        JTextField jtVorname = new JTextField(20);
        jpVorname.add(jlVorname);
        jpVorname.add(jtVorname);

        JPanel jpGeburtstag = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlGeburtstag = new JLabel("Geburtstag:");
        JTextField jtGeburtstag = new JTextField(20);
        jpGeburtstag.add(jlGeburtstag);
        jpGeburtstag.add(jtGeburtstag);

        JPanel jpAnschrift = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAnschrift = new JLabel("Anschrift:");
        JTextField jtAnschrift = new JTextField(20);
        jpAnschrift.add(jlAnschrift);
        jpAnschrift.add(jtAnschrift);

        JPanel jpPlz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlPlz = new JLabel("Postleitzahl:");
        JTextField jtPlz = new JTextField(20);
        jpPlz.add(jlPlz);
        jpPlz.add(jtPlz);

        JPanel jpOrt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlOrt = new JLabel("Ort:");
        JTextField jtOrt = new JTextField(20);
        jpOrt.add(jlOrt);
        jpOrt.add(jtOrt);

        JPanel jpUst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUst = new JLabel("UST-ID:");
        JTextField jtUst = new JTextField(20);
        jpUst.add(jlUst);
        jpUst.add(jtUst);

        JPanel jpNotizen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlNotizen = new JLabel("Notizen:");
        JTextField jtNotizen = new JTextField(20);
        jpNotizen.add(jlNotizen);
        jpNotizen.add(jtNotizen);

        JPanel jpErreichbarkeit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlErreichbarkeit = new JLabel("Sonderausstattung:");
        JTextField jtErreichbarkeit = new JTextField(20);
        String[] Erreichbarkeit = { "Telefon", "Handy", "E-Mail", "Details"};
        JComboBox jcErreichbarkeitsListe = new JComboBox(Erreichbarkeit);
        jcErreichbarkeitsListe.setSelectedIndex(3);
        jcErreichbarkeitsListe.setEditable(true);

        jpErreichbarkeit.add(jlErreichbarkeit);
        jpErreichbarkeit.add(jcErreichbarkeitsListe);



        jpPersonAngaben.add(jpPid);
        jpPersonAngaben.add(jpVorname);
        jpPersonAngaben.add(jpPlz);
        jpPersonAngaben.add(jpGeburtstag);

        jpErwAngaben.add(jpAnrede);
        jpErwAngaben.add(jpName);
        jpErwAngaben.add(jpAnschrift);
        jpErwAngaben.add(jpErreichbarkeit);

        jpSonstigeAngaben.add(jpUst);
        jpSonstigeAngaben.add(jpOrt);
        jpSonstigeAngaben.add(jpNotizen);

        jp.add(jpErwAngaben);
        jp.add(jpPersonAngaben);
        jp.add(jpSonstigeAngaben);
        jfPersonEdit.add(jp, BorderLayout.WEST);



        //JFrame jf Größe mitgeben
        jfPersonEdit.setSize(1024, 768);



        //JFrame jf auf Bildschirm plazieren
        jfPersonEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfPersonEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfPersonEdit.setVisible(true);
        try {
           // db.insertPerson(new Person());
            db.printTable("t_Person");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public Person getPerson(){
        return null;
    }



}
