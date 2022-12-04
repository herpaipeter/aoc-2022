package hu.herpaipeter.aoc2022.day03;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RucksackOrganizerPart2Test {

    RucksackOrganizer organizer;

    @BeforeEach
    void setUp() {
        organizer = new RucksackOrganizer();
    }

    @Test
    void empty_list_should_return_zero() {
        assertEquals(0, organizer.sumOfThreeRucksacksBadge(List.of()));
    }

    @Test
    void list_of_three_same_as_should_return_1() {
        assertEquals(1, organizer.sumOfThreeRucksacksBadge(List.of("a", "a", "a")));
    }

    @Test
    void list_of_three_same_bs_should_return_2() {
        assertEquals(2, organizer.sumOfThreeRucksacksBadge(List.of("b", "b", "b")));
    }

    @Test
    void list_of_three_different_should_return_0() {
        assertEquals(0, organizer.sumOfThreeRucksacksBadge(List.of("a", "b", "c")));
    }

    @Test
    void list_of_three_different_but_has_same_should_return_1() {
        assertEquals(1, organizer.sumOfThreeRucksacksBadge(List.of("ab", "ca", "da")));
    }

    @Test
    void list_of_three_different_but_has_same_not_in_first_place_should_return_1() {
        assertEquals(1, organizer.sumOfThreeRucksacksBadge(List.of("cab", "ca", "da")));
    }

    @Test
    void different_length_elements_should_return_the_common_char_value() {
        assertEquals(18, organizer.sumOfThreeRucksacksBadge(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg")));
    }

    @Test
    void more_than_three_elements_should_return_the_groups_common_char_values_sum() {
        assertEquals(18 + 52, organizer.sumOfThreeRucksacksBadge(
                List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"
                ,"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw")));
    }

    @Test
    void file_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day03");
        System.out.println(organizer.sumOfThreeRucksacksBadge(inputTxt));
    }
}
