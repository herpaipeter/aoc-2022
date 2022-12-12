package hu.herpaipeter.aoc2022.day11;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyKeepAwayInputParserTest {

    @Test
    void empty_input_list_should_give_empty_monkeys_list() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        assertEquals(List.of(), parser.getMonkeys(List.of()));
    }

    @Test
    void monkey_line_should_give_one_monkey() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        assertEquals(List.of(), parser.getMonkeys(List.of("Monkey 0:")).get(0).getItems());
    }

    @Test
    void monkey_line_with_items_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        assertEquals(List.of(79L, 98L), parser.getMonkeys(List.of("Monkey 0:", "  Starting items: 79, 98")).get(0).getItems());
    }

    @Test
    void monkey_line_with_items_and_operation_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> input = List.of("Monkey 0:", "  Starting items: 79, 98", "  Operation: new = old * 19");
        Monkey monkey0 = parser.getMonkeys(input).get(0);
        assertEquals(List.of(79L, 98L), monkey0.getItems());
        assertEquals(190, monkey0.getOperation().calculate(10));
    }

    @Test
    void monkey_line_with_items_and_operation_old_second_part_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> input = List.of("Monkey 0:", "  Starting items: 79, 98", "  Operation: new = old * old");
        Monkey monkey0 = parser.getMonkeys(input).get(0);
        assertEquals(List.of(79L, 98L), monkey0.getItems());
        assertEquals(9, monkey0.getOperation().calculate(3));
    }

    @Test
    void monkey_line_with_items_and_operation_and_test_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> input = List.of("Monkey 0:", "  Starting items: 79, 98", "  Operation: new = old * 19", "  Test: divisible by 23");
        Monkey monkey0 = parser.getMonkeys(input).get(0);
        assertEquals(List.of(79L, 98L), monkey0.getItems());
        assertEquals(190, monkey0.getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(23,0,0), monkey0.getMonkeyThrowTest());
    }

    @Test
    void monkey_line_with_items_and_operation_and_test_and_true_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> input = List.of("Monkey 0:", "  Starting items: 79, 98", "  Operation: new = old * 19", "  Test: divisible by 23", "    If true: throw to monkey 2");
        Monkey monkey0 = parser.getMonkeys(input).get(0);
        assertEquals(List.of(79L, 98L), monkey0.getItems());
        assertEquals(190, monkey0.getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(23,2,0), monkey0.getMonkeyThrowTest());
    }

    @Test
    void monkey_line_with_items_and_operation_and_test_and_true_and_false_should_give_one_monkey_with_starting_items() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> input = List.of("Monkey 0:", "  Starting items: 79, 98", "  Operation: new = old * 19", "  Test: divisible by 23", "    If true: throw to monkey 2", "    If false: throw to monkey 3");
        Monkey monkey0 = parser.getMonkeys(input).get(0);
        assertEquals(List.of(79L, 98L), monkey0.getItems());
        assertEquals(190, monkey0.getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(23,2,3), monkey0.getMonkeyThrowTest());
    }

    @Test
    void multi_monkeys_parse() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day11", "example_4_monkeys.txt");
        List<Monkey> monkeys = parser.getMonkeys(inputTxt);
        assertEquals(List.of(79L, 98L), monkeys.get(0).getItems());
        assertEquals(190, monkeys.get(0).getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(23,2,3), monkeys.get(0).getMonkeyThrowTest());
        assertEquals(List.of(54L, 65L, 75L, 74L), monkeys.get(1).getItems());
        assertEquals(16, monkeys.get(1).getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(19,2,0), monkeys.get(1).getMonkeyThrowTest());
        assertEquals(List.of(79L, 60L, 97L), monkeys.get(2).getItems());
        assertEquals(100, monkeys.get(2).getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(13,1,3), monkeys.get(2).getMonkeyThrowTest());
        assertEquals(List.of(74L), monkeys.get(3).getItems());
        assertEquals(13, monkeys.get(3).getOperation().calculate(10));
        assertEquals(new MonkeyThrowTest(17,0,1), monkeys.get(3).getMonkeyThrowTest());
    }

}
