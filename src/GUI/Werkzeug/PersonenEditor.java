package GUI.Werkzeug;

import Datenhaltung.Person;
import GUI.Ansicht;

/**
 * Created by cdreher on 16.05.2016.
 */
//todo:stub
public class PersonenEditor extends Ansicht {
    public PersonenEditor(Person person) {
        this.person = person;
    }
    public PersonenEditor() {

    }
    public Person getPerson(){
        return person;
    }
}
