package Datenhaltung;

/**
 * @author cdreher on 23.05.2016.
 *
 * Datenhaltungsklasse für die Erreichbarkeiten
 */
public class Erreichbarkeit {
    /**
     * Erreichbarkeits-ID
     */
    private long eid;
    /**
     * eine Person aus der Klasse Person
     */
    private Person person;
    /**
     * eine Telefonnummer
     */
    private String telefonNummer;
    /**
     * eine Handynummer
     */
    private String handyNummer;
    /**
     * eine E-Mail-Adresse
     */
    private String email;
    /**
     * Details zu den Erreichbarkeiten
     */
    private String details;

    /**Maximaler Konstruktor der genutzt wird um eine Erreichbarkeit aus Datensätzen aus der Datenbank zu erstellen
     *
     * @param eid Erreichbarkeits-ID
     * @param person eine Person
     * @param telefonNummer eine Telefonnummer
     * @param handyNummer eine Handynummer
     * @param email eine E-Mail-Adresse
     * @param details Details zu den Erreichbarkeiten
     */
    public Erreichbarkeit(long eid, Person person, String telefonNummer, String handyNummer, String email, String details) {
        this.eid = eid;
        this.person = person;
        this.telefonNummer = telefonNummer;
        this.handyNummer = handyNummer;
        this.email = email;
        this.details = details;
    }

    /**Konstruktor ohne EID der genutzt wird um eine Erreichbarkeit mit allen Parametern anzugeben und die Erreichbarkeit an die Datenbank zu übergeben
     *
     * @param person eine Person
     * @param telefonNummer eine Telefonnummer
     * @param handyNummer eine Handynummer
     * @param email eine E-Mail-Adresse
     * @param details Details
     */
    public Erreichbarkeit(Person person, String telefonNummer, String handyNummer, String email, String details) {
        this.person = person;
        this.telefonNummer = telefonNummer;
        this.handyNummer = handyNummer;
        this.email = email;
        this.details = details;
    }

    /**Liefert die PID
     *
     * @return Positiver Long der Erreichbarkeits-ID oder 0 wenn Erreichbarkeit nicht aus der Datenbank kommt
     */
    public long getEid() {
        return eid;
    }

    /** Liefert die Person zu den Erreichbarkeiten
     *
     * @return eine Person
     */
    public Person getPerson() {
        return person;
    }

    /**Setzt die Person zu den Erreichbarkeiten
     *
     * @param person ist eine neue Person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**Liefert eine Telefonnummer
     *
     * @return eine Telefonnummer oder Null falls nicht eingetragen
     */
    public String getTelefonNummer() {
        return telefonNummer;
    }

    /**Setzt eine Telefonnummer
     *
     * @param telefonNummer ist eine neue Telefonnummer
     */
    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    /**Liefert eine Handynummer
     *
     * @return eine Handynummer oder Null falls nicht eingetragen
     */
    public String getHandyNummer() {
        return handyNummer;
    }

    /**Setzt eine Handynummer
     *
     * @param handyNummer ist eine neue Handynummer
     */
    public void setHandyNummer(String handyNummer) {
        this.handyNummer = handyNummer;
    }

    /**Liefert eine E-Mail-Addresse
     *
     * @return eine E-Mail-Addresse oder Null falls nicht eingetragen
     */
    public String getEmail() {
        return email;
    }

    /**Setzt eine E-Mail-Addresse
     *
     * @param email ist eine neue E-Mail-Addresse
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**Liefert Details zu den Erreichbarkeiten
     *
     * @return Details oder Null falls nicht eingetragen
     */
    public String getDetails() {
        return details;
    }

    /**Setzt Details zu den Erreichbarkeiten
     *
     * @param details sind neue Details zu den Erreichbarkeiten
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**Gibt die Erreichbarkeit als String zurück
     *
     * @return Details,Telefon,Mobil und E-Mail der Erreichbarkeit als String
     */
    @Override
    public String toString() {
        return "Details: "+details+" \n"+
                 "Telefon: "+telefonNummer + " \n"
                  +"Mobil: "+handyNummer + " \n"
                   +"E-Mail: " +email + " \n\n";
    }
}
