package day2;

public class LessThanOperator extends BinaryAndStoreOperator {
    public LessThanOperator() {
        super();
    }

    @Override
    public int compute(int first, int second) {
        if (first < second) {
            return 1;
        }
        return 0;
    }
}
