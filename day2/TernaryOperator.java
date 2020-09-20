package day2;

import java.math.BigInteger;
import java.util.ArrayList;
import day2.Intcode.ExitCode;

public abstract class TernaryOperator extends Operator {
    public TernaryOperator() {
        super();
        setOpLength(4);
        setNumParams(3);
    }

    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
        BigInteger first = params.getParameter(1, code);
        BigInteger second = params.getParameter(2, code);
        int location = params.getLocationParameter(3, code);

        Intcode.ensureSize(code, location + 1);
        code.set(location, compute(first, second));

        setStatus(ExitCode.CONTINUE);

        return index + getOpLength();
    }

    public abstract BigInteger compute(BigInteger first, BigInteger second);
}
