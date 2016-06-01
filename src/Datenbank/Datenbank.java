package Datenbank;

import Datenhaltung.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Datenbank {
    private static Datenbank datenbank;
    private static Connection conn;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");

    private Datenbank() {
    }

    public static void schließen() {
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
                getInstance("db_okfzs", host, port);
                //throw new SQLException("Datenbank existiert nicht", e.getSQLState(), e);
            }

        }
        return datenbank;
    }

    public static Datenbank getInstance(String database, String host, int port) throws SQLException {


        String url = "jdbc:postgresql://" + host + ":" + port + "/";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");

        try {
            conn = DriverManager.getConnection(url, props);
            conn.createStatement().executeUpdate("CREATE DATABASE " + database);
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Zugriff verweigert", e.getSQLState(), e);
        }
        try {
            getInstance(host, port);
        } catch (ClassNotFoundException e) {

        }

        einlesenScript();

        return datenbank;
    }

    private static void einlesenScript() throws SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(Datenbank.class.getResource("dblaeuft.sql").getFile()))) {
            String sqlInstruction = "";
            String zeile;
            while ((zeile = br.readLine()) != null) {
                zeile = zeile.split("--")[0];
                sqlInstruction += zeile + " ";
                if (sqlInstruction.trim().endsWith(";")) {
                    Statement stmt = conn.createStatement();
                    stmt.execute(sqlInstruction);
                    sqlInstruction = "";
                }

            }

        } catch (IOException e) {
        }

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


    public void closeDBConnection() throws SQLException {
        conn.close();
    }

    public Person insertPerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,gebTag) VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getGeburtstag() + "')");
            ResultSet rs = stmt.executeQuery("SELECT max(pid) FROM t_person");
            if (rs.next())
                person.setPid(rs.getLong(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return person;
    }

    public Person insertOrUpdatePerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',vorname='" + person.getVorname() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");
            if (count < 1) {
                ResultSet rs = null;
                stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                        " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getVorname() + "','" + person.getGeburtstag() + "','" + person.getAnschrift() + "'," + person.getPostleitzahl() + ",'" + person.getOrt() + "','" + person.getUstID() + "')");
                if ((rs = stmt.executeQuery("SELECT max(pid) FROM t_person")).next())
                    person.setPid(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return person;
    }

    public void insertOrUpdatePersonAdmin(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',name='" + person.getName() + "',vorname='" + person.getVorname() + "',gebtag='" + person.getGeburtstag() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");

        } catch (SQLException e) {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                    " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getVorname() + "','" + person.getGeburtstag() + "','" + person.getAnschrift() + "'," + person.getPostleitzahl() + ",'" + person.getOrt() + "','" + person.getUstID() + "')");

        }
    }

    public void insertOrUpdateErreichbarkeit(Erreichbarkeit erreichbarkeit) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_erreichbarkeit SET fk_t_person_pid=" + erreichbarkeit.getPerson().getPid() + ", tel='" + erreichbarkeit.getTelefonNummer() + "',handy='" + erreichbarkeit.getHandyNummer() + "',email='" + erreichbarkeit.getEmail() + "',text='" + erreichbarkeit.getDetails() + "' WHERE eid=" + erreichbarkeit.getEid() + "");
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_erreichbarkeit(fk_t_person_pid,tel,handy,email,text) VALUES (" + erreichbarkeit.getPerson().getPid() + ",'" + erreichbarkeit.getTelefonNummer() + "', '" + erreichbarkeit.getHandyNummer() + "','" + erreichbarkeit.getEmail() + "','" + erreichbarkeit.getDetails() + "')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrUpdateNotiz(Notiz notiz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_notiz SET fk_t_person_pid=" + notiz.getPerson().getPid() + ", text='" + notiz.getBeschreibung() + "',datum='" + notiz.getDatum() + "' WHERE nid=" + notiz.getNid() + "");
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_notiz(fk_t_person_pid,text,datum) VALUES (" + notiz.getPerson().getPid() + ",'" + notiz.getBeschreibung() + "', '" + notiz.getDatum() + "')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrUpdateVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "',NULL )");
            else
                stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "','" + new Date() + "')");
        } catch (SQLException e) {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid=" + verkaeufer.getPerson().getPid() + ", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='" + verkaeufer.getPasswortHash() + "',inaktivseit=NULL WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
            else
                stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid=" + verkaeufer.getPerson().getPid() + ", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='" + verkaeufer.getPasswortHash() + "', inaktivseit='" + new Date() + "' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
        }
    }

    public void insertOrUpdateAdmins(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_verkaeufer_fk_t_person_pid) VALUES (" + verkaeufer.getPerson().getPid() + ")");
            else
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_verkaeufer_fk_t_person_pid) VALUES (" + verkaeufer.getPerson().getPid() + ")");
        } catch (SQLException e) {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("UPDATE t_admins SET fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + " WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
            else
                stmt.executeUpdate("UPDATE t_admins SET fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + " WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");
        }
    }

    public void deleteAdmin(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("DELETE FROM t_admins WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrUpdateKfz(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int i = stmt.executeUpdate("UPDATE t_kfz SET fin='" + kfz.getFin() + "',hersteller='" + kfz.getHersteller() + "',modell='" + kfz.getModell() + "',kfz_brief='" + kfz.getKfzBriefNr() + "',leistung=" + kfz.getLeistungInKw() + ",farbe='" + kfz.getFarbe() + "',ez='" + kfz.getEz() + "', plakette='" + kfz.getUmweltPlakette() + "',kraftstoff='" + kfz.getKraftstoff() + "'WHERE fin='" + kfz.getFin() + "'");
            if (i < 1)
                stmt.executeUpdate("INSERT  INTO t_kfz(fin,hersteller,modell,kfz_brief,leistung,farbe,ez,plakette,kraftstoff) VALUES ('" + kfz.getFin() + "','" + kfz.getHersteller() + "', '" + kfz.getModell() + "','" + kfz.getKfzBriefNr() + "'," + kfz.getLeistungInKw() + ",'" + kfz.getFarbe() + "','" + kfz.getEz() + "','" + kfz.getUmweltPlakette() + "','" + kfz.getKraftstoff() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertOrUpdateAusstattung(kfz);
    }

    public void insertOrUpdateAusstattung(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        List<Sonderausstattung> alteSA = sonderausstattungKFZ(kfz);
        List<Sonderausstattung> neueSA = kfz.getSonderausstattung();
        for (Sonderausstattung s : alteSA) {
            if (!neueSA.contains(s)) {
                stmt.executeUpdate("DELETE  FROM t_ausstattungsliste WHERE fk_t_sonderausstattung_sid=" + s.getSid() + " AND fk_t_kfz_fin='" + kfz.getFin() + "'");
            }
        }
        for (Sonderausstattung s : neueSA) {
            if (!alteSA.contains(s)) {
                stmt.executeUpdate("INSERT INTO t_ausstattungsliste(fk_t_sonderausstattung_sid, fk_t_kfz_fin) VALUES (" + s.getSid() + ",'" + kfz.getFin() + "')");
            }
        }
    }

    public void insertOrUpdateAktion(Aktion aktion) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_aktion(fk_t_person_pid,fk_t_kfz_fin,datum,text) VALUES (" + aktion.getDurchfuehrender().getPid() + ",'" + aktion.getKfz().getFin() + "', '" + aktion.getDurchfuehrung() + "','" + aktion.getBeschreibung() + "')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_aktion SET fk_t_person_pid='" + aktion.getDurchfuehrender().getPid() + "', fk_t_kfz_fin='" + aktion.getKfz().getFin() + "',datum='" + aktion.getDurchfuehrung() + "',text='" + aktion.getBeschreibung() + "' WHERE fk_t_person_pid=" + aktion.getDurchfuehrender().getPid() + " AND fk_t_kfz_fin='" + aktion.getKfz().getFin() + "'");
        }
    }

    public void insertOrUpdateSonderausstattung(Sonderausstattung sonderausstattung) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_sonderausstattung SET art='" + sonderausstattung.getBeschreibung() + "' WHERE sid=" + sonderausstattung.getSid() + "");
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_sonderausstattung(art) VALUES ('" + sonderausstattung.getBeschreibung() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrUpdateVorgang(Vorgang vorgang) throws SQLException {
        Long kPid = (vorgang.getKauefer() != null) ? vorgang.getKauefer().getPid() : null;
        Long vkPid = (vorgang.getVerkaeufer() != null) ? vorgang.getVerkaeufer().getPerson().getPid() : null;
        Long ekPid = vorgang.getEinkaeufer().getPerson().getPid();
        KFZ kfz = vorgang.getKfz();
        Double vPreis = (vorgang.getvPreis() == 0) ? null : vorgang.getvPreis();
        Double ePreis = (vorgang.getePreis() == 0) ? null : vorgang.getePreis();
        Double vPreisPlan = (vorgang.getvPreisPlan() == 0) ? null : vorgang.getvPreisPlan();
        String vkDatum = (vorgang.getVerkaufsDatum() == null) ? null : "'"+sdf.format(vorgang.getVerkaufsDatum())+"'";
        String rabbatGrund = (vorgang.getRabattGrund() == null) ? "" : vorgang.getRabattGrund();
        String sonstVereinbarung = (vorgang.getSonstvereinbarungen() == null) ? "" : vorgang.getSonstvereinbarungen();
        String ekDatum = sdf.format(vorgang.getEinkaufsDatum());
        String scheaden = (vorgang.getSchaeden() == null) ? "" : vorgang.getSchaeden();
        String tuev = (vorgang.getTuev() == null) ? null : "'"+sdf.format(vorgang.getTuev())+"'";
        String kenzeichen = (vorgang.getKennzeichen() == null) ? "" : vorgang.getKennzeichen();
        Integer km = (vorgang.getKilometer() == 0) ? null : vorgang.getKilometer();

        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_vorgang(fk_t_person_pid,fk_t_verkaeufer_pid_ek,fk_t_verkaeufer_pid_vk,fk_t_kfz_fin,epreis,vpreis,km,schaeden,vkdatum,ekdatum,kennz,rabattgrund,tuev,sonstvereinb,vpreisplan) VALUES (" + kPid + "," + ekPid + ", " + vkPid + ",'" + kfz.getFin() + "'," + ePreis + "," + vPreis + "," + km + ",'" + scheaden + "'," + vkDatum + ",'" + ekDatum + "','" + kenzeichen + "','" + rabbatGrund + "'," + tuev + ",'" + sonstVereinbarung + "'," + vPreisPlan + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            stmt.executeUpdate("UPDATE t_vorgang SET fk_t_person_pid=" + kPid + ", fk_t_verkaeufer_pid_ek=" + ekPid + ",fk_t_verkaeufer_pid_vk=" + vkPid + ",fk_t_kfz_fin='" + kfz.getFin() + "',epreis=" + ePreis + ",vpreis=" + vPreis+ ",km=" + km + ",schaeden='" + scheaden + "',vkdatum='" + vkDatum + "',ekdatum='" + ekDatum + "',kennz='" + kenzeichen + "',rabattgrund='" + rabbatGrund + "',tuev=" + tuev + ",sonstvereinb='" + sonstVereinbarung + "',vpreisplan=" + vPreisPlan + " WHERE vid=" + vorgang.getVid() + "");
        }
    }

    public void insertOrUpdateAusstattungsliste(Sonderausstattung sonderausstattung, KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_ausstattungsliste(fk_t_sonderausstattung_sid,fk_t_kfz_fin) VALUES (" + sonderausstattung.getSid() + ",'" + kfz.getFin() + "')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_ausstattungsliste SET fk_t_sonderausstattung_sid=" + sonderausstattung.getSid() + ",fk_t_kfz_fin='" + kfz.getFin() + "' WHERE fk_t_sonderausstattung_sid=" + sonderausstattung.getSid() + " AND fk_t_kfz_fin='" + kfz.getFin() + "' ");
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
    public List<Vorgang> VorgaengeZuVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_verkaeufer_pid_vk=" + verkaeufer.getPerson().getPid() + "");
        List<Vorgang> vorgaenge = new ArrayList<>();


        while (r.next()) {
            long id = r.getLong("vid");
            String fin = r.getString("fk_t_kfz_fin");
            long pid = r.getLong("fk_t_person_pid");
            long ek = r.getLong("fk_t_verkaeufer_pid_ek");
            long vk = r.getLong("fk_t_verkaeufer_pid_vk");
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            int km = r.getInt("km");
            String schaeden = r.getString("Schaeden");
            Date vkdatum = r.getDate("vkdatum");
            Date ekdatum = r.getDate("ekdatum");
            String kennz = r.getString("kennz");
            String rabattgrund = r.getString("rabattgrund");
            Date tuev = r.getDate("tuev");
            String sonstvereinb = r.getString("sonstvereinb");
            double vpreisplan = r.getDouble("vpreisplan");
            Vorgang vorgang = new Vorgang(id, einePerson(pid), einVerkaufer(vk), einVerkaufer(ek), einKfz(fin), vpreis, epreis, vpreisplan, vkdatum, rabattgrund, sonstvereinb, ekdatum, schaeden, tuev, kennz, km);
            vorgaenge.add(vorgang);

        }
        r.close();
        return vorgaenge;
    }

    public Person einePerson(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person WHERE pid=" + id + "");
        Person personret = null;

        while (r.next()) {
            String anrede = r.getString("anrede");
            String name = r.getString("name");
            String vorname = r.getString("vorname");
            Date gebtag = r.getDate("gebtag");
            String anschrift = r.getString("anschrift");
            int plz = r.getInt("plz");
            String ort = r.getString("ort");
            String ust_id = r.getString("ust_id");


            personret = new Person(id, anrede, name, vorname, gebtag, anschrift, plz, ort, ust_id);

        }
        r.close();
        return personret;
    }

    public KFZ einKfz(String fin) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz WHERE fin='" + fin + "'");
        KFZ kfz = null;

        while (r.next()) {

            String hersteller = r.getString("hersteller");
            String modell = r.getString("modell");
            String kfz_brief = r.getString("kfz_brief");
            int leistung = r.getInt("leistung");
            String farbe = r.getString("farbe");
            Date ez = r.getDate("ez");
            byte plakette = r.getByte("plakette");
            String kraftstoff = r.getString("kraftstoff");
            kfz = new KFZ(fin, hersteller, modell, kfz_brief, leistung, farbe, ez, plakette, kraftstoff);

        }
        r.close();
        kfz.setAktionen(aktionenZuKFZ(kfz));
        kfz.setSonderausstattung(sonderausstattungKFZ(kfz));
        return kfz;
    }

    private List<Aktion> aktionenZuKFZ(KFZ kfz) throws SQLException {
        List<Aktion> ret = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM t_aktion WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");
        while (rs.next()) {
            ret.add(new Aktion(rs.getDate("datum"), einePerson(rs.getLong("fk_t_person_pid")), rs.getString("text"), kfz));
        }
        return ret;
    }

    private List<Sonderausstattung> sonderausstattungKFZ(KFZ kfz) throws SQLException {
        List<Sonderausstattung> ret = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT fk_t_sonderausstattung_sid FROM t_ausstattungsliste WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");
        while (rs.next()) {
            ret.add(eineSonderausstattung(rs.getLong("fk_t_sonderausstattung_sid")));
        }
        return ret;
    }


    public Verkaeufer einVerkaufer(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE fk_t_person_pid=" + id + "");
        Verkaeufer verkaeufer = null;
        while (r.next()) {

            String anmeldename = r.getString("anmeldename");
            String passwort = r.getString("passwort");
            Date inaktivseit = r.getDate("inaktivseit");
            boolean aktiv = (inaktivseit == null);


            verkaeufer = new Verkaeufer(anmeldename, passwort, einePerson(id), aktiv, isAdmin(id));

        }
        r.close();
        return verkaeufer;
    }

    public Verkaeufer einVerkaufer(String anmeldeName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE anmeldename='" + anmeldeName + "'");
        Verkaeufer verkaeufer = null;
        while (r.next()) {
            long pid = r.getLong("fk_t_person_pid");
            String anmeldename = r.getString("anmeldename");
            String passwort = r.getString("passwort");
            Date inaktivseit = r.getDate("inaktivseit");
            boolean aktiv = (inaktivseit == null);

            verkaeufer = new Verkaeufer(anmeldename, passwort, einePerson(pid), aktiv, isAdmin(pid));

        }
        r.close();
        return verkaeufer;
    }

    public boolean isAdmin(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_admins WHERE fk_t_verkaeufer_fk_t_person_pid=" + id + "");
        boolean isAdmin = false;


        while (r.next()) {
            long pid = r.getLong("fk_t_verkaeufer_fk_t_person_pid");
            if (pid == id) isAdmin = true;
        }
        r.close();
        return isAdmin;
    }

    public Notiz eineNotiz(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_notiz WHERE fk_t_person_pid=" + person.getPid() + "");
        Notiz notiz = null;
        while (r.next()) {
            long nid = r.getLong("nid");
            String text = r.getString("text");
            Date datum = r.getDate("datum");
            notiz = new Notiz(nid, person, datum, text);
            person.addNotiz(notiz);
        }
        r.close();
        return notiz;
    }

    public Erreichbarkeit eineErreichbarkeit(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE fk_t_person_pid=" + person.getPid() + "");
        Erreichbarkeit erreichbarkeit = null;
        while (r.next()) {
            long eid = r.getLong("eid");
            String tel = r.getString("tel");
            String handy = r.getString("handy");
            String email = r.getString("email");
            String text = r.getString("text");
            erreichbarkeit = new Erreichbarkeit(eid, person, tel, handy, email, text);
            person.addErreichbarkeit(erreichbarkeit);
        }
        r.close();
        return erreichbarkeit;
    }
    public List<Person> eineTelefonunner(String s) throws SQLException {
        Statement stmt = conn.createStatement();
        List<Person> personen = new ArrayList<>();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE tel LIKE '%" + s + "%'");
        Erreichbarkeit erreichbarkeit = null;
        while (r.next()) {
            long pid = r.getLong("fk_t_person_pid");
            personen.add(einePerson(pid));
        }
        r.close();
        return personen;
    }

    public Aktion eineAktion(KFZ kfz, Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_aktion WHERE fk_t_kfz_fin='" + kfz.getFin() + "' AND fk_t_person_pid=" + person.getPid() + "");
        Aktion aktion = null;
        while (r.next()) {
            long eid = r.getLong("eid");
            Date datum = r.getDate("datum");
            String text = r.getString("text");

            aktion = new Aktion(datum, person, text, kfz);
            kfz.addAktion(aktion);
        }
        r.close();
        return aktion;
    }

    public Sonderausstattung eineSonderausstattung(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_sonderausstattung WHERE sid=" + id + "");
        Sonderausstattung sonderausstattung = null;
        while (r.next()) {
            String text = r.getString("art");
            sonderausstattung = new Sonderausstattung(id, text);

        }
        r.close();
        return sonderausstattung;
    }

    public List<Sonderausstattung> ausstattungsliste(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT sid,art from t_sonderausstattung INNER JOIN t_ausstattungsliste ON t_sonderausstattung.sid = t_ausstattungsliste.fk_t_sonderausstattung_sid INNER JOIN t_kfz ON t_kfz.fin = t_ausstattungsliste.fk_t_kfz_fin WHERE fin='" + kfz.getFin() + "'");
        List<Sonderausstattung> sonderausstattungsListe = new ArrayList<>();


        while (r.next()) {
            long sid = r.getLong("sid");
            String art = r.getString("art");
            Sonderausstattung sonderausstattung = new Sonderausstattung(sid, art);
            sonderausstattungsListe.add(sonderausstattung);

        }
        r.close();
        return sonderausstattungsListe;
    }

    public List<KFZ> alleKfz() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz");
        List<KFZ> kfzListe = new ArrayList<>();
        KFZ kfz = null;

        while (r.next()) {
            String fin = r.getString("fin");
            String hersteller = r.getString("hersteller");
            String modell = r.getString("modell");
            String kfz_brief = r.getString("kfz_brief");
            int leistung = r.getInt("leistung");
            String farbe = r.getString("farbe");
            Date ez = r.getDate("ez");
            byte plakette = r.getByte("plakette");
            String kraftstoff = r.getString("kraftstoff");
            kfz = new KFZ(fin, hersteller, modell, kfz_brief, leistung, farbe, ez, plakette, kraftstoff);
            kfzListe.add(kfz);

        }
        r.close();
        return kfzListe;
    }
    public List<KFZ> alleKfzNichtImVerkauf() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz LEFT JOIN t_vorgang ON t_kfz.fin = t_vorgang.fk_t_kfz_fin WHERE vid ISNULL OR (fk_t_verkaeufer_pid_vk NOTNULL AND fk_t_person_pid NOTNULL AND vpreis NOTNULL AND vkdatum<now())");
        List<KFZ> kfzListe = new ArrayList<>();
        KFZ kfz = null;

        while (r.next()) {
            String fin = r.getString("fin");
            String hersteller = r.getString("hersteller");
            String modell = r.getString("modell");
            String kfz_brief = r.getString("kfz_brief");
            int leistung = r.getInt("leistung");
            String farbe = r.getString("farbe");
            Date ez = r.getDate("ez");
            byte plakette = r.getByte("plakette");
            String kraftstoff = r.getString("kraftstoff");
            kfz = new KFZ(fin, hersteller, modell, kfz_brief, leistung, farbe, ez, plakette, kraftstoff);
            kfzListe.add(kfz);

        }
        r.close();
        return kfzListe;
    }

    public List<Person> allePersonen() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person");
        Person person = null;
        List<Person> personenListe = new ArrayList<>();


        while (r.next()) {
            long pid = r.getLong("pid");
            String anrede = r.getString("anrede");
            String name = r.getString("name");
            String vorname = r.getString("vorname");
            Date gebtag = r.getDate("gebtag");
            String anschrift = r.getString("anschrift");
            int plz = r.getInt("plz");
            String ort = r.getString("ort");
            String ust_id = r.getString("ust_id");
            person = new Person(pid, anrede, name, vorname, gebtag, anschrift, plz, ort, ust_id);
            personenListe.add(person);

        }
        r.close();
        return personenListe;

    }

    public List<Person> allePersonenSortiert() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person ORDER BY name");
        Person person = null;
        List<Person> personenListe = new ArrayList<>();


        while (r.next()) {
            long pid = r.getLong("pid");
            String anrede = r.getString("anrede");
            String name = r.getString("name");
            String vorname = r.getString("vorname");
            Date gebtag = r.getDate("gebtag");
            String anschrift = r.getString("anschrift");
            int plz = r.getInt("plz");
            String ort = r.getString("ort");
            String ust_id = r.getString("ust_id");
            person = new Person(pid, anrede, name, vorname, gebtag, anschrift, plz, ort, ust_id);
            personenListe.add(person);

        }
        r.close();
        return personenListe;
    }

    public List<Vorgang> unverkaufteVorgaenge() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz INNER JOIN t_vorgang ON t_kfz.fin = t_vorgang.fk_t_kfz_fin WHERE fk_t_verkaeufer_pid_vk ISNULL OR fk_t_person_pid ISNULL OR vpreis ISNULL OR vkdatum>now()");
        List<Vorgang> vorgaenge = new ArrayList<>();


        while (r.next()) {
            long id = r.getLong("vid");
            String fin = r.getString("fk_t_kfz_fin");
            long pid = r.getLong("fk_t_person_pid");
            long ek = r.getLong("fk_t_verkaeufer_pid_ek");
            long vk = r.getLong("fk_t_verkaeufer_pid_vk");
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            int km = r.getInt("km");
            String schaeden = r.getString("Schaeden");
            Date vkdatum = r.getDate("vkdatum");
            Date ekdatum = r.getDate("ekdatum");
            String kennz = r.getString("kennz");
            String rabattgrund = r.getString("rabattgrund");
            Date tuev = r.getDate("tuev");
            String sonstvereinb = r.getString("sonstvereinb");
            double vpreisplan = r.getDouble("vpreisplan");
            Vorgang vorgang = new Vorgang(id, einePerson(pid), einVerkaufer(vk), einVerkaufer(ek), einKfz(fin), vpreis, epreis, vpreisplan, vkdatum, rabattgrund, sonstvereinb, ekdatum, schaeden, tuev, kennz, km);
            vorgaenge.add(vorgang);

        }
        r.close();
        return vorgaenge;
    }

    public Double statistikGewinnVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_verkaeufer_pid_vk=" + verkaeufer.getPerson().getPid() + "");
        Double gewinn = 0.0;


        while (r.next()) {
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            long ek = r.getLong("fk_t_verkaeufer_pid_ek");
            if (ek != verkaeufer.getPerson().getPid())
                gewinn += (vpreis - epreis) / 2;
            else
                gewinn += vpreis - epreis;


        }
        r.close();
        return gewinn;
    }

    public List<Vorgang> alleVorgaenge() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang");
        List<Vorgang> vorgaenge = new ArrayList<>();


        while (r.next()) {
            long id = r.getLong("vid");
            String fin = r.getString("fk_t_kfz_fin");
            long pid = r.getLong("fk_t_person_pid");
            long ek = r.getLong("fk_t_verkaeufer_pid_ek");
            long vk = r.getLong("fk_t_verkaeufer_pid_vk");
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            int km = r.getInt("km");
            String schaeden = r.getString("Schaeden");
            Date vkdatum = r.getDate("vkdatum");
            Date ekdatum = r.getDate("ekdatum");
            String kennz = r.getString("kennz");
            String rabattgrund = r.getString("rabattgrund");
            Date tuev = r.getDate("tuev");
            String sonstvereinb = r.getString("sonstvereinb");
            double vpreisplan = r.getDouble("vpreisplan");
            Vorgang vorgang = new Vorgang(id, einePerson(pid), einVerkaufer(vk), einVerkaufer(ek), einKfz(fin), vpreis, epreis, vpreisplan, vkdatum, rabattgrund, sonstvereinb, ekdatum, schaeden, tuev, kennz, km);
            vorgaenge.add(vorgang);

        }
        r.close();
        return vorgaenge;
    }

    public Vorgang einVorgang(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");
        Vorgang vorgang = null;
        while (r.next()) {
            long id = r.getLong("vid");
            String fin = r.getString("fk_t_kfz_fin");
            long pid = r.getLong("fk_t_person_pid");
            long ek = r.getLong("fk_t_verkaeufer_pid_ek");
            long vk = r.getLong("fk_t_verkaeufer_pid_vk");
            double epreis = r.getDouble("epreis");
            double vpreis = r.getDouble("vpreis");
            int km = r.getInt("km");
            String schaeden = r.getString("Schaeden");
            Date vkdatum = r.getDate("vkdatum");
            Date ekdatum = r.getDate("ekdatum");
            String kennz = r.getString("kennz");
            String rabattgrund = r.getString("rabattgrund");
            Date tuev = r.getDate("tuev");
            String sonstvereinb = r.getString("sonstvereinb");
            double vpreisplan = r.getDouble("vpreisplan");
            vorgang = new Vorgang(id, einePerson(pid), einVerkaufer(vk), einVerkaufer(ek), einKfz(fin), vpreis, epreis, vpreisplan, vkdatum, rabattgrund, sonstvereinb, ekdatum, schaeden, tuev, kennz, km);


        }
        r.close();
        return vorgang;
    }


    public List<Verkaeufer> alleVerkaeufer() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer");
        List<Verkaeufer> verkaeufer = new ArrayList<>();
        while (r.next()) {
            long pid = r.getLong("fk_t_person_pid");
            String anmeldename = r.getString("anmeldename");
            String passwort = r.getString("passwort");
            Date inaktivseit = r.getDate("inaktivseit");
            boolean aktiv = (inaktivseit == null);

            verkaeufer.add(new Verkaeufer(anmeldename, passwort, einePerson(pid), aktiv, isAdmin(pid)));

        }
        r.close();
        return verkaeufer;
    }

    public List<Notiz> alleNotizen(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_notiz WHERE fk_t_person_pid=" + person.getPid() + "");
        List<Notiz> notizen = new ArrayList<>();
        Notiz notiz = null;
        while (r.next()) {
            long nid = r.getLong("nid");
            String text = r.getString("text");
            Date datum = r.getDate("datum");
            notiz = new Notiz(nid, person, datum, text);
            person.addNotiz(notiz);
            notizen.add(notiz);
        }
        r.close();
        return notizen;
    }

    public List<Erreichbarkeit> alleErreichbarkeiten(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE fk_t_person_pid=" + person.getPid() + "");
        List<Erreichbarkeit> erreichbarkeiten = new ArrayList<>();
        Erreichbarkeit erreichbarkeit = null;
        while (r.next()) {
            long eid = r.getLong("eid");
            String tel = r.getString("tel");
            String handy = r.getString("handy");
            String email = r.getString("email");
            String text = r.getString("text");
            erreichbarkeit = new Erreichbarkeit(eid, person, tel, handy, email, text);
            person.addErreichbarkeit(erreichbarkeit);
            erreichbarkeiten.add(erreichbarkeit);
        }
        r.close();
        return erreichbarkeiten;
    }

    public List<Sonderausstattung> ausstattungsliste() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT sid,art FROM t_sonderausstattung");
        List<Sonderausstattung> sonderausstattungsListe = new ArrayList<>();


        while (r.next()) {
            long sid = r.getLong("sid");
            String art = r.getString("art");
            Sonderausstattung sonderausstattung = new Sonderausstattung(sid, art);
            sonderausstattungsListe.add(sonderausstattung);

        }
        r.close();
        return sonderausstattungsListe;
    }

    public List<Sonderausstattung> ausstattungslisteSortiert() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT sid,art FROM t_sonderausstattung ORDER BY art");
        List<Sonderausstattung> sonderausstattungsListe = new ArrayList<>();


        while (r.next()) {
            long sid = r.getLong("sid");
            String art = r.getString("art");
            Sonderausstattung sonderausstattung = new Sonderausstattung(sid, art);
            sonderausstattungsListe.add(sonderausstattung);

        }
        r.close();
        return sonderausstattungsListe;
    }

    public boolean adminDa() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet r = stmt.executeQuery("SELECT count(fk_t_verkaeufer_fk_t_person_pid) FROM t_admins");
            if (r.next())
                return (r.getInt(1) > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Verkaeufer insertVerkaeufer(Verkaeufer verkaeufer) {


        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return verkaeufer;
    }

}

