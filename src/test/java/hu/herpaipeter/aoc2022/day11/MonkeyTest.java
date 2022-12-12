package hu.herpaipeter.aoc2022.day11;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyTest {

    @Test
    void empty_items_inspection_should_return_0() {
        Monkey monkey = new Monkey(List.of(), null, null);
        assertEquals(0, monkey.getInspectedItemsCount());
    }

    @Test
    void one_item_inspection_should_return_1() {
        Monkey monkey = new Monkey(List.of(1L), null, null);
        monkey.inspectItems();
        assertEquals(1, monkey.getInspectedItemsCount());
    }

    @Test
    void two_items_inspection_should_return_2() {
        Monkey monkey = new Monkey(List.of(1L, 2L), null, null);
        monkey.inspectItems();
        assertEquals(2, monkey.getInspectedItemsCount());
    }

    @Test
    void no_inspection_should_return_empty_inspected_items() {
        Monkey monkey = new Monkey(List.of(), null, null);
        monkey.inspectItems();
        assertEquals(List.of(), monkey.getInspectedItems());
    }

    @Test
    void one_item_inspection_should_return_one_inspected_item() {
        Monkey monkey = new Monkey(List.of(1L), null, null);
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(1, null)), monkey.getInspectedItems());
    }

    @Test
    void two_items_inspection_should_return_two_inspected_items() {
        Monkey monkey = new Monkey(List.of(1L, 2L), null, null);
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(1, null), new InspectedItem(2, null)), monkey.getInspectedItems());
    }

    @Test
    void item_with_operation_should_change_inspected_item_worry_level() {
        Monkey monkey = new Monkey(List.of(6L), op -> 2 * op, null);
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(4, null)), monkey.getInspectedItems());
    }

    @Test
    void item_with_operation_should_return_rounded_worry_level() {
        Monkey monkey = new Monkey(List.of(6L), op -> 2 * op + 1, null);
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(4, null)), monkey.getInspectedItems());
    }

    @Test
    void item_with_operation_should_return_down_rounded_worry_level() {
        Monkey monkey = new Monkey(List.of(6L), op -> 2 * op + 2, null);
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(4, null)), monkey.getInspectedItems());
    }

    @Test
    void item_with_true_monkey_throw_test_should_change_inspected_item_cather() {
        Monkey monkey = new Monkey(List.of(1L), null, new MonkeyThrowTest(1, 1, 2));
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(1, 1)), monkey.getInspectedItems());
    }

    @Test
    void item_with_false_monkey_throw_test_should_change_inspected_item_cather() {
        Monkey monkey = new Monkey(List.of(3L), null, new MonkeyThrowTest(2, 1, 2));
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(3, 2)), monkey.getInspectedItems());
    }

    @Test
    void full_monkey_data_test() {
        Monkey monkey = new Monkey(List.of(79L, 98L), op -> 19 * op, new MonkeyThrowTest(23, 2, 3));
        monkey.inspectItems();
        assertEquals(List.of(new InspectedItem(500, 3), new InspectedItem(620, 3)), monkey.getInspectedItems());
    }
}
