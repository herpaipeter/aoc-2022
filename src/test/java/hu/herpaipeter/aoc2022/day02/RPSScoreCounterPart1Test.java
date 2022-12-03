package hu.herpaipeter.aoc2022.day02;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RPSScoreCounterPart1Test {
    RPSScoreCounter scoreCounter;

    @BeforeEach
    void setUp() {
        scoreCounter = new RPSScoreCounter();
    }

    @Test
    void empty_rounds_should_return_zero() {
        assertEquals(0, scoreCounter.count(List.of()));
    }

    @Test
    void one_round_rock_draw_should_return_4() {
        assertEquals(4, scoreCounter.count(List.of("A X")));
    }

    @Test
    void one_round_paper_draw_should_return_5() {
        assertEquals(5, scoreCounter.count(List.of("B Y")));
    }

    @Test
    void one_round_scissors_draw_should_return_6() {
        assertEquals(6, scoreCounter.count(List.of("C Z")));
    }

    @Test
    void one_round_rock_lost_should_return_1() {
        assertEquals(1, scoreCounter.count(List.of("B X")));
    }

    @Test
    void one_round_rock_win_should_return_7() {
        assertEquals(7, scoreCounter.count(List.of("C X")));
    }

    @Test
    void one_round_paper_lost_should_return_2() {
        assertEquals(2, scoreCounter.count(List.of("C Y")));
    }

    @Test
    void one_round_paper_win_should_return_8() {
        assertEquals(8, scoreCounter.count(List.of("A Y")));
    }

    @Test
    void one_round_scissors_lost_should_return_3() {
        assertEquals(3, scoreCounter.count(List.of("A Z")));
    }

    @Test
    void one_round_scissors_win_should_return_9() {
        assertEquals(9, scoreCounter.count(List.of("B Z")));
    }

    @Test
    void two_rounds_test() {
        assertEquals(9, scoreCounter.count(List.of("A X", "B Y")));
    }

    @Test
    void more_rounds_test() {
        assertEquals(15, scoreCounter.count(List.of("A X", "B Y", "A Z", "C Y", "B X")));
    }

    @Test
    void file_result_part1() {
        List<String> roundTexts = FileReader.readStringLines("src\\test\\java\\hu\\herpaipeter\\aoc2022\\day02\\input.txt");
        System.out.println(scoreCounter.count(roundTexts));
    }
}
