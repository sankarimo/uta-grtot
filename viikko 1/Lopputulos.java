package buttondemo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements ActionListener {
    private JButton button;
    private JLabel label;
    private JButton reset;

    public Main()
    {
        super("Button Demo week 1");
        button = new JButton("PRESS ME!");
        label = new JLabel("flip");
        reset = new JButton("Reset state");

        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(label);
        this.getContentPane().add(button);
        this.getContentPane().add(reset);
        this.pack();

        button.addActionListener(this);
        button.setActionCommand("NAPPI");

        reset.addActionListener(this);
        reset.setActionCommand("RESET");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand() == "NAPPI")
            label.setText(label.getText().equals("flip")?"flop":"flip");
        else
            label.setText("flip");
    }

    public static void main(String[] args) {
        new Main().setVisible(true);

    }

}
