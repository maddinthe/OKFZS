package Datenhaltung;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Verkaeufer {
    private String anmeldeName;
    private  String passwortHash;
    private Person person;
    private boolean istAktiv;
    private boolean istAdmin;

    public Verkaeufer(String anmeldeName, String passwortHash, Person person, boolean istAktiv, boolean istAdmin) {
        this.anmeldeName = anmeldeName;
        this.passwortHash = passwortHash;
        this.person = person;
        this.istAktiv = istAktiv;
        this.istAdmin = istAdmin;
    }

    public String getAnmeldeName() {
        return anmeldeName;
    }

    public String getPasswortHash() {
        return passwortHash;
    }

    public Person getPerson() {
        return person;
    }

    public boolean istAktiv() {
        return istAktiv;
    }

    public boolean istAdmin() {
        return istAdmin;
    }
}
