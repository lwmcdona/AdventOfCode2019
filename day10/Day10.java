package day10;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import helpers.InputHelper;

public class Day10 {
    private static final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\day10.txt");

    private Path path;
    private List<String> map;

    public Day10() {
        this.path = DEFAULT_PATH;
        initializeMap();
    }

    public Day10(Path path) {
        this.path = path;
        initializeMap();
    }

    private void initializeMap() {
        readMapFromFile();
    }

    public void readMapFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        this.map = ihelper.readLines();
    }

    public int part1() {
        HashMap<Integer, Detected> detected = new HashMap<>();

        int maxDetected = Integer.MIN_VALUE;
        Detected station = new Detected(-1);

        for (int y1 = 0; y1 < this.map.size(); y1++) { // y
            String line = this.map.get(y1);
            for (int x1 = 0; x1 < line.length(); x1++) { // x
                if (line.charAt(x1) == '#') {
                    int asteroidNum = calculateAsteroidNumber(x1, y1);
                    detected.putIfAbsent(asteroidNum, new Detected(asteroidNum));
                    for (int x2 = x1 + 1; x2 < line.length(); x2++) { // y
                        if (line.charAt(x2) == '#') {
                            addToDetected(detected, asteroidNum, calculateAsteroidNumber(x2, y1), x2 - x1, 0);
                        }
                    }

                    for (int y2 = y1 + 1; y2 < this.map.size(); y2++) {
                        String row = this.map.get(y2);
                        for (int x2 = 0; x2 < row.length(); x2++) {
                            if (row.charAt(x2) == '#') {
                                addToDetected(detected, asteroidNum, calculateAsteroidNumber(x2, y2), x2 - x1, y2 - y1);
                            }
                        }
                    }

                    if (maxDetected < detected.get(asteroidNum).getTotal()) {
                        station = detected.get(asteroidNum);
                        maxDetected = station.getTotal();
                    }
                }
            }
        }
        System.out.println("The station chose is at: " + station.asteroidNum);
        return maxDetected;
    }

    private void addToDetected(HashMap<Integer, Detected> detected, int startAsteroid, int endAsteroid, int xOffset,
            int yOffset) {
        int gcd = gcdByEuclidsAlgorithm(xOffset, yOffset);
        int first = xOffset / gcd;
        int second = yOffset / gcd;
        detected.get(startAsteroid).add(Pair.createNewInstance(first, second));
        detected.putIfAbsent(endAsteroid, new Detected(endAsteroid));
        detected.get(endAsteroid).add(Pair.createNewInstance(-first, -second));
    }

    private int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

    public int part2() {
        HashMap<Integer, Station> detected = new HashMap<>();

        int maxDetected = Integer.MIN_VALUE;
        Station station = new Station(-1);

        for (int y1 = 0; y1 < this.map.size(); y1++) { // y
            String line = this.map.get(y1);
            for (int x1 = 0; x1 < line.length(); x1++) { // x
                if (line.charAt(x1) == '#') {
                    int asteroidNum = calculateAsteroidNumber(x1, y1);
                    detected.putIfAbsent(asteroidNum, new Station(asteroidNum));
                    for (int x2 = x1 + 1; x2 < line.length(); x2++) { // y
                        if (line.charAt(x2) == '#') {
                            double slope = calculateSlope(x1, y1, x2, y1);
                            int sightAsteroidNum = calculateAsteroidNumber(x2, y1);
                            detected.get(asteroidNum).addToForward(slope, sightAsteroidNum);
                            detected.putIfAbsent(sightAsteroidNum, new Station(sightAsteroidNum));
                            detected.get(sightAsteroidNum).addToBackward(slope, asteroidNum);
                        }
                    }

                    for (int y2 = y1 + 1; y2 < this.map.size(); y2++) {
                        String row = this.map.get(y2);
                        for (int x2 = 0; x2 < row.length(); x2++) {
                            if (row.charAt(x2) == '#') {
                                double slope = calculateSlope(x1, y1, x2, y2);
                                int sightAsteroidNum = calculateAsteroidNumber(x2, y2);
                                detected.get(asteroidNum).addToForward(slope, sightAsteroidNum);
                                detected.putIfAbsent(sightAsteroidNum, new Station(sightAsteroidNum));
                                detected.get(sightAsteroidNum).addToBackward(slope, asteroidNum);
                            }
                        }
                    }

                    if (maxDetected < detected.get(asteroidNum).getTotal()) {
                        station = detected.get(asteroidNum);
                        maxDetected = station.getTotal();
                    }
                }
            }
        }
        System.out.println("Station chosen is: " + station.getAsteroid());

        station.sort();
        return station.vaporize(200);
    }

    private int calculateAsteroidNumber(int x, int y) {
        return x * 100 + y;
    }

    // private int calculateCoordinates(int asteroidNum) {
    // return (asteroidNum % this.MAP_WIDTH * 100) + (asteroidNum / this.MAP_WIDTH);
    // }

    private double calculateSlope(int x1, int y1, int x2, int y2) {
        if (x2 - x1 == 0) {
            if (y2 - y1 > 0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        }
        return ((double) (y2 - y1)) / (x2 - x1);
    }

    public static void main(String[] args) {
        // Day10 day10 = new Day10();

        // System.out.println("The answer to part 1 is: " + day10.part1());

        // System.out.println("The answer to part 2 is: " + day10.part2());

        HashMap<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        map.put(Pair.createNewInstance(1, 2), 5);
        map.put(Pair.createNewInstance(1, 2), 6);
        System.out.print("test complete");

    }
}
