package day5;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import day2.Intcode;
import helpers.InputHelper;

public class Day5 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day5\\day5.txt");

    private Path path;
    private String icode;
    private Intcode computer;

    public Day5() {
        this.path = DEFAULT_PATH;
        readCodeFromFile();
        this.computer = new Intcode(this.icode);
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        computer.reset();
        return computer.compute();
    }

    public int part2() {
        computer.reset();
        return computer.compute();
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();

        day5.part1();

        day5.part2();
    }
}
