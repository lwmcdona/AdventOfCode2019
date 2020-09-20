package day2;

import java.math.BigInteger;

public class EqualsOperator extends TernaryOperator {
    public EqualsOperator() {
        super();
    }

    @Override
    public BigInteger compute(BigInteger first, BigInteger second) {
        if (first.equals(second)) {
            return BigInteger.ONE;
        }
        return BigInteger.ZERO;
    }

}
