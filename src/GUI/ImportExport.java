package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by tkertz on 23.05.2016.
 */
public class ImportExport extends Ansicht{
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
                    //todo: lesen einbauen
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
                    //todo:schreiben einbauen
                    System.out.println(br);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }

    public  String getAlleDaten(){
        return getAlleDaten();
    }

    public boolean setDaten(String str){
        return true;
    }
}
