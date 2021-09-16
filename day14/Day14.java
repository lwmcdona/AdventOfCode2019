package day14;

import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Queue;

import helpers.InputHelper;

public class Day14 {
    private final Path DEFAULT_PATH = FileSystems.getDefault().getPath("day14", "day14.txt");

    private Path path;
    private String icode;
    private Map<String, Reaction> reactions;

    public Day14() {
        this.path = DEFAULT_PATH.toAbsolutePath();
        this.reactions = new HashMap<>();
        readCodeFromFile();
    }

    public String getIntcode() {
        return icode;
    }

    public long part1() {
        return calculateOre(1);
    }

    public long part2() {
        return binarySearchFuel();
    }

    private void readCodeFromFile() {
        InputHelper ihelper = new InputHelper(this.path);
        List<String> input = ihelper.readLines();

        for (String reaction : input) {
            String[] strings = reaction.split(" => ");
            String reactants = strings[0];
            String product = strings[1];

            Pattern p = Pattern.compile("(\\d+) ([A-Z]+)");
            Matcher mReactant = p.matcher(reactants);
            Matcher mProduct = p.matcher(product);

            String productName = "";
            while (mProduct.find()) {
                Long amount = Long.valueOf(mProduct.group(1));
                productName = mProduct.group(2);

                reactions.put(productName, new Reaction(amount));
            }

            while (mReactant.find()) {
                Long amount = Long.valueOf(mReactant.group(1));
                String name = mReactant.group(2);

                reactions.get(productName).addReactant(new Reactant(name, amount));
            }
        }
    }

    private long calculateOre(long fuelCount) {
        long oreCount = 0;

        Queue<Reactant> resources = new LinkedList<>();
        Map<String, Long> surplus = new HashMap<>();
        resources.add(new Reactant("FUEL", fuelCount));
        while (!resources.isEmpty()) {
            Reactant resource = resources.remove();

            if (resource.getName().equals("ORE")) {
                oreCount += resource.getAmount();
            } else if (surplus.getOrDefault(resource.getName(), 0L) > resource.getAmount()) {
                // surplus can handle the amount needed
                surplus.put(resource.getName(), surplus.get(resource.getName()) - resource.getAmount());
            } else {

                Reaction reaction = reactions.get(resource.getName());
                long reactionOutput = reaction.getOutput();

                long batches = (long) Math
                        .ceil(((double) resource.getAmount() - surplus.getOrDefault(resource.getName(), 0L))
                                / reactionOutput);

                surplus.put(resource.getName(),
                        surplus.getOrDefault(resource.getName(), 0L) + batches * reactionOutput - resource.getAmount());

                for (Reactant r : reaction.getReactants()) {
                    resources.add(new Reactant(r.getName(), r.getAmount() * batches));
                }
            }

        }
        return oreCount;
    }

    private long binarySearchFuel() {
        long maxOre = 1000000000000L;
        long minFuel = maxOre / calculateOre(1);
        long maxFuel = maxOre;
        while (maxFuel - minFuel > 1) {
            long fuelCount = ((maxFuel + minFuel) / 2);
            if (calculateOre(fuelCount) < maxOre) {
                minFuel = fuelCount;
            } else {
                maxFuel = fuelCount;
            }
        }
        return minFuel;
    }

    public static void main(String[] args) {
        Day14 day14 = new Day14();

        System.out.println("The answer to part 1 is: " + day14.part1());

        System.out.println("The answer to part 2 is: " + day14.part2());
    }
}