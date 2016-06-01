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
 * @author  cdreher on 23.05.2016.
 *
 * Klasse Suche: hier wird die vorhanden Datenbank durchsucht.
 * Anhand einer Dropdown-Auswahl in der GUI kann man auswählen nach welchen Parametern (Hersteller,Modell... bei Kfz
 * bzw. Name,Telefonnummer... bei Personen) man suchen möchte. Das Drop-Down Menü ist angepasst je nachdem ob man
 * nach KFZ oder Personen suchen möchte. Der eigentliche Suchbegriff kann in der GUI eingegeben werden und die Suche
 * kann nun gestartet werden. Werden Datensätze zu der spezifischen Suche gefunden, dann werden diese in der GUI angezeigt.
 * Sollte die Suche keine Ergebnisse liefern öffnet sich in der GUI je nachdem ob man nach KFZ oder Personen gesucht hat,
 * der KFZ-Editor bzw. Personen-Editor
 *
 */
public class Suche extends Ansicht {
    /**
     * Eine Liste mit KFZs
     */
    private List<KFZ> kfzs = new ArrayList<>();
    /**
     * Eine Liste mit Persoen
     */
    private List<Person> personen = new ArrayList<>();


    /**Der öffentliche Suche-Konstruktor über den die Suche gestartet werden kann
     *
     * @param okfzsinstanz aktuelle Instanz der OKFZ
     * @param kfzOderPerson Boolean-Flag ob nach KFZ oder Person gesucht werden soll
     */
    public Suche(OKFZS okfzsinstanz, boolean kfzOderPerson) {
        super(okfzsinstanz);

        JPanel jpSuche = this;
        JPanel jpEingabe = new JPanel();
        JPanel jpButton = new JPanel();
        String[] kfz = {"Fin", "Hersteller", "Modell","KFZ-Brief","Leistung","Farbe","EZ","Plakette","Kraftstoff"};
        String[] person = {"PID", "Anrede", "Name","Vorname","Telefonnummer","Geburtstag","Anschrift","PLZ","Ort","Ust-ID"};
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

    /**Gibt die Liste mit KFZs zurück die den Suchbegriff entsprechen
     *
     * @return Liste mit KFZs
     */
    public List<KFZ> getKfzs() {
        return kfzs;
    }

    /**Gibt die Liste mit Personen zurück die den Suchbegriff entsprechen
     *
     * @return Liste mit Personen
     */
    public List<Person> getPersonen() {
        return personen;
    }

    /**privater Suche-Konstruktor fuer die Suche nach KFZs
     *
     * @param spalte ist der Parameter der festlegt nach welchem Attribut der KFZs in der Liste gesucht werden soll
     * @param begriff ist der eigentliche Suchbegriff
     */
    private void sucheKFZ(String spalte, String begriff) {

        try {
            List<KFZ> kfzListe = getOKFZSInstanz().getDatenbank().alleKfz();
            switch (spalte) {
                case ("Fin"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equalsIgnoreCase(kfz.getFin()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Hersteller"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equalsIgnoreCase(kfz.getHersteller()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("Modell"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equalsIgnoreCase(kfz.getModell()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("KFZ-Brief"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equalsIgnoreCase(kfz.getKfzBriefNr()))
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
                        if (begriff.equalsIgnoreCase(kfz.getFarbe()))
                            kfzs.add(kfz);
                    }
                    break;
                case ("EZ"):
                    for (KFZ kfz : kfzListe) {
                        if (begriff.equalsIgnoreCase(kfz.getEz().toString()))
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
                        if (begriff.equalsIgnoreCase(kfz.getKraftstoff()))
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

    /**privater Suche-Konstruktor fuer die Suche nach Personen
     *
     * @param spalte ist der Parameter der festlegt nach welchem Attribut der Personen in der Liste gesucht werden soll
     * @param begriff ist der eigentliche Suchbegriff
     */
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
                        if (person.getAnrede().equalsIgnoreCase(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Name"):
                    for (Person person : personenListe) {
                        if (person.getName().equalsIgnoreCase(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Vorname"):
                    for (Person person : personenListe) {
                        if (begriff.equalsIgnoreCase(person.getVorname()))
                            personen.add(person);
                    }
                    break;
                case ("Geburtstag"):
                    for (Person person : personenListe) {
                        if (person.getGeburtstag().toString().equalsIgnoreCase(begriff))
                            personen.add(person);
                    }
                    break;
                case ("Anschrift"):
                    for (Person person : personenListe) {
                        if (begriff.equalsIgnoreCase(person.getAnschrift()))
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
                        if (begriff.equalsIgnoreCase(person.getOrt()))
                            personen.add(person);
                    }
                    break;
                case ("Ust-ID"):
                    for (Person person : personenListe) {
                        if (begriff.equalsIgnoreCase(person.getUstID()))
                            personen.add(person);
                    }
                    break;
                case ("Telefonnummer"):
                    for (Person person : getOKFZSInstanz().getDatenbank().eineTelefonummer(begriff))
                         personen.add(person);

                    break;
                default:
                    System.out.println("Fehler");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
