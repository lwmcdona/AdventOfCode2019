package day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
    private static PasswordValidator passwordValidator;

    @BeforeAll
    public static void init() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void increasingButNoAdjacentPart1Test() {
        int num = 123789;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart1(num, num));
    }

    @Test
    public void decreasingAndAdjacentPart1Test() {
        int num = 223450;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart1(num, num));
    }

    @Test
    public void decreasingWithNoAdjacentPart1Test() {
        int num = 103789;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart1(num, num));
    }

    @Test
    public void increasingAndAdjacentPart1Test() {
        int num = 123779;
        assertEquals(1, passwordValidator.getNumPossibilitiesInRangePart1(num, num));
    }

    @Test
    public void increasingButNoAdjacentPart2Test() {
        int num = 123444;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
    }

    @Test
    public void decreasingAndAdjacentPart2Test() {
        int num = 223331;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
    }

    @Test
    public void decreasingWithNoAdjacentPart2Test() {
        int num = 124503;
        assertEquals(0, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
    }

    @Test
    public void increasingAndAdjacentPart2Test() {
        int num = 112222;
        assertEquals(1, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
        num = 223333;
        assertEquals(1, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
        num = 112233;
        assertEquals(1, passwordValidator.getNumPossibilitiesInRangePart2(num, num));
    }

}
