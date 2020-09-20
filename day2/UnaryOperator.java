package day2;

import java.math.BigInteger;
import java.util.ArrayList;

public abstract class UnaryOperator extends Operator {
    public UnaryOperator() {
        super();
        setOpLength(2);
        setNumParams(1);
    }

    public abstract int operate(ArrayList<BigInteger> code, int index, Parameters params);

}
