package day2;

import java.util.List;
import java.util.Scanner;

public class InputOperator extends Operator {
    public InputOperator() {
        super();
        this.opLength = 2;
    }

    public int operate(List<Integer> code, int index, int paramModes) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int value = scan.nextInt();
        // scan.close();

        int location = code.get(index + 1);
        code.set(location, value);

        return index + this.opLength;
    }

}
