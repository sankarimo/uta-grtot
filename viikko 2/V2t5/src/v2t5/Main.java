/*
 * GRTOT
 *
 * Harjoitus 3, tehtävä 4
 */

package v3t4;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Timo
 */
public class Main extends JFrame {

    public Main( ) {

        super("V2t5");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Haetaan kuva
        ImageIcon icon = new ImageIcon("600px-Bullseye.jpg");

        // Luodaan komponentti johon kuva asetetaan
        JLabel label = new JLabel();
        label.setIcon(icon);

        getContentPane().add(label);
        
        pack();

        // Keskittää ikkunan
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        new Main().setVisible(true);
    }

}
