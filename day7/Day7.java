package day7;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import helpers.InputHelper;

public class Day7 {
    private final Path DEFAULT_PATH = FileSystems.getDefault()
            .getPath("C:\\Users\\Logan\\OneDrive\\Problems\\AdventOfCode2019\\day7\\day7.txt");
    private static final Integer NUM_AMPLIFIERS = 5;

    private Path path;
    private String icode;
    private Connection input;
    private Connection output;

    public Day7() {
        this.path = DEFAULT_PATH;
        readCodeFromFile();
        initializeIO();
    }

    public Day7(String icode) {
        this.icode = icode;
        initializeIO();
    }

    public String getIntcode() {
        return icode;
    }

    public int part1() {
        return getMaxThrusterOutput(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4)), false);
    }

    public int part2() {
        return getMaxThrusterOutput(new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8, 9)), true);
    }

    private int getMaxThrusterOutput(List<Integer> phases, boolean isFeedback) {
        if (isFeedback) {
            this.output = this.input;
        } else {
            this.output = new Connection();
        }

        int maxOutput = Integer.MIN_VALUE;

        List<List<Integer>> phaseSequences = createAllPermutations(phases);

        List<Amplifier> amplifiers = initializeAmplifiers();

        for (List<Integer> sequence : phaseSequences) {
            LinkedList<Amplifier> ampsToRun = new LinkedList<>();
            for (int i = 0; i < sequence.size(); i++) {
                Amplifier amp = amplifiers.get(i);
                amp.reset();
                Integer phaseSetting = sequence.get(i);
                amp.send(phaseSetting);
                ampsToRun.add(amp);
            }

            this.output.send(0);

            while (!ampsToRun.isEmpty()) {
                Amplifier amp = ampsToRun.pop();
                if (amp.canRun()) {
                    amp.run();
                    if (isFeedback) {
                        ampsToRun.add(amp);
                    }
                }
            }

            int outValue = this.input.receive();
            if (maxOutput < outValue) {
                maxOutput = outValue;
            }
        }

        return maxOutput;
    }

    private List<Amplifier> initializeAmplifiers() {
        List<Amplifier> amplifiers = new ArrayList<>();
        Connection input = this.output;
        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            Connection output = new Connection();
            if (i == NUM_AMPLIFIERS - 1) {
                output = this.input;
            }
            amplifiers.add(new Amplifier(this.icode, input, output));
            input = output;
        }

        return amplifiers;
    }

    private List<List<Integer>> createAllPermutations(List<Integer> list) {
        if (list.size() == 1) {
            return Arrays.asList(list);
        } else {
            Integer phase = list.remove(0);
            List<List<Integer>> phaseSettings = new ArrayList<>();
            List<List<Integer>> permutations = createAllPermutations(list);

            for (List<Integer> p : permutations) {
                for (int i = 0; i <= p.size(); i++) {
                    List<Integer> tmp = new ArrayList<>(p);
                    tmp.add(i, phase);
                    phaseSettings.add(tmp);
                }
            }
            return phaseSettings;
        }
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();
        this.icode = input.get(0);
    }

    private void initializeIO() {
        this.input = new Connection();
        this.output = new Connection();
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7();

        System.out.println("The answer to part 1 is: " + day7.part1());

        System.out.println("The answer to part 2 is: " + day7.part2());
    }
}
