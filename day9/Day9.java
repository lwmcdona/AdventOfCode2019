package day9;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import day2.Intcode;
import helpers.InputHelper;

public class Day9 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day9\\day9.txt");

    private Path path;
    private String icode;
    private Intcode computer;

    public Day9() {
        this.path = DEFAULT_PATH;
        readCodeFromFile();
        this.computer = new Intcode(this.icode);
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        computer.reset();
        computer.compute(); // use input 1
        return computer.getResult();
    }

    public int part2() {
        computer.reset();
        computer.compute(); // use input 2
        return computer.getResult();
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9();

        // Should output 2465411646
        day9.part1();

        // Should output
        day9.part2();
    }
}
