package hu.herpaipeter.aoc2022.day06;

public class StringValidator {
    public static boolean containsUniqueChars(String str) {
        boolean unique = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                unique &= (str.charAt(i) != str.charAt(j));
            }
        }
        return unique;
    }
}
