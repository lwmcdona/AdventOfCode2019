package day2;

import java.math.BigInteger;

public class AddOperator extends TernaryOperator {
    public AddOperator() {
        super();
    }

    @Override
    public BigInteger compute(BigInteger first, BigInteger second) {
        return first.add(second);
    }
}
