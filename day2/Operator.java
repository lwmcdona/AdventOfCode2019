package day2;

import java.util.LinkedList;
import java.util.List;
import day2.Intcode.ExitCode;

public abstract class Operator {
    private int opLength;
    private int numParams;
    private ExitCode status;

    public Operator() {
    }

    public int getOpLength() {
        return this.opLength;
    }

    public int getNumParams() {
        return this.numParams;
    }

    public ExitCode getStatus() {
        return this.status;
    }

    protected void setStatus(ExitCode status) {
        this.status = status;
    }

    protected void setNumParams(int numParams) {
        this.numParams = numParams;
    }

    protected void setOpLength(int opLength) {
        this.opLength = opLength;
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
