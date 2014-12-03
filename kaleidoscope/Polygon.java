package kaleidoscope;

import java.awt.Color;

/**
 * Polygon extends Shape and adds methods and instance variables that make it 
 * compatible with the drawing methods drawPolygon and fillPolygon.
 * Overwrites the Shape method makeFacets().
 * 
 * (Does not use the inherited variable facets, so inherited method getFacets() 
 * will not return the Polygon's reflections. Instead use getFacetsPolygon().)
 * 
 * @author Nicki Hoffman
 * @author Ryan Smith
 */
public class Polygon extends Shape {
	protected int[] xArray, yArray;
	protected int n = 3;
	protected int[][][] pFacets;
	
	/** Constructs a Polygon with a set of vertices (x[i], y[i]) that has 
	 * 	initial speeds dx and dy and color c.
	 *  @param coords: A 2-d int array of the Polygon's {x, y} coordinate pairs
	 *  @param dx: Initial int speed in x direction
	 *  @param dy: Initial int speed in y direction
	 *  @param c: Initial color (as a Color)
	 */
	Polygon(int[][] coords, int dx, int dy, Color c) {
		super();
		xDelta = dx;
		yDelta = dy;
		color = c;
		n = coords.length;
		pFacets = new int[8][2][n];
		xArray = new int[n];
		yArray = new int[n];
		for (int i = 0; i < n; i++) {
			xArray[i] = coords[i][0];
			yArray[i] = coords[i][1];
		}
		x = arrayMin(xArray);
		y = arrayMin(yArray);
		width = arrayMax(xArray) - x;
		height = arrayMax(yArray) - y;
		for (int i = 0; i < n; i++) {
			xArray[i] = xArray[i] - x;
			yArray[i] = yArray[i] - y;
		}
	}
	
	/**
	 * @return The number n of vertices in the Polygon.
	 */
	int getN() {
		return n;
	}
	
	/** @return The least value in the array.
	 *  @param Array of integers to compare. */
	int arrayMin(int[] arr) {
		int min = arr[0];
		for (int i=0; i < arr.length - 1; i++) {
			min = Math.min(arr[i], arr[i+1]);
		}
		return min;
	}
	
	/** @return The greatest value in the array.
	 *  @param Array of integers to compare. */
	int arrayMax(int[] arr) {
		int max = arr[0];
		for (int i=0; i < arr.length - 1; i++) {
			max = Math.max(arr[i], arr[i+1]);
		}
		return max;
	}
    
    /** Computes & stores the x, y coordinates of each of the object's
     *  reflections/facets (including the original) in facets; 
     *  facets[i][0][] = x's for facet i, facets[i][1][] = y's for facet i. */
    public void makeFacets() {
    	int winW = xLimit + width;
    	int winH = yLimit + height;
    	for (int i = 0; i < n; i++) {
    		pFacets[0][0][i] = x + xArray[i];
    		pFacets[0][1][i] = y + yArray[i];
    		pFacets[1][0][i] = flip(x, width, winW) - xArray[i] + width;
    		pFacets[1][1][i] = y + yArray[i];
    		pFacets[2][0][i] = x + xArray[i];
    		pFacets[2][1][i] = flip(y, height, winH) - yArray[i] + height;
    		pFacets[3][0][i] = flip(x, width, winW) - xArray[i] + width;
    		pFacets[3][1][i] = flip(y, height, winH) - yArray[i] + height;
    		pFacets[4][0][i] = mirror(y, yMirrorLimit, yLimit) + yArray[i];
    		pFacets[4][1][i] = mirror(x, xMirrorLimit, xLimit) + xArray[i];
    		pFacets[5][0][i] = flip(mirror(y, yMirrorLimit, yLimit), height, winW) - yArray[i] + height;
    		pFacets[5][1][i] = mirror(x, xMirrorLimit, xLimit) + xArray[i];
    		pFacets[6][0][i] = mirror(y, yMirrorLimit, yLimit) + yArray[i];
    		pFacets[6][1][i] = flip(mirror(x, xMirrorLimit, xLimit), width, winH) - xArray[i] + width;
    		pFacets[7][0][i] = flip(mirror(y, yMirrorLimit, yLimit), height, winW) - yArray[i] + height;
    		pFacets[7][1][i] = flip(mirror(x, xMirrorLimit, xLimit), width, winH) - xArray[i] + width;
    	}
    }
    
    /** @return int[][][] pFacets - an array each of whose elements represents 
     *  one facet, as the pair of arrays containing the x values and y values 
     *  for that facet. */
	public int[][][] getFacetsPolygon() {
    	makeFacets();
    	return pFacets;
    }

}
