package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo:doku und hinterlegen der listener das die entsprechenden ansichten geladen werden
public class Menue extends JMenuBar{
    public Menue(OKFZS okfzsInstanz){
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





    }
}
