package GUI.Werkzeug;

import Datenhaltung.Person;
import Datenhaltung.Verkaeufer;
import Datenhaltung.Vorgang;
import GUI.Ansicht;
import GUI.OKFZS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mtheilen on 23.05.2016.
 */
public class KaufvertragEditor extends Ansicht {

    //todo:fertgmachen und doku
    public KaufvertragEditor(OKFZS okfzsInstanz, Vorgang vorgang){
        super(okfzsInstanz);
        KaufvertragEditor dieser=this;
        try{
            List<Person> personenlist=okfzsInstanz.getDatenbank().allePersonen();
            Person[] personen=personenlist.toArray(new Person[personenlist.size()]);
            JComboBox<Person> kaeufer=new JComboBox<>(personen);
            List<Verkaeufer> verkaeuferList=okfzsInstanz.getDatenbank().alleVerkaeufer();
            JComboBox<Verkaeufer> verkaeufer=new JComboBox<>(verkaeuferList.toArray(new Verkaeufer[verkaeuferList.size()]));
            JComboBox<Verkaeufer> einkaeufer=new JComboBox<>(verkaeuferList.toArray(new Verkaeufer[verkaeuferList.size()]));

            kaeufer.setSelectedItem((vorgang.getKauefer()!=null)?vorgang.getKauefer():null);

            verkaeufer.setSelectedItem((vorgang.getVerkaeufer()!=null)?vorgang.getVerkaeufer():null);

            einkaeufer.setSelectedItem((vorgang.getEinkaeufer()!=null)?vorgang.getEinkaeufer():null);

            this.add(new JLabel("Käufer "));
            this.add(kaeufer);
            this.add(new JLabel("Verkäufer"));
            this.add(verkaeufer);
            this.add(new JLabel("Einkäufer"));
            this.add(einkaeufer);
            JButton speichern=new JButton("Speichern");
            this.add(speichern);
            ActionListener speichernListener=new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };
            speichern.addActionListener(speichernListener);

            JButton vorgDruck=new JButton("Vertrag Drucken");
            this.add(vorgDruck);
           // vorgDruck.addActionListener(speichernListener);
            vorgDruck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dieser.kaufvertragDrucken(vorgang);
                }
            });

        }
        catch(Exception e){
            this.add(new JTextField("Es ist ein fehler aufgetreten: /n"+e.getMessage()));
        }

    }

    private void kaufvertragDrucken(Vorgang vorgang) {
        try{
            File f=File.createTempFile("Ver"+vorgang.getVid(),"html");
            BufferedWriter bw=new BufferedWriter(new FileWriter(f));
            bw.append("<table style=\"height: 297mm; width: 210mm;\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td align=\"left\" valign=\"top\">\n" +
                    "            <table style=\"width: 277mm;\">\n" +
                    "                <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <h1>Kaufvertrag</h1>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&nbsp;<img src=\"http://www.ostsee-kfz-service.de/images/template-content/2Logo-Ostsee-Kfz-Service-GmbH.jpg\" alt=\"Logo\" style=\"float: right;\" /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <p>Steuer Nr:</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <p>Rechnungs Nr.</p>\n" +
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
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Vor- und Nachname / Firma</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Vor- und Nachname / Firma</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr />\n" +
                    "                        <p>Adresse</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
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
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td style=\"width: 80px;\">&nbsp; Modell</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td style=\"width: 90px;\">Erstzulassung</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>FahrG-Nr.:</td>\n" +
                    "                    <td colspan=\"5\">\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>KM Stand<br />laut Tacho</p>\n" +
                    "                    </td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td>&nbsp; T&Uuml;V</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                    <td>Brief-Nr.:</td>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
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
                    "                    <td><input name=\"unfall\" value=\"unfall\" type=\"checkbox\" /></td>\n" +
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
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>&euro;</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td valign=\"top\"><input name=\"preisnachlass\" value=\"preisnachlass\" type=\"checkbox\" /></td>\n" +
                    "                    <td>\n" +
                    "                        <p>&euro; ________________ Preisnachlass bildet die H&ouml;he der Selbstbeteiligung<br />im Gew&auml;hrleitungsfall</p>\n" +
                    "                    </td>\n" +
                    "                    <td>&nbsp;</td>\n" +
                    "                    <td>Brutto</td>\n" +
                    "                    <td>&nbsp;</td>\n" +
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
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
                    "                        <hr /></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>&nbsp;</p>\n" +
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

            Process p=Runtime.getRuntime().exec("\""+System.getenv("PROGRAMFILES(X86)")+"\\Google\\Chrome\\Application\\chrome.exe\" "+f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
