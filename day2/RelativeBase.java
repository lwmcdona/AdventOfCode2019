package day2;

import java.math.BigInteger;

public class RelativeBase {
    private int value;

    public RelativeBase() {
        this.value = 0;
    }

    public RelativeBase(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public BigInteger getBigValue() {
        return new BigInteger(String.valueOf(value));
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int value) {
        this.value += value;
    }

}
