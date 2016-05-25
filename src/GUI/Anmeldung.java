package GUI;

import Datenhaltung.Verkaeufer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by cdreher on 23.05.2016.
 */
public class Anmeldung extends Ansicht {

    public Anmeldung(OKFZS instanz) {
        super(instanz);
        JLabel nameLabel = new JLabel("Benutzername:");
        JTextField name = new JTextField(30);
        JLabel passwortLabel = new JLabel("Passwort:");
        JPasswordField passwort=new JPasswordField(30);
        JButton anmeldebutton = new JButton("Anmelden");

        ActionListener al=new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().length() == 0 || passwort.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Bitte Benutzername und Passwort eingeben", "Anmeldefehler", JOptionPane.ERROR_MESSAGE);
                } else {
                    Verkaeufer v = anmelden(name.getText(), String.copyValueOf(passwort.getPassword()).hashCode() + "");

                    if (v == null) {
                        JOptionPane.showMessageDialog(null, "Benutzername und/oder Passwort falsch", "Anmeldefehler", JOptionPane.ERROR_MESSAGE);
                    } else {
                        instanz.setBenutzer(v);
                        getOKFZSInstanz().anzeigen("uebersicht");
                    }
                }
            }
        };

        passwort.addActionListener(al);
        anmeldebutton.addActionListener(al);

        this.add(nameLabel);
        this.add(name);
        this.add(passwortLabel);
        this.add(passwort);
        this.add(anmeldebutton);


    }

    //todo: datenbank abfrage für benutzer einbauen
    public Verkaeufer anmelden(String name, String passwortHash) {
        System.out.println(passwortHash);
        Verkaeufer v = null;
        try {
            v = getOKFZSInstanz().getDatenbank().einVerkaufer(name);
            if (passwortHash.equals(v.getPasswortHash())) {
                System.out.println(passwortHash);
                return v;}
        } catch (SQLException e) {

        }
        return null;
    }
}
