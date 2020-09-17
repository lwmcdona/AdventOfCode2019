package day2;

import java.util.LinkedList;
import java.util.List;

import day2.Intcode.ExitCode;
import day7.Connection;

public class OutputOperator extends Operator {
    private Connection output = null;

    public OutputOperator() {
        super();
        setOpLength(2);
        setNumParams(1);
    }

    public OutputOperator(Connection output) {
        super();
        setOpLength(2);
        setNumParams(1);
        this.output = output;
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        LinkedList<Integer> parameters = getParameters(code, index + 1, paramModes);

        if (output != null) {
            output.send(parameters.pop());
        } else {
            System.out.println(parameters.pop());
        }

        setStatus(ExitCode.CONTINUE);

        return index + getOpLength();
    }
}
