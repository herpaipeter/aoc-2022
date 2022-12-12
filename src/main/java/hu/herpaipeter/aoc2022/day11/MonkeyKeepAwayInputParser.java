package hu.herpaipeter.aoc2022.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonkeyKeepAwayInputParser {

    public List<Monkey> getMonkeys(List<String> lines) {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 7) {
            monkeys.add(parseMonkey(lines, i, true));
        }
        return monkeys;
    }

    public List<Monkey> getMonkeys(List<String> lines, boolean isWorryDivider) {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 7) {
            monkeys.add(parseMonkey(lines, i, isWorryDivider));
        }
        long leastCommonMultiple = monkeys.stream().mapToInt(m -> m.getMonkeyThrowTest() != null ? m.getMonkeyThrowTest().divisor() : 0).reduce((left, right) -> left * right).orElse(0);
        for (Monkey monkey : monkeys) {
            monkey.setLeastCommonMultipleDivider(leastCommonMultiple);
        }
        return monkeys;
    }

    private Monkey parseMonkey(List<String> lines, int startIdx, boolean isWorryDivider) {
        List<Long> items = null;
        MonkeyOperation operation = null;
        MonkeyThrowTest monkeyThrowTest = null;
        if (lines.get(startIdx).contains("Monkey")) {
            items = List.of();
        }
        if (startIdx + 1 < lines.size() && lines.get(startIdx + 1).contains("  Starting items:")) {
            String[] itemStrings = lines.get(startIdx + 1).substring("  Starting items:".length()).split(",");
            items = Arrays.stream(itemStrings).map(String::trim).map(Long::parseLong).toList();
        }
        if (startIdx + 2 < lines.size() && lines.get(startIdx + 2).contains("  Operation:")) {
            String[] operationTags = lines.get(startIdx + 2).substring("  Operation: new = old".length()).trim().split(" ");
            if (operationTags[0].equals("+")) {
                if (operationTags[1].equals("old")) {
                    operation = op -> op + op;
                } else {
                    operation = op -> op + Integer.parseInt(operationTags[1]);
                }
            } else if (operationTags[0].equals("*")) {
                if (operationTags[1].equals("old")) {
                    operation = op -> op * op;
                } else {
                    operation = op -> op * Integer.parseInt(operationTags[1]);
                }
            }

        }
        if (startIdx + 3 < lines.size() && lines.get(startIdx + 3).contains("  Test: divisible by")) {
            int divider = Integer.parseInt(lines.get(startIdx + 3).substring("  Test: divisible by".length()).trim());
            monkeyThrowTest = new MonkeyThrowTest(divider, 0, 0);
            if (startIdx + 4 < lines.size() && lines.get(startIdx + 4).contains("    If true: throw to monkey")) {
                int trueMonkey = Integer.parseInt(lines.get(startIdx + 4).substring("    If true: throw to monkey".length()).trim());
                monkeyThrowTest = new MonkeyThrowTest(divider, trueMonkey, 0);
                if (startIdx + 5 < lines.size() && lines.get(startIdx + 5).contains("    If false: throw to monkey")) {
                    int falseMonkey = Integer.parseInt(lines.get(startIdx + 5).substring("    If false: throw to monkey".length()).trim());
                    monkeyThrowTest = new MonkeyThrowTest(divider, trueMonkey, falseMonkey);
                }
            }
        }
        return new Monkey(items, operation, monkeyThrowTest, isWorryDivider);
    }
}
