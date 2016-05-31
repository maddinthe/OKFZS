package GUI.Werkzeug;

import Datenhaltung.KFZ;
import Datenhaltung.Person;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdreher on 23.05.2016.
 */
public class Suche extends Ansicht {
    OKFZS okfzsinstanz = new OKFZS();
    private List<KFZ> kfzs = new ArrayList<>();
    private List<Person> personen = new ArrayList<>();





    public Suche(OKFZS okfzsinstanz, boolean kfzOderPerson) {
        super(okfzsinstanz);
        JFrame jfSuche = new JFrame("Suche");
        JPanel jpSuche = new JPanel();
        JPanel jpEingabe = new JPanel();
        JPanel jpButton = new JPanel();
        String[] kfz = {"Fin", "Hersteller", "Modell","KFZ-Brief","Leistung","Farbe","EZ","Plakette","Kraftstoff"};
        String[] person = {"PID", "Anrede", "Name","Vorname","Geburtstag","Anschrift","PLZ","Ort","Ust-ID"};
        JComboBox jcKfz = new JComboBox(kfz);
        JComboBox jcPerson = new JComboBox(person);
        JTextField jtSuche = new JTextField(20);
        JButton jbSuche = new JButton("Suche");
        ActionListener alSuche = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kfzOderPerson) {
                    if (jtSuche.getText() != null)
                        sucheKFZ(jcKfz.getSelectedItem().toString(), jtSuche.getText());
                    else JOptionPane.showMessageDialog(null, "Keine Eingabe");
                }
                else{
                if (jtSuche.getText()!=null)
                    suchePerson(jcPerson.getSelectedItem().toString(),jtSuche.getText());
                else JOptionPane.showMessageDialog(null,"Keine Eingabe");
                }
                jfSuche.dispose();
            }
        };
        jbSuche.addActionListener(alSuche);
        JButton jbAbbrechen = new JButton("Abbrechen");
        ActionListener alAbbrechen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              jfSuche.dispose();
            }
        };
        jbAbbrechen.addActionListener(alAbbrechen);
        if(kfzOderPerson){
            jpEingabe.add(jcKfz);
            jpEingabe.add(jtSuche);
        }
        else{
            jpEingabe.add(jcPerson);
            jpEingabe.add(jtSuche);
        }
        jpButton.add(jbSuche);
        jpButton.add(jbAbbrechen);
        jpSuche.add(jpEingabe);
        jpSuche.add(jpButton);
        jfSuche.add(jpSuche);

        jfSuche.setSize(300, 150);
        jfSuche.setLocation(500, 500);
        jfSuche.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfSuche.setVisible(true);



    }
    public List<KFZ> getKfzs() {
        return kfzs;
    }

    public List<Person> getPersonen() {
        return personen;
    }

    private void sucheKFZ(String spalte, String begriff) {

        try {
            List<KFZ> kfzListe = okfzsinstanz.getDatenbank().alleKfz();
            switch (spalte) {
                case ("Fin"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getFin().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Hersteller"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getHersteller().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Modell"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getModell().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("KFZ-Brief"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getKfzBriefNr().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Leistung"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getLeistungInKw() == (Integer.parseInt(begriff)))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Farbe"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getFarbe().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("EZ"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getEz().toString().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Plakette"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getUmweltPlakette() == (Byte.parseByte(begriff)))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Kraftstoff"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getKraftstoff().equals(begriff))
                            kfzs.add(kfz);
                    }
                    break;
                default:
                    System.out.println("Fehler");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void suchePerson(String spalte, String begriff) {

        try {
            List<Person> personenListe = okfzsinstanz.getDatenbank().allePersonen();

            switch (spalte) {
                case ("PID"):
                    for (Person person : personenListe) {
                        if (person.getPid() == (Long.parseLong(begriff)))
                            personen.add(person);
                    }
                    break;
                case ("Anrede"):
                    for (Person person : personenListe) {
                        if (person.getAnrede().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Name"):
                    for (Person person : personenListe) {
                        if (person.getName().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Vorname"):
                    for (Person person : personenListe) {
                        if (person.getVorname().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Geburtstag"):
                    for (Person person : personenListe) {
                        if (person.getGeburtstag().toString().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Anschrift"):
                    for (Person person : personenListe) {
                        if (person.getAnschrift().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("PLZ"):
                    for (Person person : personenListe) {
                        if (person.getPostleitzahl() == (Integer.parseInt(begriff)))
                            personen.add(person);
                    }
                    break;
                case ("Ort"):
                    for (Person person : personenListe) {
                        if (person.getOrt().equals(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Ust-ID"):
                    for (Person person : personenListe) {
                        if (person.getUstID().equals(begriff))
                           personen.add(person);
                    }
                    break;
                default:
                    System.out.println("Fehler");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
