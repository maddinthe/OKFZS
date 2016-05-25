package GUI;


import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

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
        okfzsInstanz.getDatenbank().VorgaengeZuVerkaeufer(okfzsInstanz.getDatenbank().einVerkaufer(okfzsInstanz.getBenutzer().getPerson().getPid()));

    }

    public void einzelVerkaeuferStatistik(OKFZS okfzsInstanz2) {

        okfzsInstanz.getDatenbank().VorgaengeZuVerkaeufer(okfzsInstanz.getDatenbank().einVerkaufer(okfzsInstanz.getBenutzer().getPerson().getPid()));
    }

    public void gesamtVerkaeuferStatistik() {


    }

    public static void main(String[] args) {

    }
}
