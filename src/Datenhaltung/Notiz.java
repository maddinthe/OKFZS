package Datenhaltung;

import java.util.Date;

/**
 * @author cdreher on 23.05.2016.
 *
 * Datenhaltungsklasse für die Notizen
 */
public class Notiz {
    /**
     * Notiz-ID
     */
    private long nid;
    /**
     * eine Person aus der Klasse Person
     */
    private Person person;
    /**
     *  Datum der erstellten Notiz
     */
    private Date datum;
    /**
     * Beschreibung der erstellten Notiz
     */
    private String beschreibung;

    /**Maximaler Konstruktor der genutzt wird um eine Notiz aus Datensätzen aus der Datenbank zu erstellen
     *
     * @param nid Notiz-ID
     * @param person eine Person
     * @param datum ein Datum
     * @param beschreibung eine Beschreibung
     */
    public Notiz(long nid, Person person, Date datum, String beschreibung) {
        this.nid = nid;
        this.person = person;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    /**Konstruktor ohne NID der genutzt wird um eine Notiz mit allen Parametern anzugeben und die Notiz an die Datenbank zu übergeben
     *
     * @param person eine Person
     * @param datum ein Datum
     * @param beschreibung eine Beschreibung
     */
    public Notiz(Person person, Date datum, String beschreibung) {
        this.person = person;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    /** Liefert die NID
     *
     * @return Positiver Long der NID-ID oder 0 wenn Erreichbarkeit nicht aus der Datenbank kommt
     */
    public long getNid() {
        return nid;
    }

    /**Liefert die Person zu den Notizen
     *
     * @return eine Person
     */
    public Person getPerson() {
        return person;
    }

    /**Liefert das Erstellungs-Datum der Notiz
     *
     * @return eine Datum
     */
    public Date getDatum() {
        return datum;
    }

    /**Liefert die Beschreibung der erstellten Notiz
     *
     * @return eine Beschreibung oder Null falls nicht eingetragen
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**Setzt eine neue Beschreibung zu einer vorhandenen Notiz
     *
     * @param beschreibung ist eine neue Beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**Liefert eine Notiz als String zurück
     *
     * @return Datum und Beschreibung der Notiz
     */
    @Override
    public String toString() {
        return  datum + " " +  beschreibung;
    }
}
