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

    private List<KFZ> kfzs = new ArrayList<>();
    private List<Person> personen = new ArrayList<>();





    public Suche(OKFZS okfzsinstanz, boolean kfzOderPerson) {
        super(okfzsinstanz);

        JPanel jpSuche = this;
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
                    if (!jtSuche.getText().equals("")){
                        sucheKFZ(jcKfz.getSelectedItem().toString(), jtSuche.getText());
                        okfzsinstanz.anzeigen("autoAnz");
                    }else JOptionPane.showMessageDialog(null, "Keine Eingabe","Eingabefehler",JOptionPane.ERROR_MESSAGE);
                }
                else{
                if (!jtSuche.getText().equals("")){
                    suchePerson(jcPerson.getSelectedItem().toString(),jtSuche.getText());
                    okfzsinstanz.anzeigen("personAnz");
                }

                else JOptionPane.showMessageDialog(null,"Keine Eingabe","Eingabefehler", JOptionPane.ERROR_MESSAGE);
                }

            }
        };
        jbSuche.addActionListener(alSuche);
        JButton jbAbbrechen = new JButton("Abbrechen");
        ActionListener alAbbrechen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

             okfzsinstanz.anzeigen("uebersicht");
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






    }
    public List<KFZ> getKfzs() {
        return kfzs;
    }

    public List<Person> getPersonen() {
        return personen;
    }

    private void sucheKFZ(String spalte, String begriff) {

        try {
            List<KFZ> kfzListe = getOKFZSInstanz().getDatenbank().alleKfz();
            switch (spalte) {
                case ("Fin"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getFin()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Hersteller"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getHersteller()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Modell"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getModell()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("KFZ-Brief"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getKfzBriefNr()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Leistung"):
                    for (KFZ kfz : kfzListe) {
                        if ((Integer.parseInt(begriff)) == kfz.getLeistungInKw())
                            kfzs.add(kfz);
                    }
                    break;
                case ("Farbe"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getFarbe()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("EZ"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getEz().toString()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Plakette"):
                    for (KFZ kfz : kfzListe) {
                        if ((Byte.parseByte(begriff)) == kfz.getUmweltPlakette())
                            kfzs.add(kfz);
                    }
                    break;
                case ("Kraftstoff"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equals(kfz.getKraftstoff()))
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
            List<Person> personenListe = getOKFZSInstanz().getDatenbank().allePersonen();

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
                        if (begriff.equals(person.getVorname()))
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
                        if (begriff.equals(person.getAnschrift()))
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
                        if (begriff.equals(person.getOrt()))
                            personen.add(person);
                    }
                    break;
                case ("Ust-ID"):
                    for (Person person : personenListe) {
                        if (begriff.equals(person.getUstID()))
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
