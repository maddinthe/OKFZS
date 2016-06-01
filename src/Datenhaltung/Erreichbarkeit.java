package Datenhaltung;

/**
 * cdreher on 23.05.2016.
 */

/**
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

    /**
     *
     * @param eid
     * @param person
     * @param telefonNummer
     * @param handyNummer
     * @param email
     * @param details
     */
    public Erreichbarkeit(long eid, Person person, String telefonNummer, String handyNummer, String email, String details) {
        this.eid = eid;
        this.person = person;
        this.telefonNummer = telefonNummer;
        this.handyNummer = handyNummer;
        this.email = email;
        this.details = details;
    }
    public Erreichbarkeit(Person person, String telefonNummer, String handyNummer, String email, String details) {

        this.person = person;
        this.telefonNummer = telefonNummer;
        this.handyNummer = handyNummer;
        this.email = email;
        this.details = details;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getHandyNummer() {
        return handyNummer;
    }

    public void setHandyNummer(String handyNummer) {
        this.handyNummer = handyNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Details: "+details+" \n"+
                 "Telefon: "+telefonNummer + " \n"
                  +"Mobil: "+handyNummer + " \n"
                   +"E-Mail: " +email + " \n\n";
    }
}
