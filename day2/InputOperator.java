package day2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import day2.Intcode.ExitCode;
import day7.Connection;

public class InputOperator extends UnaryOperator {
    private Connection input = null;

    public InputOperator() {
        super();
    }

    public InputOperator(Connection input) {
        super();
        this.input = input;
    }

    @Override
    public int operate(ArrayList<BigInteger> code, int index, Parameters params) {
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

        int location = params.getLocationParameter(1, code);
        code.set(location, new BigInteger(String.valueOf(value)));

        setStatus(ExitCode.CONTINUE);
        return index + getOpLength();
    }
}
