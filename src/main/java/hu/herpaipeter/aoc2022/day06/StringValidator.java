package hu.herpaipeter.aoc2022.day06;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringValidator {
    public static boolean containsUniqueChars(String str) {
        Map<Integer, Long> charCount = str.chars().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return charCount.values().stream().allMatch(l -> l <= 1);
    }
}
