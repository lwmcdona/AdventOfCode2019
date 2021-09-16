package day14;

public class Reactant {
    private String name;
    private Long amount;

    public Reactant(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
