package day13;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import day2.Intcode;
import day2.Intcode.ExitCode;
import day7.Connection;

public class ArcadeCabinet {
    private Connection input;
    private Connection output;
    private Intcode computer;

    private int paddlePosX;
    private int ballPosX;
    private int blockCount;
    private int score;

    private enum TileId {
        PADDLE(3), BALL(4);

        private int value;
        private static Map<Integer, TileId> map = new HashMap<>();

        private TileId(int value) {
            this.value = value;
        }

        static {
            for (TileId id : TileId.values()) {
                map.put(id.value, id);
            }
        }

        public int getValue() {
            return value;
        }
    }

    private enum JoystickPosition {
        LEFT(-1), NEUTRAL(0), RIGHT(1);

        private int value;

        private JoystickPosition(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public ArcadeCabinet(String icode) {
        this.score = 0;
        this.blockCount = 0;
        initializeIO();
        this.computer = new Intcode(icode, input, output);
    }

    public int getBlockCount() {
        return blockCount;
    }

    public int getScore() {
        return score;
    }

    public void setup() {
        computer.compute();
        while (!output.isEmpty()) {
            output.receive();
            output.receive();
            int id = output.receive();

            if (id == 2) {
                blockCount++;
            }
        }
    }

    public void play() {
        computer.replaceCodeValue(0, new BigInteger(String.valueOf(2)));

        ExitCode code = ExitCode.WAIT_FOR_INPUT;
        while (code.equals(ExitCode.WAIT_FOR_INPUT)) {
            code = computer.compute();
            while (!output.isEmpty()) {
                int x = output.receive();
                int y = output.receive();
                int id = output.receive();

                if (id == TileId.PADDLE.getValue()) {
                    paddlePosX = x;
                } else if (id == TileId.BALL.getValue()) {
                    ballPosX = x;
                } else if (x == -1 && y == 0) {
                    score = id;
                }
            }

            if (ballPosX > paddlePosX) {
                input.send(JoystickPosition.RIGHT.getValue());
            } else if (ballPosX < paddlePosX) {
                input.send(JoystickPosition.LEFT.getValue());
            } else {
                input.send(JoystickPosition.NEUTRAL.getValue());
            }
        }
    }

    public void reset() {
        computer.reset();
    }

    private void initializeIO() {
        input = new Connection();
        output = new Connection();
    }
}
