package day6;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import helpers.InputHelper;

public class Day6 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day6\\day6.txt");

    private Path path;
    private List<String> orbits;

    public Day6() {
        this.path = DEFAULT_PATH;
        getOrbitsFromFile();
    }

    public Day6(List<String> orbits) {
        this.orbits = orbits;
    }

    public int part1() {
        OrbitalDirectedGraph graph = constructDirectedGraph();
        return graph.countOrbits();
    }

    public int part2() {
        OrbitalUndirectedGraph graph = constructUndirectedGraph();
        return graph.shortestPath("YOU", "SAN");
    }

    private void getOrbitsFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        this.orbits = ihelper.readLines();
    }

    private OrbitalDirectedGraph constructDirectedGraph() {
        OrbitalDirectedGraph graph = new OrbitalDirectedGraph();
        for (String orbit : this.orbits) {
            String orbited = orbit.substring(0, orbit.indexOf(")"));
            String orbiter = orbit.substring(orbit.indexOf(")") + 1);
            graph.insertOrbit(orbiter, orbited);
        }
        return graph;
    }

    private OrbitalUndirectedGraph constructUndirectedGraph() {
        OrbitalUndirectedGraph graph = new OrbitalUndirectedGraph();
        for (String orbit : this.orbits) {
            String orbited = orbit.substring(0, orbit.indexOf(")"));
            String orbiter = orbit.substring(orbit.indexOf(")") + 1);
            graph.insertOrbit(orbiter, orbited);
        }
        return graph;
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();

        System.out.println("The answer to part 1 is: " + day6.part1());

        System.out.println("The answer to part 2 is: " + day6.part2());
    }
}
