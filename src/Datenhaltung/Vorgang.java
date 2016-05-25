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
    private int kilometer;

    //todo:doku
    public Vorgang(KFZ kfz, Verkaeufer einkauefer, double ePreis) {
        this.kfz = kfz;
        this.einkauefer = einkauefer;
        this.ePreis = ePreis;
        this.einkaufsDatum=new Date();
        this.vPreisPlan=ePreis*1.2; //todo:realistische preisspanne einbauen
    }

    public Vorgang(long vid, Person kauefer, Verkaeufer verkaeufer, Verkaeufer einkaeufer, KFZ kfz, double vPreis, double ePreis, double vPreisPlan, Date verkaufsDatum, String rabattGrund, String sonstvereinbarungen, Date einkaufsDatum, String schaeden, Date tuev, String kennzeichen,int kilometer) {
        this.vid = vid;
        this.kauefer = kauefer;
        this.verkaeufer = verkaeufer;
        this.einkauefer = einkaeufer;
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
        this.kilometer=kilometer;
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

    public Verkaeufer getEinkaeufer() {
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

    public int getKilometer(){
        return kilometer;
    }

    /**
     * Gibt den gewinn anteilig für den Jeweiligen Verkaufer aus
     *
     * @param verkaeufer liefert den Gewinn für den angegeben Verkaufer aus (50% des gewinns für Ein oder Verkäufer nichts für alle anderen)
     * @return Anteiliger gewinn des übergebenen verkäufers in wärung
     */
    public double getGewinn(Verkaeufer verkaeufer) {
        if (verkaeufer.equals(this.einkauefer) || verkaeufer.equals(this.verkaeufer)){
            if(this.verkaeufer.equals(this.einkauefer))return getGewinn();
            return getGewinn() / 2;
        }
        return 0;
    }

    public void setVid(long vid) {
        this.vid = vid;
    }

    public void setKauefer(Person kauefer) {
        this.kauefer = kauefer;
    }

    public void setVerkaeufer(Verkaeufer verkaeufer) {
        this.verkaeufer = verkaeufer;
    }

    public void setEinkauefer(Verkaeufer einkauefer) {
        this.einkauefer = einkauefer;
    }

    public void setKfz(KFZ kfz) {
        this.kfz = kfz;
    }

    public void setvPreis(double vPreis) {
        this.vPreis = vPreis;
    }

    public void setePreis(double ePreis) {
        this.ePreis = ePreis;
    }

    public void setvPreisPlan(double vPreisPlan) {
        this.vPreisPlan = vPreisPlan;
    }

    public void setVerkaufsDatum(Date verkaufsDatum) {
        this.verkaufsDatum = verkaufsDatum;
    }

    public void setRabattGrund(String rabattGrund) {
        this.rabattGrund = rabattGrund;
    }

    public void setSonstvereinbarungen(String sonstvereinbarungen) {
        this.sonstvereinbarungen = sonstvereinbarungen;
    }

    public void setEinkaufsDatum(Date einkaufsDatum) {
        this.einkaufsDatum = einkaufsDatum;
    }

    public void setSchaeden(String schaeden) {
        this.schaeden = schaeden;
    }

    public void setTuev(Date tuev) {
        this.tuev = tuev;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public void setKilometer(int kilometer){
        this.kilometer=kilometer;
    }

    //todo: schöner machen
    public String toString() {
        return "Vorgang{" +
                "vid=" + vid +
                ", kauefer=" + kauefer +
                ", verkaeufer=" + verkaeufer +
                ", einkauefer=" + einkauefer +
                ", kfz=" + kfz +
                ", vPreis=" + vPreis +
                ", ePreis=" + ePreis +
                ", vPreisPlan=" + vPreisPlan +
                ", verkaufsDatum=" + verkaufsDatum +
                ", rabattGrund='" + rabattGrund + '\'' +
                ", sonstvereinbarungen='" + sonstvereinbarungen + '\'' +
                ", einkaufsDatum=" + einkaufsDatum +
                ", schaeden='" + schaeden + '\'' +
                ", tuev=" + tuev +
                ", kennzeichen='" + kennzeichen + '\'' +
                '}';
    }
}

