package Datenhaltung;

import java.util.Date;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Notiz {
    private long nid;
    private Person person;
    private Date date;
    private String beschreibung;

    public Notiz(long nid, Person person, Date date, String beschreibung) {
        this.nid = nid;
        this.person = person;
        this.date = date;
        this.beschreibung = beschreibung;
    }
    public Notiz(Person person,String beschreibung) {
        this.person = person;
        this.beschreibung = beschreibung;
    }

    public long getNid() {
        return nid;
    }

    public Person getPerson() {
        return person;
    }

    public Date getDate() {
        return date;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
