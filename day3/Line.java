package day3;

import java.util.List;

public abstract class Line {
    private int mainCoord, minSubCoord, maxSubCoord, distanceTo;
    private boolean directionPositive;

    public Line(int mainCoord, List<Integer> subCoords) {
        this.mainCoord = mainCoord;
        subCoords.sort((u, v) -> Integer.compare(u, v));
        this.minSubCoord = subCoords.get(0);
        this.maxSubCoord = subCoords.get(subCoords.size() - 1);
    }

    public void setDistanceTo(int distance) {
        this.distanceTo = distance;
    }

    public void setDirectionPositive(boolean positive) {
        this.directionPositive = positive;
    }

    public boolean isDirectionPositive() {
        return this.directionPositive;
    }

    public int getDistanceTo() {
        return this.distanceTo;
    }

    public int getMainCoord() {
        return this.mainCoord;
    }

    public int getMinSubCoord() {
        return this.minSubCoord;
    }

    public int getMaxSubCoord() {
        return this.maxSubCoord;
    }

}
