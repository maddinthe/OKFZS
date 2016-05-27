package Datenhaltung;

import javax.swing.*;

/**
 * Created by tkertz on 27.05.2016.
 */
public class AusstatungsListModel extends DefaultListModel{

    public Object getElementAt(int index) {
        Sonderausstattung highscore = (Sonderausstattung) super.getElementAt(index);
        return highscore.getBeschreibung();
    }


    public void addElement(Object obj) {
        if(!this.contains(obj))
        {
            int i=0;

            while(i<this.size()&&((Sonderausstattung)this.get(i)).getSid()
                    <=((Sonderausstattung)obj).getSid()){
                i++;
            }

            this.add(i, obj);
        }
    }
}
