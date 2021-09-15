package day13;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import helpers.InputHelper;

public class Day13 {
    private final Path DEFAULT_PATH = FileSystems.getDefault().getPath("day13", "day13.txt");

    private Path path;
    private String icode;
    private ArcadeCabinet cabinet;

    public Day13() {
        this.path = DEFAULT_PATH.toAbsolutePath();
        readCodeFromFile();
        this.cabinet = new ArcadeCabinet(icode);
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        cabinet.reset();
        cabinet.setup();
        return cabinet.getBlockCount();
    }

    public int part2() {
        cabinet.reset();
        cabinet.play();
        return cabinet.getScore();
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();

        System.out.println("The answer to part 1 is: " + day13.part1());

        System.out.println("The answer to part 2 is: " + day13.part2());
    }
}
