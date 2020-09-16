package day2;

public class EqualsOperator extends BinaryAndStoreOperator {
    public EqualsOperator() {
        super();
    }

    @Override
    public int compute(int first, int second) {
        if (first == second) {
            return 1;
        }
        return 0;
    }

}
