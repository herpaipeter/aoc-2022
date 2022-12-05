package hu.herpaipeter.aoc2022.day05;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class SupplyStacker {
    public String rearrange(List<Stack<String>> stacks, List<Move> moves) {
        List<Stack<String>> copy = getCopyOfStacks(stacks);
        moves.forEach(m -> rearrangeOneByOne(copy, m));
        return stacksToString(copy);
    }

    public String rearrangeMultiple(List<Stack<String>> stacks, List<Move> moves) {
        List<Stack<String>> copy = getCopyOfStacks(stacks);
        moves.forEach(m -> rearrangeInBatch(copy, m));
        return stacksToString(copy);
    }

    private List<Stack<String>> getCopyOfStacks(List<Stack<String>> stacks) {
        return stacks.stream().map(s -> {
            Stack<String> stack = new Stack<>();
            stack.addAll(s);
            return stack;
        }).toList();
    }

    private String stacksToString(List<Stack<String>> stacks) {
        return stacks.stream().map(s -> s.isEmpty() ? " " : s.peek()).collect(Collectors.joining());
    }

    private void rearrangeOneByOne(List<Stack<String>> stacks, Move move) {
        for (int i = 0; i < move.quantity(); i++) {
            String str = stacks.get(move.from() - 1).pop();
            stacks.get(move.to() - 1).push(str);
        }
    }

    private void rearrangeInBatch(List<Stack<String>> stacks, Move move) {
        Stack<String> fromStack = stacks.get(move.from() - 1);
        List<String> subList = fromStack.subList(fromStack.size() - move.quantity(), fromStack.size());
        stacks.get(move.to() - 1).addAll(subList);
        subList.clear();
    }
}
