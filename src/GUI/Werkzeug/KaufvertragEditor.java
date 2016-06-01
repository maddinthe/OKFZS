package GUI.Werkzeug;

import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
public class KaufvertragEditor extends Ansicht {
    /**
     * datumsformatierer für unser verwendetes Datumsformat
     */
    private SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");

    //todo:formatieren der anzeige

    /**
     * Editor des Vorgans zum Ändern und Drucken/Anzeigen des Kaufvertrags
     *
     * @param okfzsInstanz Die Instanz in der Der editor angezigt werden Soll
     * @param vorgang      Der Vorgnag aus dem der Vertrag generiert werden soll
     */
    public KaufvertragEditor(OKFZS okfzsInstanz, Vorgang vorgang) {
        super(okfzsInstanz);
        try {
            List<Person> personenlist = okfzsInstanz.getDatenbank().allePersonen();
            Person[] personen = personenlist.toArray(new Person[personenlist.size()]);
            JComboBox<Person> kaeufer = new JComboBox<>(personen);  //Holt sich alle Möglichen Personen um sie in einer Liste aus Möglichen Käufern anzuzeigen
            List<Verkaeufer> verkaeuferList = okfzsInstanz.getDatenbank().alleVerkaeufer();
            JComboBox<Verkaeufer> verkaeufer = new JComboBox<>(verkaeuferList.toArray(new Verkaeufer[verkaeuferList.size()])); //Holt sich alle Möglichen Verkäufer um sie in einer Liste aus möglichen Verkäufern anzuzeigen
            kaeufer.setSelectedItem((vorgang.getKauefer() != null) ? vorgang.getKauefer() : null);
            verkaeufer.setSelectedItem((vorgang.getVerkaeufer() != null) ? vorgang.getVerkaeufer() : okfzsInstanz.getBenutzer());

            JTextField verkaufspreis;
            if (vorgang.getvPreis() <= 0) {
                verkaufspreis = new JTextField(Double.toString(vorgang.getvPreisPlan()), 5);
            } else
                verkaufspreis = new JTextField(Double.toString(vorgang.getvPreis()), 5);
            this.add(verkaufspreis);
            String vkDatumString = "";
            if (vorgang.getVerkaufsDatum() != null) {
                vkDatumString = dateForm.format(vorgang.getVerkaufsDatum());
            }
            JTextField vkDatum = new JTextField(vkDatumString, 10);
            this.add(vkDatum);
            JTextArea rabatt = new JTextArea(vorgang.getRabattGrund(), 10, 20);
            this.add(rabatt);
            JTextArea vereinbar = new JTextArea(vorgang.getSonstvereinbarungen(), 10, 20);
            this.add(vereinbar);
            Date tuevDate = vorgang.getTuev();
            if (tuevDate == null) tuevDate = new Date();
            JTextField tuev = new JTextField(dateForm.format(tuevDate));
            this.add(tuev);
            JTextField kennz = new JTextField(vorgang.getKennzeichen(), 11);
            this.add(kennz);
            JTextField kfz = new JTextField(vorgang.getKfz().toString());
            this.add(kfz);
            JCheckBox entwurf=new JCheckBox();
            entwurf.setSelected(vorgang.getVerkaeufer()==null||vorgang.getKauefer()==null||vorgang.getVerkaufsDatum().after(new Date())||vorgang.getvPreis()<=0);
            this.add(entwurf);

            JButton speichern = new JButton("Speichern");
            this.add(speichern);
            ActionListener speichernListener = new ActionListener() { //Actionlistener für Speichern und Drucken
                //Speichert den Vorgang in die DB und falls druck auf Drucken für kaufvertragDrucken() aus
                @Override
                public void actionPerformed(ActionEvent e) {
                    vorgang.setVerkaeufer((Verkaeufer) verkaeufer.getSelectedItem());
                    vorgang.setKauefer((Person) kaeufer.getSelectedItem());
                    vorgang.setvPreis(Double.parseDouble(verkaufspreis.getText()));

                    try {
                        vorgang.setVerkaufsDatum(dateForm.parse(vkDatum.getText()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        vorgang.setTuev(dateForm.parse(tuev.getText()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    vorgang.setRabattGrund(rabatt.getText());
                    vorgang.setSonstvereinbarungen(vereinbar.getText());
                    vorgang.setKennzeichen(kennz.getText());

                    try {
                        okfzsInstanz.getDatenbank().insertOrUpdateVorgang(vorgang);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (e.getActionCommand().equals("Vertrag Drucken")) {
                        if(vorgang.getvPreis()!=vorgang.getvPreisPlan()&&(vorgang.getRabattGrund()==null||vorgang.getRabattGrund().equals(""))){
                            JOptionPane.showMessageDialog(okfzsInstanz,"Bitte Rabattgrund eingeben","Kein Rabattgrund",JOptionPane.ERROR_MESSAGE);
                        }else
                        kaufvertragDrucken(vorgang,entwurf.isSelected());
                    }

                }
            };
            speichern.addActionListener(speichernListener);

            JButton vorgDruck = new JButton("Vertrag Drucken");
            this.add(vorgDruck);
            vorgDruck.addActionListener(speichernListener);

            //erstellen des Layouts und einfüllen der daten aus  Vorgang/Datenbank
            JPanel jpMaster = this;
            JPanel jpKvEdit = new JPanel(new GridLayout(2,3));


            JPanel jpKvAngaben = new JPanel();
            jpKvAngaben.setBorder(new TitledBorder("Personendaten für den Verkauf"));
            jpKvAngaben.setLayout(new BoxLayout(jpKvAngaben, BoxLayout.Y_AXIS));

            JPanel jpKaeufer = new JPanel(new GridLayout(2,1));
            JLabel jlKaeufer = new JLabel("Käufer");
            jpKaeufer.add(jlKaeufer);
            jpKaeufer.add(kaeufer);

            JPanel jpVerKaeufer = new JPanel(new GridLayout(2,1));
            JLabel jlVerKaeufer = new JLabel("Verkäufer");
            jpVerKaeufer.add(jlVerKaeufer);
            jpVerKaeufer.add(verkaeufer);

            JPanel jpPreisAngaben = new JPanel();
            jpPreisAngaben.setBorder(new TitledBorder("Verkaufsdaten"));
            jpPreisAngaben.setLayout(new BoxLayout(jpPreisAngaben, BoxLayout.Y_AXIS));

            JPanel jpVerkaufspreis = new JPanel(new GridLayout(2,1));
            JLabel jlVerkaufspreis = new JLabel("Verkaufspreis");
            jpVerkaufspreis.add(jlVerkaufspreis);
            jpVerkaufspreis.add(verkaufspreis);

            JPanel jpVerkaufsdatum = new JPanel(new GridLayout(2,1));
            JLabel jlVerkaufsdatum = new JLabel("Verkaufsdatum");
            jpVerkaufsdatum.add(jlVerkaufsdatum);
            jpVerkaufsdatum.add(vkDatum);

            JPanel jpErgKFZAngaben = new JPanel();
            jpErgKFZAngaben.setBorder(new TitledBorder("Ergänzende KFZ Daten"));
            jpErgKFZAngaben.setLayout(new BoxLayout(jpErgKFZAngaben, BoxLayout.Y_AXIS));

            JPanel jpTuevBis = new JPanel(new GridLayout(2,1));
            JLabel jlTuevBis = new JLabel("TÜV bis:");
            jpTuevBis.add(jlTuevBis);
            jpTuevBis.add(tuev);

            JPanel jpAktKennzeichen = new JPanel(new GridLayout(2,1));
            JLabel jlAktKennzeichen = new JLabel("Aktuelles Kennzeichen");
            jpAktKennzeichen.add(jlAktKennzeichen);
            jpAktKennzeichen.add(kennz);

            JPanel jpRabattGrundTitel = new JPanel();
            jpRabattGrundTitel.setBorder(new TitledBorder("Gründe für einen Rabatt"));
            jpRabattGrundTitel.setLayout(new BoxLayout(jpRabattGrundTitel, BoxLayout.Y_AXIS));

            JPanel jpRabattGrund = new JPanel(new GridLayout(1,1));
            jpRabattGrund.add(rabatt);

            JPanel jpSonderVereinbarungTitel = new JPanel();
            jpSonderVereinbarungTitel.setBorder(new TitledBorder("Besondere Vereinbarungen"));
            jpSonderVereinbarungTitel.setLayout(new BoxLayout(jpSonderVereinbarungTitel, BoxLayout.Y_AXIS));

            JPanel jpSonderVereinbarung = new JPanel(new GridLayout(1,1));
            jpSonderVereinbarung.add(vereinbar);

            JPanel jpAktionTitel = new JPanel();
            jpAktionTitel.setBorder(new TitledBorder("Aktion ausführen"));
            jpAktionTitel.setLayout(new BoxLayout(jpAktionTitel, BoxLayout.Y_AXIS));

            JPanel jpAktion = new JPanel(new GridLayout(3,1));
            JLabel jlEntwurf = new JLabel("Als Entwurf drucken");
            jpAktion.add(speichern);
            jpAktion.add(vorgDruck);
            jpAktion.add(entwurf);
            jpAktion.add(jlEntwurf);


            jpKvAngaben.add(jpKaeufer);
            jpKvAngaben.add(jpVerKaeufer);

            jpPreisAngaben.add(jpVerkaufspreis);
            jpPreisAngaben.add(jpVerkaufsdatum);

            jpErgKFZAngaben.add(jpTuevBis);
            jpErgKFZAngaben.add(jpAktKennzeichen);

            jpRabattGrundTitel.add(jpRabattGrund);
            jpSonderVereinbarungTitel.add(jpSonderVereinbarung);

            jpAktionTitel.add(jpAktion);

            jpKvEdit.add(jpKvAngaben);
            jpKvEdit.add(jpPreisAngaben);
            jpKvEdit.add(jpErgKFZAngaben);
            jpKvEdit.add(jpRabattGrundTitel);
            jpKvEdit.add(jpSonderVereinbarungTitel);
            jpKvEdit.add(jpAktionTitel);

            jpMaster.add(jpKvEdit, BorderLayout.WEST);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Es ist ein fehler aufgetreten:",JOptionPane.ERROR_MESSAGE);
        }




    }

    /**
     * Generiert ein temporäres HTML-Dokument aus dem vorgang welches den Kaufvertrag darstellen soll
     *
     * @param vorgang Vorgang zu dem der Vertrag generiert werden soll
     * @param entwurf gibt an ob der Kaufvertrag ein Entwurf ist oder die finale form hat true=entwurf
     */
    private void kaufvertragDrucken(Vorgang vorgang,boolean entwurf) {
        //todo:fertig machen
        try {
            File f = File.createTempFile("Vertrag_" + vorgang.getVid() + "-", ".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            String tuev = "";
            try {
                tuev = dateForm.format(vorgang.getTuev());
            } catch (Exception e) {

            }
            String vkName = vorgang.getVerkaeufer().getPerson().getAnrede() + " " + vorgang.getVerkaeufer().getPerson().getName() + ", " + vorgang.getVerkaeufer().getPerson().getVorname();
            String kName = vorgang.getKauefer().getAnrede() + " " + vorgang.getKauefer().getName() + ", " + vorgang.getKauefer().getVorname();
            String vkAdresse = vorgang.getVerkaeufer().getPerson().getAnschrift() + ", " + vorgang.getVerkaeufer().getPerson().getPostleitzahl() + " " + vorgang.getVerkaeufer().getPerson().getOrt();
            String kAdresse = vorgang.getKauefer().getAnschrift() + ", " + vorgang.getKauefer().getPostleitzahl() + " " + vorgang.getKauefer().getOrt();
            String unfallCheck = (vorgang.getSchaeden() != null && vorgang.getSchaeden().length() > 1) ? "" : "checked";
            String maerchensteuer = "" + (((vorgang.getvPreis() != 0) ? vorgang.getvPreis() : vorgang.getvPreisPlan()) * 0.1597);
            String brutto = "" + ((vorgang.getvPreis() != 0) ? vorgang.getvPreis() : vorgang.getvPreisPlan());
            String besonderes = vorgang.getSchaeden() + " " + vorgang.getSonstvereinbarungen() + " " + vorgang.getRabattGrund();
            String[] alles = besonderes.split(" ");
            List<String> zeilen = new ArrayList<>();
            String eineZeile = "";

            for (int i = 0; i < alles.length; ) {
                while (eineZeile.length() < 200) {
                    eineZeile += " " + alles[i++];
                    if (i >= alles.length) break;
                }
                zeilen.add(eineZeile.trim());
                eineZeile = "";
            }
            String zeile1 = "" + ((zeilen.size() > 0) ? zeilen.get(0) : "");
            String zeile2 = "" + ((zeilen.size() > 1) ? zeilen.get(1) : "");
            String zeile3 = "" + ((zeilen.size() > 2) ? zeilen.get(2) : "");
            String zeile4 = "" + ((zeilen.size() > 3) ? zeilen.get(3) : "");

            String entwurfString="";
            if(entwurf)
                entwurfString=" Entwurf";

            bw.append("<!DOCTYPE html>\n" +
                    "<html lang=\"de\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Kaufvertrag Nr.:" + vorgang.getVid() + "</title>\n" +
                    "</head>" +
                    "<table style=\"height: 297mm; width: 210mm;\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td align=\"left\" valign=\"top\">\n" +
                    "            <table style=\"width: 277mm;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <h1>Kaufvertrag"+entwurfString+"</h1>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;<img src=\"http://www.ostsee-kfz-service.de/images/template-content/2Logo-Ostsee-Kfz-Service-GmbH.jpg\" alt=\"Logo\" style=\"float: right;\" /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <p></p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <p>Vertrags Nr." + vorgang.getVid() + "</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <h2>&nbsp;Verk&auml;ufer-Informationen</h2>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <h2>&nbsp;K&auml;ufer-Informationen</h2>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vkName + "</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Vor- und Nachname / Firma</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + kName + "</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Vor- und Nachname / Firma</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vkAdresse + "</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Adresse</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + kAdresse + "</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Adresse</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <p>&nbsp;</p>\n" +
                    "            <table style=\"width: 1040px; height: 173px;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td colspan=\"6\" style=\"width: 80px;\">\n" +
                    "                        <h2>Fahrzeug-Informationen</h2>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td style=\"width: 80px;\">Hersteller</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vorgang.getKfz().getHersteller() + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td style=\"width: 80px;\">&nbsp; Modell</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vorgang.getKfz().getModell() + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td style=\"width: 90px;\">Erstzulassung</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + dateForm.format(vorgang.getKfz().getEz()) + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>FahrG-Nr.:</td>\n" +
                    "                    <td colspan=\"5\">\n" +
                    "                        <p>" + vorgang.getKfz().getFin() + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>KM Stand<br />laut Tacho</p>\n" +
                    "                    </td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vorgang.getKilometer() + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td>&nbsp; T&Uuml;V</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + tuev + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td>Brief-Nr.:</td>\n" +
                    "                    <td>\n" +
                    "                        <p>" + vorgang.getKfz().getKfzBriefNr() + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <p>&nbsp;</p>\n" +
                    "            <table style=\"width: 1040px; height: 168px;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td colspan=\"2\">\n" +
                    "                        <h2>Sonstiges</h2>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td colspan=\"3\">\n" +
                    "                        <h2>Kaufpreis</h2>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td><input name=\"unfall\" value=\"unfall\" type=\"checkbox\" " + unfallCheck + "/></td>\n" +
                    "                    <td>\n" +
                    "                        <p>Unfall nicht bekannt</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n" +
                    "                    <td>Netto&nbsp;</td>\n" +
                    "                    <td><input name=\"netto\" value=\"netto\" type=\"checkbox\" /></td>\n" +
                    "                    <td rowspan=\"2\" style=\"width: 230px;\">&euro;</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td><input name=\"motorschaden\" value=\"motorschaden\" type=\"checkbox\" /></td>\n" +
                    "                    <td>\n" +
                    "                        <p>Motorschaden / Getriebeshaden</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&sect;25a UStG</td>\n" +
                    "                    <td><input name=\"25aUStG\" value=\"25aUStG\" type=\"checkbox\" /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td><input name=\"haendlerGeschaeft\" value=\"haendlerGeschaeft\" type=\"checkbox\" /></td>\n" +
                    "                    <td>\n" +
                    "                        <p>Gesch&auml;ft unter H&auml;ndlern ohne Gew&auml;hleistung</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>MwSt. 19%</td>\n" +
                    "                    <td>" + maerchensteuer + "</td>\n" +
                    "                    <td>&euro;</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td valign=\"top\"><input name=\"preisnachlass\" value=\"preisnachlass\" type=\"checkbox\" /></td>\n" +
                    "                    <td>\n" +
                    "                        <p>&euro; ________________ Preisnachlass bildet die H&ouml;he der Selbstbeteiligung<br />im Gew&auml;hrleitungsfall</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>Brutto</td>\n" +
                    "                    <td>" + brutto + "</td>\n" +
                    "                    <td>&euro;</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>Anzahlung</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&euro;</td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <p style=\"text-align: justify;\">&nbsp;</p>\n" +
                    "            <p style=\"text-align: justify;\">Der Verk&auml;ufer beh&auml;lt das Eigentum am Fahrzeug bis zur vollst&auml;ndigen Kaufpreiszahlung. Bei Vertragsbruch verf&auml;llt die Anzahlung. Sofern das Fahrzeug bei der &Uuml;bergabe noch angemeldet ist, versichert der K&auml;ufer das Fahrzeug sp&auml;testens am n&auml;chsten Werktag abzumelden. Im Zuge der Aufbereitung k&ouml;nnen Ausbesserungs- und Lackarbeiten bestehen. Im Gew&auml;hrleitungsfall wird das Fahrzeug auf Kosten des K&auml;ufers zum Verkaufsort gebracht.</p>\n" +
                    "            <h2>Besondere Vereinbarungen:</h2>\n" +
                    "            <table style=\"width: 1040px; height: 83px;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + zeile1 + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + zeile2 + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + zeile3 + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>" + zeile4 + "</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            <p>&nbsp;</p>\n" +
                    "            <p>&nbsp;</p>\n" +
                    "            <p>\n" +
                    "            <table style=\"width: 1040px; height: 23px;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td><hr />\n" +
                    "                        <p>Ort, Datum</p>\n" +
                    "                    </td>\n" +
                    "                    <td><hr />\n" +
                    "                        <p>Unterschrift Verk&auml;ufer</p>\n" +
                    "                    </td>\n" +
                    "                    <td><hr />\n" +
                    "                        <p>Unterschrift K&auml;ufer</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "            </p>\n" +
                    "            <table style=\"width: 1040px; height: 90px; margin-top: 100px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td colspan=\"4\" valign=\"top\" width=\"31%\"><hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td valign=\"top\" width=\"31%\">\n" +
                    "                        <p><span style=\"font-size: 8pt;\"><strong>Bankverbindung:</strong></span><br /><span style=\"font-size: 8pt;\">Bankname: Ostseesparkasse Rostock</span><br /><span style=\"font-size: 8pt;\">IBAN: DE12 1305 0000 0201 0577 60</span><br /><span style=\"font-size: 8pt;\">BIC: NOLADE21ROS</span><br /><span style=\"font-size: 8pt;\">Steuernr.: 079/115/05511</span></p>\n" +
                    "                    </td>\n" +
                    "                    <td valign=\"top\" width=\"25%\">\n" +
                    "                        <p><span style=\"font-size: 8pt;\"><strong>Zust&auml;ndiges Handelsregister</strong></span><br /><span style=\"font-size: 8pt;\">Handelsregister Rostock</span><br /><span style=\"font-size: 8pt;\">HRB-Nr. 13266</span><br /><span style=\"font-size: 8pt;\">Firmensitz: Rostock</span><br /><span style=\"font-size: 8pt;\">USt-ID-Nr: DE301551045</span></p>\n" +
                    "                    </td>\n" +
                    "                    <td valign=\"top\" width=\"23%\">\n" +
                    "                        <p><span style=\"font-size: 8pt;\"><strong>Erreichbarkeiten:</strong></span><br /><span style=\"font-size: 8pt;\">Tel.:&nbsp; +49 (381) 87729707</span><br /><span style=\"font-size: 8pt;\">Fax.: +49 (381) 87729708</span><br /><span style=\"font-size: 8pt;\">info@ostsee-kfz-service.de</span><br /><span style=\"font-size: 8pt;\">www.ostsee-kfz-service.de</span></p>\n" +
                    "                    </td>\n" +
                    "                    <td valign=\"top\" width=\"19%\">\n" +
                    "                        <p><span style=\"font-size: 8pt;\"><strong>Gesch&auml;ftsf&uuml;hrung:</strong></span><br /><span style=\"font-size: 8pt;\">Michael Grewe</span><br /><span style=\"font-size: 8pt;\"><strong>Adresse:</strong></span><br /><span style=\"font-size: 8pt;\">Carl-Hopp-Stra&szlig;e 12</span><br /><span style=\"font-size: 8pt;\">18069 Rostock</span></p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table>");
            bw.close();

            Process p = Runtime.getRuntime().exec("\"" + System.getenv("PROGRAMFILES(X86)") + "\\Google\\Chrome\\Application\\chrome.exe\" " + f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
