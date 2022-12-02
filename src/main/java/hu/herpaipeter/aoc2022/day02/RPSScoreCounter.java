package hu.herpaipeter.aoc2022.day02;

import java.util.List;

public class RPSScoreCounter {

    private static final int P1_CHOICE = 0;
    private static final int P2_CHOICE = 1;
    private static final String P2_ROCK = "X";
    private static final String P2_PAPER = "Y";
    private static final String P2_SCISSOR = "Z";
    private static final String P1_ROCK = "A";
    private static final String P1_PAPER = "B";
    private static final String P1_SCISSORS = "C";
    private static final int ROCK_POINT = 1;
    private static final int PAPER_POINT = 2;
    private static final int SCISSORS_POINT = 3;
    private static final int DRAW_POINT = 3;
    private static final int LOSE_POINT = 0;
    private static final int WIN_POINT = 6;
    private static final String P2_LOSE = "X";
    private static final String P2_DRAW = "Y";
    private static final String P2_WIN = "Z";

    public int count(List<String> rounds) {
        int points = 0;
        for (int i = 0; i < rounds.size(); i++) {
            String[] round = rounds.get(i).split(" ");
            points += getPoints(round);
        }
        return points;
    }

    private static int getPoints(String[] round) {
        int result = 0;
        if (round[P2_CHOICE].equalsIgnoreCase(P2_ROCK)) {
            result += ROCK_POINT;
            if (round[P1_CHOICE].equalsIgnoreCase(P1_ROCK))
                result += DRAW_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_PAPER))
                result += LOSE_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_SCISSORS))
                result += WIN_POINT;
        }
        else if (round[P2_CHOICE].equalsIgnoreCase(P2_PAPER)) {
            result += PAPER_POINT;
            if (round[P1_CHOICE].equalsIgnoreCase(P1_PAPER))
                result += DRAW_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_SCISSORS))
                result += LOSE_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_ROCK))
                result += WIN_POINT;
        }
        else if (round[P2_CHOICE].equalsIgnoreCase(P2_SCISSOR)) {
            result += SCISSORS_POINT;
            if (round[P1_CHOICE].equalsIgnoreCase(P1_SCISSORS))
                result += DRAW_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_ROCK))
                result += LOSE_POINT;
            else if (round[P1_CHOICE].equalsIgnoreCase(P1_PAPER))
                result += WIN_POINT;
        }
        return result;
    }

    public int countByOutcome(List<String> rounds) {
        int points = 0;
        for (int i = 0; i < rounds.size(); i++) {
            String[] round = rounds.get(i).split(" ");
            points += getOutcomePoints(round);
        }
        return points;
    }

    private static int getOutcomePoints(String[] round) {
        if (round[P1_CHOICE].equalsIgnoreCase(P1_ROCK)) {
            if (round[P2_CHOICE].equalsIgnoreCase(P2_DRAW))
                return DRAW_POINT + ROCK_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_LOSE))
                return LOSE_POINT + SCISSORS_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_WIN))
                return WIN_POINT + PAPER_POINT;
        }
        else if (round[P1_CHOICE].equalsIgnoreCase(P1_PAPER)) {
            if (round[P2_CHOICE].equalsIgnoreCase(P2_DRAW))
                return DRAW_POINT + PAPER_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_LOSE))
                return LOSE_POINT + ROCK_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_WIN))
                return WIN_POINT + SCISSORS_POINT;
        }
        else if (round[P1_CHOICE].equalsIgnoreCase(P1_SCISSORS)) {
            if (round[P2_CHOICE].equalsIgnoreCase(P2_DRAW))
                return DRAW_POINT + SCISSORS_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_LOSE))
                return LOSE_POINT + PAPER_POINT;
            else if (round[P2_CHOICE].equalsIgnoreCase(P2_WIN))
                return WIN_POINT + ROCK_POINT;
        }
        return 0;
    }
}
