import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * TownGraphManager
 * 
 * @author Zhiming Lin
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	private Graph g;

	public TownGraphManager() {
		g = new Graph();
	}

	@Override
	/**
	 * Adds a road with 2 towns and a road name
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town addTown1 = new Town(town1);
		Town addTown2 = new Town(town2);
		g.addEdge(addTown1, addTown2, weight, roadName);
		return true;
	}

	@Override
	/**
	 * Returns the name of the road that both towns are connected through
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null
	 *         if not
	 */
	public String getRoad(String town1, String town2) {
		Town addTown1 = new Town(town1);
		Town addTown2 = new Town(town2);
		String s = g.getEdge(addTown1, addTown2) + "";
		return s;
	}

	@Override
	/**
	 * Adds a town to the graph
	 * 
	 * @param v the town's name (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {
		Town addTown1 = new Town(v);
		if (g.addVertex(addTown1)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Gets a town with a given name
	 * 
	 * @param name the town's name
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		Town town = new Town(name);
		if (containsTown(name)) {
			return town;
		}
		return null;
	}

	@Override
	/**
	 * Determines if a town is already in the graph
	 * 
	 * @param v the town's name
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		Town town = new Town(v);
		if (g.containsVertex(town)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Determines if a road is in the graph
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		Town addTown1 = new Town(town1);
		Town addTown2 = new Town(town2);
		if (g.containsEdge(addTown1, addTown2)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * 
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() {
		ArrayList<String> list = new ArrayList<String>();
		Road minRoad;
		for (Road r : g.edgeSet()) {
			minRoad = r;
			for (Road j : g.edgeSet()) {
				if (list.contains(minRoad.toString())) {
					minRoad = j;
				} else if (minRoad.compareTo(j) > 0 && !list.contains(j.toString())) {
					minRoad = j;
				}
			}
			list.add(minRoad.toString());
		}
		return list;
	}

	@Override
	/**
	 * Deletes a road from the graph
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town addTown1 = new Town(town1);
		Town addTown2 = new Town(town2);
		g.removeEdge(addTown1, addTown2, 0, road);
		return true;
	}

	@Override
	/**
	 * Deletes a town from the graph
	 * 
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		if (g.removeVertex(town)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first
	 * name)
	 * 
	 * @return an arraylist of all towns in alphabetical order (last name, first
	 *         name)
	 */
	public ArrayList<String> allTowns() {
		ArrayList<String> list = new ArrayList<String>();
		Town minTown;
		for (Town t : g.vertexSet()) {
			minTown = t;
			for (Town j : g.vertexSet()) {
				if (list.contains(minTown.toString())) {
					minTown = j;
				} else if (minTown.compareTo(j) > 0 && !list.contains(j.toString())) {
					minTown = j;
				}
			}
			list.add(minTown.toString());
		}
		return list;
	}

	@Override
	/**
	 * Returns the shortest path from town 1 to town 2
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 *         towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2) {
		Town addTown1 = new Town(town1);
		Town addTown2 = new Town(town2);
		return g.shortestPath(addTown1, addTown2);
	}

	public void populateTownGraph(File input) throws FileNotFoundException, IOException {
		Scanner file = new Scanner(input);
		String[] roadInfor;
		String[] road;
		Town source, destination;
		String roadNum;
		int weight;
		while (file.hasNext()) {
			roadInfor = file.next().split(";");
			road = roadInfor[0].split(",");
			roadNum = road[0];
			weight = Integer.valueOf(road[1]);
			source = new Town(roadInfor[1]);
			destination = new Town(roadInfor[2]);
			g.addEdge(source, destination, weight, roadNum);
			if (g.containsVertex(source)) {
				g.addVertex(source);
			}
			if (g.containsVertex(destination)) {
				g.addVertex(destination);
			}
		}
	}

}
