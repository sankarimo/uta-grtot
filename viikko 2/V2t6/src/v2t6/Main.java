package v2t6;

/**
 *	GRTOT
 *
 *	Viikko 2, tehtävä 6
 *
 *	Kuvan mukainen tekstinvalikointilomake.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Main extends JFrame {

	private JLabel label_status;
	private JLabel label_chosen;
	private JButton btn_refresh;
	private JButton btn_prev;
	private JButton btn_next;
	private String[] texts = new String[7];
	private int current = 2;

	public Main()
	{
		super("V2t6");

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		texts[0] = "aaa";
		texts[1] = "bbb";
		texts[2] = "ccc";
		texts[3] = "ddd";
		texts[4] = "eee";
                texts[5] = "fff";
                texts[6] = "ggg";

		label_status = new JLabel(texts[current], SwingConstants.CENTER);
		label_chosen = new JLabel("", SwingConstants.RIGHT);
		btn_refresh = new JButton("Päivitä");
		btn_prev = new JButton("<-");
		btn_next = new JButton("->");



		label_chosen.setPreferredSize(new Dimension(450, 40));
		btn_refresh.setPreferredSize(new Dimension(450, 40));
		btn_prev.setPreferredSize(new Dimension(100, 160));
		btn_next.setPreferredSize(new Dimension(100, 160));

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(btn_refresh, BorderLayout.PAGE_START);
		getContentPane().add(btn_prev, BorderLayout.LINE_START);
		getContentPane().add(btn_next, BorderLayout.LINE_END);
		getContentPane().add(label_status, BorderLayout.CENTER);
		getContentPane().add(label_chosen, BorderLayout.PAGE_END);

		pack();

		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				label_chosen.setText(texts[current]);
			}
		});

		btn_prev.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				current = current > 0 ? current - 1: texts.length - 1;

				label_status.setText(texts[current]);
			}
		});

		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				current = current < texts.length - 1 ? current + 1 : 0;

				label_status.setText(texts[current]);
			}
		});
	}

	public static void main( String[] args ) {

		new Main().setVisible(true);
	}
}

