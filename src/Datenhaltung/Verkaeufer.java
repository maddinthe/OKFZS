package Datenhaltung;

import java.sql.Date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verkaeufer that = (Verkaeufer) o;

        if (!anmeldeName.equals(that.anmeldeName)) return false;
        return person.equals(that.person);

    }

    @Override
    public int hashCode() {
        int result = anmeldeName.hashCode();
        result = 31 * result + person.hashCode();
        return result;
    }
}
