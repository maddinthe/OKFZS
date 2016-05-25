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
        jpPersonenAngaben.setBorder(new TitledBorder("Angaben zur Person"));
        jpPersonenAngaben.setLayout(new BoxLayout(jpPersonenAngaben, BoxLayout.Y_AXIS));

        JPanel jpAdressdaten = new JPanel();
        jpAdressdaten.setBorder(new TitledBorder("Adressdaten"));
        jpAdressdaten.setLayout(new BoxLayout(jpAdressdaten, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpPid = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlPid = new JLabel("Personen-ID:");
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
        jpPersonenAngaben.add(jpAnrede);
        jpPersonenAngaben.add(jpVorname);
        jpPersonenAngaben.add(jpName);

        jpAdressdaten.add(jpAnschrift);
        jpAdressdaten.add(jpPlz);
        jpAdressdaten.add(jpOrt);
        jpAdressdaten.add(jpErreichbarkeit);

        jpSonstigeAngaben.add(jpUst);
        jpSonstigeAngaben.add(jpNotiz);
        jpSonstigeAngaben.add(jpGebTag);

        jp.add(jpPersonenAngaben);
        jp.add(jpAdressdaten);
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
        JPanel jpWest = new JPanel();
        jpWest.setLayout(new BoxLayout(jpWest, BoxLayout.Y_AXIS));

        JPanel jpPersonenAngaben = new JPanel();
        jpPersonenAngaben.setBorder(new TitledBorder("Angaben zur Person"));
        jpPersonenAngaben.setLayout(new BoxLayout(jpPersonenAngaben, BoxLayout.Y_AXIS));

        JPanel jpAdressdaten = new JPanel();
        jpAdressdaten.setBorder(new TitledBorder("Adressdaten"));
        jpAdressdaten.setLayout(new BoxLayout(jpAdressdaten, BoxLayout.Y_AXIS));


        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAnrede = new JLabel("Anrede:");
        JTextField jtAnrede = new JTextField(20);
        String[] Anrede = { "Firma", "Frau", "Herr"};
        JComboBox jcAnredeListe = new JComboBox(Anrede);
        jcAnredeListe.setSelectedIndex(0);
        jcAnredeListe.setEditable(true);

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

        jpAnrede.add(jlAnrede);
        jpAnrede.add(jcAnredeListe);

        jpPersonenAngaben.add(jpAnrede);
        jpPersonenAngaben.add(jpVorname);
        jpPersonenAngaben.add(jpName);
        jpPersonenAngaben.add(jpGeburtstag);

        jpAdressdaten.add(jpAnschrift);
        jpAdressdaten.add(jpPlz);
        jpAdressdaten.add(jpOrt);


        JPanel jpButton = new JPanel();
        JButton jbErreichbarkeit = new JButton("Erreichbarkeiten");
        JButton jbNotiz = new JButton("Notiz");
        jpButton.add(jbErreichbarkeit);
        jpButton.add(jbNotiz);

        jpSonstigeAngaben.add(jpUst);
        jpSonstigeAngaben.add(jpButton);

        jpWest.add(jpPersonenAngaben);
        jpWest.add(jpAdressdaten);
        jpWest.add(jpSonstigeAngaben);


        //JPanel Center
        JPanel jpCenter = new JPanel();
        jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

        JPanel jpErreichbarkeiten = new JPanel();
        jpErreichbarkeiten.setBorder(new TitledBorder("Erreichbarkeiten"));
        jpErreichbarkeiten.setLayout(new BoxLayout(jpErreichbarkeiten, BoxLayout.Y_AXIS));

        JPanel jpTelefon = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTelefon = new JLabel("Telefon:");
        JTextField jtTelefon = new JTextField(20);
        jpTelefon.add(jlTelefon);
        jpTelefon.add(jtTelefon);

        JPanel jpMobil = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlMobil = new JLabel("Mobil:");
        JTextField jtMobil = new JTextField(20);
        jpMobil.add(jlMobil);
        jpMobil.add(jtMobil);

        JPanel jpEmail = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEmail = new JLabel("E-Mail:");
        JTextField jtEmail = new JTextField(20);
        jpEmail.add(jlEmail);
        jpEmail.add(jtEmail);

        JPanel jpDetails = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlDetails = new JLabel("Details:");
        JTextField jtDetails = new JTextField(20);
        jpDetails.add(jlDetails);
        jpDetails.add(jtDetails);

        JPanel jpNotiz = new JPanel();
        jpNotiz.setBorder(new TitledBorder("Notiz"));
        jpNotiz.setLayout(new BoxLayout(jpNotiz, BoxLayout.Y_AXIS));

        JPanel jpNotize = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea jtNotiz = new JTextArea(10,20);
        jpNotize.add(jtNotiz);

        jpErreichbarkeiten.add(jpTelefon);
        jpErreichbarkeiten.add(jpMobil);
        jpErreichbarkeiten.add(jpEmail);
        jpErreichbarkeiten.add(jpDetails);

        jpNotiz.add(jpNotize);
        jpCenter.add(jpErreichbarkeiten);
        jpCenter.add(jpNotiz);

        //JPanel East
        JPanel jpEast = new JPanel();
        jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

        JPanel jpPersonenListe = new JPanel();
        jpPersonenListe.setBorder(new TitledBorder("Personenliste"));
        jpPersonenListe.setLayout(new BoxLayout(jpPersonenListe, BoxLayout.Y_AXIS));

        JPanel jpPersListe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea jtPersListe = new JTextArea(10,30);
        jtPersListe.setText(String.valueOf(okfzsInstanz.getDatenbank().allePersonen()));
        jpPersListe.add(jtPersListe);

        jpPersonenListe.add(jpPersListe);

        jpEast.add(jpPersonenListe);

        jfPersonEdit.add(jpWest, BorderLayout.WEST);
        jfPersonEdit.add(jpCenter, BorderLayout.CENTER);
        jfPersonEdit.add(jpEast, BorderLayout.EAST);


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
