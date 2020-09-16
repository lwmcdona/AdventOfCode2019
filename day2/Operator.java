package day2;

import java.util.LinkedList;
import java.util.List;

public abstract class Operator {
    protected int opLength;
    protected int numParams;

    public Operator() {
    }

    public int getOpLength() {
        return this.opLength;
    }

    public int getNumParams() {
        return this.numParams;
    }

    public abstract int operate(List<Integer> code, int index, int paramModes);

    protected LinkedList<Integer> getParameters(List<Integer> code, int start, int paramModes) {
        LinkedList<Integer> parameters = new LinkedList<>();

        for (int i = start; i < start + getNumParams(); i++) {
            int mode = paramModes % 10;
            int value = code.get(i);
            if (mode == 0) {
                value = code.get(value);
            }

            parameters.add(value);

            paramModes /= 10;
        }

        return parameters;
    }
}
