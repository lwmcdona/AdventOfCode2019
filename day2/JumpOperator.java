package day2;

import java.util.LinkedList;
import java.util.List;

public abstract class JumpOperator extends Operator {
    public JumpOperator() {
        super();
        this.opLength = 3;
        this.numParams = 2;
    }

    @Override
    public int operate(List<Integer> code, int index, int paramModes) {
        LinkedList<Integer> parameters = getParameters(code, index + 1, paramModes);

        int first = parameters.removeFirst();
        int second = parameters.removeFirst();

        if (isConditionTrue(first)) {
            return second;
        }
        return index + this.opLength;
    }

    public abstract boolean isConditionTrue(int first);
}
