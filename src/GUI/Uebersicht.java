package GUI;

import Datenhaltung.Vorgang;

import javax.swing.*;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: stub
public class Uebersicht extends Ansicht {

    //todo: unfertig und doku
    public Uebersicht(OKFZS okfzsInstanz,List<Vorgang> vorgaenge){
        super(okfzsInstanz);

        String[][] td={
                {"Meyer, Hans","Nissan Micra Cool","9.500,00€","03.05.16"},
                {"Müller, Ingo","VW Golf Tour","19.500,00€","02.05.16"},
                {"Michael, Rudi","Toyota Aygo Basic","6.800,00€","02.05.16"},
                {"Schmidt, Dennis","Skoda Octavia Elegance","18.520,00€","01.05.16"}
        };
        String[] head={"Käufer","Auto","Preis","Verkaufsdatum"};


        JTable tabelle=new JTable(td,head);
        tabelle.setFont(tabelle.getFont().deriveFont(20));

        this.add(new JScrollPane(tabelle));





    }
}
