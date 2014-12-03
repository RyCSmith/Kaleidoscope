package kaleidoscope;

import java.awt.Color;

/**
 * The Shape abstract class provides instance variables and methods for 
 * determining movement, reflection positions, and setters/getters for instance
 * variables, to be used by subclass shapes.
 * @author Ryan Smith
 * @author Nicki Hoffman
 *
 */
public abstract class Shape {
	protected int width;
	protected int height;
    protected int xLimit, yLimit, xMirrorLimit, yMirrorLimit;
    protected int xDelta;
    protected int yDelta;
	protected int x;
    protected int y;
    protected int[][] facets = new int[8][4];
    protected Color color;

    
	Shape(){
	}
	
	/** Initializes facets variable. For each facets[i], populates facets[i][2] 
	 *  with the facet's width and facets[i][3] with the facet's height. */
	public void initFacets() {
		facets = new int[][] { {0, 0, width, height},
							   {0, 0, width, height},
							   {0, 0, width, height},
							   {0, 0, width, height},
							   {0, 0, height, width},
							   {0, 0, height, width},
							   {0, 0, height, width},
							   {0, 0, height, width} };
	}
	
    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - width;
        this.yLimit = yLimit - height;
    	xMirrorLimit = yLimit - width;
    	yMirrorLimit = xLimit - height;
        x = Math.min(x, xLimit);
        y = Math.min(y, yLimit);
    }

    /**
     * @return The Shape's X position.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Shape's Y position.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        x += xDelta;
        if (x < 0 || x >= xLimit) {
            xDelta = -xDelta;
            x += xDelta;
        }
        y += yDelta;
        if (y < 0 || y >= yLimit) {
            yDelta = -yDelta;
            y += yDelta;
        }
    }
    
    /** @return A coordinate of the ball reflected across a vertical or 
     * horizontal axis. */
    public int flip(int value, int size, int limit) {
    	int centerOffset = 1 - limit % 2;
    	return (value - limit / 2 + centerOffset + size) * -1 + limit / 2;
    }
    
    /** @return A coordinate of the ball reflected across a diagonal axis. */
    public int mirror(int value, int limit1, int limit2) {
    	return value * limit1 / limit2;
    }
    
    /** Computes & stores the x, y coordinates of each of the object's
     *  reflections (including the original) in facets; facets[i][0] = x,
     *  facets[i][1] = y. */
    public void makeFacets() {
    	int winW = xLimit + width;
    	int winH = yLimit + height;
    	facets[0][0] = x;
    	facets[0][1] = y;
    	facets[1][0] = flip(x, width, winW);
    	facets[1][1] = y;
    	facets[2][0] = x;
    	facets[2][1] = flip(y, height, winH);
    	facets[3][0] = flip(x, width, winW);
    	facets[3][1] = flip(y, height, winH);
    	facets[4][0] = mirror(y, yMirrorLimit, yLimit);
    	facets[4][1] = mirror(x, xMirrorLimit, xLimit);
    	facets[5][0] = flip(mirror(y, yMirrorLimit, yLimit), height, winW);
    	facets[5][1] = mirror(x, xMirrorLimit, xLimit);
    	facets[6][0] = mirror(y, yMirrorLimit, yLimit);
    	facets[6][1] = flip(mirror(x, xMirrorLimit, xLimit), width, winH);
    	facets[7][0] = flip(mirror(y, yMirrorLimit, yLimit), height, winW);
    	facets[7][1] = flip(mirror(x, xMirrorLimit, xLimit), width, winH);
    }

    /** @return int[][] facets - an array each of whose elements is an array 
     * representing one of Shape's reflections as {x, y, width, height}. */
	public int[][] getFacets() {
    	makeFacets();
    	return facets;
    }
    
    /** @return Shape's width */
    public int getWidth() {
		return width;
	}

    /** Sets Shape's width.
     * @param width - New width for Shape (integer) */
	public void setWidth(int width) {
		this.width = width;
	}

	/** @return Shape's height */
	public int getHeight() {
		return height;
	}

	/** Sets Shape's height.
	 * @param height - New height for Shape. */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return Shape's color as Color object.
	 */
	public Color getColor(){
		return color;
	}
	
	/** 
	 * Sets the Shape's color.
	 * @param color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return Current speed in x direction.
	 */
	public int getxDelta() {
		return xDelta;
	}

	/**
	 * Sets the x-direction speed.
	 * @param xDelta -  integer speed to set for x direction.
	 */
	public void setxDelta(int xDelta) {
		this.xDelta = xDelta;
	}

	/** 
	 * @return Current speed in y direction.
	 */
	public int getyDelta() {
		return yDelta;
	}

	/**
	 * Sets the y-direction speed.
	 * @param yDelta - integer speed to set for y direction.
	 */
	public void setyDelta(int yDelta) {
		this.yDelta = yDelta;
	}
	
}
