package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day09.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SandFallParserTest {

    @Test
    void empty_list_should_return_empty_lines() {
        assertEquals(List.of(), SandFallParser.getLines(List.of()));
    }

    @Test
    void one_point_line_string_should_return_one_point_line() {
        assertEquals(List.of(List.of(new Point(4, 498))), SandFallParser.getLines(List.of("498,4")));
    }

    @Test
    void two_point_line_string_should_return_two_point_line() {
        assertEquals(List.of(List.of(new Point(4, 498), new Point(6, 498))), SandFallParser.getLines(List.of("498,4 -> 498,6")));
    }

    @Test
    void long_one_line_test() {
        assertEquals(List.of(List.of(new Point(4, 503), new Point(4, 502), new Point(9, 502), new Point(9, 494))),
                SandFallParser.getLines(List.of("503,4 -> 502,4 -> 502,9 -> 494,9")));
    }

    @Test
    void multi_line_test() {
        assertEquals(List.of(List.of(new Point(4, 498), new Point(6, 498), new Point(6, 496)),
                List.of(new Point(4, 503), new Point(4, 502), new Point(9, 502), new Point(9, 494))),
                SandFallParser.getLines(List.of("498,4 -> 498,6 -> 496,6", "503,4 -> 502,4 -> 502,9 -> 494,9")));
    }
}
