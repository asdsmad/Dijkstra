import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Graph
 * 
 * @author Zhiming Lin
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	private Set<Town> towns;
	private Set<Road> roads;

	private Town[] previousTown;
	private Town[] town;
	int a = 0;

	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
		town = new Town[vertexSet().size()];
		previousTown = new Town[vertexSet().size()];
	}

	@Override
	/**
	 * Returns an edge connecting source vertex to target vertex if such vertices
	 * and such edge exist in this graph. Otherwise returns null. If any of the
	 * specified vertices is null returns null
	 *
	 * In undirected graphs, the returned edge may have its source and target
	 * vertices in the opposite order.
	 *
	 * @param sourceVertex      source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
//		Iterator<Road> it = roads.iterator();
//		while (it.hasNext()) {
//			Road curr = it.next();
//			if ((curr.getSource().equals(sourceVertex) && curr.getDestination().equals(destinationVertex))
//					|| (curr.getSource().equals(destinationVertex) && curr.getDestination().equals(sourceVertex))) {
//				return curr;
//			}
//		}
		for (Road r : roads) {
			if ((r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex))
					|| (r.getSource().equals(destinationVertex) && r.getDestination().equals(sourceVertex))) {
				return r;
			}
		}
		return null;
	}

	@Override
	/**
	 * Creates a new edge in this graph, going from the source vertex to the target
	 * vertex, and returns the created edge.
	 * 
	 * The source and target vertices must already be contained in this graph. If
	 * they are not found in graph IllegalArgumentException is thrown.
	 *
	 *
	 * @param sourceVertex      source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight            weight of the edge
	 * @param description       description for edge
	 *
	 * @return The newly created edge if added to the graph, otherwise null.
	 *
	 * @throws IllegalArgumentException if source or target vertices are not found
	 *                                  in the graph.
	 * @throws NullPointerException     if any of the specified vertices is null.
	 */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (!containsVertex(sourceVertex)) {
			addVertex(sourceVertex);
		}
		if (!containsVertex(destinationVertex)) {
			addVertex(destinationVertex);
		}
		if (!containsEdge(sourceVertex, destinationVertex)) {
			// edge.add(new Road(sourceVertex, destinationVertex, weight, description));
			roads.add(new Road(sourceVertex, destinationVertex, weight, description));
		}
		return getEdge(sourceVertex, destinationVertex);
	}

	@Override
	/**
	 * Adds the specified vertex to this graph if not already present. More
	 * formally, adds the specified vertex, v, to this graph if this graph contains
	 * no vertex u such that u.equals(v). If this graph already contains such
	 * vertex, the call leaves this graph unchanged and returns false. In
	 * combination with the restriction on constructors, this ensures that graphs
	 * never contain duplicate vertices.
	 *
	 * @param v vertex to be added to this graph.
	 *
	 * @return true if this graph did not already contain the specified vertex.
	 *
	 * @throws NullPointerException if the specified vertex is null.
	 */
	public boolean addVertex(Town v) {
		towns.add(v);
		return true;
	}

	@Override
	/**
	 * Returns true if and only if this graph contains an edge going from the source
	 * vertex to the target vertex. In undirected graphs the same result is obtained
	 * when source and target are inverted. If any of the specified vertices does
	 * not exist in the graph, or if is null, returns false.
	 *
	 * @param sourceVertex      source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return true if this graph contains the specified edge.
	 */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		// vertex.containsValue(getEdge(sourceVertex, destinationVertex))
		if (getEdge(sourceVertex, destinationVertex) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Returns true if this graph contains the specified vertex. More formally,
	 * returns true if and only if this graph contains a vertex u such that
	 * u.equals(v). If the specified vertex is null returns false.
	 *
	 * @param v vertex whose presence in this graph is to be tested.
	 *
	 * @return true if this graph contains the specified vertex.
	 */
	public boolean containsVertex(Town v) {
		// vertex.containsKey(v)
		if (towns.contains(v)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Returns a set of the edges contained in this graph. The set is backed by the
	 * graph, so changes to the graph are reflected in the set. If the graph is
	 * modified while an iteration over the set is in progress, the results of the
	 * iteration are undefined.
	 *
	 *
	 * @return a set of the edges contained in this graph.
	 */
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	/**
	 * Returns a set of all edges touching the specified vertex (also referred to as
	 * adjacent vertices). If no edges are touching the specified vertex returns an
	 * empty set.
	 *
	 * @param vertex the vertex for which a set of touching edges is to be returned.
	 *
	 * @return a set of all edges touching the specified vertex.
	 *
	 * @throws IllegalArgumentException if vertex is not found in the graph.
	 * @throws NullPointerException     if vertex is null.
	 */
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> target = new HashSet<Road>();
		for (Road r : roads) {
			if (r.contains(vertex)) {
				target.add(r);
			}
		}
		return target;
	}

	@Override
	/**
	 * Removes an edge going from source vertex to target vertex, if such vertices
	 * and such edge exist in this graph.
	 * 
	 * If weight >- 1 it must be checked If description != null, it must be checked
	 * 
	 * Returns the edge if removed or null otherwise.
	 *
	 * @param sourceVertex      source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight            weight of the edge
	 * @param description       description of the edge
	 *
	 * @return The removed edge, or null if no edge removed.
	 */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (roads.remove(getEdge(sourceVertex, destinationVertex))) {
			return new Road(sourceVertex, destinationVertex, weight, description);
		} else {
			return null;
		}
	}

	@Override
	/**
	 * Removes the specified vertex from this graph including all its touching edges
	 * if present. More formally, if the graph contains a vertex u such that
	 * u.equals(v), the call removes all edges that touch u and then removes u
	 * itself. If no such u is found, the call leaves the graph unchanged. Returns
	 * true if the graph contained the specified vertex. (The graph will not contain
	 * the specified vertex once the call returns).
	 *
	 * If the specified vertex is null returns false.
	 *
	 * @param v vertex to be removed from this graph, if present.
	 *
	 * @return true if the graph contained the specified vertex; false otherwise.
	 */
	public boolean removeVertex(Town v) {
		for (Town t : towns) {
			if (t.equals(v)) {
				towns.remove(v);
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * Returns a set of the vertices contained in this graph. The set is backed by
	 * the graph, so changes to the graph are reflected in the set. If the graph is
	 * modified while an iteration over the set is in progress, the results of the
	 * iteration are undefined.
	 *
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	public Set<Town> vertexSet() {
		return towns;
	}

	@Override
	/**
	 * Find the shortest path from the sourceVertex to the destinationVertex call
	 * the dijkstraShortestPath with the sourceVertex
	 * 
	 * @param sourceVertex      starting vertex
	 * @param destinationVertex ending vertex
	 * @return An arraylist of Strings that describe the path from sourceVertex to
	 *         destinationVertex They will be in the format: startVertex "via" Edge
	 *         "to" endVertex weight As an example: if finding path from Vertex_1 to
	 *         Vertex_10, the ArrayList<String> would be in the following
	 *         format(this is a hypothetical solution): Vertex_1 via Edge_2 to
	 *         Vertex_3 4 (first string in ArrayList) Vertex_3 via Edge_5 to
	 *         Vertex_8 2 (second string in ArrayList) Vertex_8 via Edge_9 to
	 *         Vertex_10 2 (third string in ArrayList)
	 */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		boolean check = true;
		Town end = destinationVertex;
		town = new Town[vertexSet().size()];
		previousTown = new Town[vertexSet().size()];
		ArrayList<String> list = new ArrayList<String>();
		dijkstraShortestPath(sourceVertex);
		// System.out.println(list.size());
//		for (Town t : previousTown) {
//			System.out.println(t);
//		}
		while (!end.equals(sourceVertex) && check) {
			for (int i = 0; i < town.length; i++) {
				if (town[i].equals(end)) {
					if (previousTown[i] == null) {
						check = false;
					} else {
						list.add(0, previousTown[i].toString() + " via " + getEdge(previousTown[i], end).toString()
								+ " to " + end.toString() + " " + getEdge(previousTown[i], end).getWeight() + " mi");
						end = previousTown[i];
						break;
					}

				}
			}
		}
		return list;

	}

	@Override
	/**
	 * Dijkstra's Shortest Path Method. Internal structures are built which hold the
	 * ability to retrieve the path, shortest distance from the sourceVertex to all
	 * the other vertices in the graph, etc.
	 * 
	 * @param sourceVertex the vertex to find shortest path from
	 * 
	 */
	public void dijkstraShortestPath(Town sourceVertex) {
		Set<Road> road = new HashSet<Road>();
		Set<Town> finishedVertx = new HashSet<Town>();
		Town curr = sourceVertex;
		int[] minWeight = new int[vertexSet().size()];
		int count = 0;

		for (Town t : towns) {
			minWeight[count] = Integer.MAX_VALUE;
			// System.out.println(t);
			town[count] = t;
			if (t.equals(curr)) {
				minWeight[count] = 0;
			}
			count++;
		}
		while (finishedVertx.size() < towns.size()) {
			road = edgesOf(curr);
			for (Road r : road) {
				if (r == null) {
					break;
				} else if (finishedVertx.contains(r.getDestination()) || finishedVertx.contains(r.getSource())) {
					continue;
				} else {
					int newWeight;
					int oldWeight;
					for (int i = 0; i < town.length; i++) {
						if (r.contains(town[i]) && !town[i].equals(curr)) {
							for (int j = 0; j < town.length; j++) {
								if (curr.equals(town[j])) {
									newWeight = minWeight[j] + r.getWeight();
									oldWeight = minWeight[i];
									if (newWeight < oldWeight) {
										previousTown[i] = curr;
										minWeight[i] = newWeight;
									}
								}
							}
						}
					}
				}
			}
//			System.out.println("test : " + a);
//			for(int b : minWeight) {
//				System.out.println(b);
//			}
			finishedVertx.add(curr);
//			System.out.println(finishedVertx);
			int min = 0;
			for (int q = 0; q < town.length; q++) {
				if (!finishedVertx.contains(town[q])) {
					if (min == 0) {
						min = minWeight[q];
						curr = town[q];
					} else {
						if (min > minWeight[q]) {
							min = minWeight[q];
							curr = town[q];
						}
					}
				}
			}
//			System.out.println("curr : " + curr);
		}
		
	}
}
