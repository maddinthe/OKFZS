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
 */
//todo:doku und hinterlegen der listener das die entsprechenden ansichten geladen werden
public class Menue extends JMenuBar{
    private OKFZS okfzsInstanz;
    private Map<String,JMenuItem[]> menues=new HashMap<>();

    public Menue(OKFZS okfzsInstanz){
        super();
        this.okfzsInstanz=okfzsInstanz;
        ActionListener al=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()){
                    case "Beenden":{
                        okfzsInstanz.anzeigen("ende");
                        break;
                    }
                    case"Übersicht anzeigen":{
                        okfzsInstanz.anzeigen("uebersicht");
                        break;
                    }
                    case"Import/Export":{
                        okfzsInstanz.anzeigen("impexp");
                        break;
                    }
                    case"Autos anzeigen":{
                        okfzsInstanz.anzeigen("autoAnz");
                        break;
                    }
                    case"Auto anlegen":{
                        okfzsInstanz.anzeigen("autoAnl");
                        break;
                    }
                    case"Personen anzeigen":{
                        okfzsInstanz.anzeigen("personAnz");
                        break;
                    }
                    case"Person anlegen":{
                        okfzsInstanz.anzeigen("personAnl");
                        break;
                    }
                    case"Statistik Anzeigen":{
                        okfzsInstanz.anzeigen("statstik");
                        break;
                    }
                    case"Über":{
                        okfzsInstanz.anzeigen("ueber");
                        break;
                    }
                    case"Hilfe":{
                        okfzsInstanz.anzeigen("hilfe");
                        break;
                    }
                }
            }
        };
        JMenu ubersicht=new JMenu("Übersicht");
        JMenuItem uebersichAnz=new JMenuItem("Übersicht anzeigen");
        uebersichAnz.addActionListener(al);
        ubersicht.add(uebersichAnz);
        JMenuItem impExp=new JMenuItem("Import/Export");
        impExp.addActionListener(al);
        ubersicht.add(impExp);
        JMenuItem end=new JMenuItem("Beenden");
        end.addActionListener(al);
        ubersicht.add(end);

        JMenu autos=new JMenu("Autos");
        JMenuItem autoAnz=new JMenuItem("Autos anzeigen");
        autoAnz.addActionListener(al);
        autos.add(autoAnz);
        JMenuItem autoAnl=new JMenuItem("Auto anlegen");
        autoAnl.addActionListener(al);
        autos.add(autoAnl);
        JMenuItem autoAendern=new JMenuItem("Auto ändern");
        autoAendern.addActionListener(al);

        autos.add(autoAendern);
        JMenuItem autoVerkaufen=new JMenuItem("Auto Verkaufen");
        autoVerkaufen.addActionListener(al);
        autos.add(autoVerkaufen);


        JMenu pers=new JMenu("Personen");
        JMenuItem persAnz=new JMenuItem("Personen anzeigen");
        persAnz.addActionListener(al);
        pers.add(persAnz);
        JMenuItem persAnl=new JMenuItem("Person anlegen");
        persAnl.addActionListener(al);
        pers.add(persAnl);
        JMenuItem persAendern=new JMenuItem("Person ändern");
        persAendern.addActionListener(al);
        persAendern.setEnabled(false);
        pers.add(persAendern);


        JMenu stats=new JMenu("Statistik");
        JMenuItem statsAnz=new JMenuItem("Statistik Anzeigen");
        statsAnz.addActionListener(al);
        stats.add(statsAnz);


        JMenu frageZeichen=new JMenu("?");
        JMenuItem ueber=new JMenuItem("Über");
        ueber.addActionListener(al);
        frageZeichen.add(ueber);
        JMenuItem help=new JMenuItem("Hilfe");
        help.addActionListener(al);
        frageZeichen.add(help);


        this.add(ubersicht);
        this.add(autos);
        this.add(pers);
        this.add(stats);
        this.add(frageZeichen);

        menues.put("immerAn", new JMenuItem[]{ueber, help, end});
        menues.put("uebersicht", new JMenuItem[]{uebersichAnz,ueber, help, end, impExp, statsAnz, autoAnz, autoAnl, persAnz, persAnl});
        menues.put("autolist",new JMenuItem[]{uebersichAnz,ueber,help,end,impExp,statsAnz,autoAnz,autoAnl,persAnz,persAnl,autoAendern,autoVerkaufen});
        menues.put("perslist",new JMenuItem[]{uebersichAnz,ueber,help,end,impExp,statsAnz,autoAnz,autoAnl,persAnz,persAnl,persAendern});
        menues.put("alle",new JMenuItem[]{ueber,help,end,impExp,statsAnz,autoAnz,autoAnl,persAnz,persAnl,persAendern,autoAendern,autoVerkaufen,uebersichAnz});

        menueUmschalten("anmeldung");

    }

    /**
     * Lässt die entsprechenden Menüs aktiv/inaktiv werden
     *
     * @param cardID Mögliche Werte["anmeldung","ende","uebersicht","impexp","autoAnz","autoAnl","personAnz","personAnl","statstik","ueber","hilfe"]
     */
    public void menueUmschalten(String cardID){
        for(JMenuItem jmi:menues.get("alle")){
            jmi.setEnabled(false);
        }
        switch (cardID) {
            case "uebersicht": {
                for(JMenuItem jmi:menues.get("uebersicht")){
                    jmi.setEnabled(true);
                }
                break;
            }
            case "impexp": {
                break;
            }
            case "autoAnz": {
                for(JMenuItem jmi:menues.get("autolist")){
                    jmi.setEnabled(true);
                }
                break;
            }
            case "autoAnl": {
                break;
            }
            case "personAnz": {
                for(JMenuItem jmi:menues.get("perslist")){
                    jmi.setEnabled(true);
                }
                break;
            }
            case "personAnl": {
                break;
            }
            case "statstik": {
                break;
            }
            case "ueber": {
                break;
            }
            case "hilfe":{
                break;
            }
            default:
                System.out.println(cardID);
        }

        for(JMenuItem jmi:menues.get("immerAn")){
            jmi.setEnabled(true);
        }

    }
}
