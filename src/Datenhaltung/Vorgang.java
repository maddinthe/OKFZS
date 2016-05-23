package Datenhaltung;

import java.util.Date;

/**
 * Created by tkertz on 23.05.2016.
 */
//todo: stub
public class Vorgang {
    private long vid;
    private Person kauefer;
    private Verkaeufer verkaeufer;
    private Verkaeufer einkauefer;
    private KFZ kfz;
    private double vPreis;
    private double ePreis;
    private double vPreisPlan;
    private Date verkaufsDatum;
    private String rabattGrund;
    private String sonstvereinbarungen;
    private Date einkaufsDatum;
    private String schaeden;
    private Date tuev;
    private String kennzeichen;

    //todo:doku
    public Vorgang(KFZ kfz, Verkaeufer einkauefer, double ePreis) {
        this.kfz = kfz;
        this.einkauefer = einkauefer;
        this.ePreis = ePreis;
    }

    public Vorgang(long vid, Person kauefer, Verkaeufer verkaeufer, Verkaeufer einkauefer, KFZ kfz, double vPreis, double ePreis, double vPreisPlan, Date verkaufsDatum, String rabattGrund, String sonstvereinbarungen, Date einkaufsDatum, String schaeden, Date tuev, String kennzeichen) {
        this.vid = vid;
        this.kauefer = kauefer;
        this.verkaeufer = verkaeufer;
        this.einkauefer = einkauefer;
        this.kfz = kfz;
        this.vPreis = vPreis;
        this.ePreis = ePreis;
        this.vPreisPlan = vPreisPlan;
        this.verkaufsDatum = verkaufsDatum;
        this.rabattGrund = rabattGrund;
        this.sonstvereinbarungen = sonstvereinbarungen;
        this.einkaufsDatum = einkaufsDatum;
        this.schaeden = schaeden;
        this.tuev = tuev;
        this.kennzeichen = kennzeichen;
    }

    public long getVid() {
        return vid;
    }

    public Person getKauefer() {
        return kauefer;
    }

    public Verkaeufer getVerkaeufer() {
        return verkaeufer;
    }

    public Verkaeufer getEinkauefer() {
        return einkauefer;
    }

    public KFZ getKfz() {
        return kfz;
    }

    public double getvPreis() {
        return vPreis;
    }

    public double getePreis() {
        return ePreis;
    }

    public double getvPreisPlan() {
        return vPreisPlan;
    }

    public Date getVerkaufsDatum() {
        return verkaufsDatum;
    }

    public String getRabattGrund() {
        return rabattGrund;
    }

    public String getSonstvereinbarungen() {
        return sonstvereinbarungen;
    }

    public Date getEinkaufsDatum() {
        return einkaufsDatum;
    }

    public String getSchaeden() {
        return schaeden;
    }

    public Date getTuev() {
        return tuev;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public double getGewinn() {
        return vPreis - ePreis;
    }

    /**
     * Gibt den gewinn anteilig für den Jeweiligen Verkaufer aus
     *
     * @param verkaeufer liefert den Gewinn für den angegeben Verkaufer aus (50% des gewinns für Ein oder Verkäufer nichts für alle anderen)
     * @return Anteiliger gewinn des übergebenen verkäufers in wärung
     */
    public double getGewinn(Verkaeufer verkaeufer) {
        if (verkaeufer.equals(this.einkauefer) || verkaeufer.equals(this.verkaeufer)) return getGewinn() / 2;
        return 0;
    }
}
