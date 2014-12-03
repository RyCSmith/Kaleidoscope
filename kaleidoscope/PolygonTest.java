package kaleidoscope;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
/**
 * Provides a test for the methods Make and Get Facets specific to Polygon class.
 * @author Ryan Smith
 * @author Nicki Hoffman
 *
 */
public class PolygonTest {
	Shape shape3;

	@Before
	public void setUp() throws Exception {
		int[][] coords = new int[][] { {40, 10}, {10, 50}, {80, 20} };
		shape3 = new Polygon(coords, 1, 5, Color.yellow);
	}

	@Test
	public void testMakeAndGetFacets() {
		shape3.setLimits(1000, 1000);
		shape3.makeFacets();
		assertArrayEquals(shape3.facets, shape3.getFacets());
	}

}
