package hu.herpaipeter.aoc2022.day11;

import hu.herpaipeter.aoc2022.day10.ClockCircuit;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyKeepAwayTest {

    @Test
    void empty_monkey_list_should_throw_exception() {
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(List.of());
        monkeyKeepAway.runTurn();
        assertEquals(0, monkeyKeepAway.getMonkeysCount());
        assertThrows(IndexOutOfBoundsException.class, () -> monkeyKeepAway.getInspectedItemsBy(0));
    }

    @Test
    void one_monkey_empty_items_list_should_return_inspected_items_0() {
        Monkey monkey = new Monkey(List.of(), op -> op, new MonkeyThrowTest(1,0,0));
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(List.of(monkey));
        monkeyKeepAway.runTurn();
        assertEquals(1, monkeyKeepAway.getMonkeysCount());
        assertEquals(0, monkeyKeepAway.getInspectedItemsBy(0));
    }

    @Test
    void one_monkey_one_item_should_return_inspected_items_1() {
        Monkey monkey = new Monkey(List.of(1L), op -> op, new MonkeyThrowTest(1,0,0));
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(List.of(monkey));
        monkeyKeepAway.runTurn();
        assertEquals(1, monkeyKeepAway.getInspectedItemsBy(0));
    }

    @Test
    void one_monkey_two_items_should_return_inspected_items_2() {
        Monkey monkey = new Monkey(List.of(1L, 2L), null, null);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(List.of(monkey));
        monkeyKeepAway.runTurn();
        assertEquals(2, monkeyKeepAway.getInspectedItemsBy(0));
    }

    @Test
    void one_monkey_one_item_2_turns_should_return_1_because_of_all_item_thrown() {
        Monkey monkey = new Monkey(List.of(1L), null, null);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(List.of(monkey));
        monkeyKeepAway.runTurn();
        monkeyKeepAway.runTurn();
        assertEquals(1, monkeyKeepAway.getInspectedItemsBy(0));
    }

    @Test
    void one_to_other_monkey_thrown_item_should_appear_in_second_monkeys_list() {
        Monkey monkey0 = new Monkey(List.of(10L), op -> op, new MonkeyThrowTest(3,1,0));
        Monkey monkey1 = new Monkey(List.of(), op -> op, new MonkeyThrowTest(1,0,1));
        MonkeyKeepAwaySpy monkeyKeepAwaySpy = new MonkeyKeepAwaySpy(List.of(monkey0, monkey1));
        monkeyKeepAwaySpy.inspectMonkey(0);
        assertEquals(1, monkeyKeepAwaySpy.getInspectedItemsBy(0));
        assertEquals(0, monkeyKeepAwaySpy.getInspectedItemsBy(1));
        List<List<Long>> monkey0ItemsHistory = List.of(List.of(10L), List.of());
        List<List<Long>> monkey1ItemsHistory = List.of(List.of(), List.of(3L));
        assertEquals(List.of(monkey0ItemsHistory, monkey1ItemsHistory), monkeyKeepAwaySpy.getMonkeyItemsStates());
    }

    @Test
    void after_run_turn_item_should_be_in_monkey_0_items() {
        Monkey monkey0 = new Monkey(List.of(10L), op -> op, new MonkeyThrowTest(3,1,0));
        Monkey monkey1 = new Monkey(List.of(), op -> op, new MonkeyThrowTest(1,0,1));
        MonkeyKeepAwaySpy monkeyKeepAwaySpy = new MonkeyKeepAwaySpy(List.of(monkey0, monkey1));
        monkeyKeepAwaySpy.runTurn();
        assertEquals(1, monkeyKeepAwaySpy.getInspectedItemsBy(0));
        assertEquals(1, monkeyKeepAwaySpy.getInspectedItemsBy(1));
        List<List<Long>> monkey0ItemsHistory = List.of(List.of(10L), List.of(), List.of(1L));
        List<List<Long>> monkey1ItemsHistory = List.of(List.of(), List.of(3L), List.of());
        assertEquals(List.of(monkey0ItemsHistory, monkey1ItemsHistory), monkeyKeepAwaySpy.getMonkeyItemsStates());
    }

    @Test
    void multi_monkeys_run_turn() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day11", "example_4_monkeys.txt");
        List<Monkey> monkeys = parser.getMonkeys(inputTxt);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(monkeys);
        monkeyKeepAway.runTurn();
        assertEquals(List.of(20L, 23L, 27L, 26L), monkeyKeepAway.getMonkeys().get(0).getItems());
        assertEquals(List.of(2080L, 25L, 167L, 207L, 401L, 1046L), monkeyKeepAway.getMonkeys().get(1).getItems());
        assertEquals(List.of(), monkeyKeepAway.getMonkeys().get(2).getItems());
        assertEquals(List.of(), monkeyKeepAway.getMonkeys().get(3).getItems());
    }

    @Test
    void multi_monkeys_run_turn_20_times() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day11", "example_4_monkeys.txt");
        List<Monkey> monkeys = parser.getMonkeys(inputTxt);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(monkeys);
        monkeyKeepAway.runTurns(20);
        assertEquals(List.of(10L, 12L, 14L, 26L, 34L), monkeyKeepAway.getMonkeys().get(0).getItems());
        assertEquals(List.of(245L, 93L, 53L, 199L, 115L), monkeyKeepAway.getMonkeys().get(1).getItems());
        assertEquals(List.of(), monkeyKeepAway.getMonkeys().get(2).getItems());
        assertEquals(List.of(), monkeyKeepAway.getMonkeys().get(3).getItems());
        assertEquals(101, monkeyKeepAway.getInspectedItemsBy(0));
        assertEquals(95, monkeyKeepAway.getInspectedItemsBy(1));
        assertEquals(7, monkeyKeepAway.getInspectedItemsBy(2));
        assertEquals(105, monkeyKeepAway.getInspectedItemsBy(3));
    }

    @Test
    void file_result_part_1() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day11");
        List<Monkey> monkeys = parser.getMonkeys(inputTxt);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(monkeys);
        monkeyKeepAway.runTurns(20);
        List<Integer> sortedInspections = IntStream.range(0, monkeyKeepAway.getMonkeysCount()).map(monkeyKeepAway::getInspectedItemsBy).boxed().sorted(Comparator.reverseOrder()).toList();
        System.out.println("part1: " + sortedInspections.get(0) * sortedInspections.get(1));
    }

//    @Test
//    void multi_monkeys_run_turn_10000_times_no_worry_divider() {
//        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
//        List<String> inputTxt = FileReader.readAoCInputFileLines("day11", "example_4_monkeys.txt");
//        List<Monkey> monkeys = parser.getMonkeys(inputTxt, 1);
//        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(monkeys);
//        monkeyKeepAway.runTurns(10000);
//        assertEquals(52166, monkeyKeepAway.getInspectedItemsBy(0));
//        assertEquals(47830, monkeyKeepAway.getInspectedItemsBy(1));
//        assertEquals(1938, monkeyKeepAway.getInspectedItemsBy(2));
//        assertEquals(52013, monkeyKeepAway.getInspectedItemsBy(3));
//    }

    @Test
    void file_result_part_2() {
        MonkeyKeepAwayInputParser parser = new MonkeyKeepAwayInputParser();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day11");
        List<Monkey> monkeys = parser.getMonkeys(inputTxt, 1);
        MonkeyKeepAway monkeyKeepAway = new MonkeyKeepAway(monkeys);
        monkeyKeepAway.runTurns(10000);
        List<Integer> sortedInspections = IntStream.range(0, monkeyKeepAway.getMonkeysCount()).map(monkeyKeepAway::getInspectedItemsBy).boxed().sorted(Comparator.reverseOrder()).toList();
        System.out.println("part1: " + sortedInspections.get(0) * sortedInspections.get(1));
    }

    private class MonkeyKeepAwaySpy extends MonkeyKeepAway {
        private List<List<List<Long>>> monkeyItemsStates = new ArrayList<>();

        public MonkeyKeepAwaySpy(List<Monkey> monkeys) {
            super(monkeys);
            monkeys.forEach(m -> {
                List<List<Long>> monkeyStates = new ArrayList<>();
                monkeyStates.add(new ArrayList<>(m.getItems()));
                monkeyItemsStates.add(monkeyStates);
            });
        }

        @Override
        public void inspectMonkey(int monkeyIndex) {
            super.inspectMonkey(monkeyIndex);
            for (int i = 0; i < getMonkeys().size(); i++) {
                monkeyItemsStates.get(i).add(new ArrayList<>(getMonkeys().get(i).getItems()));
            }
        }

        public List<List<List<Long>>> getMonkeyItemsStates() {
            return monkeyItemsStates;
        }
    }
}
