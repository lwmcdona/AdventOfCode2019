package day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IntersectionFinderTest {
    @Test
    public void testManhattanDistanceIntersection() {
        List<String> wirePaths = new ArrayList<>(Arrays.asList("R8,U5,L5,D3", "U7,R6,D4,L4"));
        Day3 day3 = new Day3(wirePaths);
        assertEquals(6, day3.part1());

        wirePaths = new ArrayList<>(
                Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"));
        day3 = new Day3(wirePaths);
        assertEquals(159, day3.part1());

        wirePaths = new ArrayList<>(
                Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
        day3 = new Day3(wirePaths);
        assertEquals(135, day3.part1());
    }

    @Test
    public void testManhattanDistanceIntersectionFromFile() {
        Day3 day3 = new Day3();

        assertEquals(260, day3.part1());
    }

    @Test
    public void testTotalStepDistanceIntersection() {
        List<String> wirePaths = new ArrayList<>(Arrays.asList("R8,U5,L5,D3", "U7,R6,D4,L4"));
        Day3 day3 = new Day3(wirePaths);
        assertEquals(30, day3.part2());

        wirePaths = new ArrayList<>(
                Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"));
        day3 = new Day3(wirePaths);
        assertEquals(610, day3.part2());

        wirePaths = new ArrayList<>(
                Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
        day3 = new Day3(wirePaths);
        assertEquals(410, day3.part2());
    }

    @Test
    public void testTotalStepDistanceIntersectionFromFile() {
        Day3 day3 = new Day3();

        assertEquals(15612, day3.part2());
    }
}
