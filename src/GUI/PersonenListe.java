package GUI;

import Datenhaltung.Person;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mtheilen on 23.05.2016.
 * Personenliste sorgt für eine Anzeige von einer Liste aus Personen
 * @author Martin Theilen
 */
public class PersonenListe extends Ansicht {
    /**
     * Die Person die in der Liste ausgewählt ist
     */
    private Person selectedPers;

    /**
     * Grundkonstruktor der die Übergebene Liste von Personen ind der OKFZS instanz zur anzeige bringen kann
     * @param okfzsInstanz OKFZS in der diese Liste angezeigt wird
     * @param personen Person liste die Anzuzeigen ist
     */
    public PersonenListe(@NotNull OKFZS okfzsInstanz,@NotNull List<Person> personen) {
        super(new BorderLayout(), okfzsInstanz);
        Map<String, Person> personMap = new TreeMap<>();

        String[] thead = {"PID", "Anrede", "Name", "Vorname", "Plz", "Ort"};
        String[][] tdata = new String[personen.size()][6];
        for (int i = 0; i < personen.size(); i++) {
            Person p = personen.get(i);
            personMap.put(p.getPid() + "", p);
            tdata[i][0] = p.getPid() + "";
            tdata[i][1] = p.getAnrede();
            tdata[i][2] = p.getName();
            tdata[i][3] = (p.getVorname() != null) ? p.getVorname() : "";
            tdata[i][4] = (p.getPostleitzahl() != 0) ? p.getPostleitzahl() + "" : "";
            tdata[i][5] = (p.getOrt() != null) ? p.getOrt() : "";
        }

        JTable tabelle = new JTable(tdata, thead);
        this.add(new JScrollPane(tabelle));
        tabelle.setAutoCreateRowSorter(true);
        tabelle.addRowSelectionInterval(0, 0);


        selectedPers = personen.get(0);
        JTextArea quickInfo = new JTextArea(selectedPers.toString(), 5, 100);

        this.add(BorderLayout.SOUTH, quickInfo);

        tabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedPers = personMap.get(tabelle.getValueAt(tabelle.getSelectedRow(), 0));
                quickInfo.setText(selectedPers.toString());
            }
        });


    }

    /**
     * Gibt die Aktuell ausgewählte person zurück
     * @return Person die ausgewählt wurde oder NULL wenn keine ausgewählt wurde
     */
    public Person getSelectedPers() {
        return selectedPers;
    }
}
