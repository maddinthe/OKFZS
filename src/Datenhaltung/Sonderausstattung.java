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

        String[] ausstattung = {"Klimaanlage.",
                "Allgemeine Betriebserlaubnis",
                "Antiblockiersystem",
                "automatischer Blockierverhinderer",
                "Adaptive Cruise Control",
                "Anhängerkupplung",
                "Automatische lastabhängige Bremse",
                "Acoustic Parking System",
                "Antriebsschlupfregelung",
                "Ansaugstutzen",
                "Austauschmotor",
                "Abgasuntersuchung",
                "All Wheel Drive, Four Wheel Drive",
                "Bauartbedingte Höchstgeschwindigkeit",
                "Bordcomputer",
                "Bremskraftverstärker",
                "Kubikzentimeter",
                "CD-Spieler",
                "Common-Rail-Einspritzung",
                "CD-Wechsler",
                "Coming-Home-Funktion",
                "Compressed Natural Gas",
                "Dieselpartikelfilter",
                "Dynamic Stability Control",
                "Direct Turbo Injection",
                "Drehzahlmesser",
                "Elektronische Bremskraftverteilung",
                "Elektronische Differentialsperre",
                "Elektrisch verstellbare Außenspiegel",
                "Elektrische Fensterheber",
                "Elektrohydraulische Bremse",
                "Elektrisches Schiebedach",
                "Elektronisches Stabilitätsprogramm",
                "Euronorm",
                "Erstzulassung Monat/Jahr",
                "Fernbedienung",
                "Fensterheber",
                "Fahrerinformationssystem",
                "Facelift",
                "Funkfernbedienung für Zentralverriegelung",
                "Geregelter Katalysator",
                "Global Positioning System",
                "Geschwindigkeitsregelanlage",
                "Glasschiebedach",
                "High Pressure Direct Injection",
                "Hybrid-Elektrofahrzeug",
                "Herstellerschlüsselnummer",
                "Hauptuntersuchung",
                "Head-Up-Display",
                "Hybridfahrzeug",
                "Höhenverstellbarer Fahrersitz",
                "Heizung",
                "Jahreswagen",
                "Kilowatt",
                "Liter",
                "Leichtmetallfelgen",
                "Lenkrad",
                "Laderaumabdeckung",
                "Lautsprecher",
                "Mittelarmlehne",
                "Multifunktionsanzeige, -display",
                "Modellpflege (Daimler)",
                "MP3-Player",
                "Matsch-und-Schnee-Reifen",
                "Navigationssystem",
                "Niveauregulierung",
                "Neupreis",
                "Nichtraucherfahrzeug",
                "Nebelscheinwerfer",
                "Park Distance Control",
                "Pollenfilter",
                "Pferdestärke",
                "Allradantrieb",
                "Radiorekorder",
                "Radio Data System",
                "Regen- / Lichtsensor",
                "Rußpartikelfilter",
                "Schiebe-Ausstelldach",
                "Seitenaufprallschutz",
                "scheckheftgepflegt",
                "Schiebedach, Stahlschiebedach",
                "Saugdiesel (Direkteinspritzer)",
                "Servolenkung",
                "Schiebefenster",
                "Schiebe-Hebedach",
                "Sitzheizung",
                "Side Impact Protection System",
                "Sommerreifen",
                "Standheizung",
                "Scheinwerferreinigungsanlage",
                "Traction Control System",
                "Turbocharged Direct Injection",
                "Türsteuergerät (Audi)",
                "Typschlüsselnummer",
                "Technischer Überwachungsverein",
                "Tageszulassung",
                "Ungeregelter Katalysator",
                "Verhandlungsbasis, Verhandlungssache",
                "Wegfahrsperre",
                "Winterreifen",
                "Xenon-Scheinwerfer",
                "Zentralverriegelung (/mit Fernbedienung)",
                "4 wheel drive",
                "Winterreifen zusätzlich"
        };

        return beschreibung;
    }
}
