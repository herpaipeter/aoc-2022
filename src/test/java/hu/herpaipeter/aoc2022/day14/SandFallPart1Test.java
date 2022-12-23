package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day09.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SandFallPart1Test {

    @Test
    void no_line_no_stop() {
        SandFall sandFall = new SandFall(List.of());
        sandFall.fallASand();
        assertEquals(0, sandFall.getSandPositions().size());
    }

    @Test
    void horizontal_line_should_stop_the_sand() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 490), new Point(5, 510))));
        sandFall.fallASand();
        assertEquals(Set.of(new Point(4, 500)), sandFall.getSandPositions());
    }

    @Test
    void second_sand_should_be_left_to_the_first() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 490), new Point(5, 510))));
        sandFall.fallASand();
        sandFall.fallASand();
        assertEquals(Set.of(new Point(4, 500), new Point(4, 499)), sandFall.getSandPositions());
    }

    @Test
    void third_sand_should_be_right_to_the_first() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 490), new Point(5, 510))));
        sandFall.fallSands(3);
        assertEquals(Set.of(new Point(4, 500), new Point(4, 499), new Point(4, 501)), sandFall.getSandPositions());
    }

    @Test
    void fourth_sand_should_be_on_top_of_the_first() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 490), new Point(5, 510))));
        sandFall.fallSands(4);
        assertEquals(Set.of(new Point(4, 500), new Point(4, 499), new Point(4, 501), new Point(3, 500)), sandFall.getSandPositions());
    }

    @Test
    void sand_falls_next_to_the_cliff_should_fall_down() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 500), new Point(5, 510))));
        sandFall.fallASand();
        assertEquals(Set.of(), sandFall.getSandPositions());
    }

    @Test
    void short_example() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(4, 498), new Point(6, 498), new Point(6, 496)),
                List.of(new Point(4, 503), new Point(4, 502), new Point(9, 502), new Point(9, 494))));
        sandFall.fallSandsUntilNoMore();
        assertEquals(24, sandFall.getSandPositions().size());
    }

    @Test
    void file_input_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day14");
        List<List<Point>> lines = SandFallParser.getLines(inputTxt);
        SandFall sandFall = new SandFall(lines);
        sandFall.fallSandsUntilNoMore();
        System.out.println("part1: " + sandFall.getSandPositions().size());
    }

    @Test
    void sand_falls_next_to_the_cliff_should_fall_to_the_floor() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(5, 500), new Point(5, 510))), true);
        sandFall.fallASand();
        assertEquals(Set.of(new Point(6, 499)), sandFall.getSandPositions());
    }

    @Test
    void short_example_part_2() {
        SandFall sandFall = new SandFall(List.of(List.of(new Point(4, 498), new Point(6, 498), new Point(6, 496)),
                List.of(new Point(4, 503), new Point(4, 502), new Point(9, 502), new Point(9, 494))), true);
        sandFall.fallSandsUntilNoMore();
        assertEquals(93, sandFall.getSandPositions().size());
    }

    @Test
    void file_input_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day14");
        List<List<Point>> lines = SandFallParser.getLines(inputTxt);
        SandFall sandFall = new SandFall(lines, true);
        sandFall.fallSandsUntilNoMore();
        System.out.println("part2: " + sandFall.getSandPositions().size());
    }
}
