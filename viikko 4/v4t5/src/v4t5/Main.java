/*
 * GRTOT
 *
 * Harjoitus 4, tehtävä 5
 */

package v4t5;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Main extends JFrame {

    JFileChooser choose;
    JButton load;
    JButton save;
    JTextArea text;
    File file;

    Main( ) {

        super("v4t5");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Alustetaan komponentit
        choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY);

        text = new JTextArea(10, 10);

        load = new JButton("Load");
        load.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                int result = choose.showOpenDialog(Main.this);

                if (result == JFileChooser.APPROVE_OPTION) {

                    // Luetaan tiedosto tekstialueeseen.
                    text.setText("");
                    file = choose.getSelectedFile();

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            text.append(line);
                            text.append(System.getProperty("line.separator"));
                        }

                        reader.close();
                    }
                    catch (Exception e) {
                        // Epähyvä poikkeustilanne.
                    }
                }
            }
        });

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                int result = choose.showOpenDialog(Main.this);

                if (result == JFileChooser.APPROVE_OPTION) {

                    // Kirjoitetaan tekstialueen sisältö tiedostoon.
                    file = choose.getSelectedFile();

                    try {
                        // Alustetaan tiedosto tyhjyydellä
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.flush();
                        writer.write(text.getText());
                        writer.close();
                    }
                    catch (Exception e) {
                        // Epähyvä poikkeustilanne.
                    }
                }
            }
        });

        setLayout(new FlowLayout());
        getContentPane().add(load);
        getContentPane().add(save);
        getContentPane().add(text);
    }

    public static void main(String[] args) {

        Main m = new Main();
        m.setVisible(true);
    }

}
