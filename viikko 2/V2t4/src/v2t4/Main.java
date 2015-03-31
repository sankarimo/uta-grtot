package v2t4;

/*
	GRTOT

	Viikko 2, tehtävä 4

	Kuuntelijatapojen esittelyä.
*/

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Kuuntelijaluokka
 */

class ButtonListener implements ActionListener {

    private JLabel label;

    public ButtonListener( JLabel l )
    {
        label = l;
    }

    public void actionPerformed( ActionEvent e ) {

       label.setText(((JButton) e.getSource()).getText());
    }
}

/**
 * Pääluokka
 */

public class Main extends JFrame implements ActionListener {

	private JLabel last;
	private JButton a;
	private JButton b;
	private JButton c;

	public Main()
	{
		super("V2t4");

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                last = new JLabel("-");
                a = new JButton("a");
                b = new JButton("b");
                c = new JButton("c");

                getContentPane().setLayout(new FlowLayout());
		getContentPane().add(last);
                getContentPane().add(a);
                getContentPane().add(b);
                getContentPane().add(c);

		pack();

                // Kuuntelijan toteutus metodina
                a.addActionListener(this);

                // Erillisenä luokkana
                b.addActionListener(new ButtonListener(last));

                // Nimettömänä sisäluokkana
		c.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				last.setText(((JButton) e.getSource()).getText());
			}
		});
	}

	public static void main( String[] args ) {

		new Main().setVisible(true);
	}
        
        public void actionPerformed(ActionEvent e) {

            last.setText(((JButton) e.getSource()).getText());
        }

}

