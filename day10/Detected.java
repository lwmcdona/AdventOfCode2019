package day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Detected {
    public int asteroidNum;
    private HashSet<Pair<Integer, Integer>> directions;
    // private HashSet<Double> backward;

    public Detected(int asteroidNum) {
        this.directions = new HashSet<>();
        // this.forward = new HashSet<>();
        // this.backward = new HashSet<>();
        this.asteroidNum = asteroidNum;
    }

    public void add(Pair<Integer, Integer> pair) {
        directions.add(pair);
    }

    // public void addToForward(double value) {
    // forward.add(value);
    // }

    // public void addToBackward(double value) {
    // backward.add(value);
    // }

    // public int getTotal() {
    // return backward.size() + forward.size();
    // }

    public int getTotal() {
        return this.directions.size();
    }
}
