package day2;

import java.math.BigInteger;
import java.util.ArrayList;

public class Parameters {
    ArrayList<Integer> modes = new ArrayList<>();
    RelativeBase relativeBase;
    int start;

    public Parameters(int paramModes, RelativeBase relativeBase, int start, int numParams) {
        this.relativeBase = relativeBase;
        this.start = start;
        for (int i = 0; i < numParams; i++) {
            this.modes.add(paramModes % 10);
            paramModes /= 10;
        }
    }

    public void addToRelativeBase(int value) {
        relativeBase.add(value);
    }

    public int getLocationParameter(int paramNum, ArrayList<BigInteger> code) {
        int mode = this.modes.get(paramNum - 1);
        int parameter = code.get(this.start + paramNum).intValue();
        if (mode == 2) {
            return parameter + this.relativeBase.getValue();
        }
        return parameter;
    }

    public BigInteger getParameter(int paramNum, ArrayList<BigInteger> code) {
        int mode = this.modes.get(paramNum - 1);
        BigInteger parameter = code.get(this.start + paramNum);
        if (mode == 0) {
            int index = parameter.intValue();
            Intcode.ensureSize(code, index + 1);
            return code.get(index);
        } else if (mode == 2) {
            int index = parameter.add(this.relativeBase.getBigValue()).intValue();
            Intcode.ensureSize(code, index + 1);
            return code.get(index);
        }
        return parameter;
    }
}
