package day2;

public class AddOperator extends BinaryAndStoreOperator {
    public AddOperator() {
        super();
    }

    @Override
    public int compute(int first, int second) {
        return first + second;
    }
}
