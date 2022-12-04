package hu.herpaipeter.aoc2022.day03;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RucksackOrganizerPart1Test {

    RucksackOrganizer organizer;

    @BeforeEach
    void setUp() {
        organizer = new RucksackOrganizer();
    }

    @Test
    void empty_list_should_return_zero() {
        assertEquals(0, organizer.sumOfCompartmentSameElements(List.of()));
    }

    @Test
    void same_two_as_should_return_one() {
        assertEquals(1, organizer.sumOfCompartmentSameElements(List.of("aa")));
    }

    @Test
    void same_two_bs_should_return_two() {
        assertEquals(2, organizer.sumOfCompartmentSameElements(List.of("bb")));
    }

    @Test
    void same_two_zs_should_return_26() {
        assertEquals(26, organizer.sumOfCompartmentSameElements(List.of("zz")));
    }

    @Test
    void same_two_As_should_return_27() {
        assertEquals(27, organizer.sumOfCompartmentSameElements(List.of("AA")));
    }

    @Test
    void same_two_Zs_should_return_52() {
        assertEquals(52, organizer.sumOfCompartmentSameElements(List.of("ZZ")));
    }

    @Test
    void different_two_elements_should_return_zero() {
        assertEquals(0, organizer.sumOfCompartmentSameElements(List.of("ab")));
    }

    @Test
    void more_elements_should_return_the_same_elements_value_from_the_compartments() {
        assertEquals(1, organizer.sumOfCompartmentSameElements(List.of("abac")));
    }

    @Test
    void more_elements_not_the_same_position_should_return_the_same_elements_value_from_the_compartments() {
        assertEquals(1, organizer.sumOfCompartmentSameElements(List.of("abca")));
    }

    @Test
    void long_rucksack_tests() {
        assertEquals(16, organizer.sumOfCompartmentSameElements(List.of("vJrwpWtwJgWrhcsFMMfFFhFp")));
        assertEquals(38, organizer.sumOfCompartmentSameElements(List.of("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")));
    }

    @Test
    void more_list_elements_should_return_sum() {
        assertEquals(16 + 38, organizer.sumOfCompartmentSameElements(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")));
    }

    @Test
    void file_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day03");
        System.out.println(organizer.sumOfCompartmentSameElements(inputTxt));
    }
}
