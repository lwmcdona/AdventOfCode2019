package day11;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import helpers.InputHelper;

public class Day11 {
    private final Path DEFAULT_PATH = FileSystems.getDefault().getPath("day11", "day11.txt");

    private Path path;
    private String icode;
    private PaintingRobot robot;

    public Day11() {
        this.path = DEFAULT_PATH.toAbsolutePath();
        readCodeFromFile();
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        robot = new PaintingRobot(this.icode, false);
        robot.run();
        return robot.getCoverage();
    }

    public void part2() {
        robot = new PaintingRobot(this.icode, true);
        robot.run();
        robot.printHull();
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11();

        System.out.println("The answer to part 1 is: " + day11.part1());

        System.out.println("The answer to part 2 is: ");
        day11.part2();
    }
}
