package day2;

import java.util.List;

import day2.Intcode.ExitCode;

public class HaltOperator extends Operator {
    public HaltOperator() {
        super();
        setOpLength(1);
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        setStatus(ExitCode.SUCCESS);
        return index;
    }
}
