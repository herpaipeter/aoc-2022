package hu.herpaipeter.aoc2022.day05;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SupplyStackerPart2Test {

    @Test
    void empty_stack_list_should_return_empty_order() {
        SupplyStacker stacker = new SupplyStacker();
        assertEquals("", stacker.rearrangeMultiple(List.of(), List.of()));
    }

    @Test
    void more_multi_element_stacks_with_no_move_should_return_top_of_stacks() {
        SupplyStacker stacker = new SupplyStacker();
        Stack<String> stack1 = new Stack<>();
        stack1.addAll(Arrays.asList("A", "D"));
        Stack<String> stack2 = new Stack<>();
        stack2.addAll(Arrays.asList("B", "E", "F", "G"));
        Stack<String> stack3 = new Stack<>();
        stack3.addAll(Arrays.asList("C", "H", "I"));
        assertEquals("DGI", stacker.rearrangeMultiple(List.of(stack1, stack2, stack3), List.of()));
    }

    @Test
    void two_stacks_with_one_move_right_in_batch_should_return_top_of_new_stacks() {
        SupplyStacker stacker = new SupplyStacker();
        Stack<String> stack = new Stack<>();
        stack.addAll(List.of("A"));
        Stack<String> stack2 = new Stack<>();
        assertEquals(" A", stacker.rearrangeMultiple(List.of(stack, stack2), List.of(new Move(1, 1, 2))));
    }

    @Test
    void two_stacks_with_one_move_left_in_batch_should_return_top_of_new_stacks() {
        SupplyStacker stacker = new SupplyStacker();
        Stack<String> stack = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        stack2.addAll(List.of("A"));
        assertEquals("A ", stacker.rearrangeMultiple(List.of(stack, stack2), List.of(new Move(1, 2, 1))));
    }

    @Test
    void move_two_element_in_batch_right_should_return_top_of_new_stacks() {
        SupplyStacker stacker = new SupplyStacker();
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList("A", "B", "C"));
        Stack<String> stack2 = new Stack<>();
        assertEquals("AC", stacker.rearrangeMultiple(List.of(stack, stack2),
                List.of(new Move(2, 1, 2))));
    }

    @Test
    void move_two_element_right_with_two_moves_should_return_top_of_new_stacks() {
        SupplyStacker stacker = new SupplyStacker();
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList("A", "B", "C"));
        Stack<String> stack2 = new Stack<>();
        assertEquals("AB", stacker.rearrangeMultiple(List.of(stack, stack2),
                List.of(new Move(1, 1, 2), new Move(1, 1, 2))));
    }

    @Test
    void file_result() {
        SupplyStacker stacker = new SupplyStacker();
        SupplyStackerParser parser = new SupplyStackerParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day05");
        System.out.println(stacker.rearrangeMultiple(parser.getStacks(inputTxt), parser.getMoves(inputTxt)));
    }
}
