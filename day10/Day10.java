package day10;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import helpers.InputHelper;

public class Day10 {
    private static final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\day10.txt");

    private Path path;
    private List<String> map;
    private MapSurveyor mapSurveyor;

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
        this.mapSurveyor = new MapSurveyor(this.map);
    }

    public void readMapFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        this.map = ihelper.readLines();
    }

    public int part1() {
        return this.mapSurveyor.getAsteroidsSeenFromBestMonitoringLocation();
    }

    public int part2() {
        Station station = this.mapSurveyor.getBestMonitoringLocation();
        return station.vaporize(200);
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();

        System.out.println("The answer to part 1 is: " + day10.part1());

        System.out.println("The answer to part 2 is: " + day10.part2());
    }
}
