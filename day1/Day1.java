package day1;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day1 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day1\\day1.txt");

    private Path path;
    private FuelCalculator fuelCalculator = new FuelCalculator();
    private List<Integer> masses;

    public Day1() {
        this.path = DEFAULT_PATH;
        readMassesFromFile();
    }

    public int part1() {
        return fuelCalculator.calculateAllModuleFuel(this.masses);
    }

    public int part2() {
        return fuelCalculator.calculateAllModuleFuelWithFuelMass(this.masses);
    }

    private void readMassesFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.masses = input.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();

        System.out.println("The answer to Part 1 is: " + day1.part1());
        System.out.println("The answer to Part 2 is: " + day1.part2());
    }
}