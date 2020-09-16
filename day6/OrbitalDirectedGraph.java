package day6;

import java.util.ArrayList;
import java.util.HashMap;

public class OrbitalDirectedGraph implements OrbitalGraph {
    private static final String Arrays = null;
    private HashMap<String, ArrayList<String>> orbits;

    public OrbitalDirectedGraph() {
        this.orbits = new HashMap<>();
    }

    public void insertOrbit(String orbiter, String orbited) {
        ArrayList<String> value = new ArrayList<>();
        this.orbits.putIfAbsent(orbiter, value);
        this.orbits.get(orbiter).add(orbited);
    }

    public int countOrbits() {
        HashMap<String, Integer> numOrbits = new HashMap<>();
        int totalOrbits = 0;
        for (String key : this.orbits.keySet()) {
            totalOrbits += countOrbits(numOrbits, key);
        }
        return totalOrbits;
    }

    public ArrayList<String> get(String key) {
        return this.orbits.get(key);
    }

    private int countOrbits(HashMap<String, Integer> numOrbits, String key) {
        if (key.equals("COM")) {
            return 0;
        }

        if (numOrbits.containsKey(key)) {
            return numOrbits.get(key);
        }

        int orbits = countOrbits(numOrbits, this.orbits.get(key).get(0)) + 1;
        numOrbits.put(key, orbits);
        return orbits;
    }
}
