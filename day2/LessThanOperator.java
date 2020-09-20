package day2;

import java.math.BigInteger;

public class LessThanOperator extends TernaryOperator {
    public LessThanOperator() {
        super();
    }

    @Override
    public BigInteger compute(BigInteger first, BigInteger second) {
        if (first.compareTo(second) < 0) {
            return BigInteger.ONE;
        }
        return BigInteger.ZERO;
    }
}
