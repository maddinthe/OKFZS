package Datenhaltung;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 * Das ist die Datenhaltungsklasse eines Objektes vom Typ KFZ
 * @author Toni Kertz
 */


public class KFZ {

    /**
     * Initialsierung der Fahrzeug-Identifikationsnummer
     */
    private String fin;

    /**
     * Initialsierung des KFZ-Herstellers
     */
    private String hersteller;

    /**
     * Initialsierung des KFZ-Modells
     */
    private String modell;

    /**
     * Initialsierung der KFZ-Brief-Nr.
     */
    private String kfzBriefNr;

    /**
     * Initialsierung der Leistung in Kilowatt
     */
    private int leistungInKw;

    /**
     * Initialsierung der Farbe eines KFZs
     */
    private String farbe;

    /**
     * Initialsierung des Erstzulassungsdatums
     */
    private Date ez;

    /**
     * Initialsierung der Umweltplakettennummer
     */
    private Byte umweltPlakette;

    /**
     * Initialsierung der Kraftstoffart
     */
    private String kraftstoff;

    /**
     * Initialisierung der Aktionenliste, in welcher später alle Aktionen des Fahrzeugs enthalten sein werden
     */
    private List<Aktion> aktionen=new ArrayList<>();

    /**
     * Initialisierung der Sonderausstattungsliste, in welcher später auch neue Sonderausstattungseinträge enthalten sein können
     */
    private List<Sonderausstattung> sonderausstattung=new ArrayList<>();

    /**
     * Konstruktor der ein Objekt KFZ erzeugt mit den folgenden Parametern:
     * @param fin - Fahrzeug Ident Nr
     * @param hersteller - Fahrzeug Hersteller
     * @param modell - Fahrzeug Modell
     * @param kfzBriefNr - KFZ Brief Nummer eines Fahrzeugs
     * @param leistungInKw - Leistung eines Fahrzeugs in Kilowatt
     * @param farbe - Farbe eines Fahrzeugs
     * @param ez - Datum der Erstzulassung
     * @param umweltPlakette - Wert der Umweltplakette (z.B. 3 für Gelb, 4 für Grün etc.pp.)
     * @param kraftstoff - Art des Kraftstoffes eines Fahrzeugs
     */
    public KFZ(String fin, String hersteller, String modell, String kfzBriefNr, int leistungInKw, String farbe, Date ez, Byte umweltPlakette, String kraftstoff) {
        this.fin = fin;
        this.hersteller = hersteller;
        this.modell = modell;
        this.kfzBriefNr = kfzBriefNr;
        this.leistungInKw = leistungInKw;
        this.farbe = farbe;
        this.ez = ez;
        this.umweltPlakette = umweltPlakette;
        this.kraftstoff = kraftstoff;
    }

    /**
     * Gibt die Fahrzeug-Identifikationsnummer zurück
     * @return Fahrzeug-Identifikationsnummer
     */
    public String getFin() {
        return fin;
    }

    /**
     * Gibt den KFZ-Hersteller zurück
     * @return KFZ-Hersteller
     */
    public String getHersteller() {
        return hersteller;
    }

    /**
     * Gibt das KFZ-Modell zurück
     * @return KFZ-Modell
     */
    public String getModell() {
        return modell;
    }

    /**
     * Gibt die KFZ Briefnummer zurück
     * @return KFZ Briefnummer
     */
    public String getKfzBriefNr() {
        return kfzBriefNr;
    }

    /**
     * Gibt die Leistung eines KFZs in Kilowatt zurück
     * @return Leistung in Kilowatt
     */
    public int getLeistungInKw() {
        return leistungInKw;
    }

    /**
     * Gibt die Farbe eines KFZs zurück
     * @return Farbe eines KFZs
     */
    public String getFarbe() {
        return farbe;
    }

    /**
     * Gibt das Datum der Erstzulassung eines KFZs zurück
     * @return Datum Erstzulassung
     */
    public Date getEz() {
        return ez;
    }

    /**
     * Gibt den Zahlenwert der Umwelplakette eines KFZs zurück
     * @return Zahlenwert Umweltplakette eines KFZs
     */
    public Byte getUmweltPlakette() {
        return umweltPlakette;
    }

    /**
     * Gibt die Kraftstoffart eines KFZs zurück
     * @return Kraftstoff eines KFZs
     */
    public String getKraftstoff() {
        return kraftstoff;
    }

    /**
     * Gibt eine Liste mit Aktionen eines KFZs zurück
     * @return Aktionen eines KFZs
     */
    public List<Aktion> getAktionen() {
        return aktionen;
    }

    /**
     * Gibt eine Liste mit Sonderausstattung eines KFZs zurück
     * @return Sonderausstattung eines KFZs
     */
    public List<Sonderausstattung> getSonderausstattung() {
        return sonderausstattung;
    }

    /**
     * Die Methode ersetzt alle Aktionen der Liste Aktionen mit der übergebenen Liste
     * @param aktionen - Liste die übergeben wird
     */
    public void setAktionen(List<Aktion> aktionen) {
        this.aktionen = aktionen;
    }

    /**
     * Die Methode ersetzt die Liste mit der übergebenen Liste
     * @param sonderausstattung - Liste die übergeben wird
     */
    public void setSonderausstattung(List<Sonderausstattung> sonderausstattung) {
        this.sonderausstattung = sonderausstattung;
    }

    /**
     * Die Methode fügt der Liste Aktionen eine Aktion hinzu
     * @param a - Aktion die hinzugefügt wird
     */
    public void addAktion(Aktion a) {
        aktionen.add(a);
    }

    /**
     * Die Methode fügt der Liste Sonderausstattungen einen Eintrag hinzu
     * @param s - Sonderausstattungseintrag der hinzugefügt wird
     */
    public void addSonderstattung(Sonderausstattung s) {
        sonderausstattung.add(s);
    }

    /**
     * Diese Methode stellt die optimierte Anzeige der KFZ Daten zur Verfügung
     * @return - Objekt KFZ als String
     * @author Martin Theilen
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, TÜV: %5$tm - %5$tY, %6$s", hersteller, modell, farbe, kraftstoff, ez, fin);
    }

    /**
     * Diese Methode stellt die optimierte Anzeige der KFZ Daten zur Verfügung
     * @return - Objekt KFZ als String
     * @author Martin Theilen
     */
    public String toDetailString() {
        String sonderausstattungString = "keine";
        if (sonderausstattung != null) {
            sonderausstattungString = "";
            for (Sonderausstattung s : sonderausstattung) {
                sonderausstattungString += s.toString() + ", ";
            }
        }
        if (sonderausstattungString.length() > 0)
            sonderausstattungString = sonderausstattungString.substring(0, sonderausstattungString.length() - 2);

        return String.format("%s, %s, %s, %s, TÜV: %5$tm-%5$tm, FIN: %6$s, %7$d KW, UWP: %8$d, KFZ-Brief: %9s, Ausstattung: %10s"
                , hersteller, modell, farbe, kraftstoff, ez, fin, leistungInKw, umweltPlakette, kfzBriefNr, sonderausstattungString);
    }
}
