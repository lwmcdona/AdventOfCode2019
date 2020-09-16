package day6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import helpers.InputHelper;

public class OrbitalGraphTest {
    private OrbitalDirectedGraph constructDirectedGraph(List<String> input) {
        OrbitalDirectedGraph graph = new OrbitalDirectedGraph();
        for (String orbit : input) {
            String orbited = orbit.substring(0, orbit.indexOf(")"));
            String orbiter = orbit.substring(orbit.indexOf(")") + 1);
            graph.insertOrbit(orbiter, orbited);
        }
        return graph;
    }

    private OrbitalUndirectedGraph constructUndirectedGraph(List<String> input) {
        OrbitalUndirectedGraph graph = new OrbitalUndirectedGraph();
        for (String orbit : input) {
            String orbited = orbit.substring(0, orbit.indexOf(")"));
            String orbiter = orbit.substring(orbit.indexOf(")") + 1);
            graph.insertOrbit(orbiter, orbited);
        }
        return graph;
    }

    @Test
    public void testDirectedOrbitCounter() {
        InputHelper ihelper = new InputHelper(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day6\\testInput1.txt"));
        List<String> input = ihelper.readLines();
        OrbitalDirectedGraph graph = constructDirectedGraph(input);
        assertEquals(42, graph.countOrbits());
    }

    @Test
    public void testUndirectedShortestPath() {
        InputHelper ihelper = new InputHelper(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day6\\testInput2.txt"));
        List<String> input = ihelper.readLines();
        OrbitalUndirectedGraph graph = constructUndirectedGraph(input);
        assertEquals(4, graph.shortestPath("YOU", "SAN"));
    }

}
