package day12;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

import helpers.InputHelper;

public class Day12 {
    private final Path DEFAULT_PATH = FileSystems.getDefault().getPath("day12", "day12.txt");
    private final int NUM_STEPS = 1000;

    private Path path;
    private List<Moon> moons;
    private List<Moon> originalMoons;

    public Day12() {
        this.path = DEFAULT_PATH.toAbsolutePath();
        this.originalMoons = new ArrayList<>();
        readCodeFromFile();
    }

    public int part1() {
        resetMoons();
        for (int i = 0; i < NUM_STEPS; i++) {
            applyGravity();
            applyVelocity();
        }

        return totalEnergy();
    }

    public long part2() {
        resetMoons();
        applyGravity();
        applyVelocity();

        long stepsX = 1;
        long stepsY = 1;
        long stepsZ = 1;

        while (!isCompleteOrbitX()) {
            applyGravityX();
            applyVelocityX();
            stepsX++;
        }

        while (!isCompleteOrbitY()) {
            applyGravityY();
            applyVelocityY();
            stepsY++;
        }

        while (!isCompleteOrbitZ()) {
            applyGravityZ();
            applyVelocityZ();
            stepsZ++;
        }

        return lcm(lcm(stepsX, stepsY), stepsZ);
    }

    private void resetMoons() {
        moons = new ArrayList<>();
        for (Moon moon : originalMoons) {
            moons.add(new Moon(moon.getX(), moon.getY(), moon.getZ()));
        }
    }

    public long gcd(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            long absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }

    public long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            long gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        for (String moonCoords : input) {
            Pattern p = Pattern.compile("\\<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)\\>");
            Matcher m = p.matcher(moonCoords);
            if (m.matches()) {
                Integer x = Integer.valueOf(m.group(1));
                Integer y = Integer.valueOf(m.group(2));
                Integer z = Integer.valueOf(m.group(3));
                originalMoons.add(new Moon(x, y, z));
            }
        }
    }

    private boolean isCompleteOrbitX() {
        boolean isComplete = true;
        for (Moon moon : moons) {
            isComplete = isComplete && moon.isCompleteOrbitX();
        }
        return isComplete;
    }

    private boolean isCompleteOrbitY() {
        boolean isComplete = true;
        for (Moon moon : moons) {
            isComplete = isComplete && moon.isCompleteOrbitY();
        }
        return isComplete;
    }

    private boolean isCompleteOrbitZ() {
        boolean isComplete = true;
        for (Moon moon : moons) {
            isComplete = isComplete && moon.isCompleteOrbitZ();
        }
        return isComplete;
    }

    private void applyGravity() {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i; j < moons.size(); j++) {
                Moon.applyGravity(moons.get(i), moons.get(j));
            }
        }
    }

    private void applyGravityX() {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i; j < moons.size(); j++) {
                Moon.applyGravityX(moons.get(i), moons.get(j));
            }
        }
    }

    private void applyGravityY() {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i; j < moons.size(); j++) {
                Moon.applyGravityY(moons.get(i), moons.get(j));
            }
        }
    }

    private void applyGravityZ() {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i; j < moons.size(); j++) {
                Moon.applyGravityZ(moons.get(i), moons.get(j));
            }
        }
    }

    private void applyVelocity() {
        for (Moon moon : moons) {
            moon.applyVelocity();
        }
    }

    private void applyVelocityX() {
        for (Moon moon : moons) {
            moon.applyVelocityX();
        }
    }

    private void applyVelocityY() {
        for (Moon moon : moons) {
            moon.applyVelocityY();
        }
    }

    private void applyVelocityZ() {
        for (Moon moon : moons) {
            moon.applyVelocityZ();
        }
    }

    private int totalEnergy() {
        int totalEnergy = 0;
        for (Moon moon : moons) {
            totalEnergy += moon.totalEnergy();
        }
        return totalEnergy;
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();

        System.out.println("The answer to part 1 is: " + day12.part1());

        System.out.println("The answer to part 2 is: " + day12.part2());
    }
}
