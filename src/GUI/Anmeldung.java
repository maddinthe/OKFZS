package GUI;

import Datenhaltung.Verkaeufer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton anmeldebutton=new JButton("Anmelden");

        anmeldebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.getText().length()==0||passwort.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"Bitte Benutzername und Passwort eingeben","Anmeldefehler",JOptionPane.ERROR_MESSAGE);
                }else{
                    Verkaeufer v=anmelden(name.getText(),passwort.getText().hashCode()+"");
                    if(v==null){
                        JOptionPane.showMessageDialog(null,"Benutzername und/oder Passwort falsch","Anmeldefehler",JOptionPane.ERROR_MESSAGE);
                    }else{
                        instanz.setBenutzer(v);
                        instanz.anzeigen("uebersicht");
                    }
                }
            }
        });

        this.add(nameLabel);
        this.add(name);
        this.add(passwortLabel);
        this.add(passwort);
        this.add(anmeldebutton);




    }

    //todo: datenbank abfrage für benutzer einbauen
    public Verkaeufer anmelden(String name,String passwortHash)
    {
        //Verkaeufer v=getOKFZSInstanz().getDatenbank().getVerkaufer(name);
        Verkaeufer v=new Verkaeufer(name,"testpw".hashCode()+"",null,true,true);
        if (v.getPasswortHash().equals(passwortHash))
        return v;
        else return null;
    }
}
