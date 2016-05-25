package GUI.Werkzeug;

import Datenhaltung.KFZ;
import Datenhaltung.Person;
import GUI.OKFZS;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cdreher on 23.05.2016.
 */
public class Suche {
    OKFZS okfzsinstanz = new OKFZS();

    public Suche(String spalte, String begriff, boolean kfzOderPerson) {
        if (kfzOderPerson)
            sucheKFZ(spalte, begriff);
        else
            suchePerson(spalte, begriff);
    }

    private KFZ sucheKFZ(String spalte, String begriff) {

        try {
            List<KFZ> kfzListe = okfzsinstanz.getDatenbank().alleKfz();
            switch (spalte) {
                case ("Fin"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getFin().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("Hersteller"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getHersteller().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("Modell"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getModell().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("KFZ-Brief"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getKfzBriefNr().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("Leistung"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getLeistungInKw() == (Integer.parseInt(begriff)))
                            return kfz;
                    }
                    break;
                case ("Farbe"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getFarbe().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("EZ"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getEz().toString().equals(begriff))
                            return kfz;
                    }
                    break;
                case ("Plakette"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getUmweltPlakette() == (Byte.parseByte(begriff)))
                            return kfz;
                    }
                    break;
                case ("Kraftstoff"):
                    for (KFZ kfz : kfzListe) {
                        if (kfz.getKraftstoff().equals(begriff))
                            return kfz;
                    }
                    break;
                default:
                    System.out.println("Fehler");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person suchePerson(String spalte, String begriff) {

        List<Person> personenListe = null;
        try {
            personenListe = okfzsinstanz.getDatenbank().allePersonen();

            switch (spalte) {
                case ("Pid"):
                    for (Person person : personenListe) {
                        if (person.getPid() == (Long.parseLong(begriff)))
                            return person;
                    }
                    break;
                case ("Anrede"):
                    for (Person person : personenListe) {
                        if (person.getAnrede().equals(begriff))
                            return person;
                    }
                    break;
                case ("Name"):
                    for (Person person : personenListe) {
                        if (person.getName().equals(begriff))
                            return person;
                    }
                    break;
                case ("Vorname"):
                    for (Person person : personenListe) {
                        if (person.getVorname().equals(begriff))
                            return person;
                    }
                    break;
                case ("Geburtstag"):
                    for (Person person : personenListe) {
                        if (person.getGeburtstag().toString().equals(begriff))
                            return person;
                    }
                    break;
                case ("Anschrift"):
                    for (Person person : personenListe) {
                        if (person.getAnschrift().equals(begriff))
                            return person;
                    }
                    break;
                case ("PLZ"):
                    for (Person person : personenListe) {
                        if (person.getPostleitzahl() == (Integer.parseInt(begriff)))
                            return person;
                    }
                    break;
                case ("Ort"):
                    for (Person person : personenListe) {
                        if (person.getOrt().equals(begriff))
                            return person;
                    }
                    break;
                case ("Ust-ID"):
                    for (Person person : personenListe) {
                        if (person.getUstID().equals(begriff))
                            return person;
                    }
                    break;
                default:
                    System.out.println("Fehler");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
