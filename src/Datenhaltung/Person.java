package Datenhaltung;

import Datenbank.Datenbank;

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
    private List<Notiz> notizen;
    private List<Erreichbarkeit> erreichbarkeiten;

    public Person(long pid, String anrede, String name, String vorname, Date geburtstag, String anschrift, int postleitzahl, String ort, String ustID, List<Notiz> notizen, List<Erreichbarkeit> erreichbarkeiten) {
        this.pid = pid;
        this.anrede = anrede;
        this.name = name;
        this.vorname = vorname;
        this.geburtstag = geburtstag;
        this.anschrift = anschrift;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.ustID = ustID;
        this.notizen = notizen;
        this.erreichbarkeiten = erreichbarkeiten;
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
        return "Person{" +
                "pid=" + pid +
                ", anrede='" + anrede + '\'' +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", geburtstag=" + geburtstag +
                ", anschrift='" + anschrift + '\'' +
                ", postleitzahl=" + postleitzahl +
                ", ort='" + ort + '\'' +
                ", ustID='" + ustID + '\'' +
                ", notizen=" + notizen +
                ", erreichbarkeiten=" + erreichbarkeiten +
                '}';
    }
}
