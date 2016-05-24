package Datenhaltung;

import java.util.Date;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 */
//todo: stub


public class KFZ {
    private String fin;
    private String hersteller;
    private String modell;
    private String kfzBriefNr;
    private int leistungInKw;
    private String farbe;
    private Date ez;
    private Byte umweltPlakette;
    private String kraftstoff;
    private List<Aktion> aktionen;
    private List<Sonderausstattung> sonderausstattung;

    public KFZ(String fin, String hersteller, String modell, String kfzBriefNr, int leistungInKw, String farbe, Date ez, Byte umweltPlakette, String kraftstoff, List<Aktion> aktionen, List<Sonderausstattung> sonderausstattung) {
        this.fin = fin;
        this.hersteller = hersteller;
        this.modell = modell;
        this.kfzBriefNr = kfzBriefNr;
        this.leistungInKw = leistungInKw;
        this.farbe = farbe;
        this.ez = ez;
        this.umweltPlakette = umweltPlakette;
        this.kraftstoff = kraftstoff;
        this.aktionen = aktionen;
        this.sonderausstattung = sonderausstattung;
    }


    public String getFin() {
        return fin;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getModell() {
        return modell;
    }

    public String getKfzBriefNr() {
        return kfzBriefNr;
    }

    public int getLeistungInKw() {
        return leistungInKw;
    }

    public String getFarbe() {
        return farbe;
    }

    public Date getEz() {
        return ez;
    }

    public Byte getUmweltPlakette() {
        return umweltPlakette;
    }

    public String getKraftstoff() {
        return kraftstoff;
    }

    public List<Aktion> getAktionen() {
        return aktionen;
    }

    public List<Sonderausstattung> getSonderausstattung() {
        return sonderausstattung;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setKfzBriefNr(String kfzBriefNr) {
        this.kfzBriefNr = kfzBriefNr;
    }

    public void setLeistungInKw(int leistungInKw) {
        this.leistungInKw = leistungInKw;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public void setEz(Date ez) {
        this.ez = ez;
    }

    public void setUmweltPlakette(Byte umweltPlakette) {
        this.umweltPlakette = umweltPlakette;
    }

    public void setKraftstoff(String kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public void setAktionen(List<Aktion> aktionen) {
        this.aktionen = aktionen;
    }

    public void setSonderausstattung(List<Sonderausstattung> sonderausstattung) {
        this.sonderausstattung = sonderausstattung;
    }

    public void addAktion(Aktion a){
        aktionen.add(a);
    }

    public void addSonderstattung(Sonderausstattung s){
        sonderausstattung.add(s);
    }
}
