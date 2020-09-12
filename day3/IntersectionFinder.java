package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionFinder {
    private List<String> wirePaths;

    private Mode mode;

    private enum Mode {
        MANHATTAN, STEPS;
    }

    public IntersectionFinder(List<String> wirePaths) {
        this.wirePaths = wirePaths;
    }

    public int findClosestManhattanDistanceIntersection() {
        this.mode = Mode.MANHATTAN;
        return getClosestIntersection();
    }

    public int findClosestWireStepIntersection() {
        this.mode = Mode.STEPS;
        return getClosestIntersection();
    }

    private int getClosestIntersection() {
        int minDistance = Integer.MAX_VALUE;

        ArrayList<Line> v = new ArrayList<>();
        ArrayList<Line> h = new ArrayList<>();

        for (int i = 0; i < this.wirePaths.size(); i++) {
            List<String> path = Arrays.asList(this.wirePaths.get(i).split(","));

            int currX = 0, currY = 0, distanceTravelled = 0;
            for (int j = 0; j < path.size(); j++) {
                String edge = path.get(j);
                char dir = edge.charAt(0);
                int distance = Integer.valueOf(edge.substring(1));
                if (dir == 'L' || dir == 'D') {
                    distance = -distance;
                }

                if (dir == 'R' || dir == 'L') {
                    Line horz = new HorizontalLine(currY, Arrays.asList(currX, currX + distance));
                    horz.setDistanceTo(distanceTravelled);
                    horz.setDirectionPositive(distance > 0);

                    if (i == 0) {
                        h.add(horz);
                    } else {
                        minDistance = checkIntersections(v, horz, minDistance);
                    }
                    currX += distance;
                } else if (dir == 'U' || dir == 'D') {
                    Line vert = new VerticalLine(currX, Arrays.asList(currY, currY + distance));
                    vert.setDistanceTo(distanceTravelled);
                    vert.setDirectionPositive(distance > 0);

                    if (i == 0) {
                        v.add(vert);
                    } else {
                        minDistance = checkIntersections(h, vert, minDistance);
                    }
                    currY += distance;
                }
                distanceTravelled += Math.abs(distance);
            }
            if (i == 0) {
                v.sort((v1, v2) -> v1.getMainCoord() - v2.getMainCoord());
                h.sort((h1, h2) -> h1.getMainCoord() - h2.getMainCoord());
            }
        }

        return minDistance;
    }

    private int checkIntersections(List<Line> lines, Line currLine, int minDistance) {
        for (int k = 0; k < lines.size() && lines.get(k).getMainCoord() <= currLine.getMaxSubCoord(); k++) {
            if (lines.get(k).getMainCoord() < currLine.getMinSubCoord()) {
                continue;
            }

            Line perpLine = lines.get(k);
            if (perpLine.getMinSubCoord() <= currLine.getMainCoord()
                    && perpLine.getMaxSubCoord() >= currLine.getMainCoord()) {

                int distanceFromPort;
                if (this.mode == Mode.MANHATTAN) {
                    distanceFromPort = getIntersectionManhattanDistance(currLine, perpLine);
                } else {
                    distanceFromPort = getIntersectionTotalStepDistance(currLine, perpLine);
                }

                if (distanceFromPort != 0 && distanceFromPort < minDistance) {
                    minDistance = distanceFromPort;
                }
            }
        }
        return minDistance;
    }

    private int getIntersectionManhattanDistance(Line currLine, Line perpLine) {
        return Math.abs(perpLine.getMainCoord()) + Math.abs(currLine.getMainCoord());
    }

    private int getIntersectionTotalStepDistance(Line currLine, Line perpLine) {
        return currLine.getDistanceTo() + perpLine.getDistanceTo() + getEdgeStepsToIntersection(currLine, perpLine)
                + getEdgeStepsToIntersection(perpLine, currLine);
    }

    private int getEdgeStepsToIntersection(Line currLine, Line perpLine) {
        int stepsToIntersection;
        if (currLine.isDirectionPositive()) {
            stepsToIntersection = perpLine.getMainCoord() - currLine.getMinSubCoord();
        } else {
            stepsToIntersection = currLine.getMaxSubCoord() - perpLine.getMainCoord();
        }
        return stepsToIntersection;
    }
}
