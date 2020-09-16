package day2;

import java.util.LinkedList;
import java.util.List;

public class OutputOperator extends Operator {
    public OutputOperator() {
        super();
        this.opLength = 2;
        this.numParams = 1;
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        LinkedList<Integer> parameters = getParameters(code, index + 1, paramModes);

        System.out.println(parameters.removeFirst());

        return index + this.opLength;
    }
}
