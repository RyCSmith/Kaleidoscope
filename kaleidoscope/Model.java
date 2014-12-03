package kaleidoscope;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Model class for a kaleidoscope. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author Ryan Smith
 * @author Nicki Hoffman
 */
public class Model extends Observable {

    private Timer timer;
    private Shape[] shapeArray = new Shape[10];
    
    /**
     * Tells the ball to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the ball to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, 40); // 25 times a second        
    }
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /** 
     * @return Array of Shapes to be drawn.
     */
    public Shape[] getShapes(){
    	return shapeArray;
    }
    
    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
        	for (Shape shape : shapeArray){
             	if (shape != null){
             		shape.makeOneStep();
             	}
        	}
            // Notify observers
            setChanged();
            notifyObservers();
        }
    }
}