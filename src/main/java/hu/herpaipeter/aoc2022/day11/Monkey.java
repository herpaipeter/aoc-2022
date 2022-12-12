package hu.herpaipeter.aoc2022.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private List<Long> items = new ArrayList<>();
    private MonkeyOperation operation;
    private MonkeyThrowTest monkeyThrowTest;
    private int sumOfInspectedItemsCount = 0;
    private List<InspectedItem> inspectedItems = List.of();
    private boolean isWorryDivider;
    private Long leastCommonMultipleDivider;

    public Monkey(List<Long> items, MonkeyOperation operation, MonkeyThrowTest monkeyThrowTest) {
        this(items, operation, monkeyThrowTest, true);
    }

    public Monkey(List<Long> items, MonkeyOperation operation, MonkeyThrowTest monkeyThrowTest, boolean isWorryDivider) {
        this.items.addAll(items);
        this.operation = operation;
        this.monkeyThrowTest = monkeyThrowTest;
        this.isWorryDivider = isWorryDivider;
    }

    public void setLeastCommonMultipleDivider(Long leastCommonMultipleDivider) {
        this.leastCommonMultipleDivider = leastCommonMultipleDivider;
    }

    public int getInspectedItemsCount() {
        return sumOfInspectedItemsCount;
    }

    public void inspectItems() {
        sumOfInspectedItemsCount += items.size();
        inspectedItems = items.stream()
                .map(i -> {
                    long newWorryLevel;
                    Integer catcherMonkey;
                    if (isWorryDivider) {
                        newWorryLevel = operation != null ? operation.calculate(i) / 3 : i;
                        catcherMonkey = monkeyThrowTest != null ? newWorryLevel % monkeyThrowTest.divisor() == 0 ? monkeyThrowTest.ifTrueMonkey() : monkeyThrowTest.ifFalseMonkey() : null;
                    } else {
                        newWorryLevel = operation.calculate(i) % leastCommonMultipleDivider;
                        newWorryLevel = newWorryLevel == 0 ? leastCommonMultipleDivider : newWorryLevel;
                        catcherMonkey = newWorryLevel % monkeyThrowTest.divisor() == 0 ? monkeyThrowTest.ifTrueMonkey() : monkeyThrowTest.ifFalseMonkey();
                    }
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
