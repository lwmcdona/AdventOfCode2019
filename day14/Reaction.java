package day14;

import java.util.ArrayList;
import java.util.List;

public class Reaction {
    private Long output;
    private List<Reactant> reactants;

    public Reaction(Long output) {
        this.output = output;
        this.reactants = new ArrayList<>();
    }

    public Long getOutput() {
        return output;
    }

    public List<Reactant> getReactants() {
        return reactants;
    }

    public void addReactant(Reactant r) {
        reactants.add(r);
    }

}
