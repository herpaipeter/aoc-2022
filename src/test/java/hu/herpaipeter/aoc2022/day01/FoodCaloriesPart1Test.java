package hu.herpaipeter.aoc2022.day01;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FoodCaloriesPart1Test {

    FoodCalories foodCalories;

    @BeforeEach
    void setUp() {
        foodCalories = new FoodCalories();
    }

    @Test
    void empty_input_should_return_zero() {
        assertEquals(0, foodCalories.max(List.of()));
    }

    @Test
    void one_element_should_return_same() {
        assertEquals(1, foodCalories.max(List.of("1")));
    }

    @Test
    void two_elements_should_return_sum() {
        assertEquals(3, foodCalories.max(List.of("1", "2")));
    }

    @Test
    void more_elements_should_return_sum() {
        assertEquals(5063, foodCalories.max(List.of("1", "2", "10", "5050")));
    }

    @Test
    void separated_elements_should_return_the_greater() {
        assertEquals(2, foodCalories.max(List.of("1", "", "2")));
    }

    @Test
    void separated_elements_should_return_the_greater_sum() {
        assertEquals(4, foodCalories.max(List.of("1", "3", "", "1", "2")));
    }

    @Test
    void multi_elements_groups_should_return_the_greater_sum_independent_of_order() {
        assertEquals(5003, foodCalories.max(List.of("1", "100", "3", "306", "", "1", "2", "5000", "", "1", "10", "33", "306", "1521")));
        assertEquals(5003, foodCalories.max(List.of("1", "2", "5000", "", "1", "100", "3", "306", "", "1", "10", "33", "306", "1521")));
    }

    @Test
    void get_file_values() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day01");
        System.out.println(foodCalories.max(inputTxt));
    }
}
