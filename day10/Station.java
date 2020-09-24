package day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Station {
    private int asteroidNum;
    private HashMap<Pair<Integer, Integer>, LinkedList<Integer>> asteroids;
    private ArrayList<Pair<Integer, Integer>> sortedDirections;
    private int nextVaporized = -1;

    public Station(int asteroidNum) {
        this.asteroids = new HashMap<>();
        this.asteroidNum = asteroidNum;
    }

    public void setAsteroids(HashMap<Pair<Integer, Integer>, LinkedList<Integer>> asteroids) {
        this.asteroids = asteroids;
    }

    public void setSortedDirections(ArrayList<Pair<Integer, Integer>> sortedDirections) {
        this.sortedDirections = sortedDirections;
    }

    public void setNextVaporized(int nextVaporized) {
        this.nextVaporized = nextVaporized;
    }

    public Station deepCopy() {
        HashMap<Pair<Integer, Integer>, LinkedList<Integer>> asteroidsCopy = new HashMap<>(this.asteroids);
        ArrayList<Pair<Integer, Integer>> sortedCopy = new ArrayList<>(this.sortedDirections);

        Station toReturn = new Station(this.asteroidNum);
        toReturn.setNextVaporized(this.nextVaporized);
        toReturn.setAsteroids(asteroidsCopy);
        toReturn.setSortedDirections(sortedCopy);
        return toReturn;
    }

    public void add(Pair<Integer, Integer> direction, int asteroidNum) {
        asteroids.putIfAbsent(direction, new LinkedList<>());
        asteroids.get(direction).add(asteroidNum);
    }

    public void addFirst(Pair<Integer, Integer> direction, int asteroidNum) {
        asteroids.putIfAbsent(direction, new LinkedList<>());
        asteroids.get(direction).addFirst(asteroidNum);
    }

    public int getNumAsteroidsSeen() {
        return asteroids.keySet().size();
    }

    public void sort() {
        this.sortedDirections = new ArrayList<>(this.asteroids.keySet());
        this.sortedDirections.sort(new DirectionComparator());
        this.nextVaporized = 0;
    }

    public int vaporize() {
        Pair<Integer, Integer> key = this.sortedDirections.get(this.nextVaporized);
        LinkedList<Integer> asteroids = this.asteroids.get(key);
        int asteroidNum = asteroids.pop();
        if (asteroids.isEmpty()) {
            this.sortedDirections.remove(this.nextVaporized);
            this.asteroids.remove(key);
            this.nextVaporized--;
        }
        this.nextVaporized = (this.nextVaporized + 1) % this.sortedDirections.size();
        return asteroidNum;
    }

    public int vaporize(int num) {
        int asteroidNum = -1;
        for (int i = num; i > 0; i--) {
            asteroidNum = vaporize();
            // System.out.println("The " + (num - i + 1) + "th asteroid to be vaporized is
            // at " + asteroidNum);
        }
        return asteroidNum;
    }

    public int getAsteroid() {
        return this.asteroidNum;
    }

    public class DirectionComparator implements Comparator<Pair<Integer, Integer>> {
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            Double angle1 = -Math.atan2(o1.getFirst(), o1.getSecond());
            Double angle2 = -Math.atan2(o2.getFirst(), o2.getSecond());
            return angle1.compareTo(angle2);
        }
    }
}
