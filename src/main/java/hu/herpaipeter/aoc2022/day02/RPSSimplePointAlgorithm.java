package hu.herpaipeter.aoc2022.day02;

import java.util.Map;

public class RPSSimplePointAlgorithm implements RPSPointAlgorithm {

    Map<String, Integer> player1Symbols = Map.of("A", 1, "B", 2, "C", 3);
    Map<String, Integer> player2Symbols = Map.of("X", 1, "Y", 2, "Z", 3);

    private static final int P1_CHOICE = 0;
    private static final int P2_CHOICE = 1;
    private static final int DRAW_POINT = 3;
    private static final int WIN_POINT = 6;

    @Override
    public int getPoints(String[] round) {
        int p1choice = player1Symbols.get(round[P1_CHOICE]);
        int p2choice = player2Symbols.get(round[P2_CHOICE]);
        int result = p2choice;
        if ((p1choice) % 3 == p2choice - 1) result += WIN_POINT;
        else if (p1choice == p2choice) result += DRAW_POINT;
        return result;
    }
}
