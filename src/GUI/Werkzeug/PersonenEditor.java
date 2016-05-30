package GUI.Werkzeug;

import Datenbank.Datenbank;
import Datenhaltung.Erreichbarkeit;
import Datenhaltung.Notiz;
import Datenhaltung.Person;
import GUI.Ansicht;
import GUI.OKFZS;
import GUI.PersonenListe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Created by cdreher on 16.05.2016.
 */
//todo:stub
public class PersonenEditor extends Ansicht {
    Person selectedPers;

    public PersonenEditor(OKFZS okfzsInstanz, Person p) {
        super(okfzsInstanz);
        try {
            Person person = okfzsInstanz.getDatenbank().einePerson(p.getPid());
            selectedPers = p;
            List<Notiz> notizen = okfzsInstanz.getDatenbank().alleNotizen(p);
            List<Erreichbarkeit> erreichbarkeiten = okfzsInstanz.getDatenbank().alleErreichbarkeiten(p);
            JPanel jfPersonEdit = this;
            this.setLayout(new BorderLayout());
            JPanel jpWest = new JPanel();
            jpWest.setLayout(new BoxLayout(jpWest, BoxLayout.Y_AXIS));

            JPanel jpPersonenAngaben = new JPanel();
            jpPersonenAngaben.setBorder(new TitledBorder("Angaben zur Person"));
            jpPersonenAngaben.setLayout(new BoxLayout(jpPersonenAngaben, BoxLayout.Y_AXIS));

            JPanel jpAdressdaten = new JPanel();
            jpAdressdaten.setBorder(new TitledBorder("Adressdaten"));
            jpAdressdaten.setLayout(new BoxLayout(jpAdressdaten, BoxLayout.Y_AXIS));


            JPanel jpSonstigeAngaben = new JPanel();
            jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
            jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

            JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAnrede = new JLabel("Anrede:");
            String[] Anrede = {"Firma", "Frau", "Herr"};
            JComboBox jcAnredeListe = new JComboBox(Anrede);
            for (int i = 0; i < Anrede.length; i++) {
                if (person.getAnrede().equals(Anrede[i]))
                    jcAnredeListe.setSelectedIndex(i);
            }
            jcAnredeListe.setEditable(false);

            JPanel jpName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlName = new JLabel("Name:");
            JTextField jtName = new JTextField(20);
            jtName.setText(person.getName());
            jtName.setEditable(false);
            jpName.add(jlName);
            jpName.add(jtName);

            JPanel jpVorname = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlVorname = new JLabel("Vorname:");
            JTextField jtVorname = new JTextField(20);
            jtVorname.setText(person.getVorname());
            jpVorname.add(jlVorname);
            jpVorname.add(jtVorname);

            JPanel jpGeburtstag = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlGeburtstag = new JLabel("Geburtstag:");
            JTextField jtGeburtstag = new JTextField(20);
            jtGeburtstag.setText(person.getGeburtstag().toString());
            jtGeburtstag.setEditable(false);
            jpGeburtstag.add(jlGeburtstag);
            jpGeburtstag.add(jtGeburtstag);

            JPanel jpAnschrift = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAnschrift = new JLabel("Anschrift:");
            JTextField jtAnschrift = new JTextField(20);
            jtAnschrift.setText(person.getAnschrift());
            jpAnschrift.add(jlAnschrift);
            jpAnschrift.add(jtAnschrift);

            JPanel jpPlz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlPlz = new JLabel("Postleitzahl:");
            JTextField jtPlz = new JTextField(20);
            jtPlz.setText(String.valueOf(person.getPostleitzahl()));
            jpPlz.add(jlPlz);
            jpPlz.add(jtPlz);

            JPanel jpOrt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlOrt = new JLabel("Ort:");
            JTextField jtOrt = new JTextField(20);
            jtOrt.setText(person.getOrt());
            jpOrt.add(jlOrt);
            jpOrt.add(jtOrt);

            JPanel jpUst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlUst = new JLabel("UST-ID:");
            JTextField jtUst = new JTextField(20);
            jtUst.setText(person.getUstID());
            jpUst.add(jlUst);
            jpUst.add(jtUst);

            jpAnrede.add(jlAnrede);
            jpAnrede.add(jcAnredeListe);

            jpPersonenAngaben.add(jpAnrede);
            jpPersonenAngaben.add(jpVorname);
            jpPersonenAngaben.add(jpName);
            jpPersonenAngaben.add(jpGeburtstag);

            jpAdressdaten.add(jpAnschrift);
            jpAdressdaten.add(jpPlz);
            jpAdressdaten.add(jpOrt);


            JPanel jpButton = new JPanel();
            JButton jbSpeichern = new JButton("Speichern");
            JButton jbAbbrechen = new JButton("Abbrechen");
            jpButton.add(jbSpeichern);
            jpButton.add(jbAbbrechen);
            ActionListener alSpeichern = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Person temp = new Person(p.getPid(), jcAnredeListe.getSelectedItem().toString(), jtName.getText(), jtVorname.getText(), umwandeln(jtGeburtstag.getText()), jtAnschrift.getText(), Integer.parseInt(jtPlz.getText()), jtOrt.getText(), jtUst.getText());

                    try {
                        okfzsInstanz.getDatenbank().insertOrUpdatePerson(temp);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    okfzsInstanz.anzeigen("personAend");
                }

            };
            jbSpeichern.addActionListener(alSpeichern);

            ActionListener alAbbrechen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    okfzsInstanz.anzeigen("persAnz");
                }
            };
            jbSpeichern.addActionListener(alSpeichern);


            jpSonstigeAngaben.add(jpUst);
            jpSonstigeAngaben.add(jpButton);

            jpWest.add(jpPersonenAngaben);
            jpWest.add(jpAdressdaten);
            jpWest.add(jpSonstigeAngaben);


            //JPanel Center
            JPanel jpCenter = new JPanel();
            jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

            JPanel jpErreichbarkeit = new JPanel();
            jpErreichbarkeit.setBorder(new TitledBorder("Erreichbarkeiten"));
            jpErreichbarkeit.setLayout(new BoxLayout(jpErreichbarkeit, BoxLayout.Y_AXIS));
            JPanel jpErreichbarkeiten = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JList<Erreichbarkeit> jlErreichbarkeitsListe = new JList<>(erreichbarkeiten.toArray(new Erreichbarkeit[erreichbarkeiten.size()]));
            jlErreichbarkeitsListe.setCellRenderer(new ErrListRenderer());
            JScrollPane jsErreichbarkeitsListe = new JScrollPane(jlErreichbarkeitsListe);
            jpErreichbarkeiten.add(jsErreichbarkeitsListe);
            JPanel jpButtonCenter = new JPanel();
            JPanel jpErreichbarkeitButton = new JPanel();
            JButton jbErreichbarkeitEdit = new JButton("Edit");
            JButton jbErreichbarkeitNeu = new JButton("Neu");

            ActionListener alErreichbarkeitEdit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfErreichbarkeit = new JFrame("Erreichbarkeit Ändern");
                    JPanel jpErreichbarkeiten = new JPanel();
                    JPanel jpDetails = new JPanel();
                    JPanel jpTel = new JPanel();
                    JPanel jpMob = new JPanel();
                    JPanel jpMail = new JPanel();
                    JLabel jlDetails = new JLabel("Details:");
                    JLabel jlTel = new JLabel("Telefonnummer:");
                    JLabel jlMob = new JLabel("Mobil:");
                    JLabel jlMail = new JLabel("E-Mail:");
                    JTextField jtDetails = new JTextField(10);
                    JTextField jtTel = new JTextField(10);
                    JTextField jtMob = new JTextField(10);
                    JTextField jtMail = new JTextField(10);
                    jtDetails.setText(jlErreichbarkeitsListe.getSelectedValue().getDetails());
                    jtTel.setText(jlErreichbarkeitsListe.getSelectedValue().getTelefonNummer());
                    jtMob.setText(jlErreichbarkeitsListe.getSelectedValue().getHandyNummer());
                    jtMail.setText(jlErreichbarkeitsListe.getSelectedValue().getEmail());
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlErreichbarkeitsListe.getSelectedValue().setDetails(jtDetails.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setTelefonNummer(jtTel.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setHandyNummer(jtMob.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setEmail(jtMail.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateErreichbarkeit(jlErreichbarkeitsListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAend");
                            jfErreichbarkeit.dispose();

                        }
                    };
                    ActionListener alAbbrechenErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfErreichbarkeit.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernErreichbarkeit);
                    jbAbbrechen.addActionListener(alAbbrechenErreichbarkeit);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpDetails.add(jlDetails);
                    jpDetails.add(jtDetails);
                    jpTel.add(jlTel);
                    jpTel.add(jtTel);
                    jpMob.add(jlMob);
                    jpMob.add(jtMob);
                    jpMail.add(jlMail);
                    jpMail.add(jtMail);


                    jpErreichbarkeiten.add(jpDetails);
                    jpErreichbarkeiten.add(jpTel);
                    jpErreichbarkeiten.add(jpMob);
                    jpErreichbarkeiten.add(jpMail);
                    jpErreichbarkeiten.add(jpButton);
                    jfErreichbarkeit.add(jpErreichbarkeiten);
                    jfErreichbarkeit.setSize(800, 150);
                    jfErreichbarkeit.setLocation(500, 500);
                    jfErreichbarkeit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfErreichbarkeit.setVisible(true);

                }
            };


            ActionListener alErreichbarkeitNeu = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfErreichbarkeit = new JFrame("Neue Erreichbarkeit");
                    JPanel jpErreichbarkeiten = new JPanel();
                    JPanel jpDetails = new JPanel();
                    JPanel jpTel = new JPanel();
                    JPanel jpMob = new JPanel();
                    JPanel jpMail = new JPanel();
                    JLabel jlDetails = new JLabel("Details:");
                    JLabel jlTel = new JLabel("Telefonnummer:");
                    JLabel jlMob = new JLabel("Mobil:");
                    JLabel jlMail = new JLabel("E-Mail:");
                    JTextField jtDetails = new JTextField(10);
                    JTextField jtTel = new JTextField(10);
                    JTextField jtMob = new JTextField(10);
                    JTextField jtMail = new JTextField(10);
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlErreichbarkeitsListe.getSelectedValue().setDetails(jtDetails.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setTelefonNummer(jtTel.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setHandyNummer(jtMob.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setEmail(jtMail.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateErreichbarkeit(jlErreichbarkeitsListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAend");
                            jfErreichbarkeit.dispose();

                        }
                    };
                    ActionListener alAbbrechenErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfErreichbarkeit.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernErreichbarkeit);
                    jbAbbrechen.addActionListener(alAbbrechenErreichbarkeit);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpDetails.add(jlDetails);
                    jpDetails.add(jtDetails);
                    jpTel.add(jlTel);
                    jpTel.add(jtTel);
                    jpMob.add(jlMob);
                    jpMob.add(jtMob);
                    jpMail.add(jlMail);
                    jpMail.add(jtMail);


                    jpErreichbarkeiten.add(jpDetails);
                    jpErreichbarkeiten.add(jpTel);
                    jpErreichbarkeiten.add(jpMob);
                    jpErreichbarkeiten.add(jpMail);
                    jpErreichbarkeiten.add(jpButton);
                    jfErreichbarkeit.add(jpErreichbarkeiten);
                    jfErreichbarkeit.setSize(800, 150);
                    jfErreichbarkeit.setLocation(500, 500);
                    jfErreichbarkeit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfErreichbarkeit.setVisible(true);

                }
            };
            jbErreichbarkeitEdit.addActionListener(alErreichbarkeitEdit);
            jbErreichbarkeitNeu.addActionListener(alErreichbarkeitNeu);
            jpErreichbarkeitButton.add(jbErreichbarkeitNeu);
            jpErreichbarkeitButton.add(jbErreichbarkeitEdit);
            jpButtonCenter.add(jpErreichbarkeitButton);
            jpErreichbarkeiten.add(jpButtonCenter);
            jpErreichbarkeit.add(jpErreichbarkeiten);
            jpCenter.add(jpErreichbarkeit);


            //JPanel East
            JPanel jpEast = new JPanel();
            jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

            JPanel jpNotiz = new JPanel();
            jpNotiz.setBorder(new TitledBorder("Notizen"));
            jpNotiz.setLayout(new BoxLayout(jpNotiz, BoxLayout.Y_AXIS));

            JPanel jpNotizListe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JList<Notiz> jlNotizListe = new JList<>(notizen.toArray(new Notiz[notizen.size()]));
            jpNotizListe.add(jlNotizListe);
            JPanel jpNotizButton = new JPanel();
            JButton jbNotizNeu = new JButton("Neu");
            JButton jbNotizEdit = new JButton("Edit");

            ActionListener alNotizEdit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfNotiz = new JFrame("Notiz Ändern");
                    JPanel jpNotiz = new JPanel();
                    JPanel jpText = new JPanel();
                    JTextField jtNotiz = new JTextField(25);
                    jtNotiz.setText(jlNotizListe.getSelectedValue().getBeschreibung());
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlNotizListe.getSelectedValue().setBeschreibung(jtNotiz.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateNotiz(jlNotizListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAend");
                            jfNotiz.dispose();

                        }
                    };
                    ActionListener alAbbrechenNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfNotiz.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernNotiz);
                    jbAbbrechen.addActionListener(alAbbrechenNotiz);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpText.add(jtNotiz);
                    jpNotiz.add(jpText);
                    jpNotiz.add(jpButton);
                    jfNotiz.add(jpNotiz);
                    jfNotiz.setSize(300, 150);
                    jfNotiz.setLocation(500, 500);
                    jfNotiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfNotiz.setVisible(true);

                }
            };


            ActionListener alNotizNeu = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfNotiz = new JFrame("Neue Notiz");
                    JPanel jpNotiz = new JPanel();
                    JPanel jpText = new JPanel();
                    JTextField jtNotiz = new JTextField(25);
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Notiz temp = new Notiz(p, new Date(), jtNotiz.getText());
                            System.out.println(temp.toString());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateNotiz(temp);

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAend");
                            jfNotiz.dispose();

                        }
                    };
                    ActionListener alAbbrechenNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfNotiz.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernNotiz);
                    jbAbbrechen.addActionListener(alAbbrechenNotiz);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpText.add(jtNotiz);
                    jpNotiz.add(jpText);
                    jpNotiz.add(jpButton);
                    jfNotiz.add(jpNotiz);
                    jfNotiz.setSize(300, 150);
                    jfNotiz.setLocation(500, 500);
                    jfNotiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfNotiz.setVisible(true);

                }
            };
            jbNotizNeu.addActionListener(alNotizNeu);
            jbNotizEdit.addActionListener(alNotizEdit);
            jpNotizButton.add(jbNotizEdit);
            jpNotizButton.add(jbNotizNeu);
            jpNotiz.add(jpNotizListe);
            jpNotiz.add(jpNotizButton);
            jpEast.add(jpNotiz);


            jfPersonEdit.add(jpWest, BorderLayout.WEST);
            jfPersonEdit.add(jpCenter, BorderLayout.CENTER);
            jfPersonEdit.add(jpEast, BorderLayout.EAST);


            //JFrame jf Größe mitgeben
            jfPersonEdit.setSize(1024, 768);


            //JFrame jf auf Bildschirm plazieren
            jfPersonEdit.setLocation(200, 400);

            //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind


            //JFrame jf anzeigen
            jfPersonEdit.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public PersonenEditor(OKFZS okfzsInstanz) throws SQLException {
        super(okfzsInstanz);
            JPanel jfPersonEdit = this;
            this.setLayout(new BorderLayout());
            JPanel jpWest = new JPanel();
            jpWest.setLayout(new BoxLayout(jpWest, BoxLayout.Y_AXIS));

            JPanel jpPersonenAngaben = new JPanel();
            jpPersonenAngaben.setBorder(new TitledBorder("Angaben zur Person"));
            jpPersonenAngaben.setLayout(new BoxLayout(jpPersonenAngaben, BoxLayout.Y_AXIS));

            JPanel jpAdressdaten = new JPanel();
            jpAdressdaten.setBorder(new TitledBorder("Adressdaten"));
            jpAdressdaten.setLayout(new BoxLayout(jpAdressdaten, BoxLayout.Y_AXIS));


            JPanel jpSonstigeAngaben = new JPanel();
            jpSonstigeAngaben.setBorder(new TitledBorder("Sonstige Angaben"));
            jpSonstigeAngaben.setLayout(new BoxLayout(jpSonstigeAngaben, BoxLayout.Y_AXIS));

            JPanel jpAnrede = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAnrede = new JLabel("Anrede:");
            String[] Anrede = {"Firma", "Frau", "Herr"};
            JComboBox jcAnredeListe = new JComboBox(Anrede);



            JPanel jpName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlName = new JLabel("Name:");
            JTextField jtName = new JTextField(20);

            jpName.add(jlName);
            jpName.add(jtName);

            JPanel jpVorname = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlVorname = new JLabel("Vorname:");
            JTextField jtVorname = new JTextField(20);

            jpVorname.add(jlVorname);
            jpVorname.add(jtVorname);

            JPanel jpGeburtstag = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlGeburtstag = new JLabel("Geburtstag:");
            JTextField jtGeburtstag = new JTextField(20);


            jpGeburtstag.add(jlGeburtstag);
            jpGeburtstag.add(jtGeburtstag);

            JPanel jpAnschrift = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlAnschrift = new JLabel("Anschrift:");
            JTextField jtAnschrift = new JTextField(20);

            jpAnschrift.add(jlAnschrift);
            jpAnschrift.add(jtAnschrift);

            JPanel jpPlz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlPlz = new JLabel("Postleitzahl:");
            JTextField jtPlz = new JTextField(20);

            jpPlz.add(jlPlz);
            jpPlz.add(jtPlz);

            JPanel jpOrt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlOrt = new JLabel("Ort:");
            JTextField jtOrt = new JTextField(20);

            jpOrt.add(jlOrt);
            jpOrt.add(jtOrt);

            JPanel jpUst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JLabel jlUst = new JLabel("UST-ID:");
            JTextField jtUst = new JTextField(20);

            jpUst.add(jlUst);
            jpUst.add(jtUst);

            jpAnrede.add(jlAnrede);
            jpAnrede.add(jcAnredeListe);

            jpPersonenAngaben.add(jpAnrede);
            jpPersonenAngaben.add(jpVorname);
            jpPersonenAngaben.add(jpName);
            jpPersonenAngaben.add(jpGeburtstag);

            jpAdressdaten.add(jpAnschrift);
            jpAdressdaten.add(jpPlz);
            jpAdressdaten.add(jpOrt);


            JPanel jpButton = new JPanel();
            JButton jbSpeichern = new JButton("Speichern");
            JButton jbAbbrechen = new JButton("Abbrechen");
            jpButton.add(jbSpeichern);
            jpButton.add(jbAbbrechen);
            ActionListener alSpeichern = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Person temp = null;
                    System.out.println(e);
                    try {
                        if("".equals(jtVorname.getText())||jtVorname.getText()==null){
                            temp = new Person(jcAnredeListe.getSelectedItem().toString(),jtName.getText(),umwandeln((jtGeburtstag.getText())));
                            okfzsInstanz.getDatenbank().insertPerson(temp);


                        }
                        else{
                            temp = new Person(jcAnredeListe.getSelectedItem().toString(), jtName.getText(), jtVorname.getText(), umwandeln(jtGeburtstag.getText()), jtAnschrift.getText(), Integer.parseInt(jtPlz.getText()), jtOrt.getText(), jtUst.getText());
                            okfzsInstanz.getDatenbank().insertOrUpdatePerson(temp);
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    okfzsInstanz.anzeigen("personAnz");
                }

            };
            jbSpeichern.addActionListener(alSpeichern);

            ActionListener alAbbrechen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    okfzsInstanz.anzeigen("persAnz");
                }
            };
            jbAbbrechen.addActionListener(alAbbrechen);


            jpSonstigeAngaben.add(jpUst);
            jpSonstigeAngaben.add(jpButton);

            jpWest.add(jpPersonenAngaben);
            jpWest.add(jpAdressdaten);
            jpWest.add(jpSonstigeAngaben);


            //JPanel Center
            JPanel jpCenter = new JPanel();
            jpCenter.setEnabled(false);
            jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

            JPanel jpErreichbarkeit = new JPanel();
            jpErreichbarkeit.setBorder(new TitledBorder("Erreichbarkeiten"));
            jpErreichbarkeit.setLayout(new BoxLayout(jpErreichbarkeit, BoxLayout.Y_AXIS));
            JPanel jpErreichbarkeiten = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JList<Erreichbarkeit> jlErreichbarkeitsListe = new JList<>();
            jlErreichbarkeitsListe.setCellRenderer(new ErrListRenderer());
            JScrollPane jsErreichbarkeitsListe = new JScrollPane(jlErreichbarkeitsListe);
            jpErreichbarkeiten.add(jsErreichbarkeitsListe);
            JPanel jpButtonCenter = new JPanel();
            JPanel jpErreichbarkeitButton = new JPanel();
            JButton jbErreichbarkeitEdit = new JButton("Edit");
            JButton jbErreichbarkeitNeu = new JButton("Neu");

            ActionListener alErreichbarkeitEdit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfErreichbarkeit = new JFrame("Erreichbarkeit Ändern");
                    JPanel jpErreichbarkeiten = new JPanel();
                    JPanel jpDetails = new JPanel();
                    JPanel jpTel = new JPanel();
                    JPanel jpMob = new JPanel();
                    JPanel jpMail = new JPanel();
                    JLabel jlDetails = new JLabel("Details:");
                    JLabel jlTel = new JLabel("Telefonnummer:");
                    JLabel jlMob = new JLabel("Mobil:");
                    JLabel jlMail = new JLabel("E-Mail:");
                    JTextField jtDetails = new JTextField(10);
                    JTextField jtTel = new JTextField(10);
                    JTextField jtMob = new JTextField(10);
                    JTextField jtMail = new JTextField(10);
                    jtDetails.setText(jlErreichbarkeitsListe.getSelectedValue().getDetails());
                    jtTel.setText(jlErreichbarkeitsListe.getSelectedValue().getTelefonNummer());
                    jtMob.setText(jlErreichbarkeitsListe.getSelectedValue().getHandyNummer());
                    jtMail.setText(jlErreichbarkeitsListe.getSelectedValue().getEmail());
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlErreichbarkeitsListe.getSelectedValue().setDetails(jtDetails.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setTelefonNummer(jtTel.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setHandyNummer(jtMob.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setEmail(jtMail.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateErreichbarkeit(jlErreichbarkeitsListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAnl");
                            jfErreichbarkeit.dispose();

                        }
                    };
                    ActionListener alAbbrechenErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfErreichbarkeit.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernErreichbarkeit);
                    jbAbbrechen.addActionListener(alAbbrechenErreichbarkeit);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpDetails.add(jlDetails);
                    jpDetails.add(jtDetails);
                    jpTel.add(jlTel);
                    jpTel.add(jtTel);
                    jpMob.add(jlMob);
                    jpMob.add(jtMob);
                    jpMail.add(jlMail);
                    jpMail.add(jtMail);


                    jpErreichbarkeiten.add(jpDetails);
                    jpErreichbarkeiten.add(jpTel);
                    jpErreichbarkeiten.add(jpMob);
                    jpErreichbarkeiten.add(jpMail);
                    jpErreichbarkeiten.add(jpButton);
                    jfErreichbarkeit.add(jpErreichbarkeiten);
                    jfErreichbarkeit.setSize(800, 150);
                    jfErreichbarkeit.setLocation(500, 500);
                    jfErreichbarkeit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfErreichbarkeit.setVisible(true);

                }
            };


            ActionListener alErreichbarkeitNeu = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfErreichbarkeit = new JFrame("Neue Erreichbarkeit");
                    JPanel jpErreichbarkeiten = new JPanel();
                    JPanel jpDetails = new JPanel();
                    JPanel jpTel = new JPanel();
                    JPanel jpMob = new JPanel();
                    JPanel jpMail = new JPanel();
                    JLabel jlDetails = new JLabel("Details:");
                    JLabel jlTel = new JLabel("Telefonnummer:");
                    JLabel jlMob = new JLabel("Mobil:");
                    JLabel jlMail = new JLabel("E-Mail:");
                    JTextField jtDetails = new JTextField(10);
                    JTextField jtTel = new JTextField(10);
                    JTextField jtMob = new JTextField(10);
                    JTextField jtMail = new JTextField(10);
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlErreichbarkeitsListe.getSelectedValue().setDetails(jtDetails.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setTelefonNummer(jtTel.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setHandyNummer(jtMob.getText());
                            jlErreichbarkeitsListe.getSelectedValue().setEmail(jtMail.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateErreichbarkeit(jlErreichbarkeitsListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAnl");
                            jfErreichbarkeit.dispose();

                        }
                    };
                    ActionListener alAbbrechenErreichbarkeit = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfErreichbarkeit.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernErreichbarkeit);
                    jbAbbrechen.addActionListener(alAbbrechenErreichbarkeit);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpDetails.add(jlDetails);
                    jpDetails.add(jtDetails);
                    jpTel.add(jlTel);
                    jpTel.add(jtTel);
                    jpMob.add(jlMob);
                    jpMob.add(jtMob);
                    jpMail.add(jlMail);
                    jpMail.add(jtMail);


                    jpErreichbarkeiten.add(jpDetails);
                    jpErreichbarkeiten.add(jpTel);
                    jpErreichbarkeiten.add(jpMob);
                    jpErreichbarkeiten.add(jpMail);
                    jpErreichbarkeiten.add(jpButton);
                    jfErreichbarkeit.add(jpErreichbarkeiten);
                    jfErreichbarkeit.setSize(800, 150);
                    jfErreichbarkeit.setLocation(500, 500);
                    jfErreichbarkeit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfErreichbarkeit.setVisible(true);

                }
            };
            jbErreichbarkeitEdit.addActionListener(alErreichbarkeitEdit);
            jbErreichbarkeitNeu.addActionListener(alErreichbarkeitNeu);
            jpErreichbarkeitButton.add(jbErreichbarkeitNeu);
            jpErreichbarkeitButton.add(jbErreichbarkeitEdit);
            jpButtonCenter.add(jpErreichbarkeitButton);
            jpErreichbarkeiten.add(jpButtonCenter);
            jpErreichbarkeit.add(jpErreichbarkeiten);
            jpCenter.add(jpErreichbarkeit);


            //JPanel East
            JPanel jpEast = new JPanel();
            jpEast.setEnabled(false);
            jpEast.setLayout(new BoxLayout(jpEast, BoxLayout.Y_AXIS));

            JPanel jpNotiz = new JPanel();
            jpNotiz.setBorder(new TitledBorder("Notizen"));
            jpNotiz.setLayout(new BoxLayout(jpNotiz, BoxLayout.Y_AXIS));

            JPanel jpNotizListe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JList<Notiz> jlNotizListe = new JList<>();
            jpNotizListe.add(jlNotizListe);
            JPanel jpNotizButton = new JPanel();
            JButton jbNotizNeu = new JButton("Neu");
            JButton jbNotizEdit = new JButton("Edit");

            ActionListener alNotizEdit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfNotiz = new JFrame("Notiz Ändern");
                    JPanel jpNotiz = new JPanel();
                    JPanel jpText = new JPanel();
                    JTextField jtNotiz = new JTextField(25);
                    jtNotiz.setText(jlNotizListe.getSelectedValue().getBeschreibung());
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jlNotizListe.getSelectedValue().setBeschreibung(jtNotiz.getText());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateNotiz(jlNotizListe.getSelectedValue());

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAnl");
                            jfNotiz.dispose();

                        }
                    };
                    ActionListener alAbbrechenNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfNotiz.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernNotiz);
                    jbAbbrechen.addActionListener(alAbbrechenNotiz);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpText.add(jtNotiz);
                    jpNotiz.add(jpText);
                    jpNotiz.add(jpButton);
                    jfNotiz.add(jpNotiz);
                    jfNotiz.setSize(300, 150);
                    jfNotiz.setLocation(500, 500);
                    jfNotiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfNotiz.setVisible(true);

                }
            };


            ActionListener alNotizNeu = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame jfNotiz = new JFrame("Neue Notiz");
                    JPanel jpNotiz = new JPanel();
                    JPanel jpText = new JPanel();
                    JTextField jtNotiz = new JTextField(25);
                    JPanel jpButton = new JPanel();
                    JButton jbSpeichern = new JButton("Speichern");
                    JButton jbAbbrechen = new JButton("Abbrechen");
                    ActionListener alSpeichernNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Notiz temp = new Notiz(selectedPers, new Date(), jtNotiz.getText());
                            System.out.println(temp.toString());
                            try {
                                okfzsInstanz.getDatenbank().insertOrUpdateNotiz(temp);

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            okfzsInstanz.anzeigen("personAnl");
                            jfNotiz.dispose();

                        }
                    };
                    ActionListener alAbbrechenNotiz = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfNotiz.dispose();
                        }
                    };
                    jbSpeichern.addActionListener(alSpeichernNotiz);
                    jbAbbrechen.addActionListener(alAbbrechenNotiz);
                    jpButton.add(jbSpeichern);
                    jpButton.add(jbAbbrechen);
                    jpText.add(jtNotiz);
                    jpNotiz.add(jpText);
                    jpNotiz.add(jpButton);
                    jfNotiz.add(jpNotiz);
                    jfNotiz.setSize(300, 150);
                    jfNotiz.setLocation(500, 500);
                    jfNotiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jfNotiz.setVisible(true);

                }
            };
            jbNotizNeu.addActionListener(alNotizNeu);
            jbNotizEdit.addActionListener(alNotizEdit);
            jpNotizButton.add(jbNotizEdit);
            jpNotizButton.add(jbNotizNeu);
            jpNotiz.add(jpNotizListe);
            jpNotiz.add(jpNotizButton);
            jpEast.add(jpNotiz);


            jfPersonEdit.add(jpWest, BorderLayout.WEST);
            jfPersonEdit.add(jpCenter, BorderLayout.CENTER);
            jfPersonEdit.add(jpEast, BorderLayout.EAST);


            //JFrame jf Größe mitgeben
            jfPersonEdit.setSize(1024, 768);


            //JFrame jf auf Bildschirm plazieren
            jfPersonEdit.setLocation(200, 400);

            //JFrame jf, beim Klicken auf X ist Fenster nicht sichtbar, Programm wird erst geschlossen wenn alle geschlossen sind


            //JFrame jf anzeigen
            jfPersonEdit.setVisible(true);


    }


    public Person getPerson() {
        return selectedPers;
    }

    public static java.sql.Date umwandeln(String datum) {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
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

    static class ErrListRenderer extends JLabel implements ListCellRenderer<Erreichbarkeit>{

        @Override
        public Component getListCellRendererComponent(JList<? extends Erreichbarkeit> list, Erreichbarkeit value, int index, boolean isSelected, boolean cellHasFocus) {

            setText("<html>"+value.getDetails()+"<br>"
                    +"Telefon: "+value.getTelefonNummer()+"<br>"
                    +"Handy: "+value.getHandyNummer()+"<br>"
                    +"E-Mail: "+value.getEmail()+"</html>");

            if (isSelected) {
                setOpaque(true);
                setBorder(new LineBorder(Color.BLACK));
                setBackground(Color.BLUE);
                setForeground(Color.WHITE);
            } else {
                setBorder(null);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            return this;
        }
    }
}

