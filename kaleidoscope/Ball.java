package kaleidoscope;

import java.awt.Color;

/**
 * Ball extends Shape. It does not overwrite any methods, but allows the View
 * to determine when it should draw the Shape with fillOval.
 * @author Ryan Smith
 * @author Nicki Hoffman
 */
public class Ball extends Shape {

	/** 
	 * Constructs a Ball object with given width, height, x and y speeds,
	 * top left coordinates (x, y), and color.
	 * @param width
	 * @param height
	 * @param xDelta
	 * @param yDelta
	 * @param x
	 * @param y
	 * @param color
	 */
    Ball(int width, int height, int xDelta, int yDelta, int x, int y, Color color) {
    	super();
    	this.width = width;
    	this.height = height;
        this.xDelta = xDelta;
        this.yDelta = yDelta;
        this.x = x;
        this.y = y;
        this.color = color;
        initFacets();
    }

}
