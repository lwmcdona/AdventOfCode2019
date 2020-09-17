package day2;

import java.util.LinkedList;
import java.util.List;

import day2.Intcode.ExitCode;

public abstract class JumpOperator extends Operator {
    public JumpOperator() {
        super();
        setOpLength(3);
        setNumParams(2);
    }

    @Override
    public int operate(List<Integer> code, int index, int paramModes) {
        LinkedList<Integer> parameters = getParameters(code, index + 1, paramModes);

        int first = parameters.removeFirst();
        int second = parameters.removeFirst();

        setStatus(ExitCode.CONTINUE);

        if (isConditionTrue(first)) {
            return second;
        }
        return index + getOpLength();
    }

    public abstract boolean isConditionTrue(int first);
}
