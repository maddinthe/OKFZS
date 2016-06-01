package Datenhaltung;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * Datenhaltungsklasse für die Personen
 * @author cdreher on 23.05.2016.
 */
public class Person {
    /**
     * Personen-ID
     */
    private long pid;
    /**
     * Anrede der Person
     */
    private String anrede;
    /**
     * Nachname der Person
     */

    private String name;
    /**
     *  Vorname der Person
     */
    private String vorname;
    /**
     * Geburtsdatum der Person
     */
    private Date geburtstag;
    /**
     * Straße und Hausnummer der Person
     */
    private String anschrift;
    /**
     * Postleitzahl der Person
     */
    private int postleitzahl;
    /**
     * Ort der Person
     */
    private String ort;
    /**
     * Umsatzsteuer-ID falls die Person eine Firma ist
     */
    private String ustID;
    /**
     * Eine Liste von Notizen zu der Person
     */
    private List<Notiz> notizen= new ArrayList<>();
    /**
     * Eine Liste von Erreichbarkeiten zu der Person
     */
    private List<Erreichbarkeit> erreichbarkeiten = new ArrayList<>();


    /** Maximaler Konstruktor der genutzt wird um eine Person aus Datensätzen aus der Datenbank zu erstellen
     *
     * @param pid Personen-ID
     * @param anrede Anrede der Person
     * @param name Nachname der Person
     * @param vorname Vorname der Person
     * @param geburtstag Geburtstag der Person
     * @param anschrift Straße und Hausnummer der Person
     * @param postleitzahl Postleitzahl der Person
     * @param ort Ort der Person
     * @param ustID Umsatzsteuer-ID falls die Person eine Firma ist
     */
    public Person(long pid,String anrede, String name, String vorname, Date geburtstag, String anschrift, int postleitzahl, String ort, String ustID ) {
        this.pid = pid;
        this.anrede = anrede;
        this.name = name;
        this.vorname = vorname;
        this.geburtstag = geburtstag;
        this.anschrift = anschrift;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.ustID = ustID;

    }

    /**
     * Minimaler Konstruktor der genutzt wird um eine Person mit minimalen Parametern anzugeben und diese Person an die Datenbank zu übergeben
     * @param anrede Anrede der Person
     * @param name Nachame der Person
     * @param geburtstag Geburtstag der Person
     */
    public Person(String anrede,String name,Date geburtstag) {
        this.anrede = anrede;
        this.name = name;
        this.geburtstag = geburtstag;

    }

    /** Konstruktor ohne PID der genutzt wird um eine Person mit allen Parametern anzugeben und die Person an die Datenbank zu übergeben
     *
     * @param anrede Anrede der Person
     * @param name Nachname der Person
     * @param vorname Vorname der Person
     * @param geburtstag Geburtstag der Person
     * @param anschrift Straße und Hausnummer der Person
     * @param postleitzahl Postleitzahl der Person
     * @param ort Ort der Person
     * @param ustID Umsatzsteuer-ID falls die Person eine Firma ist
     */
    public Person(String anrede, String name, String vorname, Date geburtstag, String anschrift, int postleitzahl, String ort, String ustID ) {
        this.anrede = anrede;
        this.name = name;
        this.vorname = vorname;
        this.geburtstag = geburtstag;
        this.anschrift = anschrift;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.ustID = ustID;

    }


    /**Liefert die PID
     *
     * @return Positiver Long der Personen-ID oder 0 wenn Person nicht aus der Datenbank kommt
     */
    public long getPid() {
        return pid;
    }

    /**Liefert die Anrede einer Person
     *
     * @return die Anrede der der Person
     */
    public String getAnrede() {
        return anrede;
    }

    /**Liefert den Nachnamen der Person
     *
     * @return den Nachnamen der Person
     */
    public String getName() {
        return name;
    }

    /**Liefert den Vornamen der Person
     *
     * @return den Vornamen der Person oder Null falls nicht eingetragen
     */
    public String getVorname() {
        return vorname;
    }

    /**Liefert den Geburtstag der Person
     *
     * @return den Geburtstag der Person
     */
    public Date getGeburtstag() {
        return geburtstag;
    }

    /**Liefert die Straße und Hausnummer der Person
     *
     * @return die Straße und Hausnummer der Person oder Null falls nicht eingetragen
     */
    public String getAnschrift() {
        return anschrift;
    }

    /**Liefert die Postleitzahl der Person
     *
     * @return die Postleitzahl der Person oder 0 falls nicht eingetragen
     */
    public int getPostleitzahl() {
        return postleitzahl;
    }

    /**Liefert den Ort der Person
     *
     * @return den Ort der Person oder Null falls nicht eingetragen
     */
    public String getOrt() {
        return ort;
    }

    /**Liefert die Umsatzsteuer-ID der Person
     *
     * @return Liefert die Umsatzsteuer-ID der Person oder Null falls nicht eingetragen
     */
    public String getUstID() {
        return ustID;
    }

    /**Setzt die Personen-ID neu
     *
     * @param pid ist eine neue Personen-ID
     */
    public void setPid(long pid) {
        this.pid = pid;
    }

    /**Fügt der Liste Erreichbarkeiten eine Erreichbarkeit hinzu
     *
     * @param erreichbarkeit ist eine neue Erreichbarkeit
     */
    public void addErreichbarkeit(Erreichbarkeit erreichbarkeit){
        erreichbarkeiten.add(erreichbarkeit);
    }

    /**Fügt der Liste Notizen eine
     *
     * @param notiz ist eine neue Notiz
     */
    public void addNotiz(Notiz notiz){
        notizen.add(notiz);
    }

    /**Gibt die Person als String zurück
     *
     * @return Nachname,Vorname,Postleitzahl und Ort der Person als String
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s",name,vorname,postleitzahl,ort);
    }

    /** Macht die Klasse Personen vergleichbar
     *
     * @param o ist ein zu vergleichendes Objekt
     * @return true wenn o=this, false wenn o!=this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (pid != person.pid) return false;
        if (postleitzahl != person.postleitzahl) return false;
        if (anrede != null ? !anrede.equals(person.anrede) : person.anrede != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (vorname != null ? !vorname.equals(person.vorname) : person.vorname != null) return false;
        if (geburtstag != null ? !geburtstag.equals(person.geburtstag) : person.geburtstag != null) return false;
        if (anschrift != null ? !anschrift.equals(person.anschrift) : person.anschrift != null) return false;
        if (ort != null ? !ort.equals(person.ort) : person.ort != null) return false;
        return !(ustID != null ? !ustID.equals(person.ustID) : person.ustID != null);

    }

    /**Berechnet den Hash-Code der Person
     *
     * @return gibt den Hash-Code als Integer zurück
     */
    @Override
    public int hashCode() {
        int result = (int) (pid ^ (pid >>> 32));
        result = 31 * result + (anrede != null ? anrede.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (geburtstag != null ? geburtstag.hashCode() : 0);
        result = 31 * result + (anschrift != null ? anschrift.hashCode() : 0);
        result = 31 * result + postleitzahl;
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        result = 31 * result + (ustID != null ? ustID.hashCode() : 0);
        return result;
    }


}
