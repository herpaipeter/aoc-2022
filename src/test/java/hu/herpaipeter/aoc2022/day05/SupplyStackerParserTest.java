package hu.herpaipeter.aoc2022.day05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SupplyStackerParserTest {

    SupplyStackerParser parser;
    Stack<String> stack;
    Stack<String> stack2;

    @BeforeEach
    void setUp() {
        parser = new SupplyStackerParser();
        stack = new Stack<>();
        stack2 = new Stack<>();
    }

    @Test
    void empty_lines_should_return_empty_stacks_list() {
        assertEquals(List.of(), parser.getStacks(List.of()));
    }

    @Test
    void one_simple_stack_text_return_one_stacks_with_one_element() {
        stack.addAll(List.of("A"));
        assertEquals(List.of(stack), parser.getStacks(List.of("[A]", " 1")));
    }

    @Test
    void two_simple_stacks_text_return_two_stacks_with_one_element() {
        stack.addAll(List.of("A"));
        stack2.addAll(List.of("B"));
        List<Stack<String>> stacks = parser.getStacks(List.of("[A] [B]", " 1   2"));
        assertEquals(List.of(stack, stack2), stacks);
        assertEquals(1, stacks.get(0).size());
        assertEquals(1, stacks.get(1).size());
    }

    @Test
    void two_stacks_but_first_empty_text_return_one_stack_with_one_element_and_an_empty() {
        stack2.addAll(List.of("B"));
        List<Stack<String>> stacks = parser.getStacks(List.of("    [B]", " 1   2"));
        assertEquals(List.of(stack, stack2), stacks);
        assertEquals(0, stacks.get(0).size());
        assertEquals(1, stacks.get(1).size());
    }

    @Test
    void two_stacks_more_elements_text_return_two_stacks_with_more_elements() {
        stack.addAll(Arrays.asList("A", "C"));
        stack2.addAll(Arrays.asList("B", "D", "E"));
        List<Stack<String>> stacks = parser.getStacks(List.of("    [E]", "[C] [D]", "[A] [B]", " 1   2"));
        assertEquals(List.of(stack, stack2), stacks);
        assertEquals(2, stacks.get(0).size());
        assertEquals(3, stacks.get(1).size());
    }

    @Test
    void no_move_lines_should_return_empty_moves_list() {
        assertEquals(List.of(), parser.getMoves(List.of()));
    }

    @Test
    void find_move_line_after_stacks() {
        List<Move> moves = List.of(new Move(1, 1, 2));
        assertEquals(moves, parser.getMoves(List.of("    [E]", "[C] [D]", "[A] [B]", " 1   2", "move 1 from 1 to 2")));
    }

    @Test
    void find_move_lines_after_stacks() {
        List<Move> moves = List.of(new Move(1, 1, 2), new Move(3, 2, 1));
        assertEquals(moves, parser.getMoves(List.of("    [E]", "[C] [D]", "[A] [B]", " 1   2", "move 1 from 1 to 2", "move 3 from 2 to 1")));
    }
}
