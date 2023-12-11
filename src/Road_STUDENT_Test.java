import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {
	private Road road;
	private Town town1, town2;
	
	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("Town_1");
		town2 = new Town("Town_2");
		road = new Road(town1, town2, 4, "Road_1");
	}

	@AfterEach
	void tearDown() throws Exception {
		road = null;
	}

	@Test
	void testContains() {
		assertEquals(true, road.contains(new Town("Town_1")));
	}

	@Test
	void testToString() {
		assertEquals("Road_1", road.toString());
	}

	@Test
	void testGetName() {
		assertEquals("Road_1", road.getName());
	}

	@Test
	void testGetDestination() {
		assertEquals(new Town("Town_2"), road.getDestination());
	}

	@Test
	void testGetSource() {
		assertEquals(new Town("Town_1"), road.getSource());
	}

	@Test
	void testEquals() {
		assertEquals(true, road.equals(new Road(town1, town2, 4, "Road_1")));
	}
	
	@Test
	void testCompareTo() {
		assertEquals(0, road.compareTo(new Road(town1, town2, 4, "Road_1")));
	}

	@Test
	void testGetWeight() {
		assertEquals(4, road.getWeight());
	}

}
