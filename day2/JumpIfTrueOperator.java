package day2;

public class JumpIfTrueOperator extends JumpOperator {
    public JumpIfTrueOperator() {
        super();
    }

    @Override
    public boolean isConditionTrue(int first) {
        return first != 0;
    }

}
