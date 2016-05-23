package Datenhaltung;

/**
 * Created by tkertz on 23.05.2016.
 */
//todo: stub
public class Sonderausstattung {
    private long sid;
    private String beschreibung;

    public Sonderausstattung(long sid, String beschreibung) {
        this.sid = sid;
        this.beschreibung = beschreibung;
    }

    public long getSid() {
        return sid;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
