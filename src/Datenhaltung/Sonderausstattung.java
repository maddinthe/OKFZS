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
    public Sonderausstattung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public long getSid() {
        return sid;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    public String toString(){
        return beschreibung;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sonderausstattung that = (Sonderausstattung) o;

        if (sid != that.sid) return false;
        return beschreibung.equals(that.beschreibung);

    }

    @Override
    public int hashCode() {
        int result = (int) (sid ^ (sid >>> 32));
        result = 31 * result + beschreibung.hashCode();
        return result;
    }
}
