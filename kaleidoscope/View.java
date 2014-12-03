package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author Ryan Smith
 * @author Nicki Hoffman
 */
public class View extends JPanel implements Observer {
    
    /** This is what we will be observing. */
    Model model;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Model model) {
        this.model = model;
    }

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Shape shape : model.getShapes()){
        	if (shape != null){
        		g.setColor(shape.getColor());
        		if (shape instanceof Polygon) {
        			for (int[][] coords : ((Polygon) shape).getFacetsPolygon()) {
            			g.fillPolygon(coords[0], coords[1], ((Polygon) shape).getN());
        			}
        		} else {
	        		for(int[] coords: shape.getFacets()){
	        			if (shape instanceof Ball) {
	            			g.fillOval(coords[0], coords[1], coords[2], coords[3]);
	        			} else if (shape instanceof Rectangle) {
	            			g.fillRect(coords[0], coords[1], coords[2], coords[3]);
	        			}
	        		}
        		}
        	}
        }
        
    }

    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
}