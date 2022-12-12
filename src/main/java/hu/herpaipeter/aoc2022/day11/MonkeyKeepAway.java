package hu.herpaipeter.aoc2022.day11;

import java.util.List;

public class MonkeyKeepAway {

    private final List<Monkey> monkeys;

    public MonkeyKeepAway(List<Monkey> monkeys) {
        this.monkeys = monkeys;
    }

    public List<Monkey> getMonkeys() {
        return monkeys;
    }

    public int getInspectedItemsBy(int monkeyIndex) {
        return monkeys.get(monkeyIndex).getInspectedItemsCount();
    }

    public int getMonkeysCount() {
        return monkeys.size();
    }

    public void runTurn() {
         for (int i = 0; i < monkeys.size(); i++)
            inspectMonkey(i);
    }

    public void inspectMonkey(int monkeyIndex) {
        monkeys.get(monkeyIndex).inspectItems();
        List<InspectedItem> inspectedItems = monkeys.get(monkeyIndex).getInspectedItems();
        inspectedItems.stream().filter(ii -> ii.catcherMonkey() != null).forEach(item -> monkeys.get(item.catcherMonkey()).addItem(item.worryLevel()));
    }

    public void runTurns(int rounds) {
        for (int i = 0; i < rounds; i++)
            runTurn();
    }
}
