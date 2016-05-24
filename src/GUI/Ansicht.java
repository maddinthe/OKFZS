package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo:stub
public class Ansicht extends JPanel{
    private OKFZS instanz;


    /**
     * Legt die Ansicht an
     * @param instanz die Instanz von OKFZ in der diese Ansicht angezeigt werden soll
     */
    public Ansicht(OKFZS instanz){
        super();
        this.instanz=instanz;
    }

    public Ansicht(LayoutManager layout, OKFZS instanz) {
        super(layout);
        this.instanz = instanz;
    }

    public OKFZS getOKFZSInstanz() {
        return instanz;
    }
}
