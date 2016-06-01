package GUI;

import Datenhaltung.KFZ;
import Datenhaltung.Vorgang;
import GUI.Werkzeug.KFZEditor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mtheilen on 23.05.2016.
 * @author Martin Theilen
 */
public class KFZListe extends Ansicht {
    /**
     * Der aktuell ausgewählte vorgang
     */
    private Vorgang selectedVorg;
    /**
     * das Aktuell ausgewählte KFZ
     */
    private KFZ selectedKFZ;

    /**
     * Bring eine liste von autos oder vorgängen als Autoliste zur Anzeige
     * @param okfzsInstanz Die OKFZS instanz inder die Liste angezeigt werden soll
     * @param items Muss eine Liste von KFZ oder Vorgang sein
     */
    public KFZListe(OKFZS okfzsInstanz, List<? extends Object> items) {
        super(new BorderLayout(), okfzsInstanz);
        if (items.get(0).getClass().equals(Vorgang.class)) {
            List<Vorgang> vorgaenge = items.getClass().cast(items);
            Map<String, Vorgang> finVorgaenge = new TreeMap<>();
            String[] thead = {"FIN", "Hersteller", "Modell", "Ankaufspreis", "Einkaufsdatum","Kraftstoff","Leistung"};
            String[][] data = new String[vorgaenge.size()][8];
            for (int i = 0; i < vorgaenge.size(); i++) {
                Vorgang v = vorgaenge.get(i);
                finVorgaenge.put(v.getKfz().getFin(), v);
                data[i][0] = v.getKfz().getFin();
                data[i][1] = v.getKfz().getHersteller();
                data[i][2] = v.getKfz().getModell();
                data[i][3] = v.getePreis() + " €";
                data[i][4] = v.getEinkaufsDatum().toString();
                data[i][5] = v.getKfz().getKraftstoff();
                data[i][6] = v.getKfz().getLeistungInKw()+" KW";
            }


            JTable tabelle = new JTable(data, thead);
            tabelle.setAutoCreateRowSorter(true);
            this.add(new JScrollPane(tabelle));


            JTextArea quickInfo = new JTextArea(vorgaenge.get(0).toString());
            quickInfo.setLineWrap(true);
            quickInfo.setWrapStyleWord(true);
            tabelle.addRowSelectionInterval(0, 0);
            selectedVorg = vorgaenge.get(0);
            this.add(BorderLayout.SOUTH, new JScrollPane(quickInfo));


            tabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    selectedVorg = finVorgaenge.get(tabelle.getValueAt(tabelle.getSelectedRow(), 0));
                    quickInfo.setText(selectedVorg.toString());
                }
            });
        }else if(items.get(0).getClass().equals(KFZ.class)){
            List<KFZ> kfzs = items.getClass().cast(items);
            Map<String, KFZ> finKFZ = new TreeMap<>();
            String[] thead = {"FIN", "Hersteller", "Modell", "Erstzulassung", "Farbe"};
            String[][] data = new String[kfzs.size()][5];
            for (int i = 0; i < kfzs.size(); i++) {
                KFZ k = kfzs.get(i);
                finKFZ.put(k.getFin(), k);
                data[i][0] = k.getFin();
                data[i][1] = k.getHersteller();
                data[i][2] = k.getModell();
                data[i][3] = k.getEz().toString();
                data[i][4] = k.getFarbe();
            }


            JTable tabelle = new JTable(data, thead);
            tabelle.setAutoCreateRowSorter(true);
            this.add(new JScrollPane(tabelle));


            JTextArea quickInfo = new JTextArea(kfzs.get(0).toString());
            quickInfo.setLineWrap(true);
            quickInfo.setWrapStyleWord(true);
            tabelle.addRowSelectionInterval(0, 0);
            selectedKFZ = kfzs.get(0);
            this.add(BorderLayout.SOUTH, new JScrollPane(quickInfo));

            tabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    selectedKFZ = finKFZ.get(tabelle.getValueAt(tabelle.getSelectedRow(), 0));
                    quickInfo.setText(selectedKFZ.toString());
                }
            });
        }
    }

    /**
     * Gibt den aktuell angewählten Vorgang zurück;
     * @return Aktuell ausgewählter Vorgang oder NULL wenn keiner Ausgewählt ist
     */
    public Vorgang getSelectedVorg() {
        return selectedVorg;
    }

    /**
     * gibt das Aktuell ausgewählte KFZ zurück
     * @return Aktuell Ausgewähltes KFZ oder NULL wenn keins ausgewählt ist
     */
    public KFZ getSelectedKFZ(){return selectedKFZ;}
}
