import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {
	private Town town1, town2;
	
	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("Town_1");
		town2 = new Town("Town_2");
	}

	@AfterEach
	void tearDown() throws Exception {
		town1 = town2 = null;
	}

	@Test
	void testGetName() {
		assertEquals("Town_1", town1.getName());
		assertEquals("Town_2", town2.getName());
	}

	@Test
	void testCompareTo() {
		assertEquals(0, town1.compareTo(new Town("Town_1")));
		assertEquals(0, town2.compareTo(new Town("Town_2")));

	}

	@Test
	void testToString() {
		assertEquals("Town_1", town1.toString());
		assertEquals("Town_2", town2.toString());
	}

	@Test
	void testEqualsObject() {
		assertEquals(true, town1.equals(new Town("Town_1")));
		assertEquals(true, town2.equals(new Town("Town_2")));
	}

}
