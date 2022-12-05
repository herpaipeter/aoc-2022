package hu.herpaipeter.aoc2022.day05;

import javax.sound.midi.Receiver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SupplyStackerParser {
    public List<Stack<String>> getStacks(List<String> textLines) {
        List<Stack<String>> stacks = new ArrayList<>();
        boolean lastLine = false;
        for (int j = 0; j < textLines.size() && !lastLine; j++) {
            for (int i = 0; i < textLines.get(j).length() && !lastLine; i += 4) {
                if (stacks.size() < (i + 4) / 4) {
                    Stack<String> stack = new Stack<>();
                    stacks.add(stack);
                }
                String elem = String.valueOf(textLines.get(j).charAt(i + 1));
                if (elem.equals("1")) {
                    lastLine = true;
                } else if (!elem.isEmpty() && !elem.equals(" "))
                    stacks.get(i/4).add(elem);
            }
        }
        return stacks.stream().map(this::reverse).toList();
    }

    private Stack<String> reverse(Stack<String> stack) {
        Stack<String> reverseStack = new Stack<>();
        while (!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        return reverseStack;
    }

    public List<Move> getMoves(List<String> textLines) {
        List<Move> moves = new ArrayList<>();
        for (String textLine : textLines)
            if (textLine.contains("move")) {
                String[] numbers = textLine.split("[a-z ]+");
                moves.add(new Move(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]), Integer.parseInt(numbers[3])));
            }
        return moves;
    }
}
