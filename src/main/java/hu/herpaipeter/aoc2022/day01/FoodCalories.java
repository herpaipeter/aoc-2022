package hu.herpaipeter.aoc2022.day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FoodCalories {

    public int max(List<String> calories) {
        List<Integer> sums = getGroupSums(calories);
        return sums.stream().max(Integer::compareTo).orElse(0);
    }

    public int sumOfMaxes(List<String> calories, int maxCount) {
        List<Integer> reverseSortedSums = getGroupSums(calories).stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        return reverseSortedSums.stream().limit(maxCount).mapToInt(value -> value).sum();
    }

    private static List<Integer> getGroupSums(List<String> calories) {
        List<Integer> sums = new ArrayList<>();
        int fromIndex = 0;
        for(int i = 0; i < calories.size(); i++) {
            if (calories.get(i).isEmpty()) {
                addSublistSum(sums, calories, fromIndex, i);
                fromIndex = i + 1;
            }
        }
        addSublistSum(sums, calories, fromIndex, calories.size());
        return sums;
    }

    private static void addSublistSum(List<Integer> sums, List<String> calories, int from, int to) {
        sums.add(getSum(calories.subList(from, to)));
    }

    private static int getSum(List<String> calories) {
        return calories.stream().mapToInt(Integer::parseInt).sum();
    }

}
