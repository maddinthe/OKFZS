package GUI;

import javax.swing.*;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo:Stub
public class Menue extends JMenuBar{
    public Menue(List<Ansicht> menueItems){
        JMenu ubersicht=new JMenu("Übersicht");
        JMenuItem uebersichAnz=new JMenuItem("Übersicht anzeigen");
        ubersicht.add(uebersichAnz);
        JMenuItem impExp=new JMenuItem("Import/Export");
        ubersicht.add(impExp);
        JMenuItem end=new JMenuItem("Beenden");
        ubersicht.add(end);

        JMenu autos=new JMenu("Autos");
        JMenuItem autoAnz=new JMenuItem("Autos anzeigen");
        autos.add(autoAnz);
        JMenuItem autoAnl=new JMenuItem("Auto anlegen");
        autos.add(autoAnl);
        JMenuItem autoAendern=new JMenuItem("Auto ändern");
        autoAendern.setEnabled(false);
        autos.add(autoAendern);
        JMenuItem autoVerkaufen=new JMenuItem("Auto Verkaufen");
        autoVerkaufen.setEnabled(false);
        autos.add(autoVerkaufen);


        JMenu pers=new JMenu("Personen");
        JMenuItem persAnz=new JMenuItem("Personen anzeigen");
        pers.add(persAnz);
        JMenuItem persAnl=new JMenuItem("Person anlegen");
        pers.add(persAnl);
        JMenuItem persAendern=new JMenuItem("Person ändern");
        persAendern.setEnabled(false);
        pers.add(persAendern);


        JMenu stats=new JMenu("Statistik");
        JMenuItem statsAnz=new JMenuItem("Statistik Anzeigen");
        stats.add(statsAnz);


        JMenu frageZeichen=new JMenu("?");
        JMenuItem ueber=new JMenuItem("Über");
        frageZeichen.add(ueber);
        JMenuItem help=new JMenuItem("Hilfe");
        frageZeichen.add(help);


        this.add(ubersicht);
        this.add(autos);
        this.add(pers);
        this.add(stats);
        this.add(frageZeichen);



    }
}
