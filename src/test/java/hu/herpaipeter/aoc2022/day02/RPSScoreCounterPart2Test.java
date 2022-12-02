package hu.herpaipeter.aoc2022.day02;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RPSScoreCounterPart2Test {
    RPSScoreCounter scoreCounter;

    @BeforeEach
    void setUp() {
        scoreCounter = new RPSScoreCounter();
    }

    @Test
    void empty_rounds_should_return_zero() {
        assertEquals(0, scoreCounter.countByOutcome(List.of()));
    }

    @Test
    void one_round_rock_and_draw_should_return_4() {
        assertEquals(4, scoreCounter.countByOutcome(List.of("A Y")));
    }

    @Test
    void one_round_rock_and_lose_should_return_3() {
        assertEquals(3, scoreCounter.countByOutcome(List.of("A X")));
    }

    @Test
    void one_round_rock_and_win_should_return_8() {
        assertEquals(8, scoreCounter.countByOutcome(List.of("A Z")));
    }

    @Test
    void more_rounds_test() {
        assertEquals(3+5+8+6+1, scoreCounter.countByOutcome(List.of("A X", "B Y", "A Z", "C Y", "B X")));
    }

    @Test
   void file_result_part1() {
        List<String> roundTexts = FileReader.readStringLines("src\\test\\java\\hu\\herpaipeter\\aoc2022\\day02\\input.txt");
        System.out.println(scoreCounter.countByOutcome(roundTexts));
    }
}
