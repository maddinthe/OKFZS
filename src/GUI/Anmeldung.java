package GUI;

import Datenhaltung.Verkaeufer;

import javax.swing.*;

/**
 * Created by cdreher on 23.05.2016.
 */
public class Anmeldung extends Ansicht {

    public Anmeldung(OKFZS instanz) {
        super(instanz);
        JLabel nameLabel=new JLabel("Benutzername:");
        JTextField name=new JTextField(30);
        JLabel passwortLabel=new JLabel("Passwort:");
        JTextField passwort=new JTextField(30);

        this.add(nameLabel);
        this.add(name);
        this.add(passwortLabel);
        this.add(passwort);




    }

    public Verkaeufer anmelden(String name,String passwort){
        return null;
    }
}
