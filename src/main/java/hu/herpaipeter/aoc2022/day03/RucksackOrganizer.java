package hu.herpaipeter.aoc2022.day03;

import java.util.List;

public class RucksackOrganizer {

    public int sumOfCompartmentSameElements(List<String> rucksacks) {
        return rucksacks.stream().mapToInt(RucksackOrganizer::getRucksackValue).sum();
    }

    private static int getRucksackValue(String rucksack) {
        String firstComp = rucksack.substring(0, rucksack.length() / 2);
        String secondComp = rucksack.substring(rucksack.length() / 2);
        return getCharValue(getCompartmentsSameChar(firstComp, secondComp));
    }

    private static int getCharValue(int charIntValue) {
        if (charIntValue == 0)
            return 0;
        if ((int)'a' <= charIntValue)
            return charIntValue - 'a' + 1;
        return charIntValue - 'A' + 27;
    }

    private static int getCompartmentsSameChar(String firstComp, String secondComp) {
        for(int i = 0; i < firstComp.length(); i++)
            for (int j = 0; j < secondComp.length(); j++)
                if (firstComp.charAt(i) == secondComp.charAt(j))
                    return firstComp.charAt(i);
        return 0;
    }

    public int sumOfThreeRucksacksBadge(List<String> rucksacks) {
        int sum = 0;
        for (int g = 0; g < rucksacks.size(); g += 3) {
            sum += getSumOfThree(rucksacks, g);
        }
        return sum;
    }

    private static int getSumOfThree(List<String> rucksacks, int g) {
        for (int i = 0; i < rucksacks.get(g).length(); i++) {
            char nextChar = rucksacks.get(g).charAt(i);
            if (rucksacks.get(g + 1).contains(String.valueOf(nextChar)) && rucksacks.get(g + 2).contains(String.valueOf(nextChar)))
                return getCharValue(nextChar);
        }
        return 0;
    }
}
