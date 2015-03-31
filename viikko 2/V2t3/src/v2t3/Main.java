package v2t3;
/*
 * GRTOT
 *
 * Viikko 2, tehtävä 3
 *
 * Viisi nappia, horisontaalinen keskitys.
 *
 * BoxLayoutin ja Flowlayoutin ero on siinä, että box latoo
 * komponentteja halutusti joko rinnakkain tai allekkain,
 * sallien niiden suhteellisen sijoittamisen. Flow puolestaa
 * latoo komponentit peräjälkeen rinnakain, aloittaen uuden
 * rivin edellisen täyttyessä.
 *
 */

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame {

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;

	public Main()
	{
		super("V2t3");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");

		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		getContentPane().add(btn1);
		getContentPane().add(btn2);
		getContentPane().add(btn3);
		getContentPane().add(btn4);
		getContentPane().add(btn5);
                
		pack();
	}

	public static void main( String[] args ) {

		new Main().setVisible(true);
	}
}

