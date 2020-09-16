package day2;

import java.util.List;

public class HaltOperator extends Operator {
    public HaltOperator() {
        super();
        this.opLength = 1;
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        return -1;
    }
}
