package v2t1;

/*
 * GRTOT
 *
 * Harjoitus 2, tehtävä 1
 *
 * JFrame-luokan toiminnallinen laajennos
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {

	private JLabel label;
	private JTextField tf;
	private JButton btn;

	public Main() {

		super("V2t1");

                // Luodaan komponentit: Teksti t1,
                // leveä syötekenttä sisällöllä "..."
                // ja sen tyhjennyspainike.

		label = new JLabel("t1");
		tf = new JTextField("...", 10);
		btn = new JButton("Tyhjennä");

		getContentPane().setLayout(new FlowLayout());

		getContentPane().add(label);
		getContentPane().add(tf);
		getContentPane().add(btn);
		pack();

		btn.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				tf.setText("");
			}
		});
	}

	public static void main( String[] args ) {

		new Main().setVisible(true);
	}
}

