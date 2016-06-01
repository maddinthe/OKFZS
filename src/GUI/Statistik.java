package GUI;


import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mtheilen
 * Created by tkertz on 23.05.2016.
 */

/**
 * Die Klasse die Für das Anzeigen der Statistik zuständig ist
 */
public class Statistik extends Ansicht {
    /**
     * Konstruktor für eine Statistik zu einem Einzelnen verkäufer (ruft nur statistikEinzel() auf)
     * @param okfzsInstanz Die OKFZS Instanz in der die Statistik angezeigt werden soll
     * @param verkaeufer Der Verkäufer für den die Statistik generiert werden soll
     */
    public Statistik(OKFZS okfzsInstanz,Verkaeufer verkaeufer){
        super(okfzsInstanz);
        statistikEinzel(verkaeufer);
    }

    /**
     * Der Konstruktor der die Statistik anzeigt, wenn der Angemeldete nutzer ein admin ist zeigt er eine liste ansonsten zeigt er eine Einzelsatistik
     * @param okfzsInstanz die OKFZS instanz in der die statistik angezeigt wird
     */
    public Statistik(OKFZS okfzsInstanz) {
        super(okfzsInstanz);
        if(!okfzsInstanz.getBenutzer().istAdmin()){
            statistikEinzel(okfzsInstanz.getBenutzer());
        }else{
            try {
                List<Verkaeufer> verkaeufer = okfzsInstanz.getDatenbank().alleVerkaeufer();


            String[] thead = {"Verkäufer", "Verkaufte Autos", "Gewinn in €", "Gekaufte Autos", "VK ID"};
            String[][] data = new String[verkaeufer.size()][5];
            for (int i = 0; i < verkaeufer.size(); i++) {
                Verkaeufer v = verkaeufer.get(i);
                double[] stats = getStats(v);
                data[i][0] = v.getPerson().getName() + ", " + v.getPerson().getVorname();
                data[i][1] = "" + (int) stats[1];
                data[i][2] = String.format("%.2f", stats[0]);
                data[i][3] = "" + (int) stats[2];
                data[i][4] = "" + v.getPerson().getPid();
            }


            JTable tabelle = new JTable(data, thead);
            tabelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int row = ((JTable) e.getSource()).getSelectedRow();
                    okfzsInstanz.anzeigen(new Statistik(okfzsInstanz,verkaeufer.get(row)));
                }
            });
            this.add(new JScrollPane(tabelle));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Generiert die Einzelstatistik für einen Verkäufer (Aus dem Konstruktor zur einfacheit ausgelagert)
     * @param verkaeufer Verkäufer für den die Statistik zu erstellen ist
     */
    private void statistikEinzel(Verkaeufer verkaeufer) {

        this.setLayout(new BorderLayout());
        JTextField name = new JTextField();
        name.setText("Statistik für " + verkaeufer.getPerson().getVorname() + " " + verkaeufer.getPerson().getName());
        this.add(name, BorderLayout.NORTH);


        double[] stats = getStats(verkaeufer);
        double gewinn = stats[0];
        int verkAutos = (int) stats[1];
        int gekAutos = (int) stats[2];


        JPanel jpStats = new JPanel(new GridLayout(3, 1));

        JPanel jpVerkAuto = new JPanel();
        JLabel jlVerkAuto = new JLabel("Verkaufte Autos:");
        jpVerkAuto.add(jlVerkAuto);
        JTextField jtfVerkAuto = new JTextField(verkAutos + "", 5);
        jpVerkAuto.add(jtfVerkAuto);
        jpStats.add(jpVerkAuto);

        JPanel jpGewinn = new JPanel();
        JLabel jlGewinn = new JLabel("Gewinn:");
        JTextField jtfGewinn = new JTextField(String.format("%.2f €", gewinn), 10);
        jpGewinn.add(jlGewinn);
        jpGewinn.add(jtfGewinn);
        jpStats.add(jpGewinn);

        JPanel jpGekAuto = new JPanel();
        JLabel jlGekAuto = new JLabel("Angekaufte Autos:");
        JTextField jtfGekAuto = new JTextField(gekAutos + "", 5);
        jpGekAuto.add(jlGekAuto);
        jpGekAuto.add(jtfGekAuto);
        jpStats.add(jpGekAuto);


        this.add(jpStats, BorderLayout.CENTER);

    }

    /**
     * Gibt die Statistikdaten für den Übergebenen verkäufer zurück
     * @param verkaeufer Verkäufer für den die Statistik berechnet werden soll
     * @return double[] mit den pos 0=Gewinn, 1=verkaufte Autos, 2=gekaufte Autos
     */
    private double[] getStats(Verkaeufer verkaeufer) {
        List<Vorgang> vorgaenge = new ArrayList<>();
        try {
            vorgaenge = getOKFZSInstanz().getDatenbank().VorgaengeZuVerkaeufer(verkaeufer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double gewinn = 0;
        double verkAutos = 0;
        double gekAutos = 0;
        for (Vorgang v : vorgaenge) {
            if (verkaeufer.equals(v.getEinkaeufer())) {
                gekAutos++;
            }
            if (verkaeufer.equals(v.getVerkaeufer())) {
                verkAutos++;
            }
            gewinn += v.getGewinn(verkaeufer);
        }

        return new double[]{gewinn, verkAutos, gekAutos};
    }

}
