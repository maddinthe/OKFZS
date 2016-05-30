package testwiese;

import Datenbank.Datenbank;
import Datenhaltung.Erreichbarkeit;
import javax.swing.*;

import java.awt.*;
import java.sql.SQLException;

import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
public class MaddinTest {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Spieldaten ende


        JFrame test = new JFrame("OKFZS-TEST-Maddin");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setSize(1024, 786);


        Datenbank db=Datenbank.getInstance("localhost", 5432);

        List<Erreichbarkeit> notizs=db.alleErreichbarkeiten(db.einePerson(6));

        JList<Erreichbarkeit> notizJList = new JList<>(notizs.toArray(new Erreichbarkeit[notizs.size()]));
        notizJList.setCellRenderer(new ErrListRenderer());

        test.add(notizJList);



        test.setVisible(true);






    }
  static class ErrListRenderer extends JLabel implements ListCellRenderer<Erreichbarkeit>{

      @Override
      public Component getListCellRendererComponent(JList<? extends Erreichbarkeit> list, Erreichbarkeit value, int index, boolean isSelected, boolean cellHasFocus) {

          setText("<html>"+value.getDetails()+"<br>"
                  +"Telefon: "+value.getTelefonNummer()+"<br>"
                  +"Handy: "+value.getHandyNummer()+"<br>"
                  +"E-Mail: "+value.getEmail()+"</html>");

          if (isSelected) {
              setBackground(Color.white);
              setForeground(Color.BLUE);
          } else {
              setBackground(Color.white);
              setForeground(Color.black);
          }
          return this;
      }
  }
}
