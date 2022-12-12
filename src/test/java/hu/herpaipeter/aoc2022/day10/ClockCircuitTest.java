package hu.herpaipeter.aoc2022.day10;

import hu.herpaipeter.aoc2022.day09.RopeMovement;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClockCircuitTest {

    @Test
    void empty_instruction_list_should_change_nothing() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of());
        assertEquals(1, clockCircuit.getRegister());
        assertEquals(List.of(), clockCircuit.getCycles());
    }

    @Test
    void noop_instruction_should_change_only_cycles() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of("noop"));
        assertEquals(1, clockCircuit.getRegister());
        assertEquals(List.of(1), clockCircuit.getCycles());
    }

    @Test
    void noop_twice_instruction_should_add_register_twice_to_cycles() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of("noop", "noop"));
        assertEquals(1, clockCircuit.getRegister());
        assertEquals(List.of(1, 1), clockCircuit.getCycles());
    }

    @Test
    void add_x_instruction_should_change_register_after_two_cycles() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of("addx 1"));
        assertEquals(2, clockCircuit.getRegister());
        assertEquals(List.of(1,1,2), clockCircuit.getCycles());
    }

    @Test
    void add_x_and_noop_instruction_should_change_register_after_two_cycles() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of("addx 1", "noop"));
        assertEquals(2, clockCircuit.getRegister());
        assertEquals(List.of(1,1,2), clockCircuit.getCycles());
    }

    @Test
    void add_x_instruction_twice_should_change_register_after_four_cycles() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of("addx 1", "addx -2"));
        assertEquals(0, clockCircuit.getRegister());
        assertEquals(List.of(1,1,2,2,0), clockCircuit.getCycles());
    }

    @Test
    void empty_instruction_list_should_return_signal_strength_0() {
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(List.of());
        assertEquals(0, clockCircuit.getSignalStrength(0));
    }

    @Test
    void large_example() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day10", "large_example.txt");
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(inputTxt);
        assertEquals(420, clockCircuit.getSignalStrength(20));
        assertEquals(1140, clockCircuit.getSignalStrength(60));
        assertEquals(1800, clockCircuit.getSignalStrength(100));
        assertEquals(2940, clockCircuit.getSignalStrength(140));
        assertEquals(2880, clockCircuit.getSignalStrength(180));
        assertEquals(3960, clockCircuit.getSignalStrength(220));
        assertEquals(13140, clockCircuit.getSignalStrengthSum(List.of(20,60,100,140,180,220)));
    }

    @Test
    void file_result_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day10");
        ClockCircuit clockCircuit = new ClockCircuit();
        clockCircuit.run(inputTxt);
        System.out.println("part1: " + clockCircuit.getSignalStrengthSum(List.of(20,60,100,140,180,220)));
    }
}
