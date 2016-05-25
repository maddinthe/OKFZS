package Datenbank;

import Datenhaltung.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Datenbank {
    private static Datenbank datenbank;
    private static Connection conn;
    private Datenbank() {
    }
    public static void schließen(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Datenbank getInstance(String host, int port) throws ClassNotFoundException, SQLException {
        if (datenbank == null) {
            Class.forName("org.postgresql.Driver");
            datenbank = new Datenbank();
        }
        boolean renew = conn == null;
        if (!renew)
            try {
                if (conn.isClosed()) ;
                renew = true;
            } catch (SQLException e) {
                renew = true;
            }

        if (renew) {

            String database = "db_okfzs";

            try {
                Socket socket = new Socket(host, port);
                socket.close();
            } catch (IOException e) {
                throw new SQLException("Server nicht erreichbar!", "08001", e);
            }

            String url = "jdbc:postgresql://" + host + ":" + port + "/";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");

            try {
                conn = DriverManager.getConnection(url, props);
                conn.close();
            } catch (SQLException e) {
                throw new SQLException("Zugriff verweigert", e.getSQLState(), e);
            }

            try {
                conn = DriverManager.getConnection(url + database, props);

            } catch (SQLException e) {
                throw new SQLException("Datenbank existiert nicht", e.getSQLState(), e);
            }

        }
        return datenbank;
    }
    public static Datenbank getInstance(String database, String host,int port) throws SQLException{


        String url = "jdbc:postgresql://"+host+":"+port+"/";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","root");

        try{
            conn = DriverManager.getConnection(url,props);
            conn.createStatement().executeUpdate("CREATE DATABASE"+database);
            conn.close();
        }catch (SQLException e){
            throw  new SQLException("Zugriff verweigert", e.getSQLState(), e);
        }
        try{
            getInstance(host,port);
        }catch (ClassNotFoundException e){ }

        einlesenScript();

        return datenbank;
    }

    private static void einlesenScript() throws SQLException{
        try(BufferedReader br = new BufferedReader(new FileReader( Datenbank.class.getResource("dblaeuft.sql").getFile()))){
            String sqlInstruction = "";
            String zeile;
            while ((zeile=br.readLine())!=null){
                zeile = zeile.split("--")[0];
                sqlInstruction +=zeile+" ";
                if (sqlInstruction.trim().endsWith(";")){
                    Statement stmt = conn.createStatement();
                    stmt.execute(sqlInstruction);
                    sqlInstruction="";
                }

            }

        }catch (IOException e){ }

    }

    /**
     * Diese Methode löscht eine Tabelle, falls sie
     * existiert, andernfalls tut sie nichts.
     *
     * @param tablename Der Name der Tabelle, die gelöscht werden soll.
     * @throws SQLException Wenn beim Erstellen der Verbindung ein Fehler passiert.
     */
    public void dropIfExists(String tablename) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("DROP TABLE " + tablename);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("42P01"))
                e.printStackTrace();
        }

    }




    public void insertPerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
        stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,gebTag) VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','"+person.getGeburtstag()+"')");
         } catch (SQLException e) {
         //stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',name='"+person.getName()+"' WHERE pid=" + person.getPid() + "");
            System.out.println(e.getMessage());
        }

    }
    public void insertOrUpdatePerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                    " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','"+ person.getVorname()+"','"+ person.getGeburtstag()+"','"+person.getAnschrift()+"',"+person.getPostleitzahl()+",'"+person.getOrt()+"','"+person.getUstID()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',vorname='" + person.getVorname() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");

        }
    }
    public void insertOrUpdatePersonAdmin(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                    " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','"+ person.getVorname()+"','"+ person.getGeburtstag()+"','"+person.getAnschrift()+"',"+person.getPostleitzahl()+",'"+person.getOrt()+"','"+person.getUstID()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',name='" + person.getName() + "',vorname='" + person.getVorname() + "',gebtag='" + person.getGeburtstag() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");

        }
    }
    public void insertOrUpdateErreichbarkeit(Erreichbarkeit erreichbarkeit) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
        stmt.executeUpdate("INSERT  INTO t_erreichbarkeit(fk_t_person_pid,tel,handy,email,text) VALUES ("+erreichbarkeit.getPerson().getPid()+",'" + erreichbarkeit.getTelefonNummer() + "', '" + erreichbarkeit.getHandyNummer() + "','"+erreichbarkeit.getEmail()+"','"+erreichbarkeit.getDetails()+"')");
         } catch (SQLException e) {
         stmt.executeUpdate("UPDATE t_erreichbarkeit SET fk_t_person_pid="+erreichbarkeit.getPerson().getPid()+", tel='" + erreichbarkeit.getTelefonNummer() + "',handy='"+erreichbarkeit.getHandyNummer()+"',email='"+erreichbarkeit.getEmail()+"',text='"+erreichbarkeit.getDetails()+"' WHERE eid='" + erreichbarkeit.getEid() + "'");
        }
    }
    public void insertOrUpdateNotiz(Notiz notiz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_notiz(fk_t_person_pid,text,datum) VALUES ("+notiz.getPerson().getPid()+",'" + notiz.getBeschreibung() + "', '" + notiz.getDatum() +"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_notiz SET fk_t_person_pid="+notiz.getPerson().getPid()+", text='" + notiz.getBeschreibung() + "',datum='"+notiz.getDatum()+"' WHERE nid=" + notiz.getNid() + "");
        }
    }
    public void insertOrUpdateVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if(verkaeufer.istAktiv())
            stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort) VALUES ("+verkaeufer.getPerson().getPid()+",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() +"')");
            else
                stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "','"+new Date()+"')");
        } catch (SQLException e) {
            if(verkaeufer.istAktiv())
            stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid="+verkaeufer.getPerson().getPid()+", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='"+verkaeufer.getPasswortHash()+"' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
            else
                stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid="+verkaeufer.getPerson().getPid()+", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='"+verkaeufer.getPasswortHash()+"', inaktivseit='"+new Date()+"' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
        }
    }
    public void insertOrUpdateAdmins(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if(verkaeufer.istAktiv())
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_person_pid,anmeldename,passwort) VALUES ("+verkaeufer.getPerson().getPid()+",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() +"')");
            else
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "','"+new Date()+"')");
        } catch (SQLException e) {
            if(verkaeufer.istAktiv())
                stmt.executeUpdate("UPDATE t_admins SET fk_t_person_pid="+verkaeufer.getPerson().getPid()+", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='"+verkaeufer.getPasswortHash()+"' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
            else
                stmt.executeUpdate("UPDATE t_admins SET fk_t_person_pid="+verkaeufer.getPerson().getPid()+", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='"+verkaeufer.getPasswortHash()+"', inaktivseit='"+new Date()+"' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
        }
    }
    public void insertOrUpdateKfz(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_kfz(fin,hersteller,modell,kfz_brief,leistung,farbe,ez,plakette,kraftstoff) VALUES ('"+kfz.getFin()+"','" + kfz.getHersteller() + "', '" + kfz.getModell() +"','"+kfz.getKfzBriefNr()+"',"+kfz.getLeistungInKw()+",'"+kfz.getFarbe()+"','"+kfz.getEz()+"','"+kfz.getUmweltPlakette()+"','"+kfz.getKraftstoff()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_kfz SET fin='" + kfz.getFin() + "',hersteller='"+kfz.getHersteller()+"',modell='"+kfz.getModell()+"',kfz_brief='"+kfz.getKfzBriefNr()+"',leistung="+kfz.getLeistungInKw()+",farbe='"+kfz.getFarbe()+"',ez='"+kfz.getEz()+"', plakette='"+kfz.getUmweltPlakette()+"',kraftstoff='"+kfz.getKraftstoff()+"'WHERE fin='" + kfz.getFin() + "'");

        }
    }
    public void insertOrUpdateAktion(Aktion aktion) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_aktion(fk_t_person_pid,fk_t_kfz_fin,datum,text) VALUES ("+aktion.getDurchfuehrender().getPid()+",'" + aktion.getKfz().getFin() + "', '" + aktion.getDurchfuehrung() + "','"+aktion.getBeschreibung()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_aktion SET fk_t_person_pid='"+aktion.getDurchfuehrender().getPid()+"' fk_t_kfz_fin='" + aktion.getKfz().getFin() + "',datum='"+aktion.getDurchfuehrung()+"',text='"+aktion.getBeschreibung()+"' WHERE fk_t_person_pid=" + aktion.getDurchfuehrender().getPid() + " AND fk_t_kfz_fin='"+aktion.getKfz().getFin()+"'");
        }
    }
    public void insertOrUpdateSonderausstattung(Sonderausstattung sonderausstattung) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_sonderausstattung(art) VALUES ('" + sonderausstattung.getBeschreibung()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_sonderausstattung SET art='"+sonderausstattung.getBeschreibung()+"' WHERE sid=" + sonderausstattung.getSid()+"");
        }
    }
    public void insertOrUpdateVorgang(Vorgang vorgang) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_vorgang(fk_t_person_pid,fk_t_verkaeufer_pid_ek,fk_t_verkaeufer_pid_vk,fk_t_kfz_fin,epreis,vpreis,km,schaeden,vkdatum,ekdatum,kennz,rabattgrund,tuev,sonstvereinb,vpreisplan) VALUES ("+vorgang.getKauefer().getPid()+"," + vorgang.getEinkaeufer().getPerson().getPid() + ", " + vorgang.getVerkaeufer().getPerson().getPid() + ",'"+vorgang.getKfz().getFin()+"',"+vorgang.getePreis()+","+vorgang.getvPreis()+","+vorgang.getKilometer()+",'"+vorgang.getSchaeden()+"','"+vorgang.getVerkaufsDatum()+"','"+vorgang.getEinkaufsDatum()+"','"+vorgang.getKennzeichen()+"','"+vorgang.getRabattGrund()+"',"+vorgang.getTuev()+",'"+vorgang.getSonstvereinbarungen()+"',"+vorgang.getvPreisPlan()+")");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_vorgang SET fk_t_person_pid="+vorgang.getKauefer().getPid()+", fk_t_verkaeufer_pid_ek=" + vorgang.getEinkaeufer().getPerson().getPid() + ",fk_t_verkaeufer_pid_vk="+vorgang.getVerkaeufer().getPerson().getPid()+",fk_t_kfz_fin='"+vorgang.getKfz().getFin()+"',epreis="+vorgang.getePreis()+",vpreis="+vorgang.getvPreis()+",km="+vorgang.getKilometer()+",schaeden='"+vorgang.getSchaeden()+"',vkdatum='"+vorgang.getVerkaufsDatum()+"',ekdatum='"+vorgang.getEinkaufsDatum()+"',kennz='"+vorgang.getKennzeichen()+"',rabattgrund='"+vorgang.getRabattGrund()+"',tuev="+vorgang.getTuev()+",sonstvereinb='"+vorgang.getSonstvereinbarungen()+"',vpreisplan="+vorgang.getvPreisPlan()+" WHERE vid=" + vorgang.getVid() + "");
        }
    }
    public void insertOrUpdateAusstattungsliste(Sonderausstattung sonderausstattung, KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_ausstattungsliste(fk_t_sonderausstattung_sid,fk_t_kfz_fin) VALUES ("+sonderausstattung.getSid()+",'" + kfz.getFin()+"')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_ausstattungsliste SET fk_t_sonderausstattung_sid="+sonderausstattung.getSid()+",fk_t_kfz_fin='"+kfz.getFin()+"' WHERE fk_t_sonderausstattung_sid=" + sonderausstattung.getSid()+" AND fk_t_kfz_fin='"+kfz.getFin()+"' ");
        }
    }



    public void printTable(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData rm = r.getMetaData();
        //Tabellenkopf ->Spaltennamem
        int col = rm.getColumnCount();
        int[] max = new int[col];
        List<List<String>> tabelle = new ArrayList<>(col);
        for (int i = 0; i < col; i++) {
            List<String> spalte = new ArrayList<>();
            String s = rm.getColumnLabel(i + 1);
            spalte.add(s);
            tabelle.add(spalte);
            max[i] = s.length();
        }
        //Tabelleneinträge
        if (r.next())
            do {
                for (int i = 0; i < col; i++) {
                    String s = r.getString(i + 1);
                    if (s == null) s = "null";
                    tabelle.get(i).add(s);
                    max[i] = Math.max(max[i], s.length());

                }
            } while (r.next());
        r.close();
        //Ausgabe
        for (int i = 0; i < tabelle.get(0).size(); i++) {
            String s = "";
            for (int j = 0; j < col; j++)
                s += "|" + String.format("%-" + max[j] + "s", tabelle.get(j).get(i));
            System.out.println(s + "|");
        }
    }

    //Abfragen
    public List<Vorgang> VorgaengeZuVerkaeufer(Verkaeufer verkaeufer) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_verkaeufer_pid_vk="+verkaeufer.getPerson().getPid()+"");
        List<Vorgang> vorgaenge = new ArrayList<>();


        while(r.next())
        {
            long id = r.getLong("vid");
            String fin = r.getString("fk_t_kfz_fin");
            long pid=r.getLong("fk_t_person_pid");
            long ek=r.getLong("fk_t_verkaeufer_pid_ek");
            long vk=r.getLong("fk_t_verkaeufer_pid_vk");
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            int km = r.getInt("km");
            String schaeden =r.getString("Schaeden");
            Date vkdatum = r.getDate("vkdatum");
            Date ekdatum = r.getDate("ekdatum");
            String kennz = r.getString("kennz");
            String rabattgrund = r.getString("rabattgrund");
            Date tuev = r.getDate("tuev");
            String sonstvereinb = r.getString("sonstvereinb");
            double vpreisplan = r.getDouble("vpreisplan");
            Vorgang vorgang = new Vorgang(id,einePerson(pid),einVerkaufer(vk),einVerkaufer(ek),einKfz(fin),vpreis,epreis,vpreisplan,vkdatum,rabattgrund,sonstvereinb,ekdatum,schaeden,tuev,kennz,km);
            vorgaenge.add(vorgang);

        }
        r.close();
        return vorgaenge;
    }
    public Person einePerson(long id) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person WHERE pid=" + id + "");
        Person personret=null;

        while(r.next())
        {
            long pid = r.getLong("pid");
            String anrede = r.getString("anrede");
            String name = r.getString("name");
            String vorname = r.getString("vorname");
            Date gebtag = r.getDate("gebtag");
            String anschrift = r.getString("anschrift");
            int plz = r.getInt("plz");
            String ort = r.getString("ort");
            String ust_id = r.getString("ust_id");


            personret = new Person(pid, anrede, name,vorname,gebtag,anschrift,plz,ort,ust_id);

        }
        r.close();
        return personret;
    }
    public KFZ einKfz(String fin) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz WHERE fin='" + fin + "'");
        KFZ kfz=null;

        while(r.next())
        {
            String id = r.getString("fin");
            String hersteller = r.getString("hersteller");
            String modell = r.getString("modell");
            String kfz_brief = r.getString("kfz_brief");
            int leistung = r.getInt("leistung");
            String farbe = r.getString("farbe");
            Date ez = r.getDate("ez");
            byte plakette = r.getByte("plakette");
            String kraftstoff = r.getString("kraftstoff");


            kfz = new KFZ(fin, hersteller,modell,kfz_brief,leistung,farbe,ez,plakette,kraftstoff);
            // go to next line in the customer table
        }
        r.close();
        return kfz;
    }
    public Verkaeufer einVerkaufer(long id) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE fk_t_person_pid=" + id + "");
        Verkaeufer verkaeufer=null;
        while(r.next())
        {
            long pid = r.getLong("fk_t_person_pid");
            String anmeldename = r.getString("anmeldename");
            String passwort = r.getString("passwort");
            Date inaktivseit = r.getDate("inaktivseit");
            boolean aktiv = (inaktivseit==null);


            verkaeufer = new Verkaeufer(anmeldename,passwort,einePerson(id),aktiv,isAdmin(id));

        }
        r.close();
        return verkaeufer;
    }
    public Verkaeufer einVerkaufer(String anmeldeName) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE anmeldename='" + anmeldeName + "'");
        Verkaeufer verkaeufer=null;
        while(r.next())
        {
            long pid = r.getLong("fk_t_person_pid");
            String anmeldename = r.getString("anmeldename");
            String passwort = r.getString("passwort");
            Date inaktivseit = r.getDate("inaktivseit");
            boolean aktiv = (inaktivseit==null);

            verkaeufer = new Verkaeufer(anmeldename,passwort,einePerson(pid),aktiv,isAdmin(pid));

        }
        r.close();
        return verkaeufer;
    }
    public boolean isAdmin(long id) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_admins WHERE fk_t_verkaeufer_fk_t_person_pid=" + id + "");
        boolean isAdmin=false;


        while(r.next())
        {
            long pid = r.getLong("fk_t_verkaeufer_fk_t_person_pid");
            if (pid==id)isAdmin=true;
        }
        r.close();
        return isAdmin;
    }
    public Notiz eineNotiz(Person person) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_notiz WHERE fk_t_person_pid=" + person.getPid() + "");
        Notiz notiz =null;
        while(r.next())
        {
            long nid = r.getLong("nid");
            String text = r.getString("text");
            Date datum = r.getDate("datum");
            notiz = new Notiz(nid,person,datum,text);
            person.addNotiz(notiz);
        }
        r.close();
        return notiz;
    }
    public Erreichbarkeit eineErreichbarkeit(Person person) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE fk_t_person_pid=" + person.getPid() + "");
        Erreichbarkeit erreichbarkeit =null;
        while(r.next())
        {
            long eid = r.getLong("eid");
            String tel = r.getString("tel");
            String handy = r.getString("handy");
            String email = r.getString("email");
            String text = r.getString("text");
            erreichbarkeit = new Erreichbarkeit(eid,person,tel,handy,email,text);
            person.addErreichbarkeit(erreichbarkeit);
        }
        r.close();
        return erreichbarkeit;
    }



}

