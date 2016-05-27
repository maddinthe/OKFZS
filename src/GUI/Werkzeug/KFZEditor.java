package GUI.Werkzeug;

import Datenhaltung.KFZ;
import Datenhaltung.Vorgang;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by tkertz on 23.05.2016.
 */
public class KFZEditor extends Ansicht {
    private String[] testAustattung = {
            "8fach bereift (8f)",
            "Abgasuntersuchung (Au)",
            "Airbag (AB, AIR)",
            "Airbag (AIR)",
            "Alarmanlage (Alarm)",
            "Alcantara (Alc)",
            "Alufelgen (Alu)",
            "Anhängerkupplung (AHK)",
            "Anti-Schlupf-Regelung (ASR)",
            "Antiblockiersystem (ABS)",
            "Aufstelldach (ADa)",
            "aus erster Hand (1.Hd)",
            "Außenspiegel (Asp)",
            "Außentemperaturanzeiger (ATA)",
            "Aussenthemperaturanzeige (AT)",
            "Ausstattung (Ausst.)",
            "Austauschgetriebe (ATG)",
            "Austauschmotor (ATM)",
            "ausweisbar (awb.)",
            "Auto-Check-System (AC)",
            "Automatische Motorantenne (AA)",
            "Automatisches Park-System (APS)",
            "Automatik (Aut.)",
            "automatisch abklappbarer Spiegel (aSp)",
            "Automatisches Sperrdifferenzial (ASD)",
            "Autotelefon (Tel)",
            "Baujahr (Bj.)",
            "Bordcomputer (BC)",
            "BOSE-Soundsystem (BOSE)",
            "breite Reifen (BR)",
            "Cassetten-Recorder (Cass.)",
            "Cassettenablage (Cass)",
            "CD-Wechsler (CD)",
            "Colorverglasung (Col.)",
            "Dachreling (DR)",
            "Dachreling (Dachr., DR)",
            "Dachträger (Dachtr.)",
            "Dämmglas (Dä)",
            "Diebstahlsicherung (Diebstahl.)",
            "Diebstahlwarnanlage (DWA)",
            "Differentialsperre (Diff-Sp.)",
            "Digitales Soundsystem (Digi)",
            "diverse Extras (div.Extr.)",
            "Drehzahlmesser (DZM)",
            "Durchlade (DL)",
            "Durchladesystem / Skisack (DU)",
            "elektrisch einstellb.u.beheizbare Spiegel (eSP)",
            "Elektrische Differentialsperre (EDS)",
            "Elektrisches Faltdach (eFalt)",
            "Elektrische Fensterheber (eFH)",
            "Elektrische Fensterheber vorn (eFHvo)",
            "Elektrische geregelte Klimaanlage (Climatroni)",
            "Elektrisches Schiebedach (eSD)",
            "Elektrisches Solarschiebedach (eSD)",
            "Elektrisches Sonnenrollo (eSo)",
            "Elektrisches Stabilitäts-Programm (ESP)",
            "Elektrisches Verdeck (Cabrio) (eV)",
            "Elektrisch verstellbare Sitze (eSi)",
            "Elektrische Außenspiegel (eAsp)",
            "elektrische Fensterheber (el.FH oder eFH)",
            "elektrische Zentralverriegelung (eZV)",
            "elektrisches Glas-Hebe-Dach (eGHD)",
            "elektrisches Glas-Schiebe-Dach (eGSD)",
            "elektrisches Schiebe-Dach (eSD)",
            "elektrisches Schiebe-Hebe-Dach (eSHD)",
            "elektrisches Stabilisierungsprogramm (ESP)",
            "Erstzulassung (EZ)",
            "Extras (Extr.)",
            "Fahrer-Informations-System (FIS)",
            "Fahrersitz, höhenverstellbar (vSi)",
            "Fahrradhalter (FaHa)",
            "Fernbedienung (FB)",
            "Festpreis (FP)",
            "Finanzierung (Finz.)",
            "Ganzjahresreifen (GJ)",
            "Garagenwagen (GaWa)",
            "Garagenwagen (GW)",
            "Garantie (Herstellergarantie) (GAR)",
            "Gebrauchtwagengarantie (GW-Gar.)",
            "Gepäckraumabdeckung (GA)",
            "gepflegt (gepfl.)",
            "Geregelter Katalysator (G-Kat.)",
            "Geschwindigkeitsregelanlage (GRA)",
            "geteilte Rückbank (1/3-2/3) (gRü)",
            "Glas-Hebe-Dach (GHD)",
            "Glasdach (GD)",
            "Glasdach, elektrisch (GDe)",
            "Glasschiebedach (GSD)",
            "Glasschiebehebedach (GSHD)",
            "Grünkeil (Grünk.)",
            "Gurt-Integralsitze (Cabrio) (GIS)",
            "Halbjahreswagen (HJW)",
            "Hardtop (HT)",
            "Hecklautsprecher (HLS)",
            "Heckscheibenwischer (Heckw./HW)",
            "Heckspoiler (Hecksp.)",
            "Heckspoiler (HSp)",
            "Heckwischer (HeWi.)",
            "heizbare Scheibenwaschdüsen (HS)",
            "Heizung (Hzg.)",
            "Hochdach (Reisemobil) (HDa)",
            "Holzapplikationen (HO)",
            "Im Auftrag (i.A)",
            "Innenspiegel automatisch abblendend (abSP)",
            "Inspektion durchgeführt (Ins)",
            "integrierte Kindersitze (Kind)",
            "Inzahlungsnahme (Inz.)",
            "Jahreswagen (JW)",
            "Jaquard-Satin-Sitzbezüge (JAQ)",
            "katalysator (Kat)",
            "Kilowatt (kW)",
            "Klimaanlage, elektronisch geregelt (Ke)",
            "Klimaanlage, halbautomatisch manuell (K)",
            "Klimaanlage, manuelle Regelung (Klima)",
            "Komfortsitze (KS)",
            "komplett (kpl.)",
            "Kopfstützen (Kopfst.)",
            "Kopfstützen hinten (KO)",
            "Kundendienst (KD.)",
            "Kundendienstgepflegt (KDgepfl.)",
            "kurzer Radstand (KRS)",
            "langer Radstand (LRS)",
            "Lederausstattung (Leder)",
            "Lederlenkrad (LLR)",
            "Leichtmetallfelgen (Alu)",
            "Leichtmetallfelgen (LMF oder Alu)",
            "LKW-Zulassung (LKW-Zul.)",
            "Markise (Reisemobil) (MA)",
            "Memory Sitze (MEM)",
            "Metallic-Lackierung (met)",
            "Mittelarmlehne (Arm)",
            "Mittelarmlehne (MAL oder Arm)",
            "Modell (Mod.)",
            "Multifunktionsanzeige (MFA)",
            "Navigationssystem (Navi)",
            "Navigationssystem mit Farbdisplay (RC-Navi)",
            "Nebelscheinwerfer (NS)",
            "Nebelscheinwerfer (NE)",
            "Netztrennwand (Netz)",
            "Neupreis (NP)",
            "Nichtraucherfahrzeug (NR)",
            "Niveauregulierung (Niveau)",
            "Parkdistanzkontrolle (PDC)",
            "Perl-Effect-Lackierung (perl)",
            "PKW-Zulassung (PKW-Zul.)",
            "Pollenfilter (PF)",
            "Pollenfilter (Pollenfil.)",
            "Radio (R)",
            "Radio-Cassette (RC/RD)",
            "Radio-CD-Player (RD)",
            "Radio-Gamma (RC-Gamma)",
            "Radio/Cassetten/CD (Soundsyst. oder CD)",
            "rechtgesteuert (RHD)",
            "Regensensor (RS)",
            "Reling (Rel.)",
            "Scheckheftgepflegt (Scheckh.)",
            "Scheibenantenne (SA)",
            "Scheinwerfer-Reinigungsanlage (SRA)",
            "Scheinwerferwaschanlage (SWW)",
            "Schiebedach, manuell (SD)",
            "Schiebefenster (hieb)",
            "Servolenkung (Servo)",
            "Servolenkung (SV)",
            "Servotronic (ST)",
            "Sitzheizung (Si.Hzg. oder SHZ)",
            "Sitzpaket (Sitz)",
            "Skisack (Skis.)",
            "Softtop (ST)",
            "Sommerreifen (SR)",
            "Sonnenrollo (SO)",
            "Soundsystem / Aktivlautsprecher (HiFi)",
            "Sperrdifferential (Sperrdiff.)",
            "Spoiler (Spoil.)",
            "Sportausstattung (SpA)",
            "Sportfahrwerk (Sport.FW)",
            "Sportfahrwerk (SpF)",
            "Sportlenkrad aus Leder (SpL)",
            "Sportsitze (SpS)",
            "Stahlhubdach (SHD)",
            "Stahlkurbeldach (SKD)",
            "Stahlschiebedach (SS)",
            "Standheizung (SH)",
            "Styling-Paket (Styling)",
            "Technikpaket (Technik)",
            "Teillederausstaung (TL)",
            "Telefonvorbereitung (TelVor)",
            "Tempomat (Tempom.)",
            "Thermotronic (THER)",
            "Topzustand (Topzust.)",
            "Tüv (§)",
            "Überrollbügel (ÜB)",
            "und viele Extras (u.v.Extr.)",
            "und vieles mehr (uvm.)",
            "und weitere Extras (u.w.Extr.)",
            "Unfallfrei (unf.-fr.)",
            "Ungeregelter Katalysator (U-Kat.)",
            "Velours-Sitzbezüge (VE)",
            "Verhandlungsbasis (VB)",
            "Verhandlungssache (VS)",
            "Verkaufstermin (VK)",
            "verstellbare Lenksäule (vL)",
            "Vollausstattung (Vollausst.)",
            "von Privat (v. Pr.)",
            "von Werksangehörigen (v. WA oder WA)",
            "Vorführwagen (VFW)",
            "wärmedämmendes Glas (WDG)",
            "Wärmeschutzglas (WSG)",
            "Wärmeschutzglas (Wä)",
            "Wegfahrsperre (WS)",
            "Wegfahrsperre (WFS)",
            "Windschott (Cabrio) (Wind)",
            "Winterpaket (Winter)",
            "Winterreifen (Wi.-Rf.)",
            "Xenon-Scheinwerfer (Xenon)",
            "Xenonscheinwerfer (XE)",
            "Zentralverriegelung (ZV)",
            "Zentralverriegelung mit Funkbedienung (ZVFu)",
    };

    public KFZEditor(OKFZS okfzsInstanz, KFZ k) {

        super(okfzsInstanz);
        Vorgang vorgang = null;
        try {
            vorgang = okfzsInstanz.getDatenbank().einVorgang(k);
//            System.out.println(vorgang.getEinkaeufer().getPerson().getPid());
//            Aktion aktion = okfzsInstanz.getDatenbank().eineAktion(k,vorgang.getEinkaeufer().getPerson());
//            System.out.println(aktion.toString());
            //      KFZ DATEN
            JFrame jfKfzEdit = new JFrame("KFZ-Editor");
            JPanel jpKfzWest = new JPanel();
            jpKfzWest.setLayout(new BoxLayout(jpKfzWest, BoxLayout.Y_AXIS));

            JPanel jpKfzAngaben = new JPanel();
            jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
            jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

            JPanel jpTypAngaben = new JPanel();
            jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
            jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

            JPanel jpSonstigeAngaben = new JPanel();
            jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
            jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

            JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlFin = new JLabel("Fin:");
            JTextField jtFin = new JTextField(20);
            jtFin.setText(k.getFin());
            jpFin.add(jlFin);
            jpFin.add(jtFin);

            JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlHersteller = new JLabel("Hersteller:");
            JTextField jtHersteller = new JTextField(20);
            jtHersteller.setText(k.getHersteller());
            jpHersteller.add(jlHersteller);
            jpHersteller.add(jtHersteller);

            JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlModell = new JLabel("Modell:");
            JTextField jtModell = new JTextField(20);
            jtModell.setText(k.getModell());
            jpModell.add(jlModell);
            jpModell.add(jtModell);

            JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
            JTextField jtKfzBriefNr = new JTextField(20);
            jtKfzBriefNr.setText(k.getKfzBriefNr());
            jpKfzBriefNr.add(jlKfzBriefNr);
            jpKfzBriefNr.add(jtKfzBriefNr);

            JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
            JTextField jtLeistungInKw = new JTextField(20);
            jtLeistungInKw.setText(String.valueOf(k.getLeistungInKw()));
            jpLeistungInKw.add(jlLeistungInKw);
            jpLeistungInKw.add(jtLeistungInKw);

            JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlFarbe = new JLabel("Farbe:");
            JTextField jtFarbe = new JTextField(20);
            jtFarbe.setText(k.getFarbe());
            jpFarbe.add(jlFarbe);
            jpFarbe.add(jtFarbe);

            JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEZ = new JLabel("Erstzulassung:");
            JTextField jtEZ = new JTextField(20);
            jtEZ.setText(k.getEz().toString());
            jpEZ.add(jlEZ);
            jpEZ.add(jtEZ);

            JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
            JTextField jtUmweltplakette = new JTextField(20);
            jtUmweltplakette.setText(String.valueOf(k.getUmweltPlakette()));
            jpUmweltplakette.add(jlUmweltplakette);
            jpUmweltplakette.add(jtUmweltplakette);

            JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKraftstoff = new JLabel("Kraftstoff:");
            JTextField jtKraftstoff = new JTextField(20);
            jtKraftstoff.setText(k.getKraftstoff());
            jpKraftstoff.add(jlKraftstoff);
            jpKraftstoff.add(jtKraftstoff);

            JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAktionen = new JLabel("Aktionen:");
            JTextField jtAktionen = new JTextField(20);
//            jtAktionen.setText(aktion.getDurchfuehrender().getName()+" "+aktion.getKfz().getFin()+" "+aktion.getBeschreibung()+" "+aktion.getDurchfuehrung().toString());
            jpAktionen.add(jlAktionen);
            jpAktionen.add(jtAktionen);

            jpKfzAngaben.add(jpFin);
            jpKfzAngaben.add(jpKfzBriefNr);
            jpKfzAngaben.add(jpEZ);
            jpKfzAngaben.add(jpLeistungInKw);

            jpTypAngaben.add(jpHersteller);
            jpTypAngaben.add(jpModell);
            jpTypAngaben.add(jpFarbe);

            jpSonstigeAngaben.add(jpKraftstoff);
            jpSonstigeAngaben.add(jpUmweltplakette);
            jpSonstigeAngaben.add(jpAktionen);

            jpKfzWest.add(jpTypAngaben);
            jpKfzWest.add(jpKfzAngaben);
            jpKfzWest.add(jpSonstigeAngaben);


            //JPanel Center

            JPanel jpKfzCenter = new JPanel();
            jpKfzCenter.setLayout(new BoxLayout(jpKfzCenter, BoxLayout.Y_AXIS));

            JPanel jpVKAngaben = new JPanel();
            jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
            jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

            JPanel jpMerkmale = new JPanel();
            jpMerkmale.setBorder(new TitledBorder("Merkmale"));
            jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

            JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEinkaeufer = new JLabel("Einkäufer:");
            JTextField jtEinkaeufer = new JTextField(20);
//            jtEinkaeufer.setText(vorgang.getEinkaeufer().getAnmeldeName());
            jpEinkaeufer.add(jlEinkaeufer);
            jpEinkaeufer.add(jtEinkaeufer);

            JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEK = new JLabel("Einkaufspreis:");
            JTextField jtEK = new JTextField(20);
//            jtEK.setText(String.valueOf(vorgang.getePreis()));
            jpEK.add(jlEK);
            jpEK.add(jtEK);

            JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
            JTextField jtVKP = new JTextField(20);
//            jtVKP.setText(String.valueOf(vorgang.getvPreisPlan()));
            jpVKP.add(jlVKP);
            jpVKP.add(jtVKP);

            JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlEKDat = new JLabel("Einkaufsdatum:");
            JTextField jtEKDat = new JTextField(20);
//            jtEKDat.setText(vorgang.getEinkaufsDatum().toString());
            jpEKDat.add(jlEKDat);
            jpEKDat.add(jtEKDat);

            JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
            JTextField jtSchaeden = new JTextField(20);
//            jtSchaeden.setText(vorgang.getSchaeden());
            jpSchaeden.add(jlSchaeden);
            jpSchaeden.add(jtSchaeden);

            JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlTuev = new JLabel("TÜV:");
            JTextField jtTuev = new JTextField(20);
//            jtTuev.setText(String.valueOf(vorgang.getTuev()));
            jpTuev.add(jlTuev);
            jpTuev.add(jtTuev);

            JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlKennzeichen = new JLabel("Kennzeichen:");
            JTextField jtKennzeichen = new JTextField(20);
//            jtKennzeichen.setText(vorgang.getKennzeichen());
            jpKennzeichen.add(jlKennzeichen);
            jpKennzeichen.add(jtKennzeichen);

            jpVKAngaben.add(jpEinkaeufer);
            jpVKAngaben.add(jpEKDat);
            jpVKAngaben.add(jpEK);
            jpVKAngaben.add(jpVKP);

            jpMerkmale.add(jpSchaeden);
            jpMerkmale.add(jpTuev);
            jpMerkmale.add(jpKennzeichen);

            jpKfzCenter.add(jpMerkmale);
            jpKfzCenter.add(jpVKAngaben);

//        JPanel East
            JPanel jpKfzEast = new JPanel();
            jpKfzEast.setLayout(new BoxLayout(jpKfzEast, BoxLayout.Y_AXIS));

            JPanel jpSonderausstattung = new JPanel();
            jpSonderausstattung.setBorder(new TitledBorder("Sonderausstattung"));
            jpSonderausstattung.setLayout(new BoxLayout(jpSonderausstattung, BoxLayout.Y_AXIS));

            JPanel jpAutoliste = new JPanel();
            jpAutoliste.setBorder(new TitledBorder("Fahrzeugbestand"));
            jpAutoliste.setLayout(new BoxLayout(jpAutoliste, BoxLayout.Y_AXIS));

            JPanel jpAusstattungTest = new JPanel(new GridLayout(2,1));
            JComponent jList = new JList(testAustattung);
            JScrollPane jsp = new JScrollPane(jList);

            JPanel jpButton = new JPanel();
            JButton jbEdit = new JButton("Sonderausstattung hinzufügen");
            JButton jbSave = new JButton("Speichern");

            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setNeueAustattung();
                }
            };

            jbEdit.addActionListener(al);

            jpAusstattungTest.add(jsp);
            jpButton.add(jbSave);
            jpButton.add(jbEdit);
            jpAusstattungTest.add(jpButton);

            JPanel jpListTest = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JTextArea jtListTest = new JTextArea(20, 20);
            jpListTest.add(jtListTest);

            jpSonderausstattung.add(jpAusstattungTest);
            jpAutoliste.add(jpListTest);

            jpKfzEast.add(jpSonderausstattung);
            jpKfzEast.add(jpAutoliste);


            jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
            jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
            jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);


            //JFrame jf Größe mitgeben
            jfKfzEdit.setSize(1024, 768);


            //JFrame jf auf Bildschirm plazieren
            jfKfzEdit.setLocation(200, 400);

            //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
            jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //JFrame jf anzeigen
            jfKfzEdit.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public KFZEditor(OKFZS okfzsInstanz) {

        super(okfzsInstanz);

//      KFZ DATEN
        JFrame jfKfzEdit = new JFrame("KFZ-Editor");
        JPanel jpKfzWest = new JPanel();
        jpKfzWest.setLayout(new BoxLayout(jpKfzWest, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpTypAngaben = new JPanel();
        jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
        jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFin = new JLabel("Fin:");
        JTextField jtFin = new JTextField(20);
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller:");
        JTextField jtHersteller = new JTextField(20);
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell:");
        JTextField jtModell = new JTextField(20);
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
        JTextField jtKfzBriefNr = new JTextField(20);
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
        JTextField jtLeistungInKw = new JTextField(20);
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe:");
        JTextField jtFarbe = new JTextField(20);
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung:");
        JTextField jtEZ = new JTextField(20);
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
        JTextField jtUmweltplakette = new JTextField(20);
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
        JTextField jtKraftstoff = new JTextField(20);
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextField jtAktionen = new JTextField(20);
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKfzWest.add(jpTypAngaben);
        jpKfzWest.add(jpKfzAngaben);
        jpKfzWest.add(jpSonstigeAngaben);


        //JPanel Center

        JPanel jpKfzCenter = new JPanel();
        jpKfzCenter.setLayout(new BoxLayout(jpKfzCenter, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis:");
        JTextField jtEK = new JTextField(20);
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
        JTextField jtSchaeden = new JTextField(20);
        jpSchaeden.add(jlSchaeden);
        jpSchaeden.add(jtSchaeden);

        JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTuev = new JLabel("TÜV:");
        JTextField jtTuev = new JTextField(20);
        jpTuev.add(jlTuev);
        jpTuev.add(jtTuev);

        JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKennzeichen = new JLabel("Kennzeichen:");
        JTextField jtKennzeichen = new JTextField(20);
        jpKennzeichen.add(jlKennzeichen);
        jpKennzeichen.add(jtKennzeichen);

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);

        jpKfzCenter.add(jpMerkmale);
        jpKfzCenter.add(jpVKAngaben);

//        JPanel East
        JPanel jpKfzEast = new JPanel();
        jpKfzEast.setLayout(new BoxLayout(jpKfzEast, BoxLayout.Y_AXIS));

        JPanel jpSonderausstattung = new JPanel();
        jpSonderausstattung.setBorder(new TitledBorder("Sonderausstattung"));
        jpSonderausstattung.setLayout(new BoxLayout(jpSonderausstattung, BoxLayout.Y_AXIS));

        JPanel jpAutoliste = new JPanel();
        jpAutoliste.setBorder(new TitledBorder("Fahrzeugbestand"));
        jpAutoliste.setLayout(new BoxLayout(jpAutoliste, BoxLayout.Y_AXIS));

        JPanel jpAusstattungTest = new JPanel(new GridLayout(2,1));
        JComponent jList = new JList(testAustattung);
        JScrollPane jsp = new JScrollPane(jList);

        JPanel jpButton = new JPanel();
        JButton jbEdit = new JButton("Sonderausstattung hinzufügen");
        JButton jbSave = new JButton("Speichern");

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNeueAustattung();
            }
        };

        //todo ActionListener der die ausgewählte Ausstattung speichert

        jbEdit.addActionListener(al);

        jpAusstattungTest.add(jsp);
        jpButton.add(jbSave);
        jpButton.add(jbEdit);
        jpAusstattungTest.add(jpButton);

        JPanel jpListTest = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea jtListTest = new JTextArea(20, 20);
        jpListTest.add(jtListTest);

        jpSonderausstattung.add(jpAusstattungTest);
        jpAutoliste.add(jpListTest);

        jpKfzEast.add(jpSonderausstattung);
        jpKfzEast.add(jpAutoliste);


        jfKfzEdit.add(jpKfzWest, BorderLayout.WEST);
        jfKfzEdit.add(jpKfzCenter, BorderLayout.CENTER);
        jfKfzEdit.add(jpKfzEast, BorderLayout.EAST);


        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);

    }

    public KFZEditor(OKFZS okfzsInstanz, Vorgang vorgang) {

        super(okfzsInstanz);

        //      KFZ DATEN

        JFrame jfKfzEdit = new JFrame("KFZ-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("KFZ-Angaben"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpTypAngaben = new JPanel();
        jpTypAngaben.setBorder(new TitledBorder("Typ-Angaben"));
        jpTypAngaben.setLayout(new BoxLayout(jpTypAngaben, BoxLayout.Y_AXIS));

        JPanel jpSonstigeAngaben = new JPanel();
        jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
        jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

        JPanel jpFin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFin = new JLabel("Fin:");
        JTextField jtFin = new JTextField(20);
        jtFin.setText(vorgang.getKfz().getFin());
        jpFin.add(jlFin);
        jpFin.add(jtFin);

        JPanel jpHersteller = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlHersteller = new JLabel("Hersteller:");
        JTextField jtHersteller = new JTextField(20);
        jtHersteller.setText(vorgang.getKfz().getHersteller());
        jpHersteller.add(jlHersteller);
        jpHersteller.add(jtHersteller);

        JPanel jpModell = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlModell = new JLabel("Modell:");
        JTextField jtModell = new JTextField(20);
        jtModell.setText(vorgang.getKfz().getModell());
        jpModell.add(jlModell);
        jpModell.add(jtModell);

        JPanel jpKfzBriefNr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKfzBriefNr = new JLabel("KFZ-Brief-Nr.:");
        JTextField jtKfzBriefNr = new JTextField(20);
        jtKfzBriefNr.setText(vorgang.getKfz().getKfzBriefNr());
        jpKfzBriefNr.add(jlKfzBriefNr);
        jpKfzBriefNr.add(jtKfzBriefNr);

        JPanel jpLeistungInKw = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlLeistungInKw = new JLabel("Leistung in KW:");
        JTextField jtLeistungInKw = new JTextField(20);
        jtLeistungInKw.setText(String.valueOf(vorgang.getKfz().getLeistungInKw()));
        jpLeistungInKw.add(jlLeistungInKw);
        jpLeistungInKw.add(jtLeistungInKw);

        JPanel jpFarbe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlFarbe = new JLabel("Farbe:");
        JTextField jtFarbe = new JTextField(20);
        jtFarbe.setText(vorgang.getKfz().getFarbe());
        jpFarbe.add(jlFarbe);
        jpFarbe.add(jtFarbe);

        JPanel jpEZ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEZ = new JLabel("Erstzulassung:");
        JTextField jtEZ = new JTextField(20);
        jtEZ.setText(String.valueOf(vorgang.getKfz().getEz()));
        jpEZ.add(jlEZ);
        jpEZ.add(jtEZ);

        JPanel jpUmweltplakette = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlUmweltplakette = new JLabel("Umweltplakette:");
        JTextField jtUmweltplakette = new JTextField(20);
        jtUmweltplakette.setText(String.valueOf(vorgang.getKfz().getUmweltPlakette()));
        jpUmweltplakette.add(jlUmweltplakette);
        jpUmweltplakette.add(jtUmweltplakette);

        JPanel jpKraftstoff = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKraftstoff = new JLabel("Kraftstoff:");
        JTextField jtKraftstoff = new JTextField(20);
        jtKraftstoff.setText(vorgang.getKfz().getKraftstoff());
        jpKraftstoff.add(jlKraftstoff);
        jpKraftstoff.add(jtKraftstoff);

        JPanel jpAktionen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlAktionen = new JLabel("Aktionen:");
        JTextField jtAktionen = new JTextField(20);
        jtAktionen.setText(String.valueOf(vorgang.getKfz().getAktionen()));
        jpAktionen.add(jlAktionen);
        jpAktionen.add(jtAktionen);

        JPanel jpSonderausstattungen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSonderausstattungen = new JLabel("Sonderausstattung:");
        JTextField jtSonderausstattungen = new JTextField(20);
        String[] ausstattung = {"Leder", "Navigation", "PDC", "Xenon", "Tempomat"};
        JComboBox jcAusstattungListe = new JComboBox(ausstattung);
        jtSonderausstattungen.setText(String.valueOf(vorgang.getKfz().getSonderausstattung()));
        jcAusstattungListe.setSelectedIndex(4);
        jcAusstattungListe.setEditable(true);

        jpSonderausstattungen.add(jlSonderausstattungen);
        jpSonderausstattungen.add(jcAusstattungListe);

        jpKfzAngaben.add(jpFin);
        jpKfzAngaben.add(jpKfzBriefNr);
        jpKfzAngaben.add(jpEZ);
        jpKfzAngaben.add(jpLeistungInKw);

        jpTypAngaben.add(jpHersteller);
        jpTypAngaben.add(jpModell);
        jpTypAngaben.add(jpFarbe);
        jpTypAngaben.add(jpSonderausstattungen);

        jpSonstigeAngaben.add(jpKraftstoff);
        jpSonstigeAngaben.add(jpUmweltplakette);
        jpSonstigeAngaben.add(jpAktionen);

        jpKFZ.add(jpTypAngaben);
        jpKFZ.add(jpKfzAngaben);
        jpKFZ.add(jpSonstigeAngaben);


        //Vorgang

        JPanel jpVorgang = new JPanel();
        jpVorgang.setLayout(new BoxLayout(jpVorgang, BoxLayout.Y_AXIS));

        JPanel jpVKAngaben = new JPanel();
        jpVKAngaben.setBorder(new TitledBorder("Verkaufsangaben"));
        jpVKAngaben.setLayout(new BoxLayout(jpVKAngaben, BoxLayout.Y_AXIS));

        JPanel jpMerkmale = new JPanel();
        jpMerkmale.setBorder(new TitledBorder("Merkmale"));
        jpMerkmale.setLayout(new BoxLayout(jpMerkmale, BoxLayout.Y_AXIS));

        JPanel jpEinkaeufer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEinkaeufer = new JLabel("Einkäufer:");
        JTextField jtEinkaeufer = new JTextField(20);
        jtEinkaeufer.setText(String.valueOf(vorgang.getEinkaeufer()));
        jpEinkaeufer.add(jlEinkaeufer);
        jpEinkaeufer.add(jtEinkaeufer);

        JPanel jpEK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEK = new JLabel("Einkaufspreis:");
        JTextField jtEK = new JTextField(20);
        jtEK.setText(String.valueOf(vorgang.getePreis()));
        jpEK.add(jlEK);
        jpEK.add(jtEK);

        JPanel jpVKP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlVKP = new JLabel("geplanter Verkaufspreis:");
        JTextField jtVKP = new JTextField(20);
        jtVKP.setText(String.valueOf(vorgang.getvPreisPlan()));
        jpVKP.add(jlVKP);
        jpVKP.add(jtVKP);

        JPanel jpEKDat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlEKDat = new JLabel("Einkaufsdatum:");
        JTextField jtEKDat = new JTextField(20);
        jtEKDat.setText(String.valueOf(vorgang.getEinkaufsDatum()));
        jpEKDat.add(jlEKDat);
        jpEKDat.add(jtEKDat);

        JPanel jpSchaeden = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlSchaeden = new JLabel("Bekannte Schäden:");
        JTextField jtSchaeden = new JTextField(20);
        jtSchaeden.setText(vorgang.getSchaeden());
        jpSchaeden.add(jlSchaeden);
        jpSchaeden.add(jtSchaeden);

        JPanel jpTuev = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlTuev = new JLabel("TÜV:");
        JTextField jtTuev = new JTextField(20);
        jtTuev.setText(String.valueOf(vorgang.getTuev()));
        jpTuev.add(jlTuev);
        jpTuev.add(jtTuev);

        JPanel jpKennzeichen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlKennzeichen = new JLabel("Kennzeichen:");
        JTextField jtKennzeichen = new JTextField(20);
        jtKennzeichen.setText(vorgang.getKennzeichen());
        jpKennzeichen.add(jlKennzeichen);
        jpKennzeichen.add(jtKennzeichen);

        jpVKAngaben.add(jpEinkaeufer);
        jpVKAngaben.add(jpEKDat);
        jpVKAngaben.add(jpEK);
        jpVKAngaben.add(jpVKP);

        jpMerkmale.add(jpSchaeden);
        jpMerkmale.add(jpTuev);
        jpMerkmale.add(jpKennzeichen);


        jpVorgang.add(jpMerkmale);
        jpVorgang.add(jpVKAngaben);

        jfKfzEdit.add(jpKFZ, BorderLayout.WEST);
        jfKfzEdit.add(jpVorgang, BorderLayout.EAST);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(1024, 768);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);
    }

    public String getKFZ(String kfz) {
        return kfz;
    }

    public void setNeueAustattung(){
        JFrame jfKfzEdit = new JFrame("Ausstattungs-Editor");
        JPanel jpKFZ = new JPanel();
        jpKFZ.setLayout(new BoxLayout(jpKFZ, BoxLayout.Y_AXIS));

        JPanel jpKfzAngaben = new JPanel();
        jpKfzAngaben.setBorder(new TitledBorder("Ausstattung hinzufügen"));
        jpKfzAngaben.setLayout(new BoxLayout(jpKfzAngaben, BoxLayout.Y_AXIS));

        JPanel jpAustattungHinzu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField jtFin = new JTextField(20);
        jpAustattungHinzu.add(jtFin);

        JButton jbSave = new JButton("Speichern");
        jpAustattungHinzu.add(jbSave);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo Ausstattung in DB speichern
            }
        };

        jpKfzAngaben.add(jpAustattungHinzu);
        jpKFZ.add(jpKfzAngaben);
        jfKfzEdit.add(jpKFZ, BorderLayout.CENTER);

        //JFrame jf Größe mitgeben
        jfKfzEdit.setSize(300, 200);


        //JFrame jf auf Bildschirm plazieren
        jfKfzEdit.setLocation(200, 400);

        //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind
        jfKfzEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JFrame jf anzeigen
        jfKfzEdit.setVisible(true);

    }
}

