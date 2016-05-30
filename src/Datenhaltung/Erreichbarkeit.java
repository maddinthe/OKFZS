package Datenhaltung;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Erreichbarkeit {
    private long eid;
    private Person person;
    private String telefonNummer;
    private String handyNummer;
    private String email;
    private String details;

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
        return details+" \n"+
                 "Telefon:"+telefonNummer + " \n"
                  +"Mobil:"+handyNummer + " \n"
                   +"E-Mail:" +email + " \n\n";
    }
}
