/*
 * GRTOT
 *
 * Harjoitus 3, tehtävä 5
 */

package v3t5;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class Main extends JFrame {

    JRadioButton btn_frac;
    JRadioButton btn_int;
    JSpinner spin_number;
    JCheckBox btn_lock;

    public Main( ) {

        super("v3t5");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        btn_frac = new JRadioButton();
        btn_int = new JRadioButton();
        spin_number = new JSpinner();
        btn_lock = new JCheckBox();

        ButtonGroup group = new ButtonGroup();
        group.add(btn_frac);
        group.add(btn_int);

        btn_int.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!btn_lock.isSelected() && btn_int.isSelected()) {
                    spin_number.setModel(new SpinnerNumberModel(50, 0, 100, 1));
                }
            }
        });

        btn_frac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!btn_lock.isSelected() && btn_frac.isSelected()) {
                    spin_number.setModel(new SpinnerNumberModel(0.5, 0, 1, 0.1));
                }
            }
        });
        
        btn_lock.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (btn_lock.isSelected()) {
                    // Lukitaan tämänhetkiseen arvoon.
                    Object value = spin_number.getValue();
                    spin_number.setModel(new SpinnerNumberModel((Number) value, (Comparable) value, (Comparable) value, 0));

                }
                else {
                    // Luodaan uusi malli.
                    if (btn_int.isSelected())
                        btn_int.doClick();
                    else
                        btn_frac.doClick();
                }

                // Estetään / sallitaan näppäinsyötteet
                ((DefaultEditor) spin_number.getEditor()).getTextField().setEditable(!btn_lock.isSelected());
                
            }
        });
        btn_frac.doClick();
        btn_lock.setSelected(true);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(btn_frac);
        getContentPane().add(btn_int);
        getContentPane().add(spin_number);
        getContentPane().add(btn_lock);

        pack();

    }

    public static void main(String[] args) {

        new Main().setVisible(true);
    }

}
