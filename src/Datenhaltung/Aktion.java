package Datenhaltung;

import java.util.Date;

/**
 * Created by tkertz on 23.05.2016.
 */
//todo: stub
public class Aktion {
    private Date durchfuehrung;
    private Person durchfuehrender;
    private String beschreibung;
    private KFZ kfz;

    public Aktion(Date durchfuehrung, Person durchfuehrender, String beschreibung, KFZ kfz) {
        this.durchfuehrung = durchfuehrung;
        this.durchfuehrender = durchfuehrender;
        this.beschreibung = beschreibung;
        this.kfz = kfz;
    }

    public Date getDurchfuehrung() {
        return durchfuehrung;
    }

    public Person getDurchfuehrender() {
        return durchfuehrender;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public KFZ getKfz() {
        return kfz;
    }

    public void setDurchfuehrung(Date durchfuehrung) {
        this.durchfuehrung = durchfuehrung;
    }

    public void setDurchfuehrender(Person durchfuehrender) {
        this.durchfuehrender = durchfuehrender;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setKfz(KFZ kfz) {
        this.kfz = kfz;
    }


}


