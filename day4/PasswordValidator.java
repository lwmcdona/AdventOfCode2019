package day4;

public class PasswordValidator {

    public PasswordValidator() {

    }

    public int getNumPossibilitiesInRangePart1(int minValue, int maxValue) {
        int possibilities = 0;

        for (int num = minValue; num <= maxValue; num++) {
            boolean adjacent = false;
            boolean increasing = true;
            int prevDigit = -1;
            int currNum = num;
            while (increasing && currNum > 0) {
                int digit = currNum % 10;
                if (prevDigit >= 0) {
                    if (digit > prevDigit) {
                        increasing = false;
                    } else if (digit == prevDigit) {
                        adjacent = true;
                    }
                }
                currNum /= 10;
                prevDigit = digit;
            }

            if (increasing && adjacent) {
                possibilities++;
            }
        }

        return possibilities;
    }

    public int getNumPossibilitiesInRangePart2(int minValue, int maxValue) {
        int possibilities = 0;

        for (int num = minValue; num <= maxValue; num++) {
            boolean adjacent = false;
            boolean increasing = true;
            int numAdjacents = 0;
            int prevDigit = -1;
            int currNum = num;
            while (increasing && currNum > 0) {
                int digit = currNum % 10;
                if (prevDigit >= 0) {
                    if (digit > prevDigit) {
                        increasing = false;
                        break;
                    } else if (digit == prevDigit) {
                        numAdjacents++;
                    } else {
                        if (numAdjacents == 1) {
                            adjacent = true;
                        }
                        numAdjacents = 0;
                    }
                }
                currNum /= 10;
                prevDigit = digit;
            }

            if (increasing && (adjacent || numAdjacents == 1)) {
                possibilities++;
            }
        }
        return possibilities;
    }
}
