package GUI;

import Datenhaltung.Vorgang;
import GUI.Werkzeug.KFZEditor;
import GUI.Werkzeug.KaufvertragEditor;
import GUI.Werkzeug.PersonenEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: stub
public class Uebersicht extends Ansicht {

    //todo: unfertig und doku
    public Uebersicht(OKFZS okfzsInstanz, List<Vorgang> vorgaenge) {
        super(okfzsInstanz);
        this.setLayout(new BorderLayout());
        JTextArea welkom = new JTextArea();
        welkom.setText("Wilkommen " + okfzsInstanz.getBenutzer().getPerson().getAnrede() + " " + okfzsInstanz.getBenutzer().getPerson().getName());
        this.add(welkom,BorderLayout.NORTH);
        String[][] td = new String[vorgaenge.size()][4];
        for (int i = 0; i < vorgaenge.size(); i++) {
            Vorgang v = vorgaenge.get(i);
            td[i][0] = v.getKauefer().getName() + ", " + v.getKauefer().getVorname();
            td[i][1] = v.getKfz().getHersteller() + ", " + v.getKfz().getModell();
            td[i][2] = v.getvPreis() + " €";
            td[i][3] = (v.getVerkaufsDatum() != null) ? v.getVerkaufsDatum().toString() : "";
        }
        String[] head = {"Käufer", "Auto", "Preis", "Verkaufsdatum"};

        JTable tabelle = new JTable(td, head);
        tabelle.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        tabelle.setFont(tabelle.getFont().deriveFont(20));
        tabelle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = ((JTable) e.getSource()).getSelectedRow();
                int col = ((JTable) e.getSource()).getSelectedColumn();
                if (col == 0) okfzsInstanz.anzeigen(new PersonenEditor(okfzsInstanz,vorgaenge.get(row).getKauefer()));
                else if (col == 1) okfzsInstanz.anzeigen(new KFZEditor(okfzsInstanz, vorgaenge.get(row)));
                else okfzsInstanz.anzeigen(new KaufvertragEditor(okfzsInstanz,vorgaenge.get(row)));
            }
        });
        this.add(new JScrollPane(tabelle),BorderLayout.CENTER);


    }
}
