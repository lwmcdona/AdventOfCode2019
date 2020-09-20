package day2;

import java.math.BigInteger;
import java.util.ArrayList;

import day2.Intcode.ExitCode;

public class RelativeBaseOperator extends UnaryOperator {
    public RelativeBaseOperator() {
        super();
    }

    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
        int value = params.getParameter(1, code).intValue();
        params.addToRelativeBase(value);

        setStatus(ExitCode.CONTINUE);
        return index + getOpLength();

    }
}
