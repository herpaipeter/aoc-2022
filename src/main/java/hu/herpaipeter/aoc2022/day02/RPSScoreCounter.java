package hu.herpaipeter.aoc2022.day02;

import java.util.List;

public class RPSScoreCounter {

    public int count(List<String> rounds) {
        return getPoints(rounds, new RPSSimplePointAlgorithm());
    }

    public int countByOutcome(List<String> rounds) {
        return getPoints(rounds, new RPSOutcomePointAlgorithm());
    }
    private static int getPoints(List<String> rounds, RPSPointAlgorithm algorithm) {
        return rounds.stream()
                .mapToInt(r -> algorithm.getPoints(r.split(" ")))
                .sum();
    }


}
