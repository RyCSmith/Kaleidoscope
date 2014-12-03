package kaleidoscope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author Ryan Smith
 * @author Nicki Hoffman
 */
public class Controller extends JFrame {
	JPanel outerFrame = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton colorButton = new JButton("New Colors");
    JPanel sliderPanel = new JPanel();
    JSlider speedSlider = new JSlider(-6, 6);    
    Timer timer;
    int lastSliderValue;
    Random random = new Random();
    Color[] colorList = {Color.green, Color.GREEN, Color.magenta, Color.MAGENTA, Color.blue, Color.BLUE, Color.yellow, Color.YELLOW, Color.orange, Color.ORANGE, Color.pink, Color.PINK, Color.cyan, Color.CYAN, Color.red, Color.RED};


    /** The Model is the object that does all the computations. It is
     * completely independent of the Controller and View objects. */
    Model model;
    
    /** The View object displays what is happening in the Model. */
    View view;
    
    /**
     * Runs the bouncing ball program.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     */
    private void init() {
        model = new Model();     // The model is independent from the other classes
        view = new View(model);  // The view needs to know what model to look at
        model.addObserver(view); // The model needs to give permission to be observed
        model.getShapes()[0] = new Rectangle(100, 200, 6, 4, 80, 80, Color.green);
        model.getShapes()[1] = new Rectangle(100, 100, 2, 4, 0, 0, Color.MAGENTA);
        model.getShapes()[2] = new Rectangle(100, 100, 4, 2, 10, 10, Color.BLUE);
        model.getShapes()[3] = new Ball(70, 70, 4, 6, 150, 150, Color.orange);
		int[][] coords = new int[][] { {40, 10}, {10, 50}, {80, 20} };
		model.getShapes()[4] = new Polygon(coords, 1, 5, Color.yellow);
        model.getShapes()[5] = new Ball(20, 20, 5, 4, 75, 75, Color.pink);
        model.getShapes()[6] = new Rectangle(30, 3, 6, 4, 4, 50, Color.cyan);
        model.getShapes()[7] = new Ball(5, 30, 6, 3, 0, 0, Color.cyan);
        int[][] coords2 = { {30, 10}, {20, 20}, {30, 30}, {40, 20} };
        model.getShapes()[8] = new Polygon(coords2, 7, 5, Color.red);
        model.getShapes()[9] = new Rectangle(40, 20, 4, 4, 100, 100, Color.green);
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(300, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        outerFrame.setLayout(new BorderLayout());
        outerFrame.setPreferredSize(new Dimension(300,300));
        outerFrame.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(colorButton);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setSnapToTicks(true);
        sliderPanel.add(speedSlider);
        stopButton.setEnabled(false);
        outerFrame.add(BorderLayout.CENTER, view);
        this.add(BorderLayout.CENTER, outerFrame);
        this.add(BorderLayout.SOUTH, sliderPanel);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the Model to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                model.start();
            }
        });
        // The Stop button tells the Model to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                model.pause();
            }
        });
        // The Reset button sets all speeds to same value
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	int randInt;
            	for (Shape shape : model.getShapes()){
            		if (shape != null){
            			randInt = random.nextInt(15);
            			shape.setColor(colorList[randInt]);
            		}
            	}
            }
        });
        // When the window is resized, the Model is given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
            	for (Shape shape : model.getShapes()){
                 	if (shape != null){
                 		 shape.setLimits(view.getWidth(), view.getHeight());
                 	}
            	}
               
            }
        });
        // Speed adjustment slider
        lastSliderValue = 0;
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event)
            {
            	JSlider source = (JSlider) event.getSource();
            	
                if (!source.getValueIsAdjusting()) {
                	int change = (int)source.getValue() - lastSliderValue;
                    lastSliderValue = (int)source.getValue();
                	for (Shape shape : model.getShapes()){
                		if (shape != null){
                			if (shape.getxDelta() > 0 ){
                				shape.setxDelta(shape.getxDelta() + change);
                			}
                			else{
                				shape.setxDelta(shape.getxDelta() - change);
                			}
                			if (shape.getyDelta() > 0){
                				shape.setyDelta(shape.getyDelta() + change);
                			}
                			else{
                				shape.setyDelta(shape.getyDelta() - change);
                			}
                		}
                	}
                }
            }
        });
    }
}