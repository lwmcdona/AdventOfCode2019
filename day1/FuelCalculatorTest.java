package day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FuelCalculatorTest {
    FuelCalculator fc = new FuelCalculator();

    @Test
    public void moduleFuelCalculation() {
        assertEquals(2, this.fc.calculateModuleFuel(12));
        assertEquals(2, this.fc.calculateModuleFuel(14));
        assertEquals(654, this.fc.calculateModuleFuel(1969));
        assertEquals(33583, this.fc.calculateModuleFuel(100756));
    }

    @Test
    public void moduleFuelCalculationWithFuelMass() {
        assertEquals(2, this.fc.calculateModuleFuelWithFuelMass(14));
        assertEquals(966, this.fc.calculateModuleFuelWithFuelMass(1969));
        assertEquals(50346, this.fc.calculateModuleFuelWithFuelMass(100756));
    }
}