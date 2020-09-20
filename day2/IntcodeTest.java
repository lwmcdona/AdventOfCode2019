package day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class IntcodeTest {
    Intcode icode = new Intcode();

    @Test
    public void testIntCode1() {
        this.icode.compute("1,0,0,0,99");
        assertEquals(2, this.icode.getResult());
    }

    @Test
    public void testIntCode2() {
        this.icode.compute("2,3,0,3,99");
        assertEquals(2, this.icode.getResult());
    }

    @Test
    public void testIntCode3() {
        this.icode.compute("2,4,4,5,99,0");
        assertEquals(2, this.icode.getResult());
    }

    @Test
    public void testIntCode4() {
        this.icode.compute("1,1,1,4,99,5,6,0,99");
        assertEquals(30, this.icode.getResult());
    }

    @Test
    public void testRelativeBase() {
        this.icode.compute("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99");
        // should output a copy of itself
        assertEquals(109, this.icode.getResult());
    }

    @Test
    public void testLargeNumbers1() {
        this.icode.compute("1102,34915192,34915192,7,4,7,99,0");
        // should output 1219070632396864
        assertEquals(1102, this.icode.getResult());
    }

    @Test
    public void testLargeNumbers2() {
        this.icode.compute("104,1125899906842624,99");
        // should output 1125899906842624
        assertEquals(104, this.icode.getResult());
    }
}
