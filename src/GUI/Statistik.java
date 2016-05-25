package GUI;


import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tkertz on 23.05.2016.
 */
public class Statistik extends Ansicht {

    private Person person;
    private Vorgang vorgang;
    private Verkaeufer verkaeufer;

    public Statistik(List<Vorgang> vorgangList, OKFZS okfzsInstanz) {
        super(okfzsInstanz);
        try {
            okfzsInstanz.getDatenbank().VorgaengeZuVerkaeufer(okfzsInstanz.getDatenbank().einVerkaufer(okfzsInstanz.getBenutzer().getPerson().getPid()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void einzelVerkaeuferStatistik(OKFZS okfzsInstanz) {
        try {
            okfzsInstanz.getDatenbank().statistikGewinnVerkaeufer(okfzsInstanz.getBenutzer());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gesamtVerkaeuferStatistik() {


    }

}
