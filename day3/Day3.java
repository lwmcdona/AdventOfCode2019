package day3;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import helpers.InputHelper;

public class Day3 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day3\\day3.txt");

    private Path path;
    private IntersectionFinder intersectionFinder;

    public Day3() {
        this.path = DEFAULT_PATH;
        readWirePathsFromFile();
    }

    public Day3(List<String> wirePaths) {
        this.path = DEFAULT_PATH;
        this.intersectionFinder = new IntersectionFinder(wirePaths);
    }

    private void readWirePathsFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        this.intersectionFinder = new IntersectionFinder(ihelper.readLines());
    }

    public int part1() {
        return this.intersectionFinder.findClosestManhattanDistanceIntersection();
    }

    public int part2() {
        return this.intersectionFinder.findClosestWireStepIntersection();
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();

        System.out.println("The answer to part 1 is: " + day3.part1());

        System.out.println("The answer to part 2 is: " + day3.part2());
    }
}
