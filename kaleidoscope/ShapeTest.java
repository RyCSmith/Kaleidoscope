package kaleidoscope;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
/**
 * Provides unit tests for the Shape class.
 * @author Ryan Smith
 * @author Nicki Hoffman
 *
 */
public class ShapeTest {
	Shape shape1;
	Shape shape2;
	
	@Before
	public void setUp() throws Exception {
		shape1 = new Rectangle(90, 200, 6, 4, 80, 80, Color.green);
		shape2 = new Ball(70, 70, 4, 6, 150, 150, Color.orange);
	}

	@Test
	public void testSetLimits() {
		assertEquals(0, shape1.xLimit);
		shape1.setLimits(100, 100);
		assertEquals(10, shape1.xLimit);
		
		assertEquals(0, shape2.xLimit);
		shape2.setLimits(200, 200);
		assertEquals(130, shape2.xLimit);
	}

	@Test
	public void testGetX() {
		assertEquals(80, shape1.getX());
		shape1.x = 100;
		assertEquals(100, shape1.getX());
		
		assertEquals(150, shape2.getX());
		shape2.x = 5;
		assertEquals(5, shape2.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(80, shape1.getY());
		shape1.y = 100;
		assertEquals(100, shape1.getY());
		
		assertEquals(150, shape2.getY());
		shape2.y = 1000;
		assertEquals(1000, shape2.getY());
	}

	@Test
	public void testMakeOneStep() {
		assertEquals(80, shape1.getX());
		assertEquals(80, shape1.getY());
		shape1.setLimits(1000, 1000);
		shape1.makeOneStep();
		assertEquals(86, shape1.getX());
		assertEquals(84, shape1.getY());

	}

	@Test
	public void testMirror() {
		assertEquals(10, shape1.mirror(10, 100, 100));
		assertEquals(4, shape2.mirror(2, 10, 5));
	}

	@Test
	public void testMakeAndGetFacets() {
		shape1.setLimits(1000, 1000);
		shape1.makeFacets();
		assertArrayEquals(shape1.facets, shape1.getFacets());
		
		shape2.setLimits(1000, 1000);
		shape2.makeFacets();
		assertArrayEquals(shape2.facets, shape2.getFacets());
	}

	@Test
	public void testGetAndSetWidth() {
		assertEquals(90, shape1.getWidth());
		shape1.setWidth(200);
		assertEquals(200, shape1.getWidth());
		
		assertEquals(70, shape2.getWidth());
		shape2.setWidth(5550);
		assertEquals(5550, shape2.getWidth());
	}

	@Test
	public void testGetAndGetHeight() {
		assertEquals(200, shape1.getHeight());
		shape1.setHeight(40);
		assertEquals(40, shape1.getHeight());
		
		assertEquals(70, shape2.getHeight());
		shape2.setHeight(350);
		assertEquals(350, shape2.getHeight());
	}


	@Test
	public void testGetAndSetColor() {
		assertEquals(Color.green, shape1.getColor());
		shape1.setColor(Color.BLUE);
		assertEquals(Color.BLUE, shape1.getColor());
		
		assertEquals(Color.orange, shape2.getColor());
		shape2.setColor(Color.red);
		assertEquals(Color.red, shape2.getColor());
	}

	@Test
	public void testGetAndSetxDelta() {
		assertEquals(6, shape1.getxDelta());
		shape1.setxDelta(15);
		assertEquals(15, shape1.getxDelta());
		
		assertEquals(4, shape2.getxDelta());
		shape2.setxDelta(1);
		assertEquals(1, shape2.getxDelta());
	}

	@Test
	public void testGetAndSetyDelta() {
		assertEquals(4, shape1.getyDelta());
		shape1.setyDelta(25);
		assertEquals(25, shape1.getyDelta());
		
		assertEquals(6, shape2.getyDelta());
		shape2.setyDelta(123);
		assertEquals(123, shape2.getyDelta());
	}

}
