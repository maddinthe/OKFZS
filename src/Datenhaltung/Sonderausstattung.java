package Datenhaltung;

/**
 * Created by tkertz on 23.05.2016.
 * Diese Klasse dient der Datenhaltung von Sonderausstattungen
 * @author Toni Kertz
 */

public class Sonderausstattung {

    /**
     * Initialisierung der Sonderausstattungs-Indentifikationsnummer
     */
    private long sid;

    /**
     * Initialisierung der Sonderausstattungsbeschreibung
     */
    private String beschreibung;

    /**
     * Dieser Konstruktor erzeugt ein Objekt Sonderausstattung mit einer Beschreibung und einer SID
     * @param sid - Identifikationsnummer des Sonderausstattungsobjektes
     * @param beschreibung - Beschreibung des Sonderausstattungsobjektes
     */
    public Sonderausstattung(long sid, String beschreibung) {
        this.sid = sid;
        this.beschreibung = beschreibung;
    }

    /**
     * Dieser Konstruktor erzeugt ein Objekt Sonderausstattung mit einer Beschreibung.
     * Er wird genutzt, um das Objekt in der DB zu speichern, die SID wird dabei automatisch innerhalb der DB vergeben
     * @param beschreibung - Beschreibung des Sonderausstattungsobjektes
     */
    public Sonderausstattung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    /**
     * Gibt Sonderausstattungsidentifikationsnummer zurück
     * @return - SID
     */
    public long getSid() {
        return sid;
    }

    /**
     * Gibt die Beschreibung eines Sonderausstattungobjekt zurück
     * @return - Beschreibung der Sonderausstattung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Formatiert die Ausgabe der Beschreibung
     * @return - beschreibung
     */
    public String toString(){
        return beschreibung;
    }

    /**
     * Vergleichen von Sonderausstattungsobjekten
     * @param o - Objekt welches verglichen werden soll
     * @return - true wenn Objekt o = this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sonderausstattung that = (Sonderausstattung) o;

        if (sid != that.sid) return false;
        return beschreibung.equals(that.beschreibung);

    }

    /**
     * Generiert den HashCode zum Objekt
     * @return einen Int Wert
     */
    @Override
    public int hashCode() {
        int result = (int) (sid ^ (sid >>> 32));
        result = 31 * result + beschreibung.hashCode();
        return result;
    }
}
