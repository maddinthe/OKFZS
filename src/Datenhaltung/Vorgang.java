package Datenhaltung;

import java.util.Date;

/**
 * Created by tkertz on 23.05.2016.
 * Finished by mtheilen on 31.05.2016
 */

/**
 * Datenhaltungsklasse für die Vorgänge
 */
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

    /**
     * Minimalconstruktor für den Vorgang hier ermittelt sich der geplante Verkaufspreis automatisch genause wie das Einkaufsdatum welches auf die aktuelle zeit gesetzt wird
     * @param kfz KFZ das im Vorgang behandelt wird
     * @param einkauefer Verkäufer der das KFz in diesem vorgang eingekauft hat
     * @param ePreis Einkaufspreis des Fahrzeugs in €
     */
    public Vorgang(KFZ kfz, Verkaeufer einkauefer, double ePreis) {
        this.kfz = kfz;
        this.einkauefer = einkauefer;
        this.ePreis = ePreis;
        this.einkaufsDatum=new Date();
        this.vPreisPlan=ePreis*1.2;
    }

    /**
     * maximaler Konstruktor der z.b. für eine erstellung aus der Datenbak verwendet wird
     * @param vid Vorgangsid
     * @param kauefer @Nullable der Käufer des Fahrzeugs
     * @param verkaeufer  @Nullable der Verkäufer des Fahrzeugs
     * @param einkaeufer @NotNull der Verkäufer der das Fahrzeug eingekauft hat
     * @param kfz @NotNull das Fahrzeug um das es in diesem Vorgang geht
     * @param vPreis der Verkaufspreis
     * @param ePreis der Einkaufspreis
     * @param vPreisPlan der geplante Verkaufspreis
     * @param verkaufsDatum @Nullable das Verkaufsdatum
     * @param rabattGrund @Nullable der Rabattgrund
     * @param sonstvereinbarungen @Nullable sonstige vereinbarungen zum Kauf
     * @param einkaufsDatum @NotNull das Einkaufsdatum
     * @param schaeden @Nullable evtl Schäden am Fahrzeug
     * @param tuev @Nullable Zeitpunkt an dem HU/AU ablaufen
     * @param kennzeichen @Nullable das aktuelle Kennzeichen
     * @param kilometer der Aktuelle Kilometerstand
     */
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

    /**
     * Berechnet der gewinn am KFZ-Verkauf
     * @return der Reingewinn
     */
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

    public String toString() {
        return String.format("%.2f €, Schäden: %s, %s",vPreis,schaeden,kfz.toDetailString());
    }
}

