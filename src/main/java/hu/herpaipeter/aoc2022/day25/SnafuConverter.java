package hu.herpaipeter.aoc2022.day25;

import java.util.stream.IntStream;

public class SnafuConverter {

    private static final int SNAFU_BASE_NUMBER = 5;

    public static long toDecimal(String snafu) {
        return IntStream.range(0, snafu.length())
                .mapToLong(i -> (long) (Math.pow(SNAFU_BASE_NUMBER, snafu.length() - 1 - i) * getCharValue(snafu.charAt(i))))
                .sum();
    }

    private static int getCharValue(char snafuDigit) {
        if (snafuDigit == '=')
            return -2;
        else if (snafuDigit == '-')
            return -1;
        return (snafuDigit - '0');
    }

    public static String toSnafu(long decimal) {
        String pental = Long.toUnsignedString(decimal, SNAFU_BASE_NUMBER);
        StringBuilder result = new StringBuilder();
        char overflow = '0';
        for (int i = 0; i < pental.length(); i++) {
            String snafuDigit = convertPentalToSnafuDigits(pental.charAt(pental.length() - 1 - i));
            String sumOfActPlaceAndOverflow = addSnafuDigits(getActualPlace(snafuDigit), overflow);
            result.insert(0, getActualPlace(sumOfActPlaceAndOverflow));
            overflow = getOverflow(snafuDigit, sumOfActPlaceAndOverflow);
        }
        return (overflow != '0') ? overflow + result.toString() : result.toString();
    }

    private static char getOverflow(String snafuDigit, String sumOfActPlaceAndOverflow) {
        return addSnafuDigits(getOverFlownDigit(snafuDigit), (getOverFlownDigit(sumOfActPlaceAndOverflow))).charAt(0);
    }

    private static char getOverFlownDigit(String snafuDigit) {
        return 1 < snafuDigit.length() ? snafuDigit.charAt(0) : '0';
    }

    private static char getActualPlace(String snafuDigit) {
        return 1 < snafuDigit.length() ? snafuDigit.charAt(1) : snafuDigit.charAt(0);
    }

    private static String convertPentalToSnafuDigits(char pentalDigit) {
        if (pentalDigit == '3')
            return "1=";
        else if (pentalDigit == '4')
            return "1-";
        else
            return Character.toString(pentalDigit);
    }

    private static String addSnafuDigits(char snafuDigit1, char snafuDigit2) {
        return getSnafuDigits(getCharValue(snafuDigit1) + getCharValue(snafuDigit2));
    }

    private static String getSnafuDigits(int number) {
        if (number == -2)
            return "=";
        else if (number == -1)
            return "-";
        else if (number == 3)
            return "1=";
        else if (number == 4)
            return "1-";
        else
            return Integer.toString(number);
    }

}
