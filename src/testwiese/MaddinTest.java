package testwiese;

import Datenhaltung.KFZ;
import Datenhaltung.Vorgang;
import GUI.Menue;
import GUI.OKFZS;
import GUI.Uebersicht;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by mtheilen on 23.05.2016.
 */
public class MaddinTest {
    public static void main(String[] args) {




        JFrame test=new JFrame("OKFZS-TEST-Maddin");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setSize(1024, 786);
        test.setJMenuBar(new Menue(null));
        test.add(new Uebersicht(null,new LinkedList<Vorgang>()));

        test.setVisible(true);

    }
}
