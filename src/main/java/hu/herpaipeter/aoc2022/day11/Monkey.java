package hu.herpaipeter.aoc2022.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private List<Long> items = new ArrayList<>();
    private MonkeyOperation operation;
    private MonkeyThrowTest monkeyThrowTest;
    private int sumOfInspectedItemsCount = 0;
    private List<InspectedItem> inspectedItems = List.of();
    private int worryDivider;

    public Monkey(List<Long> items, MonkeyOperation operation, MonkeyThrowTest monkeyThrowTest) {
        this(items, operation, monkeyThrowTest, 3);
    }

    public Monkey(List<Long> items, MonkeyOperation operation, MonkeyThrowTest monkeyThrowTest, int worryDivider) {
        this.items.addAll(items);
        this.operation = operation;
        this.monkeyThrowTest = monkeyThrowTest;
        this.worryDivider = worryDivider;
    }

    public int getInspectedItemsCount() {
        return sumOfInspectedItemsCount;
    }

    public void inspectItems() {
        sumOfInspectedItemsCount += items.size();
        inspectedItems = items.stream()
                .map(i -> {
                    long newWorryLevel = operation != null ? operation.calculate(i) / 3 : i;
                    Integer catcherMonkey = monkeyThrowTest != null ? newWorryLevel % monkeyThrowTest.divisor() == 0 ? monkeyThrowTest.ifTrueMonkey() : monkeyThrowTest.ifFalseMonkey() : null;
                    return new InspectedItem(newWorryLevel, catcherMonkey);
                }).toList();
        items.clear();
    }

    public List<InspectedItem> getInspectedItems() {
        return inspectedItems;
    }

    public List<Long> getItems() {
        return items;
    }

    public MonkeyOperation getOperation() {
        return operation;
    }

    public MonkeyThrowTest getMonkeyThrowTest() {
        return monkeyThrowTest;
    }

    public void addItem(long worryLevel) {
        items.add(worryLevel);
    }
}
