package Datenhaltung;


/**
 * @author  cdreher on 23.05.2016.
 *
 * Datenhaltungsklasse für die Verkaeufer
 */
public class Verkaeufer {
    /**
     * Anmeldename des Verkaeufers
     */
    private String anmeldeName;
    /**
     * Passwort-Hash des Verkaeufers
     */
    private  String passwortHash;
    /**
     * Eine Person aus der Klasse Person
     */
    private Person person;
    /**
     * Boolean ist der Verkaeufer Aktiv
     */
    private boolean istAktiv;
    /**
     * Boolean ist der Verkaeufer einAdmin
     */
    private boolean istAdmin;

    /** Maximaler Konstruktor der genutzt wird um einen Verkaeufer mit allen Parametern anzugeben und den Verkaeufer an die Datenbank zu übergeben
     *
     * @param anmeldeName ein Anmeldename
     * @param passwortHash ein Passwort-Hash
     * @param person eine Person
     * @param istAktiv ein Boolean-Flag
     * @param istAdmin ein Boolean-Flag
     */
    public Verkaeufer(String anmeldeName, String passwortHash, Person person, boolean istAktiv, boolean istAdmin) {
        this.anmeldeName = anmeldeName;
        this.passwortHash = passwortHash;
        this.person = person;
        this.istAktiv = istAktiv;
        this.istAdmin = istAdmin;
    }

    /**Liefert den Anmeldenamen des Verkaeufers
     *
     * @return den Anmeldenamen oder Null falls nichts eingetragen
     */
    public String getAnmeldeName() {
        return anmeldeName;
    }

    /**Liefert den Passwort-Hash des Verkaeufers
     *
     * @return den Passwort-Hash oder Null falls nichts eingetragen
     */
    public String getPasswortHash() {
        return passwortHash;
    }

    /** Liefert die Person hinter dem Verkaeufer
     *
     * @return eine Person
     */
    public Person getPerson() {
        return person;
    }

    /**Liefert ein Boolean-Flag ob der Verkaeufer aktiv ist
     *
     * @return true oder false
     */
    public boolean istAktiv() {
        return istAktiv;
    }

    /**Liefert ein Boolean-Flag ob der Verkaeufer ein Admin ist
     *
     * @return true oder false
     */
    public boolean istAdmin() {
        return istAdmin;
    }

    /**Setzt zu dem Verkaeufer einen neuen Anmeldenamen
     *
     * @param anmeldeName neuer Anmeldename
     */
    public void setAnmeldeName(String anmeldeName) {
        this.anmeldeName = anmeldeName;
    }

    /**Setzt zu dem Verkaeufer einen neuen Passwort-Hash
     *
     * @param passwortHash
     */
    public void setPasswortHash(String passwortHash) {
        this.passwortHash = passwortHash;
    }

    /** Setzt das Boolean-Flag neu
     *
     * @param istAktiv true oder false
     */
    public void setIstAktiv(boolean istAktiv) {
        this.istAktiv = istAktiv;
    }

    /**Setzt das Boolean-Flag neu
     *
     * @param istAdmin true oder false
     */
    public void setIstAdmin(boolean istAdmin) {
        this.istAdmin = istAdmin;
    }

    /**Macht die Klasse Verkaeufer vergleichbar
     *
     * @param o ist ein zu vergleichendes Objekt
     * @return true wenn o=this, false wenn o!=this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verkaeufer that = (Verkaeufer) o;

        if (!anmeldeName.equals(that.anmeldeName)) return false;
        return person.equals(that.person);

    }

    /**Berechnet den Hash-Code des Verkaeufers
     *
     * @return gibt den Hash-Code als Integer zurück
     */
    @Override
    public int hashCode() {
        int result = anmeldeName.hashCode();
        result = 31 * result + person.hashCode();
        return result;
    }
    public String toString(){
        return String.format("%s, %s",anmeldeName,person.toString());
    }
}
