package day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class OrbitalUndirectedGraph implements OrbitalGraph {
    private OrbitalDirectedGraph graph;

    public OrbitalUndirectedGraph() {
        this.graph = new OrbitalDirectedGraph();
    }

    public void insertOrbit(String orbiter, String orbited) {
        this.graph.insertOrbit(orbiter, orbited);
        this.graph.insertOrbit(orbited, orbiter);
    }

    public int shortestPath(String start, String end) {
        ArrayList<String> startOrbiting = this.graph.get(start);
        ArrayList<String> endOrbiting = this.graph.get(end);
        if (startOrbiting.size() != 1 || endOrbiting.size() != 1) {
            throw new IllegalArgumentException();
        } else {
            start = startOrbiting.get(0);
            end = endOrbiting.get(0);

            HashMap<String, Integer> visited = new HashMap<>();
            LinkedList<String> queue = new LinkedList<>();

            queue.add(start);
            visited.put(start, 0);

            while (queue.size() != 0) {
                String v = queue.pop();
                int level = visited.get(v) + 1;

                for (String neighbour : this.graph.get(v)) {
                    if (neighbour.equals(end)) {
                        return level;
                    }
                    if (!visited.containsKey(neighbour)) {
                        visited.put(neighbour, level);
                        queue.add(neighbour);
                    }
                }
            }
        }
        return -1;
    }

}
