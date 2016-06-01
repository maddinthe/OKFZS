package GUI;

import Datenbank.Datenbank;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mtheilen on 23.05.2016.
 * Klasse zum Anzeigen des Menüs
 * @author Martin Theilen
 */
public class Menue extends JMenuBar {
    /**
     * Instanz von OKFZS in der das menü angezeigt wird
     */
    private OKFZS okfzsInstanz;
    /**
     * Speicher zur leichteren anzeige der Ensprechenden Menüansichten
     */
    private Map<String, JMenuItem[]> menues = new HashMap<>();

    /**
     * Legt das Menü für die Übergebene OKFZS instanz an
     * @param okfzsInstanz OKFZS instanz inder das Menü angezeigt wird
     */
    public Menue(OKFZS okfzsInstanz) {
        super();
        this.okfzsInstanz = okfzsInstanz;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "Auto suchen":
                        okfzsInstanz.anzeigen("sucheKFZ");
                        break;
                    case"Person suchen":
                        okfzsInstanz.anzeigen("suchePers");
                        break;

                    case "Beenden": {
                        okfzsInstanz.anzeigen("ende");
                        break;
                    }
                    case "Übersicht anzeigen": {
                        okfzsInstanz.anzeigen("uebersicht");
                        break;
                    }
                    case "Autos anzeigen": {
                        okfzsInstanz.anzeigen("autoAnz");
                        break;
                    }
                    case "Auto ändern":{
                        okfzsInstanz.anzeigen("autoAend");
                        break;
                    }
                    case "Auto anlegen": {
                        okfzsInstanz.anzeigen("autoAnl");
                        break;
                    }
                    case "Auto verkaufen":{
                        okfzsInstanz.anzeigen("autoVerk");
                        break;
                    }
                    case "Personen anzeigen": {
                        okfzsInstanz.anzeigen("personAnz");
                        break;
                    }
                    case "Person anlegen": {
                        okfzsInstanz.anzeigen("personAnl");
                        break;
                    }
                    case "Person ändern": {
                        okfzsInstanz.anzeigen("personAend");
                        break;
                    }
                    case "Statistik anzeigen": {
                        okfzsInstanz.anzeigen("statstik");
                        break;
                    }
                    case "Hilfe": {
                        okfzsInstanz.anzeigen("hilfe");
                        break;
                    }
                }
            }
        };
        JMenu ubersicht = new JMenu("Übersicht");
        JMenuItem uebersichAnz = new JMenuItem("Übersicht anzeigen");
        uebersichAnz.addActionListener(al);
        ubersicht.add(uebersichAnz);

        JMenuItem end = new JMenuItem("Beenden");
        end.addActionListener(al);
        ubersicht.add(end);

        JMenu autos = new JMenu("Autos");
        JMenuItem autoAnz = new JMenuItem("Autos anzeigen");
        autoAnz.addActionListener(al);
        autos.add(autoAnz);
        JMenuItem autoAnl = new JMenuItem("Auto anlegen");
        autoAnl.addActionListener(al);
        autos.add(autoAnl);
        JMenuItem autoAendern = new JMenuItem("Auto ändern");
        autoAendern.addActionListener(al);
        JMenuItem sucheKFZ=new JMenuItem("Auto suchen");
        sucheKFZ.addActionListener(al);
        autos.add(sucheKFZ);


        autos.add(autoAendern);
        JMenuItem autoVerkaufen = new JMenuItem("Auto verkaufen");
        autoVerkaufen.addActionListener(al);
        autos.add(autoVerkaufen);


        JMenu pers = new JMenu("Personen");
        JMenuItem persAnz = new JMenuItem("Personen anzeigen");
        persAnz.addActionListener(al);
        pers.add(persAnz);
        JMenuItem persAnl = new JMenuItem("Person anlegen");
        persAnl.addActionListener(al);
        pers.add(persAnl);
        JMenuItem persAendern = new JMenuItem("Person ändern");
        persAendern.addActionListener(al);
        persAendern.setEnabled(false);
        pers.add(persAendern);
        JMenuItem suchePers=new JMenuItem("Person suchen");
        suchePers.addActionListener(al);
        pers.add(suchePers);


        JMenu stats = new JMenu("Statistik");
        JMenuItem statsAnz = new JMenuItem("Statistik anzeigen");
        statsAnz.addActionListener(al);
        stats.add(statsAnz);


        JMenu frageZeichen = new JMenu("?");
        JMenuItem help = new JMenuItem("Hilfe");
        help.addActionListener(al);
        frageZeichen.add(help);


        this.add(ubersicht);
        this.add(autos);
        this.add(pers);
        this.add(stats);
        this.add(frageZeichen);

        menues.put("immerAn", new JMenuItem[]{ help, end});
        menues.put("uebersicht", new JMenuItem[]{uebersichAnz,  help, end , statsAnz, autoAnz, autoAnl, persAnz, persAnl,sucheKFZ,suchePers});
        menues.put("autolist", new JMenuItem[]{sucheKFZ,suchePers,uebersichAnz,  help, end,  statsAnz, autoAnz, autoAnl, persAnz, persAnl, autoAendern, autoVerkaufen});
        menues.put("perslist", new JMenuItem[]{sucheKFZ,suchePers,uebersichAnz,  help, end,  statsAnz, autoAnz, autoAnl, persAnz, persAnl, persAendern});
        menues.put("alle", new JMenuItem[]{sucheKFZ,suchePers, help, end,  statsAnz, autoAnz, autoAnl, persAnz, persAnl, persAendern, autoAendern, autoVerkaufen, uebersichAnz});

        menueUmschalten("anmeldung");

    }

    /**
     * Lässt die entsprechenden Menüs aktiv/inaktiv werden
     * @param cardID Mögliche Werte["anmeldung","ende","uebersicht","impexp","autoAnz","autoAnl","personAnz","personAnl","statstik","ueber","hilfe"]
     */
    public void menueUmschalten(String cardID) {
        for (JMenuItem jmi : menues.get("alle")) {
            jmi.setEnabled(false);
        }
        switch (cardID) {
            case "uebersicht": {
                for (JMenuItem jmi : menues.get("uebersicht")) {
                    jmi.setEnabled(true);
                }
                break;
            }

            case "autoAnz": {
                for (JMenuItem jmi : menues.get("autolist")) {
                    jmi.setEnabled(true);
                }
                break;
            }

            case "personAnz": {
                for (JMenuItem jmi : menues.get("perslist")) {
                    jmi.setEnabled(true);
                }
                break;
            }
            case "impexp":
            case "autoAnl":
            case "autoAend":
            case "personAnl":
            case "personAend":
            case "autoVerk":
            case "statstik":
            case "ueber":
            case "suchePers":
            case "sucheKFZ":
            case "hilfe": {
                for (JMenuItem jmi : menues.get("uebersicht")) {
                    jmi.setEnabled(true);
                }
                break;
            }
            default:
                System.out.println(cardID);
        }

        for (JMenuItem jmi : menues.get("immerAn")) {
            jmi.setEnabled(true);
        }

    }
}
