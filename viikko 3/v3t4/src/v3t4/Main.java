/*
 * GRTOT
 *
 * Harjoitus 3, tehtävä 4
 */

package v3t4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Main extends JFrame implements ActionListener {

    ArrayList<JCheckBox> buttons = new ArrayList<JCheckBox>(25);
    JLabel label;

    public Main( ) {

        super("v3t4");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        label = new JLabel("Kaikki pois");
        getContentPane().setLayout(new GridLayout(6,5));

        // Lisätään 5x5 napit.
        for (int i = 0; i < 25; i++) {
            JCheckBox btn = new JCheckBox();
            buttons.add(btn);

            // Käytetään actionlisteneria jotta .setSelected() ei aktivoisi.
            btn.addActionListener((ActionListener) this);
            getContentPane().add(btn);
        }

        getContentPane().add(label);

        pack();

    }

    public static void main(String[] args) {

        new Main().setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        JCheckBox btn = (JCheckBox) ae.getSource();

        boolean state = btn.isSelected();
        int index = buttons.indexOf(btn);
        int row = index / 5 + 1;
        int col = index % 5 + 1;
        label.setText("Painettu " + row + " rivin " + col + " nappi tilaan " + state + ".");

        // Etsitään ympäröivät painikkeet. Rujosti riville sovitettuna jok'ikinen.
        
        // Yläpuolella on index - 5
        if (row > 1)
            buttons.get(index - 5).setSelected(!buttons.get(index - 5).isSelected());

        // Vasemmalla on index - 1
        if (col > 1)
            buttons.get(index - 1).setSelected(!buttons.get(index - 1).isSelected());

        // Oikealla on index + 1
        if (col < 5)
            buttons.get(index + 1).setSelected(!buttons.get(index + 1).isSelected());

        // Alla on index + 5
        if (row < 5)
            buttons.get(index + 5).setSelected(!buttons.get(index + 5).isSelected());

        // Tarkistetaan ovatko kaikki nyt pois päältä
        Iterator<JCheckBox> it = buttons.iterator();

        boolean oneOn = false;

        while (it.hasNext()) {
            if (it.next().isSelected()) {
                oneOn = true;
                break;
            }
        }

        if (!oneOn)
            label.setText("Kaikki pois!");
    }
}
