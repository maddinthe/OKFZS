package GUI;

import Datenhaltung.Vorgang;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: stub
public class Uebersicht extends Ansicht {

    //todo: unfertig und doku
    public Uebersicht(OKFZS okfzsInstanz, List<Vorgang> vorgaenge) {
        super(okfzsInstanz);

        JTextArea welkom=new JTextArea();
        welkom.setText("Wilkommen "+okfzsInstanz.getBenutzer().getPerson().getAnrede()+" "+okfzsInstanz.getBenutzer().getPerson().getName());
        this.add(welkom);
        String[][] td = new String[vorgaenge.size()][4];
        for (int i = 0; i < vorgaenge.size(); i++) {
            Vorgang v = vorgaenge.get(i);
            td[i][0] = v.getKauefer().getName() + ", " + v.getKauefer().getVorname();
            td[i][1] = v.getKfz().getHersteller() + ", " + v.getKfz().getModell();
            td[i][2] = v.getvPreis() + " €";
            td[i][3] = v.getVerkaufsDatum().toString();
        }
        String[] head = {"Käufer", "Auto", "Preis", "Verkaufsdatum"};

        JTable tabelle = new JTable(td, head);
        tabelle.setAutoCreateRowSorter(true);
        tabelle.setFont(tabelle.getFont().deriveFont(20));

        this.add(new JScrollPane(tabelle));


    }
}
