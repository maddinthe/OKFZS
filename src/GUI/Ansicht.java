package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mtheilen on 23.05.2016.
 * Die Oberklasse Ansicht ist dafür da damit in OKFZS nur Ansichten verwaltet werden müssen statt X unterschiedliche Klassen von JPanlels
 * @author Martin Theilen
 */
public class Ansicht extends JPanel{
    /**
     * instanz von OKFZS in der diese Ansicht angezeigt werden soll
     */
    private OKFZS instanz;


    /**
     * Legt die Ansicht an
     * @param instanz die Instanz von OKFZ in der diese Ansicht angezeigt werden soll
     */
    public Ansicht(OKFZS instanz){
        super();
        this.instanz=instanz;
    }

    /**
     * Legt die Ansicht an
     * @param instanz die Instanz von OKFZ in der diese Ansicht angezeigt werden soll
     * @param layout das zu verwendende Layout
     */
    public Ansicht(LayoutManager layout, OKFZS instanz) {
        super(layout);
        this.instanz = instanz;
    }

    /**
     * liefert die OKFZS Instanz zurück in der diese ansicht ausgeführt wird
     * @return OKFZS Instanz in der diese ansicht ausgeführt wird
     */
    public OKFZS getOKFZSInstanz() {
        return instanz;
    }
}
