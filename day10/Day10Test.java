package day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileSystems;
import org.junit.jupiter.api.Test;

public class Day10Test {
    @Test
    public void test1Part1() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput1.txt"));
        assertEquals(8, day10.part1());
    }

    @Test
    public void test2Part1() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput2.txt"));
        assertEquals(33, day10.part1());
    }

    @Test
    public void test3Part1() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput3.txt"));
        assertEquals(35, day10.part1());
    }

    @Test
    public void test4Part1() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput4.txt"));
        assertEquals(41, day10.part1());
    }

    @Test
    public void test5Part1() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput5.txt"));
        assertEquals(210, day10.part1());
    }

    @Test
    public void testResultPart1() {
        Day10 day10 = new Day10();
        assertEquals(256, day10.part1());
    }

    @Test
    public void test1Part2() {
        Day10 day10 = new Day10(FileSystems.getDefault()
                .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day10\\testInput5.txt"));
        assertEquals(802, day10.part2());
    }

    @Test
    public void testResultPart2() {
        Day10 day10 = new Day10();
        assertEquals(1707, day10.part2());
    }
}
