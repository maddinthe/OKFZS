package GUI;

import Datenhaltung.Vorgang;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: stub
public class KFZListe extends Ansicht {

    //todo:doku und weiter bauen
    public KFZListe(OKFZS okfzsInstanz,List<Vorgang> vorgaenge){
        super(okfzsInstanz);

        String[] thead={"Hersteller","Modell", "Verkaufspreis","Einkaufsdatum"};

        String[][] data={
                {"Nissan", "Micra Cool","9.500,00€","03.04.16"},
                {"VW","Golf Tour","19.500,00€","02.04.16"},
                {"Toyota","Aygo Basic","6.800,00€","02.04.16"},
                {"Skoda","Octavia Elegance","18.520,00€","01.04.16"}
        };
        JTable tabelle= new JTable(data,thead);

        this.add(new JScrollPane(tabelle));

        JPanel quickInfo=new JPanel();

        JTextArea quickInfoText=new JTextArea(Arrays.toString((tabelle.getSelectedRow()<-1)?data[tabelle.getSelectedRow()]:data[0]));
        quickInfoText.setPreferredSize(new Dimension(900,50));
        quickInfo.add(new JScrollPane(quickInfoText));

        this.add(quickInfo);

        //todo: noch an das datenmodell anpassen
        tabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                quickInfoText.setText(Arrays.toString(data[((DefaultListSelectionModel)e.getSource()).getMinSelectionIndex()]));
            }
        });



    }


    //todo: noch einbauen
    private void sortieren(String spalte,String richtung){

    }
}
