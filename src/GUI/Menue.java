package GUI;

import Datenbank.Datenbank;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo:doku und hinterlegen der listener das die entsprechenden ansichten geladen werden
public class Menue extends JMenuBar{
    private OKFZS okfzsInstanz;
    private JMenu[] alleMenues;
    private JMenu[] autoMenues;
    private JMenu[] persMenues;
    public Menue(OKFZS okfzsInstanz){
        super();
        this.okfzsInstanz=okfzsInstanz;
        ActionListener al=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()){
                    case "Beenden":{
                        anzeigen("ende");
                        break;
                    }
                    case"Übersicht anzeigen":{
                        anzeigen("uebersicht");
                        break;
                    }
                    case"Import/Export":{
                        anzeigen("impexp");
                        break;
                    }
                    case"Autos anzeigen":{
                        anzeigen("autoAnz");
                        break;
                    }
                    case"Auto anlegen":{
                        anzeigen("autoAnl");
                        break;
                    }
                    case"Personen anzeigen":{
                        anzeigen("personAnz");
                        break;
                    }
                    case"Person anlegen":{
                        anzeigen("personAnl");
                        break;
                    }
                    case"Statistik Anzeigen":{
                        anzeigen("statstik");
                        break;
                    }
                    case"Über":{
                        anzeigen("ueber");
                        break;
                    }
                    case"Hilfe":{
                        anzeigen("hilfe");
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
        autoAendern.setEnabled(false);
        autos.add(autoAendern);
        JMenuItem autoVerkaufen=new JMenuItem("Auto Verkaufen");
        autoVerkaufen.addActionListener(al);
        autoVerkaufen.setEnabled(false);
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
        alleMenues=new JMenu[]{ubersicht,autos,pers,stats};
        autoMenues=new JMenu[]{ubersicht,autos,stats};
        persMenues=new JMenu[]{ubersicht,pers,stats};

        inaktivSchalten(alleMenues);





    }

    public void anzeigen(String ziel){
        Verkaeufer benutzer=okfzsInstanz.getBenutzer();
        Datenbank datenbank=okfzsInstanz.getDatenbank();
        JPanel anzeige=okfzsInstanz.getAnzeige();
        CardLayout cards=okfzsInstanz.getCards();

        switch (ziel){
            case "uebersicht":{
                List<Vorgang> vorgangList=null;
                try{vorgangList=datenbank.VorgaengeZuVerkaeufer(benutzer);}
                catch (SQLException e){
                }
                anzeige.add(new Uebersicht(okfzsInstanz, vorgangList), "uebersicht");
                cards.show(anzeige, "uebersicht");
                aktivSchalten(alleMenues);
            }
        }
        System.out.println(ziel);
    }

    private void inaktivSchalten(JMenu[] menus){
        for(JMenu jm:menus){
            jm.setEnabled(false);
        }
    }
    private void aktivSchalten(JMenu[] menus){
        for(JMenu jm:menus){
            jm.setEnabled(true);
        }
    }

}
