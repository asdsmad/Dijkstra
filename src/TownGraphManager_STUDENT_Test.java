import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;

	@BeforeEach
	void setUp() throws Exception {
		graph = new TownGraphManager();
		town = new String[6];
		for (int i = 1; i < 6; i++) {
			town[i] = "Town_" + i;
			graph.addTown(town[i]);
		}
		graph.addRoad(town[1], town[2], 4, "Road_1");
		graph.addRoad(town[1], town[3], 2, "Road_2");
		graph.addRoad(town[1], town[5], 3, "Road_3");
		graph.addRoad(town[2], town[4], 2, "Road_4");
		graph.addRoad(town[4], town[5], 2, "Road_5");
		graph.addRoad(town[3], town[4], 2, "Road_6");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}

	@Test
	void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		graph.addRoad(town[3], town[5], 1,"Road_7");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_7", roads.get(6));
		
	}

	@Test
	void testGetRoad() {
		assertEquals("Road_1", graph.getRoad(town[1], town[2]));
		assertEquals("Road_4", graph.getRoad(town[2], town[4]));
	}

	@Test
	void testAddTown() {
		assertEquals(false, graph.containsTown("Town_6"));
		graph.addTown("Town_6");
		assertEquals(true, graph.containsTown("Town_6"));
	}

	@Test
	void testGetTown() {
		assertEquals(false, graph.containsTown("Town_6"));
		graph.addTown("Town_6");
		ArrayList<String> path = graph.getPath(town[1],"Town_6");
		assertFalse(path.size() > 0);
	}

	@Test
	void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_7"));
	}

	@Test
	void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[3]));
		assertEquals(false, graph.containsRoadConnection(town[3], town[5]));
	}

	@Test
	void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_4", roads.get(3));
		assertEquals("Road_5", roads.get(4));
	}

	@Test
	void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[3], town[4]));
		graph.deleteRoadConnection(town[3], town[4], "Road_6");
		assertEquals(false, graph.containsRoadConnection(town[3], town[4]));
	}

	@Test
	void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}

	@Test
	void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_5", roads.get(4));
		assertEquals("Town_4", roads.get(3));
		assertEquals("Town_3", roads.get(2));
		assertEquals("Town_2", roads.get(1));
	}

	@Test
	void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[4]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_3 2 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_6 to Town_4 2 mi",path.get(1).trim());
	}

}
