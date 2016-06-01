package Datenhaltung;

import java.util.Date;

/**
 * Created by tkertz on 23.05.2016.
 * Diese Klasse dient der Datenhaltung für eine Aktion, die einem Auto hinzugefügt werden kann
 * @author Toni Kertz
 *
 */

public class Aktion {

    /**
     * Initialisierung des Datums der Durchführung einer Aktion
     */
    private Date durchfuehrung;
    /**
     * * Initialisierung der Person welche die Aktion durchführt
     */
    private Person durchfuehrender;
    /**
     * Initialisierung der Beschreibung, was als Aktion durchgeführt wird
     */
    private String beschreibung;
    /**
     * Initialisert ein Objekt vom Typ KFZ
     */
    private KFZ kfz;


    /**
     * Konstruktor der ein Objekt Aktion erzeugt
     * @param durchfuehrung - Datum an dem die Aktion stattfindet
     * @param durchfuehrender - Person die die Aktion durchführt
     * @param beschreibung - welche Aktion wird durchgeführt
     * @param kfz - Für welches KFZ wird die Aktion durchgeführt
     */
    public Aktion(Date durchfuehrung, Person durchfuehrender, String beschreibung, KFZ kfz) {
        this.durchfuehrung = durchfuehrung;
        this.durchfuehrender = durchfuehrender;
        this.beschreibung = beschreibung;
        this.kfz = kfz;
    }

    /**
     * Gibt das Datum der Durchführung zurück
     * @return - Datum der Durchführung
     */
    public Date getDurchfuehrung() {
        return durchfuehrung;
    }

    /**
     * Gibt die Person zurück, welche die Aktion durchführen wird
     * @return - Objekt vom Typ Person
     */
    public Person getDurchfuehrender() {
        return durchfuehrender;
    }

    /**
     * Gibt die Aktionsbeschreibung zurück
     * @return - Aktionsbeschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Gibt das KFZ zurück, welches der Aktion zugeordnet wird
     * @return - Objekt vom Typ KFZ
     */
    public KFZ getKfz() {
        return kfz;
    }

    /**
     * Setzt das Datum der Durchführung
     * @param durchfuehrung - Datum der Durchführung
     */
    public void setDurchfuehrung(Date durchfuehrung) {
        this.durchfuehrung = durchfuehrung;
    }

    /**
     * Setzt die Person als Durchführenden der Aktion
     * @param durchfuehrender - Objekt vom Typ Person
     */
    public void setDurchfuehrender(Person durchfuehrender) {
        this.durchfuehrender = durchfuehrender;
    }

    /**
     * Setzt die Beschreibung einer Aktion
     * @param beschreibung - Beschreibung der Aktion
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Versieht das KFZ mit einer Aktion
     * @param kfz - Objekt vom Typ KFZ
     */
    public void setKfz(KFZ kfz) {
        this.kfz = kfz;
    }

    /**
     * Zeigt das Datum und die Aktionsbeschreibung im KFZEditor an, wenn eine Aktion vorhanden ist
     * @return - Datum und Beschreibung einer Aktion, eines KFZs
     */
    public String toString(){
        return durchfuehrung+" "+beschreibung;
    }

}


