package day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Intcode {
    private HashMap<Integer, Operator> map;
    private List<Integer> originalCode;
    private List<Integer> code;

    public Intcode() {
        initializeMap();
    }

    public Intcode(String icode) {
        initializeMap();
        setIntcode(icode);
    }

    public String getIntcode() {
        return String.join(",", code.stream().map(i -> String.valueOf(i)).collect(Collectors.toList()));
    }

    public void setIntcode(String icode) {
        this.originalCode = convertCodeToIntegerList(icode);
        reset();
    }

    public void reset() {
        this.code = new ArrayList<>();
        this.code.addAll(this.originalCode);
    }

    public int compute() {
        return compute(this.code);
    }

    public int compute(String icode) {
        List<Integer> code = convertCodeToIntegerList(icode);
        return compute(code);
    }

    public void replaceCodeValue(int pos, int value) {
        code.set(pos, value);
    }

    public void replaceCodeValues(HashMap<Integer, Integer> replacements) {
        replacements.forEach((pos, value) -> code.set(pos, value));
    }

    private void initializeMap() {
        this.map = new HashMap<>();
        this.map.put(99, new HaltOperator());
        this.map.put(1, new AddOperator());
        this.map.put(2, new MultiplyOperator());
        this.map.put(3, new InputOperator());
        this.map.put(4, new OutputOperator());
        this.map.put(5, new JumpIfTrueOperator());
        this.map.put(6, new JumpIfFalseOperator());
        this.map.put(7, new LessThanOperator());
        this.map.put(8, new EqualsOperator());
    }

    private List<Integer> convertCodeToIntegerList(String icode) {
        return Arrays.asList(icode.split(",")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    private int compute(List<Integer> code) {
        int i = 0;
        while (i < code.size()) {
            int instruction = code.get(i);
            int opcode = instruction % 10 + (instruction / 10 % 10 * 10);
            int paramModes = instruction / 100;
            Operator operator;

            if (this.map.containsKey(opcode)) {
                operator = this.map.get(opcode);
                if ((i = operator.operate(code, i, paramModes)) < 0) {
                    return code.get(0);
                }

            } else {
                return -1;
            }
        }
        return code.get(0);
    }
}
