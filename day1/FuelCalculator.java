package day1;

import java.util.List;

public class FuelCalculator {
    public FuelCalculator() {

    }

    // part 1
    public int calculateModuleFuel(int mass) {
        return mass / 3 - 2;
    }

    // part 2
    public int calculateModuleFuelWithFuelMass(int mass) {
        int fuel = mass / 3 - 2;
        if (fuel < 0) {
            return 0;
        } else {
            return fuel + calculateModuleFuelWithFuelMass(fuel);
        }
    }

    public int calculateAllModuleFuel(List<Integer> masses) {
        Integer total = 0;
        for (int i = 0; i < masses.size(); i++) {
            total += calculateModuleFuel(masses.get(i));
        }
        return total;
    }

    public int calculateAllModuleFuelWithFuelMass(List<Integer> masses) {
        Integer total = 0;
        for (int i = 0; i < masses.size(); i++) {
            total += calculateModuleFuelWithFuelMass(masses.get(i));
        }
        return total;
    }
}