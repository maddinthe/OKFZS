package GUI;


import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 */
public class Statistik extends Ansicht {
    private List<Vorgang> vorgaenge;

    public Statistik(OKFZS okfzsInstanz, List<Vorgang> vorgangList) {
        super(okfzsInstanz);
        vorgaenge = vorgangList;
        JTextArea jta = new JTextArea(30, 30);
        double gewinn = 0.0;
        for (Vorgang v : vorgangList) {
            gewinn += v.getGewinn(okfzsInstanz.getBenutzer());
        }
        jta.setText("Gewinn von " + okfzsInstanz.getBenutzer().getPerson().getVorname() + ": " + gewinn);

        this.add(jta);


    }

    public void einzelVerkaeuferStatistik() {

    }

    public void gesamtVerkaeuferStatistik() {


    }

}
