package day2;

public class MultiplyOperator extends BinaryAndStoreOperator {
    public MultiplyOperator() {
        super();
    }

    @Override
    public int compute(int first, int second) {
        return first * second;
    }
}
