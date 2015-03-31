/*
 * GRTOT
 *
 * Harjoitus 4, tehtävä 6
 */

package v4t6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import v4t6.Model.Gamerules;
import v4t6.Model.Gamestate;

/**
 * Malli
 */

class Model extends Observable {

    // Pelikenttä: null on vapaa, false on risti, true nolla.
    // Vuoroa vaihdellaan vuoron perään turn-muuttujan tuella.
    private Boolean[][] data;
    private Boolean player;
    private int turn;

    Model ( ) {
        data = new Boolean[3][3];
        player = null;
        turn = 0;
    }

    public void setStarter( Boolean s ) {
        // Voi suorittaa vain kerran.
        if (turn == 0 && s != null) {
            setChanged();
            player = s;
            notifyObservers(new Gamerules(player));
        }
    }

    public boolean isFree( int x, int y ) {
        
        return (data[x][y] == null);
    }

    public boolean doMove( int x, int y ) {

        if (player == null)
            return false;
        
        if (!isFree(x, y))
            return false;

        data[x][y] = player;

        // Tarkistetaan löytyikö voittaja.
        Boolean winner = findWinner();
        boolean gameover = true;

        if (winner == null) {
            // Ei löytynyt, tarkistetaan onko
            // siirtoja jäljellä.
            gameover = !movesLeft();
        }

        Gamestate state = new Gamestate(x, y, player, gameover, winner);

        setChanged();
        notifyObservers(state);

        // Flipataan siirtäjää.
        player = !player;

        return true;
    }

    private Boolean findWinner( ) {
        // Voittosuoria on maksimissaan 8.
        // 1-3: katsotaan vaakarivit
        for (int i = 0; i < 3; i++) {
            if (data[i][0] == data[i][1] && data[i][1] == data[i][2] && data[i][0] != null)
                return data[i][0];
        }

        // 4-6: katsotaan pystyrivit
        for (int i = 0; i < 3; i++) {
            if (data[0][i] == data[1][i] && data[1][i] == data[2][i] && data[0][i] != null)
                return data[0][i];
        }

        // 7-8: katsotaan viistorivit
        if (data[0][0] == data[1][1] && data[1][1] == data[2][2] && data[0][0] != null)
            return data[0][0];
        else if (data[2][0] == data[1][1] && data[1][1] == data[0][2] && data[2][0] != null)
            return data[2][0];

        return null;
    }

    private boolean movesLeft( ) {
        // Täyttyikö pelilauta.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (data[i][j] == null)
                    return true;
            }
        }

        return false;
    }

    // Sisäinen luokka aloittajasta tiedottamiseen.
    class Gamerules {

        private boolean starter;

        Gamerules( boolean s ) {

            starter = s;
        }

        public boolean getStarter( ) {
            return starter;
        }
    }
    
    // Sisäinen luokka pelitilanteen välittämiseen.
    class Gamestate {

        private int x;
        private int y;
        private boolean player;
        private boolean gameover;
        private Boolean winner;

        Gamestate( int x, int y, boolean p, boolean g, Boolean w ) {
            this.x = x;
            this.y = y;
            player = p;
            gameover = g;
            winner = w;
        }

        public int getX( ) {
            return x;
        }

        public int getY( ) {
            return y;
        }

        public boolean getPlayer( ) {
            return player;
        }

        public boolean getGameover( ) {
            return gameover;
        }

        public Boolean getWinner( ) {
            return winner;
        }
    }
}
/**
 * Kontrolleri
 */

class Controller {

    private Model model;
    private View view;

    Controller( Model m, View v ) {
        model = m;
        view = v;

        model.addObserver(view);
    }
}

/**
 * Slider-näkymä
 */

class View extends JFrame implements Observer {

    private JCheckBox starter;
    private JLabel status;
    private JButton[] buttons;
    private Model model;

    View( Model m ) {

        super("v4t6");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        model = m;

        starter = new JCheckBox("Aseta aloittajaksi X");
        starter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model.setStarter(starter.isSelected());
            }
        });

        status = new JLabel("Valitse aloittaja");

        buttons = new JButton[9];

        setLayout(new GridLayout(4, 3));

        for (int i = 0; i < 9; i++) {
            // Luodaan nappi ja säilötään komentoon monesko on.
            JButton btn = new JButton("");
            btn.setActionCommand("" + i);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    // Parsitaan x ja y... tyylikkäästi.
                    JButton btn = (JButton) ae.getSource();
                    String cmd = btn.getActionCommand();
                    int x = Integer.parseInt(cmd) / 3;
                    int y = Integer.parseInt(cmd) % 3;

                    model.doMove(x, y);
                }
            });
            getContentPane().add(btn);
            buttons[i] = btn;
        }

        getContentPane().add(status);
        getContentPane().add(starter);

        pack();
    }

    public void update(Observable o, Object o1) {

        if (o1 == null)
            return;
        else if(o1 instanceof Gamerules) {
            if (((Gamerules) o1).getStarter()) {
                status.setText("X aloittaa");
                starter.setText("Aseta aloittajaksi Y");
            }
            else {
                status.setText("O aloittaa");
                starter.setText("Aseta aloittajaksi X");
            }
        }
        else if (o1 instanceof Gamestate) {
            // Estetään aloittajan vaihto
            starter.setVisible(false);

            Gamestate o2 = (Gamestate) o1;
            String p = getPlayer(o2.getPlayer());
            String text = p + " siirsi " + (1 + o2.getX()) + "," + (1 + o2.getY()) + ".";

            int index = o2.getX() * 3 + o2.getY();
            buttons[index].setText(p);
            buttons[index].removeAll();

            if (o2.getGameover()) {
                // Ei kuunnella enempää.
                for (int i = 0; i < 9; i++)
                    buttons[i].setEnabled(false);

                if (o2.getWinner() != null)
                    text += " Hän voitti!";
                else
                    text += " Tulos on tasapeli.";
            }
            else
                text += " Vuoro vaihtuu.";


            status.setText(text);
        }
    }

    private String getPlayer( Boolean p ) {

        return (p == null ? "" : (p ? "X" : "O"));
    }
}

public class Main {

    public static void main( String[] args ) {

        // MVC nippuun.
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        view.setVisible(true);
    }

}
