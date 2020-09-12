package day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class IntcodeTest {
    Intcode icode = new Intcode();

    @Test
    public void testIntCode() {
        assertEquals(2, this.icode.compute("1,0,0,0,99"));
        assertEquals(2, this.icode.compute("2,3,0,3,99"));
        assertEquals(2, this.icode.compute("2,4,4,5,99,0"));
        assertEquals(30, this.icode.compute("1,1,1,4,99,5,6,0,99"));
    }
}
