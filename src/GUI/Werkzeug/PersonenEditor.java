package GUI.Werkzeug;

import Datenbank.Datenbank;
import Datenhaltung.Person;
import GUI.Ansicht;
import GUI.OKFZS;

import java.sql.SQLException;

/**
 * Created by cdreher on 16.05.2016.
 */
//todo:stub
public class PersonenEditor extends Ansicht {

    public PersonenEditor(OKFZS okfzsInstanz, Person person) {
        super(okfzsInstanz);
        Datenbank db = connection(okfzsInstanz);


    }
    public PersonenEditor(OKFZS okfzsInstanz,String anrede, String name) throws SQLException {
        super(okfzsInstanz);
        System.out.println(new Person(anrede, name));
       Datenbank db = connection(okfzsInstanz);
        try {
            db.insertOrUpdatePerson(anrede, name);
            db.printTable("t_Person");
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
    }
    public Person getPerson(){
        return null;
    }




    public Datenbank connection(OKFZS okfzsInstanz){
        Datenbank db = okfzsInstanz.getDatenbank();
        try {
            db = Datenbank.getInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbank-Treiber nicht gefunden");
        } catch (SQLException e) {
            if (e.getMessage().startsWith("Datenbank exisitiert nicht"))
                try {
                    db = Datenbank.getInstance("db_okfzs");
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return db;
    }
}
