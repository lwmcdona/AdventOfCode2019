package day2;

import java.math.BigInteger;
import java.util.ArrayList;
import day2.Intcode.ExitCode;

public abstract class JumpOperator extends Operator {
    public JumpOperator() {
        super();
        setOpLength(3);
        setNumParams(2);
    }

    @Override
    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
        BigInteger first = params.getParameter(1, code);
        BigInteger second = params.getParameter(2, code);

        setStatus(ExitCode.CONTINUE);

        if (isConditionTrue(first)) {
            return second.intValue();
        }
        return index + getOpLength();
    }

    public abstract boolean isConditionTrue(BigInteger first);
}
