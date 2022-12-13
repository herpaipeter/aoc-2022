package hu.herpaipeter.aoc2022.day10;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClockCircuitPart2Test {

    @Test
    void crt_screen_initially_should_be_empty() {
        CrtScreen screen = new CrtScreen();
        assertEquals(List.of(), screen.drawDisplay(List.of()));
    }

    @Test
    void crt_screen_one_cycle_should_show_first() {
        CrtScreen screen = new CrtScreen();
        assertIterableEquals(List.of("#"), screen.drawDisplay(List.of(0)));
        assertIterableEquals(List.of("#"), screen.drawDisplay(List.of(1)));
    }

    @Test
    void crt_screen_one_cycle_high_register_should_not_show_first() {
        CrtScreen screen = new CrtScreen();
        assertIterableEquals(List.of("."), screen.drawDisplay(List.of(2)));
        assertIterableEquals(List.of("."), screen.drawDisplay(List.of(3)));
    }

    @Test
    void crt_screen_two_cycles_should_show_if_in_sprite() {
        CrtScreen screen = new CrtScreen();
        assertIterableEquals(List.of("##"), screen.drawDisplay(List.of(0, 0)));
        assertIterableEquals(List.of("##"), screen.drawDisplay(List.of(0, 1)));
        assertIterableEquals(List.of("##"), screen.drawDisplay(List.of(0, 2)));
    }

    @Test
    void crt_screen_two_cycles_should_not_show_if_not_in_sprite() {
        CrtScreen screen = new CrtScreen();
        assertIterableEquals(List.of("#."), screen.drawDisplay(List.of(0, 3)));
        assertIterableEquals(List.of("#."), screen.drawDisplay(List.of(0, 4)));
    }

    @Test
    void cycles_greater_than_40_should_draw_in_second_line_if_in_sprite() {
        CrtScreen screen = new CrtScreen();
        List<Integer> registers = IntStream.range(0, 40).boxed().collect(Collectors.toList());
        registers.add(0);
        assertIterableEquals(List.of("########################################", "#"), screen.drawDisplay(registers));
    }

    @Test
    void cycles_greater_than_40_should_draw_in_second_line_dot_if_not_in_sprite() {
        CrtScreen screen = new CrtScreen();
        List<Integer> registers = IntStream.range(0, 40).boxed().collect(Collectors.toList());
        registers.add(2);
        assertIterableEquals(List.of("########################################", "."), screen.drawDisplay(registers));
    }

    @Test
    void large_example() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day10", "large_example.txt");
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(inputTxt);
        CrtScreen screen = new CrtScreen();
        assertIterableEquals(List.of("##..##..##..##..##..##..##..##..##..##..",
                                     "###...###...###...###...###...###...###.",
                                     "####....####....####....####....####....",
                                     "#####.....#####.....#####.....#####.....",
                                     "######......######......######......####",
                                     "#######.......#######.......#######....."), screen.drawDisplay(clockCircuit.getCycles()));
    }

    @Test
    void file_result_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day10");
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(inputTxt);
        CrtScreen screen = new CrtScreen();
        List<String> displayLines = screen.drawDisplay(clockCircuit.getCycles());
        System.out.println("part2: ");
        displayLines.forEach(System.out::println);
    }

}
