package day2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public class Intcode {
    private HashMap<Integer, IntBinaryOperator> map;
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
        this.code = convertCodeToIntegerList(icode);
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
        this.map = new HashMap<Integer, IntBinaryOperator>();
        this.map.put(99, null);
        this.map.put(1, (first, second) -> first + second);
        this.map.put(2, (first, second) -> first * second);
    }

    private List<Integer> convertCodeToIntegerList(String icode) {
        return Arrays.asList(icode.split(",")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    private int compute(List<Integer> code) {
        for (int i = 0; i < code.size(); i += 4) {
            int operand = code.get(i);
            if (this.map.containsKey(operand)) {
                if (operand == 99) {
                    return code.get(0);
                } else {
                    int first = code.get(code.get(i + 1));
                    int second = code.get(code.get(i + 2));
                    int location = code.get(i + 3);
                    code.set(location, this.map.get(operand).applyAsInt(first, second));
                }
            } else {
                return -1;
            }
        }
        return code.get(0);
    }
}
