package day8;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleImmutableEntry;

import helpers.InputHelper;

public class Day8 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day8\\day8.txt");
    private static final int IMAGE_HEIGHT = 6;
    private static final int IMAGE_WIDTH = 25;

    private Path path;
    private String img;

    public Day8() {
        this.path = DEFAULT_PATH;
        readImageDataFromFile();
    }

    public int part1() {
        SimpleImmutableEntry<Integer, String> minZeroLayer = new SimpleImmutableEntry<>(Integer.MAX_VALUE, null);

        int layerSize = IMAGE_HEIGHT * IMAGE_WIDTH;
        int index = 0;
        while (index < this.img.length()) {
            String layer = this.img.substring(index, index + layerSize);
            int numZeros = countChars(layer, '0');
            if (numZeros < minZeroLayer.getKey()) {
                minZeroLayer = new SimpleImmutableEntry<>(numZeros, layer);
            }

            index += layerSize;
        }

        String result = minZeroLayer.getValue();
        return countChars(result, '1') * countChars(result, '2');
    }

    public ArrayList<Character> part2() {
        ArrayList<Character> resultLayer = new ArrayList<>();
        ArrayList<String> layers = getLayers();
        for (int i = 0; i < IMAGE_HEIGHT * IMAGE_WIDTH; i++) {
            for (String layer : layers) {
                char c = layer.charAt(i);
                if (c == '0' || c == '1') {
                    resultLayer.add(c);
                    if (c == '1') {
                        System.out.print(c);
                    } else {
                        System.out.print(' ');
                    }
                    break;
                }
            }
            if (i % IMAGE_WIDTH == 24) {
                System.out.println();
            }
        }

        return resultLayer;
    }

    private int countChars(String str, char c) {
        int index = str.indexOf(c);
        int count = 0;
        while (index >= 0) {
            count++;
            index = str.indexOf(c, index + 1);
        }
        return count;
    }

    private ArrayList<String> getLayers() {
        ArrayList<String> layers = new ArrayList<>();

        int layerSize = IMAGE_HEIGHT * IMAGE_WIDTH;
        int index = 0;
        while (index < this.img.length()) {
            layers.add(this.img.substring(index, index + layerSize));
            index += layerSize;
        }

        return layers;
    }

    private void readImageDataFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.img = input.get(0);
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();

        System.out.println("The answer to part 1 is: " + day8.part1());

        System.out.println("The answer to part 2 is: ");
        day8.part2();
    }
}
