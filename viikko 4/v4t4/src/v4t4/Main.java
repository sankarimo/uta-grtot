/*
 * GRTOT
 *
 * Harjoitus 4, tehtävä 4
 */

package v4t4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Malli
 */

class Model extends Observable {

    private ArrayList <String> data;
    private int index;

    Model (ArrayList<String> d) {
        data = d;
        index = 0;
    }

    public int getIndex( ) {

        return index;
    }

    public int getMin( ) {
        
        return 0;
    }
    
    public int getMax( ) {
        
        return data.isEmpty() ? 0 : data.size() - 1;
    }

    public String getValue( ) {

        return data.get(index);
    }

    public void setIndex( int i ) {

        if (i >= 0 && i <= (data.isEmpty() ? 0 : data.size() - 1)) {
            index = i;
            setChanged();
            notifyObservers();
        }
    }

    public void setIndex( String t ) {

        // Etsitään täsmäävän tekstin indeksi. Ei löytyessä -1.
        setIndex(data.indexOf(t));
    }
}
/**
 * Kontrolleri
 */

class Controller {

    private Model model;
    private SliderView slider;
    private TextView text;

    Controller( Model m, SliderView s, TextView t ) {
        model = m;
        slider = s;
        text = t;

        // Kuuntelijat kuntoon
        slider.addListener(new SliderListener());
        text.addListener(new TextListener());

        model.addObserver(slider);
        model.addObserver(text);
    }

    class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent ce) {

            JSlider s = (JSlider) ce.getSource();
            int index = s.getValue();

            model.setIndex(index);
        }
    }

    class TextListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

               JTextField t = (JTextField) ae.getSource();
               String value = t.getText();

               model.setIndex(value);
        }
    }
}

/**
 * Slider-näkymä
 */

class SliderView extends JFrame implements Observer {
    
    private JSlider slider;
    private Model model;
    
    SliderView( Model m ) {
        
        super("v4t4");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        model = m;

        slider = new JSlider(JSlider.HORIZONTAL, model.getMin(), model.getMax(), 1);
        
        setLayout(new FlowLayout());
        getContentPane().add(slider);
        
        pack();
    }
    
    void addListener( ChangeListener cl ) {
        slider.addChangeListener(cl);
    }

    public void update(Observable o, Object o1) {

        slider.setValue(model.getIndex());
    }
}

/**
 * TextField-näkymä
 */

class TextView extends JFrame implements Observer {

    private JTextField text;
    private Model model;

    TextView( Model m ) {

        super("v4t4");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        model = m;

        text = new JTextField(model.getValue(), 10);

        setLayout(new FlowLayout());
        getContentPane().add(text);

        pack();
    }

    void addListener( ActionListener al ) {
        text.addActionListener(al);
    }

    public void update(Observable o, Object o1) {

        text.setText(model.getValue());
    }
}

public class Main {

    public static void main( String[] args ) {

        // Luodaan ja alustetaan malli
        ArrayList<String> data = new ArrayList<String>();
        data.add("että");
        data.add("jotta");
        data.add("koska");
        data.add("kun");
        data.add("jos");
        data.add("vaikka");
        data.add("kuin");
        data.add("kunnes");
        data.add("entten");
        data.add("tentten");
        data.add("teelikamentten");
        data.add("pää");
        data.add("olkapää");
        data.add("peppu");
        data.add("polvet");
        data.add("varpaat");

        Model model = new Model(data);

        // Luodaan näkymät ja kontrolleri
        SliderView slider = new SliderView(model);
        TextView text = new TextView(model);
        Controller controller = new Controller(model, slider, text);
        
        text.setVisible(true);
        slider.setVisible(true);
    }

}
