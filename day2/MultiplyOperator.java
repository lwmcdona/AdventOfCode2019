package day2;

import java.math.BigInteger;

public class MultiplyOperator extends TernaryOperator {
    public MultiplyOperator() {
        super();
    }

    @Override
    public BigInteger compute(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }
}
