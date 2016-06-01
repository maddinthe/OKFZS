package Datenbank;

import Datenhaltung.*;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * Alle Konstruktoren, Methoden, Getter, Setter, Attribute
 * die keinen  extra angegebenen Autor haben, wurden von cdreher erstellt.
 *
 * Die Klasse Datenbank dient dazu eine Datenbank für die OKFZS anzulegen.
 * Außerdem stellt Sie Methoden bereit, die die grundlegende Kommunikation zwischen Programm und Datenbank aufbaut.
 * Weiterhin bietet Sie Methoden an, um Daten aus dem Programm in die Datenbank hinzuzufügen und Datensätze aus der
 * Datenbank mittels Abfrage-Methoden im Programm sichtbar zu machen.
 *
 * @author cdreher on 23.05.2016
 */
public class Datenbank {
    /**
     *legt eine Instanz der Datenbank an
     */
    private static Datenbank datenbank;
    /**
     * legt eine Instanz einer Connection an
     */
    private static Connection conn;
    /**
     * legt ein SimpleDateFormat mit der Struktur Jahr-Monat-Tag an
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * Diese Methode schließt eine vorhandene Datenbank-Verbindung
     */
    public static void closeDBConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * wurde abgeändert: autor Major Krebs
     * @param host der Host wo die Datenbank gespeichert ist
     * @param port der Port auf dem der Datenbank-Server lauscht
     *  Die Parameter Host und Port werden jetzt mit übergeben um diese dynamisch zu halten.
     *  Außerdem wird nun beim Fall des Fails der Verbindung über den DriverManager im catch
     *  die Methode getInstance(String database,String host, String port) aufgerufen
     * @return gibt die verbundene Datenbank zurück
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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
            }

        }
        return datenbank;
    }

    /**
     *
     * wurde abgeändert: autor Major Krebs
     * @param database Name der anzulegenden Datenbank
     * @param host der Host wo die Datenbank gespeichert wird
     * @param port der Port auf dem der Datenserver lauscht
     * Die Parameter Host und Port werden jetzt mit übergeben um diese dynamisch zu halten.
     * @return gibt die verbunden Datenbank zurück
     * @throws SQLException
     */
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

    /**keine Änderungen
     * autor majorkrebs
     * @throws SQLException
     */
    private static void einlesenScript() throws SQLException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Datenbank.class.getResourceAsStream("init.sql")))) {
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
            System.out.println(e);
        }

    }


    /**
     * Diese Methode legt einen Datensatz aus der Anrede,Name und Geburtstag der übergebenden Person an
     * und gibt dir die Person ergänzt um die PID diese wieder zurück
     * @param person eine Person
     * @return die übergebene Person erweitert um die PID
     * @throws SQLException
     */
    public Person insertPerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,gebTag) VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getGeburtstag() + "')");//Anlegen eines Datensatzes
            ResultSet rs = stmt.executeQuery("SELECT max(pid) FROM t_person");//größte PID zurück geben
            if (rs.next())
                person.setPid(rs.getLong(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return person;
    }

    /**
     * Diese Methode versucht einen vorhandenen Datensatz zu Updaten (nur für den Verkaeufer veränderliche Daten der Person)
     * anhand der PID der übergebenen Person und gibt diese wieder zurück.
     * Ist kein Datensatz vorhanden wird zu der Person ein Datensatz angelegt und die
     * übergebene Person erweitert um die PID wieder zurück gegeben.
     * @param person eine Person
     * @return die übergebene Person erweitert um die PID oder die übergebene Person
     * @throws SQLException
     */
    public Person insertOrUpdatePerson(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',vorname='" + person.getVorname() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");//Update Datensatz anhand der PID
            if (count < 1) {
                ResultSet rs = null;
                stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                        " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getVorname() + "','" + person.getGeburtstag() + "','" + person.getAnschrift() + "'," + person.getPostleitzahl() + ",'" + person.getOrt() + "','" + person.getUstID() + "')");//Anlegen eines Datensatzes
                if ((rs = stmt.executeQuery("SELECT max(pid) FROM t_person")).next())//größte PID zurück geben
                    person.setPid(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return person;
    }

    /**Diese Methode erweitert die Update-Funktion von InsertOrUpdatePerson() um alle Daten einer Person
     * anhand der PID und gibt diese zurück.
     * Ist kein Datensatz vorhanden wird zu der Person ein Datensatz angelegt und die
     * übergebene Person erweitert um die PID wieder zurück gegeben.
     *
     * @param person eine Person
     * @return die übergebene Person erweitert um die PID oder die übergebene Person
     * @throws SQLException
     */
    public Person insertOrUpdatePersonAdmin(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_person SET anrede='" + person.getAnrede() + "',name='" + person.getName() + "',vorname='" + person.getVorname() + "',gebtag='" + person.getGeburtstag() + "',anschrift='" + person.getAnschrift() + "',plz=" + person.getPostleitzahl() + ",ort='" + person.getOrt() + "', ust_id='" + person.getUstID() + "'WHERE pid=" + person.getPid() + "");//Update Datensatz anhand der PID
            if(count < 1) {
                ResultSet rs = null;
                stmt.executeUpdate("INSERT  INTO t_Person(anrede,name,vorname,gebtag,Anschrift,plz,ort,ust_id)" +
                        " VALUES ('" + person.getAnrede() + "', '" + person.getName() + "','" + person.getVorname() + "','" + person.getGeburtstag() + "','" + person.getAnschrift() + "'," + person.getPostleitzahl() + ",'" + person.getOrt() + "','" + person.getUstID() + "')");//Anlegen eines Datensatzes
                if((rs=stmt.executeQuery("SELECT max(pid) FROM t_person")).next())//größte PID zurück geben
                    person.setPid(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return person;
    }

    /**Diese Methode versucht einen vorhandenen Datensatz zu Updaten anhand der EID der
     * übergebenen Erreichbarkeit.
     * Ist kein Datensatz vorhanden wird zu der Erreichbarkeit ein Datensatz angelegt.
     *
     * @param erreichbarkeit eine Erreichbarkeit
     * @throws SQLException
     */
    public void insertOrUpdateErreichbarkeit(Erreichbarkeit erreichbarkeit) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_erreichbarkeit SET fk_t_person_pid=" + erreichbarkeit.getPerson().getPid() + ", tel='" + erreichbarkeit.getTelefonNummer() + "',handy='" + erreichbarkeit.getHandyNummer() + "',email='" + erreichbarkeit.getEmail() + "',text='" + erreichbarkeit.getDetails() + "' WHERE eid=" + erreichbarkeit.getEid() + "");//Update Datensatz anhand der EIP
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_erreichbarkeit(fk_t_person_pid,tel,handy,email,text) VALUES (" + erreichbarkeit.getPerson().getPid() + ",'" + erreichbarkeit.getTelefonNummer() + "', '" + erreichbarkeit.getHandyNummer() + "','" + erreichbarkeit.getEmail() + "','" + erreichbarkeit.getDetails() + "')");//Anlegen eines Datensatzes


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Diese Methode versucht einen vorhandenen Datensatz zu Updaten anhand der NID der
     * übergebenen Notiz.
     * Ist kein Datensatz vorhanden wird zu der Notiz ein Datensatz angelegt.
     * @param notiz eine Notiz
     * @throws SQLException
     */
    public void insertOrUpdateNotiz(Notiz notiz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_notiz SET fk_t_person_pid=" + notiz.getPerson().getPid() + ", text='" + notiz.getBeschreibung() + "',datum='" + notiz.getDatum() + "' WHERE nid=" + notiz.getNid() + "");//Update Datensatz anhand der NID
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_notiz(fk_t_person_pid,text,datum) VALUES (" + notiz.getPerson().getPid() + ",'" + notiz.getBeschreibung() + "', '" + notiz.getDatum() + "')");//Anlegen eines Datensatzes


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Diese Methode versucht einen Datensatz anhand des übergebenen Verkaeufer hinzuzufügen.
     * Ist ein Datensatz zu diesem Verkaeufer bereits, vorhanden wird dieser anhand der des Fremdschlüssels fk_t_person_PID geupdatet.
     *
     * @param verkaeufer ein Verkaeufer
     * @throws SQLException
     */
    public void insertOrUpdateVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "',NULL )");//Anlegen Datensatz eines aktiven Verkaeufers
            else
                stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort,inaktivseit) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "','" + new Date() + "')");//Anlegen Datensatz eines inaktiven Verkaeufers
        } catch (SQLException e) {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid=" + verkaeufer.getPerson().getPid() + ", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='" + verkaeufer.getPasswortHash() + "',inaktivseit=NULL WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");//Update Datensatz eines aktiven Verkaeufers
            else
                stmt.executeUpdate("UPDATE t_verkaeufer SET fk_t_person_pid=" + verkaeufer.getPerson().getPid() + ", anmeldename='" + verkaeufer.getAnmeldeName() + "',passwort='" + verkaeufer.getPasswortHash() + "', inaktivseit='" + new Date() + "' WHERE fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");//Update Datensatz eines inaktiven Verkaeufers
        }
    }

    /**Diese Methode versucht einen Datensatz anhand des übergebenen Verkaeufer hinzuzufügen.
     * Ist ein Datensatz zu diesem Verkaeufer bereits, vorhanden wird dieser anhand der des Fremdschlüssels fk_t_person_PID geupdatet.     *
     * @param verkaeufer ein Verkaeufer
     * @throws SQLException
     */
    public void insertOrUpdateAdmins(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_verkaeufer_fk_t_person_pid) VALUES (" + verkaeufer.getPerson().getPid() + ")");//Anlegen Datensatz eines aktiven Admins
            else
                stmt.executeUpdate("INSERT  INTO t_admins(fk_t_verkaeufer_fk_t_person_pid) VALUES (" + verkaeufer.getPerson().getPid() + ")");//Anlegen Datensatz eines inaktiven Admins
        } catch (SQLException e) {
            if (verkaeufer.istAktiv())
                stmt.executeUpdate("UPDATE t_admins SET fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + " WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");//Update Datensatz eines aktiven Admins
            else
                stmt.executeUpdate("UPDATE t_admins SET fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + " WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid() + "");//Update Datensatz eines inaktiven Admins
        }
    }

    /**Diese Methode versucht einen Datensatz anhand des übergebenen Verkaeufer zu löschen.
     *
     * @param verkaeufer ein Verkaeufer
     * @throws SQLException
     */
    public void deleteAdmin(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("DELETE FROM t_admins WHERE fk_t_verkaeufer_fk_t_person_pid=" + verkaeufer.getPerson().getPid());//Lösche Datensatz anhand des Fremdschlüssels fk_t_verkaeufer_fk_t_person_pid
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Diese Methode versucht einen vorhandenen Datensatz zu Updaten
     * anhand der FIN des übergebenen KFZs.
     * Ist kein Datensatz vorhanden wird zu dem KFZ ein Datensatz angelegt.
     * Außerdem ruft er die Methode insertOrUpdateAusstattung auf um dem KFZ eine Ausstatttungsliste hinzuzufügen
     * @param kfz ein KFZ
     * @throws SQLException
     */
    public void insertOrUpdateKfz(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int i = stmt.executeUpdate("UPDATE t_kfz SET fin='" + kfz.getFin() + "',hersteller='" + kfz.getHersteller() + "',modell='" + kfz.getModell() + "',kfz_brief='" + kfz.getKfzBriefNr() + "',leistung=" + kfz.getLeistungInKw() + ",farbe='" + kfz.getFarbe() + "',ez='" + kfz.getEz() + "', plakette='" + kfz.getUmweltPlakette() + "',kraftstoff='" + kfz.getKraftstoff() + "'WHERE fin='" + kfz.getFin() + "'");//Update Datensatz anhand der FIN
            if (i < 1)
                stmt.executeUpdate("INSERT  INTO t_kfz(fin,hersteller,modell,kfz_brief,leistung,farbe,ez,plakette,kraftstoff) VALUES ('" + kfz.getFin() + "','" + kfz.getHersteller() + "', '" + kfz.getModell() + "','" + kfz.getKfzBriefNr() + "'," + kfz.getLeistungInKw() + ",'" + kfz.getFarbe() + "','" + kfz.getEz() + "','" + kfz.getUmweltPlakette() + "','" + kfz.getKraftstoff() + "')");//Anlegen eines Datensatzes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertOrUpdateAusstattung(kfz);
    }

    /**Diese Methode fügt bzw. löscht anhand eines übergebenen KFZs Datensätze hinzu bzw. löscht diese wieder
     * je nachdem ob sich die zu dem KFZ zugehörigen Sonderausstattungen geändert haben.
     *
     * @param kfz ein KFZ
     * @throws SQLException
     */
    public void insertOrUpdateAusstattung(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        List<Sonderausstattung> alteSA = sonderausstattungKFZ(kfz);
        List<Sonderausstattung> neueSA = kfz.getSonderausstattung();
        for (Sonderausstattung s : alteSA) {
            if (!neueSA.contains(s)) {
                stmt.executeUpdate("DELETE  FROM t_ausstattungsliste WHERE fk_t_sonderausstattung_sid=" + s.getSid() + " AND fk_t_kfz_fin='" + kfz.getFin() + "'");//löscht einen Datensatz anhand der beiden Fremdschlüssel
            }
        }
        for (Sonderausstattung s : neueSA) {
            if (!alteSA.contains(s)) {
                stmt.executeUpdate("INSERT INTO t_ausstattungsliste(fk_t_sonderausstattung_sid, fk_t_kfz_fin) VALUES (" + s.getSid() + ",'" + kfz.getFin() + "')");//Anlegen eines Datensatzes
            }
        }
    }

    /**Diese Methode legt einen Datensatz anhand der übergebenen Aktion an.
     * Ist der Datensatz bereits vorhanden wird dieser geupdatet
     *
     * @param aktion eine Aktion
     * @throws SQLException
     */
    public void insertOrUpdateAktion(Aktion aktion) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_aktion(fk_t_person_pid,fk_t_kfz_fin,datum,text) VALUES (" + aktion.getDurchfuehrender().getPid() + ",'" + aktion.getKfz().getFin() + "', '" + aktion.getDurchfuehrung() + "','" + aktion.getBeschreibung() + "')"); //Anlegen eines Datensatzes
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_aktion SET fk_t_person_pid='" + aktion.getDurchfuehrender().getPid() + "', fk_t_kfz_fin='" + aktion.getKfz().getFin() + "',datum='" + aktion.getDurchfuehrung() + "',text='" + aktion.getBeschreibung() + "' WHERE fk_t_person_pid=" + aktion.getDurchfuehrender().getPid() + " AND fk_t_kfz_fin='" + aktion.getKfz().getFin() + "'");//Update eines Datensatzes anhand der Fremdschlüssel
        }
    }

    /**Diese Methode versucht einen vorhandenen Datensatz zu Updaten
     * anhand der SID der übergebenen Sonderausstattung.
     * Ist kein Datensatz vorhanden wird zu der Sonderausstattung ein Datensatz angelegt.
     *
     * @param sonderausstattung eine Sonderausstattung
     * @throws SQLException
     */
    public void insertOrUpdateSonderausstattung(Sonderausstattung sonderausstattung) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_sonderausstattung SET art='" + sonderausstattung.getBeschreibung() + "' WHERE sid=" + sonderausstattung.getSid() + "");//Update Datensatz anhand der Sodnerausstattung
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_sonderausstattung(art) VALUES ('" + sonderausstattung.getBeschreibung() + "')");//Anlegen eines Datensatzes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Diese Methode zerlegt den Vorgang in seine Attribute um es zu ermöglichen das null übergeben werden kann.
     *  Danach versucht Sie einen vorhandenen Datensatz zu Updaten anhand der VID.
     *  ist kein Datensatz vorhanden wird zu diesem Vorgang ein Datensatz angelegt.
     *
     * @param vorgang ein Vorgang
     * @throws SQLException
     */
    public void insertOrUpdateVorgang(Vorgang vorgang) throws SQLException {
        Long kPid = (vorgang.getKauefer() != null) ? vorgang.getKauefer().getPid() : null;
        Long vkPid = (vorgang.getVerkaeufer() != null) ? vorgang.getVerkaeufer().getPerson().getPid() : null;
        Long ekPid = vorgang.getEinkaeufer().getPerson().getPid();
        KFZ kfz = vorgang.getKfz();
        Double vPreis = (vorgang.getvPreis() == 0) ? null : vorgang.getvPreis();
        Double ePreis = (vorgang.getePreis() == 0) ? null : vorgang.getePreis();
        Double vPreisPlan = (vorgang.getvPreisPlan() == 0) ? null : vorgang.getvPreisPlan();
        String vkDatum = (vorgang.getVerkaufsDatum() == null) ? null : "'" + sdf.format(vorgang.getVerkaufsDatum()) + "'";
        String rabbatGrund = (vorgang.getRabattGrund() == null) ? "" : vorgang.getRabattGrund();
        String sonstVereinbarung = (vorgang.getSonstvereinbarungen() == null) ? "" : vorgang.getSonstvereinbarungen();
        String ekDatum = sdf.format(vorgang.getEinkaufsDatum());
        String scheaden = (vorgang.getSchaeden() == null) ? "" : vorgang.getSchaeden();
        String tuev = (vorgang.getTuev() == null) ? null : "'" + sdf.format(vorgang.getTuev()) + "'";
        String kenzeichen = (vorgang.getKennzeichen() == null) ? "" : vorgang.getKennzeichen();
        Integer km = (vorgang.getKilometer() == 0) ? null : vorgang.getKilometer();

        Statement stmt = conn.createStatement();
        try {
            int count = stmt.executeUpdate("UPDATE t_vorgang SET fk_t_person_pid=" + kPid + ", fk_t_verkaeufer_pid_ek=" + ekPid + ",fk_t_verkaeufer_pid_vk=" + vkPid + ",fk_t_kfz_fin='" + kfz.getFin() + "',epreis=" + ePreis + ",vpreis=" + vPreis + ",km=" + km + ",schaeden='" + scheaden + "',vkdatum=" + vkDatum + ",ekdatum='" + ekDatum + "',kennz='" + kenzeichen + "',rabattgrund='" + rabbatGrund + "',tuev=" + tuev + ",sonstvereinb='" + sonstVereinbarung + "',vpreisplan=" + vPreisPlan + " WHERE vid=" + vorgang.getVid() + "");
            if (count < 1)
                stmt.executeUpdate("INSERT  INTO t_vorgang(fk_t_person_pid,fk_t_verkaeufer_pid_ek,fk_t_verkaeufer_pid_vk,fk_t_kfz_fin,epreis,vpreis,km,schaeden,vkdatum,ekdatum,kennz,rabattgrund,tuev,sonstvereinb,vpreisplan) VALUES (" + kPid + "," + ekPid + ", " + vkPid + ",'" + kfz.getFin() + "'," + ePreis + "," + vPreis + "," + km + ",'" + scheaden + "'," + vkDatum + ",'" + ekDatum + "','" + kenzeichen + "','" + rabbatGrund + "'," + tuev + ",'" + sonstVereinbarung + "'," + vPreisPlan + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    //Abfragen

    /**
     * Diese Methode liefert alle Vorgänge eines Verkaeufers die abgeschlossen sind anhand des übergebenen Verkaeufers zurück.
     * Dazu wird aus dem ResultSet eine Liste von Vorgängen erstellt und übergeben
     * @param verkaeufer ein Verkaeufer
     * @return eine Liste mit Vorgängen
     * @throws SQLException
     */
    public List<Vorgang> VorgaengeZuVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_verkaeufer_pid_vk=" + verkaeufer.getPerson().getPid() + " AND fk_t_person_pid NOTNULL AND vkdatum<now() AND vpreis >0 ");//Alle Daten zu einem Vorgang eines Verkaeufers wo die Spalten fk_t_person_pid nicht,vkdatum kleiner als heute und vpreis größer null sind
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

    /**
     *  Diese Methode liefert eine Person anhand der übergebenen PID zurück.
     * Dazu wird aus dem ResultSet eine Person erstellt  und übergeben
     * @param id Die PID einer Person
     * @return eine Person
     * @throws SQLException
     */
    public Person einePerson(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person WHERE pid=" + id + "");//Alle Daten einer Person die zu der übergebenen id passen
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

    /**
     * Diese Methode liefert ein KFZ anhand der übergebenen FIN zurück.
     * Dazu wird aus dem ResultSet ein KFZ erstellt und übergeben.
     * Außerdem werden dem gerade erstellten KFZ Aktionen und Sonderausstattungen hinzugefügt.
     * @param fin Die FIN eines KFZs
     * @return ein KFZ
     * @throws SQLException
     */
    public KFZ einKfz(String fin) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz WHERE fin='" + fin + "'");//Alle Daten eines KFZs die zu der übergebenen fin passen
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

    /**
     * Diese Methode liefert eine Liste mit Aktionen anhand eines übergebenen KFZs zurück.
     * Dazu wird aus dem ResultSet eine Liste mit Aktionen erstellt und übergeben.
     *
     * @param kfz ein KFZ
     * @return eine Liste mit Aktionen
     * @throws SQLException
     */
    private List<Aktion> aktionenZuKFZ(KFZ kfz) throws SQLException {
        List<Aktion> ret = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM t_aktion WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");//Alle Daten die zu der FIN des übergebenen KFZs passen
        while (rs.next()) {
            ret.add(new Aktion(rs.getDate("datum"), einePerson(rs.getLong("fk_t_person_pid")), rs.getString("text"), kfz));
        }
        return ret;
    }

    /**
     * Diese Methode liefert eine Liste mit Sonderausstattungen anhand eines übergebenen KFZs zurück.
     * Dazu wird aus dem ResultSet eine Liste mit Sonderausstattungen erstellt und übergeben.
     * @param kfz ein KFZ
     * @return eine Liste mit Sonderausstattungen
     * @throws SQLException
     */
    private List<Sonderausstattung> sonderausstattungKFZ(KFZ kfz) throws SQLException {
        List<Sonderausstattung> ret = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT fk_t_sonderausstattung_sid FROM t_ausstattungsliste WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");//Alle Daten die zu der FIN des übergebenen KFZs passen
        while (rs.next()) {
            ret.add(eineSonderausstattung(rs.getLong("fk_t_sonderausstattung_sid")));
        }
        return ret;
    }

    /**
     * Diese Methode liefert einen Verkaeufer anhand der übergebenen id zurück.
     * Dazu wird aus dem ResultSet ein Verkaeufer erstellt und übergeben.
     * @param id Die PID einer Person
     * @return ein Verkaeufer
     * @throws SQLException
     */
    public Verkaeufer einVerkaufer(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE fk_t_person_pid=" + id + "");//Alle Daten die zu der übergebenen id passen
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

    /**
     * Diese Methode liefert einen Verkaeufer anhand des übergebenen Anmeldenamens zurück.
     * Dazu wird aus dem ResultSet ein Verkaeufer erstellt und übergeben.
     * @param anmeldeName der Anmeldename eines Verkaeufers
     * @return ein Verkaeufer
     * @throws SQLException
     */
    public Verkaeufer einVerkaufer(String anmeldeName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer WHERE anmeldename='" + anmeldeName + "'");//Alle Daten die zu dem übergebenen Anmeldenamen passen
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

    /**
     * Diese Methode liefert eine boolean anhand der übergebenen id zurück.
     * Dazu wird aus dem ResultSet die pid aus der Select-Abfrage mit der übergebenen id verglichen.
     * @param id PID einer Person
     * @return true oder false
     * @throws SQLException
     */
    public boolean isAdmin(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_admins WHERE fk_t_verkaeufer_fk_t_person_pid=" + id + "");//Alle Daten die zu der übergebenen id passen
        boolean isAdmin = false;


        while (r.next()) {
            long pid = r.getLong("fk_t_verkaeufer_fk_t_person_pid");
            if (pid == id) isAdmin = true;
        }
        r.close();
        return isAdmin;
    }



    /**Diese Methode liefert eine Liste von Personen anhand des übergebenen Strings.
     * Dazu wird aus dem ResultSet eine Liste von Personen erstellt und übergeben.
     * @param s ein String
     * @return eine Liste von Personen
     * @throws SQLException
     */
    public List<Person> eineTelefonummer(String s) throws SQLException {
        Statement stmt = conn.createStatement();
        List<Person> personen = new ArrayList<>();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE tel LIKE '%" + s + "%'");//Alle Daten die zu dem übergeben String passen dabei können davor und danach noch zeichen kommen
        while (r.next()) {
            long pid = r.getLong("fk_t_person_pid");
            personen.add(einePerson(pid));
        }
        r.close();
        return personen;
    }



    /**
     * Diese Methode liefert eine Sonderausstattung anhand der übergebenen SID.
     * Dazu wird aus dem ResultSet eine Sonderausstattung erstellt und übergeben.
     * @param id eine SID
     * @return eine Sonderausstattung
     * @throws SQLException
     */
    public Sonderausstattung eineSonderausstattung(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_sonderausstattung WHERE sid=" + id + "");//Alle Daten die zu der übergebenen ID passen
        Sonderausstattung sonderausstattung = null;
        while (r.next()) {
            String text = r.getString("art");
            sonderausstattung = new Sonderausstattung(id, text);

        }
        r.close();
        return sonderausstattung;
    }



    /**
     * Diese Methode liefert alle KFZ.
     * Dazu wird aus dem ResultSet eine Liste mit KFZ erstellt und übergeben.
     * @return Liste mit KFZ
     * @throws SQLException
     */
    public List<KFZ> alleKfz() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz");//Alle Daten aus der Tabelle
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

    /**Diese Methode liefert eine Liste aus KFZs die sich aktuell nicht im Verkauf befinden
     * (das heißt keinen Vorgang haben oder schon verkauft wurden)
     * Dazu wird aus dem ResultSet eine Liste mit KFZs erstellt und übergeben.
     * @return eine Liste mit KFZs
     * @throws SQLException
     */
    public List<KFZ> alleKfzNichtImVerkauf() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz LEFT JOIN t_vorgang ON t_kfz.fin = t_vorgang.fk_t_kfz_fin WHERE vid ISNULL OR (fk_t_verkaeufer_pid_vk NOTNULL AND fk_t_person_pid NOTNULL AND vpreis NOTNULL AND vkdatum<now())");//Alle Daten zweier Tabellen die keine vid haben oder (wo fk_t_verkaeufer_pid_vk nicht null und vpreis nicht null und vkdatum in der Vergangenheit)
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

    /**
     * Diese Methode liefert alle Personen.
     * Dazu wird aus dem ResultSet eine Liste mit Personen erstellt und übergeben.
     * @return eine Liste mit Personen
     * @throws SQLException
     */
    public List<Person> allePersonen() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person");//Alle Daten aus der Tabelle
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

    /**
     * Diese Methode liefert alle Personen alphabetisch sortiert nach dem Nachnamen.
     * Dazu wird aus dem ResultSet eine Liste mit Personen erstellt und übergeben.
     * @return eine Liste mit Persoen
     * @throws SQLException
     */
    public List<Person> allePersonenSortiert() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_person ORDER BY name");//Alle Daten einer Tabelle sortiert Alphabetisch nach dem Namen
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

    /** Diese Methode liefert alle unverkauften Vorgänge.
     * Dazu wird aus dem ResultSet eine Liste mit Vorgängen erstellt und übergeben.
     * @return eine Liste mit Vorgängen
     * @throws SQLException
     */
    public List<Vorgang> unverkaufteVorgaenge() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_kfz INNER JOIN t_vorgang ON t_kfz.fin = t_vorgang.fk_t_kfz_fin WHERE fk_t_verkaeufer_pid_vk ISNULL OR fk_t_person_pid ISNULL OR vpreis ISNULL OR vkdatum>now()");//Alle Daten zweier Tabellen wo der fk_t_verkaeufer_pid_vk null oder fk_t_person_pid null oder vpreis null oder vkdatum in der Zukunft
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



    /**
     * Diese Methode liefert einen Vorgang zu einem KFZ anhand des übergebenen KFZs.
     * Dazu wird aus dem ResultSet ein Vorgang erstellt und übergeben.
     * @param kfz ein KFZ
     * @return ein Vorgang zu ein KFZ
     * @throws SQLException
     */
    public Vorgang einVorgang(KFZ kfz) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_kfz_fin='" + kfz.getFin() + "'");//Alle Daten die zu der FIN des übergebenen KFZ passen
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

    /**
     * Diese Methode liefert eine Liste mit allen Verkaeufern.
     * Dazu wird aus dem ResultSet eine Liste mit Verkaeufern erstellt und übergeben.
     * @return eine Liste mit Verkaeufer
     * @throws SQLException
     */
    public List<Verkaeufer> alleVerkaeufer() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_verkaeufer");//Alle Daten einer Tabelle
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

    /**
     * Diese Methode liefert eine Liste mit allen Notizen einer Person.
     * Dazu wird aus dem ResultSet eine Liste mit Notizen erstellt und übergeben.
     * Außderdem werdem die gerade erstellten Notizen der übergebenen Person hinzugefügt.
     * @param person eine Person
     * @return eine Liste mit Notizen
     * @throws SQLException
     */
    public List<Notiz> alleNotizen(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_notiz WHERE fk_t_person_pid=" + person.getPid() + "");//Alle Daten einer Tabelle die zu der PID der übergebenen Person passen
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

    /**Diese Methode liefert eine Liste mit allen Erreichbarkeiten einer Person.
     * Dazu wird aus dem ResultSet eine Liste mit Erreichbarkeiten erstellt und übergeben.
     * Außderdem werdem die gerade erstellten Erreichbarkeiten der übergebenen Person hinzugefügt.
     *
     * @param person eine Person
     * @return eine Liste mit Erreichbarkeiten
     * @throws SQLException
     */
    public List<Erreichbarkeit> alleErreichbarkeiten(Person person) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_erreichbarkeit WHERE fk_t_person_pid=" + person.getPid() + "");//Alle Daten einer Tabelle die zu der PID der übergebenen Person passen
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



    /**Diese Methode liefert eine Liste mit allen Sonderausstattungen alphabetisch sortiert nach der Art.
     * Dazu wird aus dem ResultSet eine Liste mit Sonderausstattungen erstellt und übergeben.
     *
     *
     * @return eine Liste mit Sonderausstattungen
     * @throws SQLException
     */
    public List<Sonderausstattung> ausstattungslisteSortiert() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT sid,art FROM t_sonderausstattung ORDER BY art");//Alle Daten einer Tabelle alphabetisch sortiert nach der Art
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

    /**Diese Methode liefert ein Boolean ob es einem Admin gibt.
     *Dazu wird aus dem ResultSet abgefragt ob Eintrag vorhanden.
     * @return true oder false
     */
    public boolean adminDa() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet r = stmt.executeQuery("SELECT count(fk_t_verkaeufer_fk_t_person_pid) FROM t_admins");//Zähle alle Einträge in einer Tabelle
            if (r.next())
                return (r.getInt(1) > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**Diese Methode legt einen neuen Datensatz anhand des Fremdschlüssels
     *  des übergebenen Verkaeufers an und gibt diesen wieder zurück
     *
     * @param verkaeufer ein Verkaeufer
     * @return ein Verkaeufer
     */
    public Verkaeufer insertVerkaeufer(Verkaeufer verkaeufer) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT  INTO t_verkaeufer(fk_t_person_pid,anmeldename,passwort) VALUES (" + verkaeufer.getPerson().getPid() + ",'" + verkaeufer.getAnmeldeName() + "', '" + verkaeufer.getPasswortHash() + "')");//Anlegen eines neuen Datensatzes
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return verkaeufer;
    }
    /**
     * Diese Methode liefert alle Vorgänge eines Verkaeufers anhand des übergebenen Verkaeufers zurück.
     * Dazu wird aus dem ResultSet eine Liste von Vorgängen erstellt und übergeben
     * @param verkaeufer ein Verkaeufer
     * @return eine Liste mit Vorgängen
     * @throws SQLException
     */
    public List<Vorgang> alleVorgaengeZuVerkaeufer(Verkaeufer verkaeufer) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT * FROM t_vorgang WHERE fk_t_verkaeufer_pid_ek="+verkaeufer.getPerson().getPid()+" OR fk_t_verkaeufer_pid_vk="+verkaeufer.getPerson().getPid());//Alle Daten zu einem Vorgang an dem der Verkaeufer beteiligt ist
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

}

