package day2;

import java.math.BigInteger;
import java.util.ArrayList;

import day2.Intcode.ExitCode;

public class HaltOperator extends Operator {
    public HaltOperator() {
        super();
        setOpLength(1);
    }

    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
        setStatus(ExitCode.SUCCESS);
        return index;
    }
}
