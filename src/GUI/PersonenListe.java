package GUI;

import Datenhaltung.Person;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo:unfertig
public class PersonenListe extends Ansicht {

    //todo: fertigstellen und doku
    public PersonenListe(OKFZS okfzsInstanz,List<Person> personen){
        super(new BorderLayout(),okfzsInstanz);
        Map<Long,Person> personMap=new TreeMap<>();

        String[] thead={"PID","Anrede","Name","Vorname","Plz","Ort"};
        String[][] tdata=new String[personen.size()][6];
        for(int i=0;i<personen.size();i++){
            Person p=personen.get(i);
            personMap.put(p.getPid(),p);
            tdata[i][0]=p.getPid()+"";
            tdata[i][1]=p.getAnrede();
            tdata[i][2]=p.getName();
            tdata[i][3]=(p.getVorname()!=null)?p.getVorname():"";
            tdata[i][4]=(p.getPostleitzahl()!=0)?p.getPostleitzahl()+"":"";
            tdata[i][5]=(p.getOrt()!=null)?p.getOrt():"";
        }

        JTable tabelle=new JTable(tdata,thead);
        tabelle.setAutoCreateRowSorter(true);
        tabelle.addRowSelectionInterval(0, 0);
        this.add(new JScrollPane(tabelle));

        JTextArea quickInfo=new JTextArea(personen.get(0).toString());

        this.add(BorderLayout.SOUTH,quickInfo);

        tabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                quickInfo.setText(personMap.get(tabelle.getValueAt(tabelle.getSelectedRow(),0)).toString());
            }
        });

    }

}
