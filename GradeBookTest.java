package JUnit;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeBookTest {
	
	private GradeBook g1;
	private GradeBook g2;
	
	//setUp
	@Before 
	public void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(63);
		g1.addScore(74);
		
		g2 = new GradeBook(5);
		g2.addScore(55);
		g2.addScore(65);
		g2.addScore(75);
		g2.addScore(85);
	}
	
	//tearDown
	@After
	public void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}
	
	@Test
	public void testAddScore() {
		g1.addScore(60);
		assertTrue(g1.toString().equals("63.0 74.0 60.0 "));
		assertEquals(3.0, g1.getScoreSize(), 0.01);
		
		g2.addScore(95);
		assertTrue(g2.toString().equals("55.0 65.0 75.0 85.0 95.0 "));
		assertEquals(5.0, g2.getScoreSize(), 0.01);
	}

	@Test
	public void testSum() {
		assertEquals(137, g1.sum(), 0.01);
		assertEquals(280, g2.sum(), 0.01);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(63.0, g1.minimum(), 0.01);
		assertEquals(55.0, g2.minimum(), 0.01);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(74.0, g1.finalScore(), 0.01);
		assertEquals(225.0, g2.finalScore(), 0.01);
	}

}
