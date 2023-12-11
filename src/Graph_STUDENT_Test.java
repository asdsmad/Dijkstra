import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;
	
	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		town = new Town[6];
		for (int i = 1; i < 6; i++) {
			town[i] = new Town("Town_" + i);
			graph.addVertex(town[i]);
		}
		graph.addEdge(town[1], town[2], 4, "Road_1");
		graph.addEdge(town[1], town[3], 2, "Road_2");
		graph.addEdge(town[1], town[5], 3, "Road_3");
		graph.addEdge(town[2], town[4], 2, "Road_4");
		graph.addEdge(town[4], town[5], 2, "Road_5");
		graph.addEdge(town[3], town[4], 2, "Road_6");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}

	@Test
	void testGetEdge() {
		assertEquals(new Road(town[1], town[4],4, "Road_1"), graph.getEdge(town[1], town[2]));
		assertEquals(new Road(town[3], town[4],2, "Road_6"), graph.getEdge(town[3], town[4]));
	}

	@Test
	void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[2], town[3]));
		graph.addEdge(town[2], town[3], 5, "Road_7");
		assertEquals(true, graph.containsEdge(town[2], town[3]));
	}

	@Test
	void testAddVertex() {
		Town newTown = new Town("Town_6");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[4], town[5]));
		assertEquals(false, graph.containsEdge(town[2], town[3]));
	}

	@Test
	void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_2")));
		assertEquals(false, graph.containsVertex(new Town("Town_124")));
	}

	@Test
	void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_4", roadArrayList.get(3));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_5", roadArrayList.get(4));
		assertEquals("Road_3", roadArrayList.get(2));
		assertEquals("Road_6", roadArrayList.get(5));
	}

	@Test
	void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
	}

	@Test
	void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[2], town[4]));
		graph.removeEdge(town[2], town[4], 2, "Road_4");
		assertEquals(false, graph.containsEdge(town[2], town[4]));
	}

	@Test
	void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[1]));
		graph.removeVertex(town[1]);
		assertEquals(false, graph.containsVertex(town[1]));
	}

	@Test
	void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[4]));
		assertEquals(true, roads.contains(town[3]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[5]));
	}

	@Test
	public void testTown_1ToTown_4() {
		  String beginTown = "Town_1", endTown = "Town_4";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_2 to Town_3 2 mi",path.get(0).trim());
			  assertEquals("Town_3 via Road_6 to Town_4 2 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
