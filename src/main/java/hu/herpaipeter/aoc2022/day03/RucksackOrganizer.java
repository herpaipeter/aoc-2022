package hu.herpaipeter.aoc2022.day03;

import java.util.List;
import java.util.stream.IntStream;

public class RucksackOrganizer {

    public int sumOfCompartmentSameElements(List<String> rucksacks) {
        return rucksacks.stream().mapToInt(RucksackOrganizer::getRucksackValue).sum();
    }

    private static int getRucksackValue(String rucksack) {
        String firstComp = rucksack.substring(0, rucksack.length() / 2);
        String secondComp = rucksack.substring(rucksack.length() / 2);
        return getCharValue(getCompartmentsSameChar(firstComp, secondComp));
    }

    private static int getCharValue(int ch) {
        return 0 < ch ? ch - ((int)'a' <= ch ? 'a' - 1 : 'A' - 27) : 0;
    }

    private static int getCompartmentsSameChar(String firstComp, String secondComp) {
        return firstComp.chars()
                .filter(fc -> secondComp.chars().anyMatch(sc -> sc == fc))
                .findFirst().orElse(0);
    }

    public int sumOfThreeRucksacksBadge(List<String> rucksacks) {
        return IntStream.iterate(0, i -> i < rucksacks.size(), i -> i + 3)
                        .map(g -> getSumOfThree(rucksacks, g))
                        .sum();
    }

    private static int getSumOfThree(List<String> rucksacks, int g) {
        int sameChar = rucksacks.get(g).chars()
                            .filter(fc -> rucksacks.get(g + 1).chars().anyMatch(c -> c == fc) &&
                                          rucksacks.get(g + 2).chars().anyMatch(c -> c == fc))
                            .findFirst().orElse(0);
        return getCharValue(sameChar);
    }
}
