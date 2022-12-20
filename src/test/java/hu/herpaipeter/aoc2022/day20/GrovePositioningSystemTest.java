package hu.herpaipeter.aoc2022.day20;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GrovePositioningSystemTest {

    @Test
    void empty_list_should_return_sum_of_0() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of());
        assertEquals(0, positioningSystem.getSumAfterZero(List.of()));
    }

    @Test
    void one_element_list_should_return_sum_of_0() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(0));
        assertEquals(0, positioningSystem.getSumAfterZero(List.of()));
    }

    @Test
    void two_element_list_should_return_sum_of_1() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(0,1));
        assertEquals(1, positioningSystem.getSumAfterZero(List.of(1)));
    }

    @Test
    void three_element_list_should_return_sum_of_2() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(0,1,2));
        assertEquals(2, positioningSystem.getSumAfterZero(List.of(1)));
    }

    @Test
    void three_element_with_negative_number_list_should_return_sum_of_minus_2() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(0,1,-2));
        assertEquals(-2L, positioningSystem.getSumAfterZero(List.of(1)));
    }

    @Test
    void three_element_list_two_element_should_return_sum_of_3() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(0,1,2));
        assertEquals(3L, positioningSystem.getSumAfterZero(List.of(1, 2)));
    }

    @Test
    void simple_example_test() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(3L, positioningSystem.getSumAfterZero(List.of(1000, 2000, 3000)));
    }

    @Test
    void input_file_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day20");
        List<Integer> integers = inputTxt.stream().map(Integer::parseInt).toList();
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(integers);
        System.out.println("Day 20 part 1: " + positioningSystem.getSumAfterZero(List.of(1000, 2000, 3000)));
    }

    @Test
    void simple_example_part_2_test() {
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(1623178306L, positioningSystem.getSumAfterZeroExtended(List.of(1000, 2000, 3000), 811589153, 10));
    }

    @Test
    void input_file_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day20");
        List<Integer> integers = inputTxt.stream().map(Integer::parseInt).toList();
        GrovePositioningSystem positioningSystem = new GrovePositioningSystem(integers);
        System.out.println("Day 20 part 2: " + positioningSystem.getSumAfterZeroExtended(List.of(1000, 2000, 3000),  811589153, 10));
    }
}
