package day4;

public class Day4 {
    private int minValue;
    private int maxValue;
    private PasswordValidator passwordValidator;

    public Day4(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.passwordValidator = new PasswordValidator();
    }

    public int part1() {
        return passwordValidator.getNumPossibilitiesInRangePart1(this.minValue, this.maxValue);
    }

    public int part2() {
        return passwordValidator.getNumPossibilitiesInRangePart2(this.minValue, this.maxValue);
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4(123257, 647015);

        System.out.println("The answer to part 1 is: " + day4.part1());

        System.out.println("The answer to part 2 is: " + day4.part2());
    }
}
