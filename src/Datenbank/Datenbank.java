package Datenbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.*;
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

    public static Datenbank getInstance() throws ClassNotFoundException, SQLException {
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
            String host = "localhost";
            int port = 5432;
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
    public static Datenbank getInstance(String database) throws SQLException{
        String host = "localhost";
        int port = 5432;

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
            getInstance();
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

    public void insertOrUpdatePerson(String anrede, String name) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name) VALUES ('" + anrede + "', '" + name + "')");
        } catch (SQLException e) {
           stmt.executeUpdate("UPDATE t_person SET anrede='" + anrede + "'WHERE name='" + name + "'");
        }

    }
    public void insertOrUpdatePersonAlles(String anrede, String name, String vorname) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT  INTO t_Person(anrede,name) VALUES ('" + anrede + "', '" + name + "')");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE t_person SET anrede='" + anrede + "'WHERE name='" + name + "'");
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



}

