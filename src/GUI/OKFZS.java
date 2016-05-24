package GUI;

import Datenbank.Datenbank;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mtheilen on 23.05.2016.
 */
//todo: noch nicht fertig
public class OKFZS extends JFrame {
    private Datenbank datenbank;
    private Map<String,Ansicht> ansichten;
    private Verkaeufer benutzer;
    private int hoehe =786;
    private int breite =1024;
    private JPanel anzeige;
    private CardLayout cards;

    //todo: stub
    public OKFZS(){
        super();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //todo: schließende sachen tuen;
                System.exit(0);
            }
        });
        //einlesen der OKFZ.cfg
        Map<String,String> config=new HashMap<>();
        try(BufferedReader br=new BufferedReader(new FileReader("OKFZS.cfg"))){
            String read;
            while ((read=br.readLine())!=null){
                String[] temp=read.split(":");
                config.put(temp[0],temp[1]);
            }
        }catch (IOException e){/**nichts tun falls nicht da**/}
        //einlesen der Config beendet

        //holen der DB-Instanz
        try {
            String host=config.get("dbhost");
            String port=config.get("dbport");
            datenbank=Datenbank.getInstance((host!=null)?host:"localhost",(port!=null)?Integer.parseInt(port):5432);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //db anteil fürs erste ende
        cards=new CardLayout();
        anzeige=new JPanel(cards);

        //nutzer anmelden
        Anmeldung a=new Anmeldung(this);

        String anmeldungID="Anmeldung";
        anzeige.add(a, anmeldungID);
        cards.show(anzeige,anmeldungID);
        //anmeldung ende

        setTitle("Ostsee KFZ Service");
        setSize(breite, hoehe);
        setJMenuBar(new Menue(this));
        add(anzeige);

        setVisible(true);
    }


    public Datenbank getDatenbank() {
        return datenbank;
    }

    public Map<String, Ansicht> getAnsichten() {
        return ansichten;
    }

    public Verkaeufer getBenutzer() {
        return benutzer;
    }

    public int getHoehe() {
        return hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public void setBenutzer(Verkaeufer benutzer) {
        this.benutzer = benutzer;
    }

    public void anzeigen(String ziel){
        switch (ziel){
            case "uebersicht":{
                List<Vorgang> vorgangList=null;
                try{vorgangList=datenbank.VorgaengeZuVerkaeufer(datenbank.einVerkaufer(5));}
                catch (SQLException e){

                }
                anzeige.add(new Uebersicht(this,vorgangList),"uebersicht");
                cards.show(anzeige,"uebersicht");
            }
        }
        System.out.println(ziel);
    }

    //todo:doku und evtl weitere details
    public static void main(String[] args) {
        new OKFZS();

    }
}
