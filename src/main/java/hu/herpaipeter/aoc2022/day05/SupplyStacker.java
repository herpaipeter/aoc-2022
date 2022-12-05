package hu.herpaipeter.aoc2022.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class SupplyStacker {
    public String rearrange(List<Stack<String>> stacks, List<Move> moves) {
        return rearrange(stacks,moves,false);
    }

    public String rearrange(List<Stack<String>> stacks, List<Move> moves, boolean batchMove) {
        for (Move move : moves) {
            if (batchMove) {
                List<String> batch = new ArrayList<>();
                for (int i = 0; i < move.quantity(); i++) {
                    batch.add(stacks.get(move.from() - 1).pop());
                }
                for (int i = batch.size() - 1; 0 <= i; i--)
                    stacks.get(move.to() - 1).push(batch.get(i));
            } else {
                for (int i = 0; i < move.quantity(); i++) {
                    String str = stacks.get(move.from() - 1).pop();
                    stacks.get(move.to() - 1).push(str);
                }
            }
        }
        return stacks.stream().map(s -> s.isEmpty() ? " " : s.peek()).collect(Collectors.joining());
    }
}
