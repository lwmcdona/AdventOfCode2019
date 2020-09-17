package day2;

import java.util.List;
import java.util.Scanner;

import day2.Intcode.ExitCode;
import day7.Connection;

public class InputOperator extends Operator {
    private Connection input = null;

    public InputOperator() {
        super();
        setOpLength(2);
    }

    public InputOperator(Connection input) {
        super();
        setOpLength(2);
        this.input = input;
    }

    @Override
    public int operate(List<Integer> code, int index, int paramModes) {
        int value;
        if (input != null) {
            if (input.isEmpty()) {
                setStatus(ExitCode.WAIT_FOR_INPUT);
                return index;
            }
            value = input.receive();
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a number: ");
            value = scan.nextInt();
        }

        int location = code.get(index + 1);
        code.set(location, value);

        setStatus(ExitCode.CONTINUE);
        return index + getOpLength();
    }
}
