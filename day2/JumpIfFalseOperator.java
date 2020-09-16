package day2;

public class JumpIfFalseOperator extends JumpOperator {
    public JumpIfFalseOperator() {
        super();
    }

    @Override
    public boolean isConditionTrue(int first) {
        return first == 0;
    }

}
