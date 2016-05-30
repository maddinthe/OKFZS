package Datenhaltung;

import Datenbank.Datenbank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Person {
    private long pid;
    private String anrede;
    private String name;
    private String vorname;
    private Date geburtstag;
    private String anschrift;
    private int postleitzahl;
    private String ort;
    private String ustID;
    private List<Notiz> notizen= new ArrayList<>();
    private List<Erreichbarkeit> erreichbarkeiten = new ArrayList<>();

    public Person(long pid, String anrede, String name, String vorname, Date geburtstag, String anschrift, int postleitzahl, String ort, String ustID ) {
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

    public Person(String anrede, String name,Date geburtstag) {
        this.anrede = anrede;
        this.name = name;
        this.geburtstag = geburtstag;

    }

    public long getPid() {
        return pid;
    }

    public String getAnrede() {
        return anrede;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public Date getGeburtstag() {
        return geburtstag;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getUstID() {
        return ustID;
    }

    public List<Notiz> getNotizen() {
        return notizen;
    }

    public List<Erreichbarkeit> getErreichbarkeiten() {
        return erreichbarkeiten;
    }

    public void addErreichbarkeit(Erreichbarkeit erreichbarkeit){
        erreichbarkeiten.add(erreichbarkeit);
    }

    public void addNotiz(Notiz notiz){
        notizen.add(notiz);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s",name,vorname,postleitzahl,ort);
    }

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
