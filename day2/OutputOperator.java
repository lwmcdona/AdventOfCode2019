package day2;

import java.math.BigInteger;
import java.util.ArrayList;
import day2.Intcode.ExitCode;
import day7.Connection;

public class OutputOperator extends UnaryOperator {
    private Connection output = null;

    public OutputOperator() {
        super();
    }

    public OutputOperator(Connection output) {
        super();
        this.output = output;
    }

    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
        BigInteger value = params.getParameter(1, code);
        if (output != null) {
            output.send(value.intValue());
        } else {
            System.out.println(value);
        }

        setStatus(ExitCode.CONTINUE);

        return index + getOpLength();
    }
}
