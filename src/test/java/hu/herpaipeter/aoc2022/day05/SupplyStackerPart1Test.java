package hu.herpaipeter.aoc2022.day05;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SupplyStackerPart1Test {

    SupplyStacker stacker;
    Stack<String> stack;
    Stack<String> stack2;
    Stack<String> stack3;

    @BeforeEach
    void setUp() {
        stacker = new SupplyStacker();
        stack = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();
    }

    @Test
    void empty_stack_list_should_return_empty_order() {
        assertEquals("", stacker.rearrange(List.of(), List.of()));
    }

    @Test
    void one_empty_stack_should_return_one_space() {
        assertEquals(" ", stacker.rearrange(List.of(stack), List.of()));
    }

    @Test
    void one_stack_with_no_move_should_return_top_of_stack() {
        stack.addAll(List.of("A"));
        assertEquals("A", stacker.rearrange(List.of(stack), List.of()));
    }

    @Test
    void two_stacks_with_no_move_should_return_top_of_stacks() {
        stack.addAll(List.of("A"));
        stack2.addAll(List.of("B"));
        assertEquals("AB", stacker.rearrange(List.of(stack, stack2), List.of()));
    }

    @Test
    void more_multi_element_stacks_with_no_move_should_return_top_of_stacks() {
        stack.addAll(Arrays.asList("A", "D"));
        stack2.addAll(Arrays.asList("B", "E", "F", "G"));
        stack3.addAll(Arrays.asList("C", "H", "I"));
        assertEquals("DGI", stacker.rearrange(List.of(stack, stack2, stack3), List.of()));
    }

    @Test
    void two_stacks_with_one_move_right_should_return_top_of_new_stacks() {
        stack.addAll(List.of("A"));
        assertEquals(" A", stacker.rearrange(List.of(stack, stack2), List.of(new Move(1, 1, 2))));
    }

    @Test
    void two_stacks_with_one_move_left_should_return_top_of_new_stacks() {
        stack2.addAll(List.of("A"));
        assertEquals("A ", stacker.rearrange(List.of(stack, stack2), List.of(new Move(1, 2, 1))));
    }

    @Test
    void move_two_element_right_should_return_top_of_new_stacks() {
        stack.addAll(Arrays.asList("A", "B", "C"));
        assertEquals("AB", stacker.rearrange(List.of(stack, stack2),
                List.of(new Move(2, 1, 2))));
    }

    @Test
    void move_two_element_right_with_two_moves_should_return_top_of_new_stacks() {
        stack.addAll(Arrays.asList("A", "B", "C"));
        assertEquals("AB", stacker.rearrange(List.of(stack, stack2),
                List.of(new Move(1, 1, 2), new Move(1, 1, 2))));
    }

    @Test
    void file_result() {
        SupplyStackerParser parser = new SupplyStackerParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day05");
        System.out.println(stacker.rearrange(parser.getStacks(inputTxt), parser.getMoves(inputTxt)));
    }

    @Test
    void test_immutability() {
        SupplyStackerParser parser = new SupplyStackerParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day05");
        List<Stack<String>> stacks = parser.getStacks(inputTxt);
        String before = stacks.toString();
        stacker.rearrange(stacks, parser.getMoves(inputTxt));
        assertEquals(before, stacks.toString());
    }
}
