package hu.herpaipeter.aoc2022.day01;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FoodCaloriesPart2Test {
    FoodCalories foodCalories;

    @BeforeEach
    void setUp() {
        foodCalories = new FoodCalories();
    }

    @Test
    void empty_input_should_return_zero() {
        assertEquals(0, foodCalories.sumOfMaxes(List.of(), 0));
    }

    @Test
    void one_element_should_return_same() {
        assertEquals(1, foodCalories.sumOfMaxes(List.of("1"), 1));
    }

    @Test
    void more_elements_should_return_sum() {
        assertEquals(5063, foodCalories.sumOfMaxes(List.of("1", "2", "10", "5050"), 1));
    }

    @Test
    void separated_elements_should_return_the_greater_with_maxcount_1() {
        assertEquals(2, foodCalories.sumOfMaxes(List.of("1", "", "2"), 1));
    }

    @Test
    void two_separated_elements_should_return_the_sum_with_maxcount_2() {
        assertEquals(3, foodCalories.sumOfMaxes(List.of("1", "", "2"), 2));
        assertEquals(3, foodCalories.sumOfMaxes(List.of("1", "", "2"), 3));
    }

    @Test
    void multi_elements_groups_should_return_the_all_sum_independent_of_order() {
        assertEquals(7284, foodCalories.sumOfMaxes(List.of("1", "100", "3", "306", "", "1", "2", "5000", "", "1", "10", "33", "306", "1521"), 3));
        assertEquals(7284, foodCalories.sumOfMaxes(List.of("1", "2", "5000", "", "1", "100", "3", "306", "", "1", "10", "33", "306", "1521"), 3));
    }

    @Test
    void multi_elements_groups_should_return_only_the_first_3_max_sum() {
        assertEquals(9, foodCalories.sumOfMaxes(List.of("1", "", "2", "", "3", "", "4"), 3));
    }

    @Test
    void get_file_values() {
        List<String> calories = FileReader.readStringLines("src\\test\\java\\hu\\herpaipeter\\aoc2022\\day01\\input.txt");
        System.out.println(foodCalories.sumOfMaxes(calories, 3));
    }
}
