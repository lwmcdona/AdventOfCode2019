package day10;

import java.util.HashMap;
import java.util.List;

public class MapSurveyor {
    private Station station;
    private List<String> map;

    public MapSurveyor(List<String> map) {
        this.map = map;
    }

    public int getAsteroidsSeenFromBestMonitoringLocation() {
        if (this.station == null) {
            getBestMonitoringLocation();
        }

        return getStation().getNumAsteroidsSeen();
    }

    public Station getBestMonitoringLocation() {
        if (this.station != null) {
            return getStation();
        }

        HashMap<Integer, Station> potentialStations = new HashMap<>();
        int maxDetected = Integer.MIN_VALUE;

        for (int y1 = 0; y1 < this.map.size(); y1++) {
            String line = this.map.get(y1);
            for (int x1 = 0; x1 < line.length(); x1++) {
                if (line.charAt(x1) == '#') {
                    int startAsteroid = calculateAsteroidNumber(x1, y1);
                    potentialStations.putIfAbsent(startAsteroid, new Station(startAsteroid));
                    for (int x2 = x1 + 1; x2 < line.length(); x2++) {
                        if (line.charAt(x2) == '#') {
                            int endAsteroid = calculateAsteroidNumber(x2, y1);
                            Pair<Integer, Integer> direction = getDirection(x1, y1, x2, y1);
                            addToPotentialStations(potentialStations, startAsteroid, endAsteroid, direction);
                        }
                    }

                    for (int y2 = y1 + 1; y2 < this.map.size(); y2++) {
                        String row = this.map.get(y2);
                        for (int x2 = 0; x2 < row.length(); x2++) {
                            if (row.charAt(x2) == '#') {
                                int endAsteroid = calculateAsteroidNumber(x2, y2);
                                Pair<Integer, Integer> direction = getDirection(x1, y1, x2, y2);
                                addToPotentialStations(potentialStations, startAsteroid, endAsteroid, direction);
                            }
                        }
                    }

                    if (maxDetected < potentialStations.get(startAsteroid).getNumAsteroidsSeen()) {
                        this.station = potentialStations.get(startAsteroid);
                        maxDetected = this.station.getNumAsteroidsSeen();
                    }
                }
            }
        }
        System.out.println("The station chosen is at: " + this.station.getAsteroid());
        this.station.sort();
        return getStation();
    }

    public Station getStation() {
        return this.station.deepCopy();
    }

    private int calculateAsteroidNumber(int x, int y) {
        return x * 100 + y;
    }

    private Pair<Integer, Integer> getDirection(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int gcd = gcdByEuclidsAlgorithm(dx, dy);
        dx /= gcd;
        dy /= gcd;
        return Pair.createNewInstance(dx, dy);
    }

    private int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return Math.abs(gcdByEuclidsAlgorithm(n2, n1 % n2));
    }

    private void addToPotentialStations(HashMap<Integer, Station> s, int start, int end, Pair<Integer, Integer> dir) {
        s.get(start).add(dir, end);
        s.putIfAbsent(end, new Station(end));
        s.get(end).add(Pair.createNewInstance(-dir.getFirst(), -dir.getSecond()), start);
    }
}
