package testwiese;

import GUI.OKFZS;
import GUI.Werkzeug.PersonenEditor;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cdreher on 23.05.2016.
 */
public class TurnerTest {
    public static void main(String[] args) throws SQLException {
        OKFZS okfzs = new OKFZS();

    }
    public static java.sql.Date umwandeln(String datum) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(datum);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        return sDate;
    }


}
