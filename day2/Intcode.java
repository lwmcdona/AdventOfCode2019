package day2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import day7.Connection;

public class Intcode {
    private HashMap<Integer, Operator> map;
    private List<BigInteger> originalCode;
    private ArrayList<BigInteger> code;
    private Integer programCounter;
    private RelativeBase relativeBase;

    public enum ExitCode {
        CONTINUE, SUCCESS, WAIT_FOR_INPUT, INVALID_INSTRUCTION, NO_HALT
    }

    public Intcode() {
        InputOperator inOp = new InputOperator();
        OutputOperator outOp = new OutputOperator();
        initializeMap(inOp, outOp);
    }

    public Intcode(Connection input, Connection output) {
        InputOperator inOp = new InputOperator(input);
        OutputOperator outOp = new OutputOperator(output);
        initializeMap(inOp, outOp);
    }

    public Intcode(String icode) {
        InputOperator inOp = new InputOperator();
        OutputOperator outOp = new OutputOperator();
        initializeMap(inOp, outOp);
        setIntcode(icode);
    }

    public Intcode(String icode, Connection input, Connection output) {
        InputOperator inOp = new InputOperator(input);
        OutputOperator outOp = new OutputOperator(output);
        initializeMap(inOp, outOp);
        setIntcode(icode);
    }

    public String getIntcode() {
        return String.join(",", code.stream().map(i -> String.valueOf(i)).collect(Collectors.toList()));
    }

    public void setIntcode(String icode) {
        this.originalCode = convertCodeToIntegerList(icode);
        reset();
    }

    public void setProgramCounter(Integer location) {
        this.programCounter = location;
    }

    public void reset() {
        this.code = new ArrayList<>();
        this.code.addAll(this.originalCode);
        this.programCounter = 0;
        this.relativeBase = new RelativeBase();
    }

    public ExitCode compute() {
        return compute(this.code);
    }

    public int getResult() {
        return this.code.get(0).intValue();
    }

    public ExitCode compute(String icode) {
        setIntcode(icode);
        return compute(this.code);
    }

    public void replaceCodeValue(int pos, BigInteger value) {
        code.set(pos, value);
    }

    public void replaceCodeValues(HashMap<Integer, BigInteger> replacements) {
        replacements.forEach((pos, value) -> code.set(pos, value));
    }

    public static void ensureSize(ArrayList<BigInteger> code, int minCapacity) {
        code.ensureCapacity(minCapacity);
        while (code.size() < minCapacity) {
            code.add(BigInteger.ZERO);
        }
    }

    private void initializeMap(InputOperator inOp, OutputOperator outOp) {
        this.map = new HashMap<>();
        this.map.put(99, new HaltOperator());
        this.map.put(1, new AddOperator());
        this.map.put(2, new MultiplyOperator());
        this.map.put(3, inOp);
        this.map.put(4, outOp);
        this.map.put(5, new JumpIfTrueOperator());
        this.map.put(6, new JumpIfFalseOperator());
        this.map.put(7, new LessThanOperator());
        this.map.put(8, new EqualsOperator());
        this.map.put(9, new RelativeBaseOperator());
    }

    private List<BigInteger> convertCodeToIntegerList(String icode) {
        return Arrays.asList(icode.split(",")).stream().map(s -> new BigInteger(s)).collect(Collectors.toList());
    }

    private ExitCode compute(ArrayList<BigInteger> code) {
        while (this.programCounter < code.size()) {
            int instruction = code.get(this.programCounter).intValue();
            int opcode = instruction % 10 + (instruction / 10 % 10 * 10);
            int paramModes = instruction / 100;

            Operator operator;
            if (this.map.containsKey(opcode)) {
                operator = this.map.get(opcode);
                this.programCounter = operator.operate(code, this.programCounter,
                        new Parameters(paramModes, this.relativeBase, this.programCounter, operator.getNumParams()));
                if (operator.getStatus() != ExitCode.CONTINUE) {
                    return operator.getStatus();
                }
            } else {
                return ExitCode.INVALID_INSTRUCTION;
            }
        }
        return ExitCode.NO_HALT;
    }
}
