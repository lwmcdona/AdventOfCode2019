package day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import day7.Connection;
import day2.Intcode;
import day2.Intcode.ExitCode;

public class PaintingRobot {
    private final Integer HULL_SIZE = 100;

    private enum Direction {
        UP(0), RIGHT(1), DOWN(2), LEFT(3);

        private int value;
        private static Map<Integer, Direction> map = new HashMap<>();

        private Direction(int value) {
            this.value = value;
        }

        static {
            for (Direction direction : Direction.values()) {
                map.put(direction.value, direction);
            }
        }

        private Direction turnLeft() {
            return map.get((((value - 1) % 4) + 4) % 4);
        }

        public Direction turnRight() {
            return map.get((((value + 1) % 4) + 4) % 4);
        }

        public Direction turn(int direction) {
            if (direction == 0) {
                return turnLeft();
            }
            return turnRight();
        }
    }

    private enum Colour {
        BLACK(0), WHITE(1);

        private int value;
        private static Map<Integer, Colour> map = new HashMap<>();

        private Colour(int value) {
            this.value = value;
        }

        static {
            for (Colour colour : Colour.values()) {
                map.put(colour.value, colour);
            }
        }

        public static Colour valueOf(int colour) {
            return map.get(colour);
        }
    }

    private Connection input;
    private Connection output;
    private Intcode computer;
    private Direction orientation = Direction.UP;
    private Integer xCoor = HULL_SIZE / 2;
    private Integer yCoor = HULL_SIZE / 2;
    private Colour[][] hull;
    private Set<Integer> visited = new HashSet<>();
    private Boolean startOnWhite;

    public PaintingRobot(String icode, Boolean startOnWhite) {
        this.startOnWhite = startOnWhite;
        initializeIO();
        initializeHull();
        computer = new Intcode(icode, input, output);
    }

    public void run() {
        ExitCode code = ExitCode.WAIT_FOR_INPUT;
        while (code.equals(ExitCode.WAIT_FOR_INPUT)) {
            input.send(hull[xCoor][yCoor].ordinal());
            code = computer.compute();

            Integer paintColour = output.receive();
            hull[xCoor][yCoor] = Colour.valueOf(paintColour);

            Integer turnDirection = output.receive();
            orientation = orientation.turn(turnDirection);

            visited.add(xCoor * HULL_SIZE + yCoor);

            move(orientation);
        }
    }

    public int getCoverage() {
        return visited.size();
    }

    public void reset() {
        initializeHull();
        visited.clear();
        computer.reset();
    }

    public void printHull() {
        for (int i = 0; i < HULL_SIZE; i++) {
            for (int j = 0; j < HULL_SIZE; j++) {
                if (hull[i][j].equals(Colour.BLACK)) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    private void initializeIO() {
        input = new Connection();
        output = new Connection();
    }

    private void initializeHull() {
        hull = new Colour[HULL_SIZE][HULL_SIZE];
        for (int i = 0; i < HULL_SIZE; i++) {
            for (int j = 0; j < HULL_SIZE; j++) {
                hull[i][j] = Colour.BLACK;
            }
        }

        if (startOnWhite) {
            hull[xCoor][yCoor] = Colour.WHITE;
        }
    }

    private void move(Direction direction) {
        switch (direction) {
            case UP:
                yCoor -= 1;
                break;
            case RIGHT:
                xCoor += 1;
                break;
            case DOWN:
                yCoor += 1;
                break;
            case LEFT:
                xCoor -= 1;
                break;
            default:
                System.out.println("Unknown direction");
        }
    }
}
