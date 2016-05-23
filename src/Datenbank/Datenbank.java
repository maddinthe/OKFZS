package Datenbank;

import java.sql.Connection;

/**
 * Created by cdreher on 23.05.2016.
 */
//todo: stub
public class Datenbank {
    private Datenbank datenbank;
    private Connection connection;

    public Datenbank getInstance(){
        return datenbank;
    }
    public Datenbank getInstance(String name){
        return datenbank;
    }

    public void einlesenScript(){

    }

}
