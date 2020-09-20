package day2;

import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import helpers.InputHelper;

public class Day2 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day2\\day2.txt");

    private Path path;
    private String icode;
    private Intcode computer;

    public Day2() {
        this.path = DEFAULT_PATH;
        readCodeFromFile();
        this.computer = new Intcode(this.icode);
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        HashMap<Integer, BigInteger> replacements = new HashMap<>();
        replacements.put(1, new BigInteger("12"));
        replacements.put(2, new BigInteger("2"));

        computer.replaceCodeValues(replacements);
        return computer.getResult();
    }

    public int part2() {
        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                computer.replaceCodeValue(1, new BigInteger(String.valueOf(noun)));
                computer.replaceCodeValue(2, new BigInteger(String.valueOf(verb)));
                computer.compute();
                if (computer.getResult() == 19690720) {
                    return 100 * noun + verb;
                } else {
                    computer.setIntcode(this.icode);
                }
            }
        }
        return -1;
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2();

        System.out.println("The answer to part 1 is: " + day2.part1());

        System.out.println("The answer to part 1 is: " + day2.part2());
    }

}
