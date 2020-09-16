package day2;

import java.util.LinkedList;
import java.util.List;

public abstract class BinaryAndStoreOperator extends Operator {
    public BinaryAndStoreOperator() {
        super();
        this.opLength = 4;
        this.numParams = 2;
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        LinkedList<Integer> parameters = getParameters(code, index + 1, paramModes);

        int first = parameters.removeFirst();
        int second = parameters.removeFirst();
        int location = code.get(index + 3);
        code.set(location, compute(first, second));

        return index + this.opLength;
    }

    public abstract int compute(int first, int second);
}
