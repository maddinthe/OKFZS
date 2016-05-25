package GUI.Werkzeug;


import Datenhaltung.KFZ;
import Datenhaltung.Person;
import GUI.OKFZS;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cdreher on 23.05.2016.
 */
public class Suche {
    OKFZS okfzsinstanz = new OKFZS();

    public Suche(String spalte, String begriff, boolean entscheidung) {

    }

    private void sucheKFZ(String name, String begriff){
        try {
            List<KFZ> kfzListe = okfzsinstanz.getDatenbank().alleKfz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void suchePerson(String name, String begriff){
        try {
            List<Person> personenListe = okfzsinstanz.getDatenbank().allePersonen();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









    switch (suche) {
        case "h":
            for (KFZ kfz : kfzliste) {
                if (kfz.getHersteller().equals(parameter))
                    System.out.println(kfz.toString());
            }
            break;
        case "m":
            for (KFZ kfz : kfzliste) {
                if (kfz.getModell().equals(parameter))
                    System.out.println(kfz.toString());
            }
            break;
        case "p":
            for (KFZ kfz : kfzliste) {
                if (kfz.getPreis().equals(parameter))
                    System.out.println(kfz.toString());
            }
            break;
        case "hmp":
            for (KFZ kfz : kfzliste) {
                if (kfz.getHersteller().equals(parameter) || kfz.getModell().equals(parameter) || kfz.getPreis().equals(parameter))
                    System.out.println(kfz.toString());
            }
            break;
        default:
            System.out.println("Fehler");
    }


}
}
