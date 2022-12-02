package hu.herpaipeter.aoc2022.day02;

import java.util.Map;

public class RPSOutcomePointAlgorithm implements RPSPointAlgorithm {

    Map<String, Integer> player1Symbols = Map.of("A", 1, "B", 2, "C", 3);
    Map<String, Integer> resultSymbols = Map.of("X", 0, "Y", 3, "Z", 6);
    private static final int P1_CHOICE = 0;
    private static final int RESULT = 1;

    @Override
    public int getPoints(String[] round) {
        int p1choice = player1Symbols.get(round[P1_CHOICE]);
        int result = resultSymbols.get(round[RESULT]);
        int p2choice = (p1choice + (int)Math.signum(result - 3)) % 3;
        p2choice = p2choice == 0 ? 3 : p2choice;
        return result + p2choice;
    }
}
