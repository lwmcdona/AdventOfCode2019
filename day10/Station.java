package day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Station {
    private int asteroidNum;
    private HashMap<Double, LinkedList<Integer>> forward;
    private HashMap<Double, LinkedList<Integer>> backward;
    private ArrayList<Double> fSortedSlopes;
    private ArrayList<Double> bSortedSlopes;
    private boolean useBackward = true;
    private int nextVaporized = -1;

    public Station(int asteroidNum) {
        this.forward = new HashMap<>();
        this.backward = new HashMap<>();
        this.asteroidNum = asteroidNum;
    }

    public void addToForward(double slope, int asteroidNum) {
        forward.putIfAbsent(slope, new LinkedList<>());
        forward.get(slope).add(asteroidNum);
    }

    public void addToBackward(double slope, int asteroidNum) {
        backward.putIfAbsent(slope, new LinkedList<>());
        backward.get(slope).addFirst(asteroidNum);
    }

    public int getTotal() {
        return backward.size() + forward.size();
    }

    public void sort() {
        this.fSortedSlopes = new ArrayList<>(forward.keySet());
        this.fSortedSlopes.sort(new DirectionComparator());
        this.bSortedSlopes = new ArrayList<>(backward.keySet());
        this.bSortedSlopes.sort(new DirectionComparator());
        this.nextVaporized = findNextVaporized();
    }

    private int findNextVaporized() {
        for (int i = 0; i < this.bSortedSlopes.size() - 1; i++) {
            if (this.bSortedSlopes.get(i) > this.bSortedSlopes.get(i + 1)) {
                return i;
            }
        }
        this.useBackward = false;
        return 0;
    }

    public int vaporize() {
        int asteroid;
        if (this.useBackward) {
            double key = this.bSortedSlopes.get(this.nextVaporized);
            LinkedList<Integer> asteroids = this.backward.get(key);
            asteroid = asteroids.pop();
            if (asteroids.isEmpty()) {
                this.bSortedSlopes.remove(this.nextVaporized);
                this.backward.remove(key);
                this.nextVaporized--;
            }
            this.nextVaporized++;
            if (this.nextVaporized >= this.bSortedSlopes.size()) {
                this.useBackward = false;
                this.nextVaporized = 0;
            }
        } else {
            double key = this.fSortedSlopes.get(this.nextVaporized);
            LinkedList<Integer> asteroids = this.forward.get(key);
            asteroid = asteroids.pop();
            if (asteroids.isEmpty()) {
                this.fSortedSlopes.remove(this.nextVaporized);
                this.forward.remove(key);
                this.nextVaporized--;
            }
            this.nextVaporized++;
            if (this.nextVaporized >= this.fSortedSlopes.size()) {
                this.useBackward = true;
                this.nextVaporized = 0;
            }
        }
        return asteroid;
    }

    public int vaporize(int num) {
        int asteroidNum = -1;
        for (int i = num; i > 0; i--) {
            asteroidNum = vaporize();
            System.out.println("The " + (num - i + 1) + "th asteroid to be vaporized is at " + asteroidNum);
        }
        return asteroidNum;
    }

    // private int calculateCoordinates(int asteroidNum) {
    // return (asteroidNum % 20 * 100) + (asteroidNum / 20);
    // }

    public int getAsteroid() {
        return this.asteroidNum;
    }

    public class DirectionComparator implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            if (o1 == o2) {
                return 0;
            } else if ((o1 < 0 && o2 < 0) || (o1 >= 0 && o2 >= 0)) {
                return o1.compareTo(o2);
            } else {
                return o2.compareTo(o1);
            }
        }
    }
}
