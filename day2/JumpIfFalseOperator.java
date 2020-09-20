package day2;

import java.math.BigInteger;

public class JumpIfFalseOperator extends JumpOperator {
    public JumpIfFalseOperator() {
        super();
    }

    @Override
    public boolean isConditionTrue(BigInteger first) {
        return first.equals(BigInteger.ZERO);
    }

}
