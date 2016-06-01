package GUI;

import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * by mtheilen on 01.06.2016
 */
public class Anmeldung extends Ansicht {

    public Anmeldung(OKFZS instanz) {
        super(instanz);

        if(instanz.getDatenbank().adminDa()) {


            JLabel nameLabel = new JLabel("Benutzername:");
            JTextField name = new JTextField(30);
            JLabel passwortLabel = new JLabel("Passwort:");
            JPasswordField passwort = new JPasswordField(30);
            JButton anmeldebutton = new JButton("Anmelden");

            ActionListener al = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (name.getText().length() == 0 || passwort.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(null, "Bitte Benutzername und Passwort eingeben", "Anmeldefehler", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Verkaeufer v = anmelden(name.getText(), String.copyValueOf(passwort.getPassword()).hashCode() + "");
                        if(String.copyValueOf(passwort.getPassword()).equals("password")){
                            JPanel pwpanel = new JPanel();
                            JLabel pwlabel = new JLabel("Neues passwort Eingeben:");
                            JPasswordField pass = new JPasswordField(10);
                            pwpanel.add(pwlabel);
                            pwpanel.add(pass);
                            String[] optionen = new String[]{"OK"};
                            JOptionPane.showOptionDialog(getOKFZSInstanz(), pwpanel, "The title",
                                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, optionen, optionen[0]);
                            String passwort=String.copyValueOf(pass.getPassword());
                            v.setPasswortHash(""+passwort.hashCode());
                            try {
                                getOKFZSInstanz().getDatenbank().insertOrUpdateVerkaeufer(v);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }

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
        else{
            this.add(new JLabel("Neuer Benutzer"));
            this.add(new JLabel("Anrede"));
            JComboBox<String> anrede=new JComboBox<>(new String[]{"Herr","Frau"});
            this.add(anrede);
            this.add(new JLabel("Name"));
            JTextField name=new JTextField(30);
            this.add(name);
            this.add(new JLabel("Geburtstag"));
            JTextField geburtstag=new JTextField(10);
            this.add(geburtstag);
            this.add(new JLabel("Anmelde Name"));
            JTextField anmeldeName=new JTextField(30);
            this.add(anmeldeName);
            this.add(new JLabel("Passwort"));
            JPasswordField passwort=new JPasswordField(30);
            this.add(passwort);
            this.add(new JLabel("Passwort Bestätigen"));
            JPasswordField paswortBest=new JPasswordField(30);
            this.add(paswortBest);
            JButton absenden=new JButton("Absenden");
            absenden.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(anrede.getSelectedItem()!=null&&!name.getText().equals("")&&!geburtstag.getText().equals("")&&!anmeldeName.getText().equals("")){
                        System.out.println(String.copyValueOf(passwort.getPassword()));
                        System.out.println(String.copyValueOf(paswortBest.getPassword()));
                        if ((String.copyValueOf(passwort.getPassword()).hashCode()==String.copyValueOf(paswortBest.getPassword()).hashCode())&&passwort.getPassword().length>=4){
                            try {
                                SimpleDateFormat sdf=new SimpleDateFormat("DD.MM.YYYY");
                                Person p=getOKFZSInstanz().getDatenbank().insertPerson(new Person((String)anrede.getSelectedItem(),name.getText(),sdf.parse(geburtstag.getText())));
                                Verkaeufer v=getOKFZSInstanz().getDatenbank().insertVerkaeufer(new Verkaeufer(anmeldeName.getText(),String.copyValueOf(passwort.getPassword()).hashCode()+"",p,true,true));
                                getOKFZSInstanz().getDatenbank().insertOrUpdateAdmins(v);
                                getOKFZSInstanz().setBenutzer(v);
                                getOKFZSInstanz().anzeigen("uebersicht");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }

                        }else{
                            JOptionPane.showMessageDialog(getOKFZSInstanz(),"Die Passwörter Stimmen nicht überein oder sind zu kurz(4 Zeichen)","Passwortfehler",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(getOKFZSInstanz(),"Bitte füllen sie alle Felder aus","Nicht komplett...",JOptionPane.ERROR_MESSAGE);

                    }
                }
            });
            this.add(absenden);



        }

    }

    /**
     * Überprüft ob ein Nutzer mit Anmeldenam name und Passwort-Hash passwortHash vorhanden ist und liefert ihn aus der DB zurück
     * @param name  Der Anmeldename des gewollten Nutzers
     * @param passwortHash Der PasswortHash des gewollten Nutzers
     * @return VERKAEUFER Der Gewollte Nutzer oder NULL wenn PW falsch/Nutzer nicht Vorhanden
     */
    private Verkaeufer anmelden(@NotNull String name,@NotNull String passwortHash) {
        System.out.println(passwortHash);
        Verkaeufer v;
        try {
            v = getOKFZSInstanz().getDatenbank().einVerkaufer(name);
            if (v==null)return null;
            if (passwortHash.equals(v.getPasswortHash())) {
                System.out.println(passwortHash);
                return v;}
        } catch (SQLException e) {

        }
        return null;
    }
}
