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
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "The title",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            System.out.println("Your password is: " + new String(password));
        }
    }
}
