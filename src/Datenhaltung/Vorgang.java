package Datenhaltung;
import com.sun.istack.internal.NotNull;

import java.util.Date;
/**
 * Datenhaltungsklasse für die Vorgänge
 * by mtheilen on 31.05.2016
 * @author  Martin Theilen
 */
public class Vorgang {
    /**
     * VorgangsID
     */
    private long vid;
    /**
     * Der Käufer in diesem Vorgang
     */
    private Person kauefer;
    /**
     * Der Verkäufer in diesem Vorgang
     */
    private Verkaeufer verkaeufer;
    /**
     * Der Einkäufer in diesem Vorgang
     */
    private Verkaeufer einkauefer;
    /**
     * Das KFZ um das es in diesem Vorgang geht, nie null
     */
    private KFZ kfz;
    /**
     * der Tatsächliche Verkaufspreis
     */
    private double vPreis;
    /**
     * Der Einkaufspreis nie null
     */
    private double ePreis;
    /**
     * der Geplante Einkaufspreis
     */
    private double vPreisPlan;
    /**
     * das tatsächliche Verkuafsdatum
     */
    private Date verkaufsDatum;
    /**
     * der Grund für einen Evtl Rabatt
     */
    private String rabattGrund;
    /**
     * Sondervereinbarungen zum Vertrag
     */
    private String sonstvereinbarungen;
    /**
     * Das Einkaufsdatum nie null
     */
    private Date einkaufsDatum;
    /**
     * evtl Schäden
     */
    private String schaeden;
    /**
     * Das datum an dem die HU abläuft
     */
    private Date tuev;
    /**
     * das aktuelle kennzeichen des KFZ beim verkauf
     */
    private String kennzeichen;
    /**
     * der Kilometerstand des KFZ beim Verkauf
     */
    private int kilometer;

    /**
     * Minimalconstruktor für den Vorgang hier ermittelt sich der geplante Verkaufspreis automatisch genause wie das Einkaufsdatum welches auf die aktuelle zeit gesetzt wird
     * @param kfz KFZ das im Vorgang behandelt wird
     * @param einkauefer Verkäufer der das KFz in diesem vorgang eingekauft hat
     * @param ePreis Einkaufspreis des Fahrzeugs in €
     */
    public Vorgang(@NotNull KFZ kfz,@NotNull Verkaeufer einkauefer,@NotNull double ePreis) {
        this.kfz = kfz;
        this.einkauefer = einkauefer;
        this.ePreis = ePreis;
        this.einkaufsDatum=new Date();
        this.vPreisPlan=ePreis*1.2;
    }

    /**
     * maximaler Konstruktor der z.b. für eine erstellung aus der Datenbak verwendet wird
     * @param vid Vorgangsid
     * @param kauefer  der Käufer des Fahrzeugs
     * @param verkaeufer   der Verkäufer des Fahrzeugs
     * @param einkaeufer  der Verkäufer der das Fahrzeug eingekauft hat
     * @param kfz  das Fahrzeug um das es in diesem Vorgang geht
     * @param vPreis der Verkaufspreis
     * @param ePreis der Einkaufspreis
     * @param vPreisPlan der geplante Verkaufspreis
     * @param verkaufsDatum  das Verkaufsdatum
     * @param rabattGrund  der Rabattgrund
     * @param sonstvereinbarungen  sonstige vereinbarungen zum Kauf
     * @param einkaufsDatum  das Einkaufsdatum
     * @param schaeden  evtl Schäden am Fahrzeug
     * @param tuev  Zeitpunkt an dem HU/AU ablaufen
     * @param kennzeichen  das aktuelle Kennzeichen
     * @param kilometer der Aktuelle Kilometerstand
     */
    public Vorgang(long vid, Person kauefer, Verkaeufer verkaeufer,@NotNull Verkaeufer einkaeufer,@NotNull KFZ kfz,double vPreis,@NotNull double ePreis, double vPreisPlan, Date verkaufsDatum, String rabattGrund, String sonstvereinbarungen,@NotNull Date einkaufsDatum, String schaeden, Date tuev, String kennzeichen,int kilometer) {
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

    /**
     * Liefert die VorgangsID
     * @return Positiver long der VorgID oder 0 wenn vorgang nicht aus DB kommt
     */
    public long getVid() {
        return vid;
    }

    /**
     * Liefert den Käufer
     * @return Person des käufers oder NULL wenn kein Käufer eingetragen
     */
    public Person getKauefer() {
        return kauefer;
    }

    /**
     * Liefert den Verkäufer
     * @return Verkäufer des Verkäufers oder NULL wenn kein Veräufer eingetragen
     */
    public Verkaeufer getVerkaeufer() {
        return verkaeufer;
    }

    /**
     * Liefert den Einkäufer
     * @return Verkäufer des Einkäufers oder NULL wenn kein Einkäufer eingetragen
     */
    public Verkaeufer getEinkaeufer() {
        return einkauefer;
    }

    /**
     * Liefert das zu Verkaufende KFZ
     * @return KFZ das zu Verkaufen ist
     */
    public KFZ getKfz() {
        return kfz;
    }

    /**
     * Liefert den Tatsächlichen verkaufspreis
     * @return den Tatsächlichen Verkaufspreis oder 0.0 wenn keiner eingetragen ist
     */
    public double getvPreis() {
        return vPreis;
    }

    /**
     * Liefert den Einkaufspreis
     * @return Der Einkaufspreis
     */
    public double getePreis() {
        return ePreis;
    }

    /**
     *  Liefert den geplanten Verkaufspreis per default ist das 1,2*ePreis
     * @return den geplanten Verkaufspreis
     */
    public double getvPreisPlan() {
        return vPreisPlan;
    }

    /**
     * Liefert das Tatsächliche Verkaufsdatum
     * @return das verkaufsdatum oder NULL falls nicht eingetragen
     */
    public Date getVerkaufsDatum() {
        return verkaufsDatum;
    }

    /**
     * Liefert den Grund für einen evtl Rabatt
     * @return der Grund für einen Rabatt oder NULL/Leerer String falls keiner eingetragen
     */
    public String getRabattGrund() {
        return rabattGrund;
    }
    /**
     * Liefert Sonstige vereinbarungen zum Vertrag
     * @return iefert Sonstige vereinbarungen zum Vertrag oder NULL/Leerer String falls keine eingetragen
     */
    public String getSonstvereinbarungen() {
        return sonstvereinbarungen;
    }

    /**
     * Liefert das Einkaufsdatum
     * @return Einkaufsdatum
     */
    public Date getEinkaufsDatum() {
        return einkaufsDatum;
    }

    /**
     * Liefert evtl Schäden am KFZ
     * @return liefert evtl Schäden am KFZ oder NULL/Leerer String falls keine eingetragen
     */
    public String getSchaeden() {
        return schaeden;
    }

    /**
     * Liefert das Datum an dem die HU abläuft
     * @return DATE das datum an dem die HU abläuft oder NULL wenn keins eingetragen
     */
    public Date getTuev() {
        return tuev;
    }

    /**
     * Liefert das Kennzeichen
     * @return liefert das Kennzeichen oder NULL/Leerer String falls keine eingetragen
     */
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

    /**
     * Liefert den Aktuellen KM Stand
     * @return liefert den Aktuellen KM stand oder 0 wenn keiner eingetragen
     */
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
            if(this.einkauefer.equals(this.verkaeufer))return getGewinn();
            return getGewinn() / 2;
        }
        return 0;
    }

    /**
     * Setzt den Käufer des KFZ
     * @param kauefer PERSON die das KFZ kaufen Soll
     */
    public void setKauefer(Person kauefer) {
        this.kauefer = kauefer;
    }

    /**
     * Setzt den Verkäufer
     * @param verkaeufer VERKÄUFER der das KFZ verkauft
     */
    public void setVerkaeufer(Verkaeufer verkaeufer) {
        this.verkaeufer = verkaeufer;
    }


    /**
     * Setzt den Tatsächelichen Verkaufspreis
     * @param vPreis DOUBLE des Verkuafspreises in EURO
     */
    public void setvPreis(double vPreis) {
        this.vPreis = vPreis;
    }

    /**
     * Setzt das Verkaufsdatum
     * @param verkaufsDatum das datum an dem das KFZ verkauft wurde/wird
     */
    public void setVerkaufsDatum(@NotNull Date verkaufsDatum) {
        this.verkaufsDatum = verkaufsDatum;
    }

    /**
     * Der Rabattgrund
     * @param rabattGrund STRING der Grund für einen Evtl Rabatt und ersetzt den alten
     */
    public void setRabattGrund(String rabattGrund) {
        this.rabattGrund = rabattGrund;
    }

    /**
     * Sonstige Vereinbarungen
     * @param sonstvereinbarungen STRING setzt evtl Sonstige vereinbarungen und ersetzt alte
     */
    public void setSonstvereinbarungen(String sonstvereinbarungen) {
        this.sonstvereinbarungen = sonstvereinbarungen;
    }

    /**
     * Das datum an dem Die HU des KFZ abläuft
     * @param tuev das DATE an dem die HU des KFZ ablaufen wird/Abgelaufen ist
     */
    public void setTuev(Date tuev) {
        this.tuev = tuev;
    }

    /**
     * das kennzeichen welches im Vertrag festgehalten wird
     * @param kennzeichen STRING das kennzeichen welches sich am KFZ befindet
     */
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    /**
     * Der Kilometestand zum Verkaufstag
     * @param kilometer der Kilometerstand des KFZ am Verkaufstag
     */
    public void setKilometer(int kilometer){
        this.kilometer=kilometer;
    }

    /**
     * Die toString Methode für einen Vorgang
     * @return String "100.00€, Schäden: 'getSchaenden()', 'kfz.toDetailString()'"
     */
    public String toString() {
        return String.format("%.2f €, Schäden: %s, %s",vPreis,schaeden,kfz.toDetailString());
    }
}

