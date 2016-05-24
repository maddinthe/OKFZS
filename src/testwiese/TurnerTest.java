package testwiese;

import GUI.OKFZS;
import GUI.Werkzeug.PersonenEditor;

import java.sql.SQLException;

/**
 * Created by cdreher on 23.05.2016.
 */
public class TurnerTest {
    public static void main(String[] args) throws SQLException {
        OKFZS okfzs = new OKFZS();
        PersonenEditor personenEditor =new PersonenEditor(okfzs,"Herr","Theilen");

    }


}
