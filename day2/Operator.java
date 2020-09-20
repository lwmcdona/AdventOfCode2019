package day2;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public abstract int operate(ArrayList<BigInteger> code, int index, Parameters params);
}
