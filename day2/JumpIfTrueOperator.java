package day2;

import java.math.BigInteger;

public class JumpIfTrueOperator extends JumpOperator {
    public JumpIfTrueOperator() {
        super();
    }

    @Override
    public boolean isConditionTrue(BigInteger first) {
        return !first.equals(BigInteger.ZERO);
    }

}
