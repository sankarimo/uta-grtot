/*
 * GRTOT
 *
 * Harjoitus 3, tehtävä 6
 */

package v3t6;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Main extends JFrame {

    JButton insert;
    JButton reverse;
    JTextField input;
    JTextArea storage;

    public Main( ) {

        super("v3t6");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        insert = new JButton("Insert");
        reverse = new JButton("Reverse");
        input = new JTextField(10);
        storage = new JTextArea(5, 10);

        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = input.getText();

                if (text.length() > 0) {
                    input.setText("");
                    String all = storage.getText();
                    int pos = storage.getCaretPosition();
                    String before = all.substring(0, pos);
                    String after = all.substring(pos);
                    storage.setText(before + text + after);
                }
            }
        });

        reverse.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String selected = storage.getSelectedText();

               if (selected != null) {
                   int start = storage.getSelectionStart();
                   int end = storage.getSelectionEnd();

                   String all = storage.getText();
                   String reversed = new StringBuffer(selected).reverse().toString();
                   String before = all.substring(0, start);
                   String after = all.substring(end);
                   storage.setText(before + reversed + after);
                   storage.select(start, end);
               }
           }
        });

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(insert);
        getContentPane().add(reverse);
        getContentPane().add(input);
        getContentPane().add(storage);

        pack();

    }

    public static void main(String[] args) {

        new Main().setVisible(true);
    }

}
