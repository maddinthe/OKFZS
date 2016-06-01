package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author mtheilen
 */

/**
 * Ansicht die das Importieren und Exportieren des datenbestandes ermöglicht
 */
public class ImportExport extends Ansicht{
    /**
     * Basiskonstruktor für die Ansicht
     * @param instanz die OKFZ instanz für die diese Ansicht arbeitet
     */
    public ImportExport(OKFZS instanz) {
        super(instanz);

        JTextField file=new JTextField(50);
        JButton auswahl=new JButton("Dateiauswahl");
        JFileChooser fileChooser=new JFileChooser();
        JButton export=new JButton("Export");
        JButton imporT=new JButton("Importieren");
        this.add(new JLabel("Quell/Ziel Datei"));
        this.add(auswahl);
        auswahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(getOKFZSInstanz());
            }
        });
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("ApproveSelection")){
                    file.setText(fileChooser.getSelectedFile().toString());
                }
            }
        });
        this.add(file);
        this.add(export);
        this.add(imporT);
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    bw.append(getAlleDaten());
                    System.out.println(bw);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        imporT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(BufferedReader br=new BufferedReader(new FileReader(fileChooser.getSelectedFile()))){
                    String read;
                    StringBuilder sb=new StringBuilder();
                    while((read=br.readLine())!=null){
                        sb.append(read);
                        sb.append('\n');
                    }
                    setDaten(sb.toString());

                    System.out.println(br);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }

    /**
     * Ausgelagerte Methode die einen Kompletten sql dump mit insert into statements erzeugt
     * @return String mit einem Kompletten sql dump mit insert into statements
     */
    private  String getAlleDaten(){

        return getAlleDaten();
    }

    /**
     * Methode die Insert Into statements aus einem SQL dump ausführt;
     * @param str Mehrzeiliger SQL dump der in die DB eingelesen werden soll
     * @return erfolgsmeldung
     */
    private boolean setDaten(String str){

        return true;
    }
}
