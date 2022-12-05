package hu.herpaipeter.aoc2022.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SupplyStackerParser {

    private static final String MOVE_LINE_KEY_WORD = "move";
    private static final int STACK_FIRST_CHAR_POSITION = 1;
    private static final int STACK_CHAR_DISTANCE_IN_TEXT = 4;

    public List<Stack<String>> getStacks(List<String> textLines) {
        List<Stack<String>> stacks = new ArrayList<>();
        boolean lastLine = false;
        for (int j = 0; j < textLines.size() && !lastLine; j++) {
            for (int i = 0; i < textLines.get(j).length() && !lastLine; i += STACK_CHAR_DISTANCE_IN_TEXT) {
                int stackIndex = i / STACK_CHAR_DISTANCE_IN_TEXT;
                addRequiredStack(stacks, stackIndex + 1);
                String elem = String.valueOf(textLines.get(j).charAt(i + STACK_FIRST_CHAR_POSITION));
                if (isNumberLine(elem))
                    lastLine = true;
                else if (isNotEmptyOrSpace(elem))
                    stacks.get(stackIndex).add(elem);
            }
        }
        return stacks.stream().map(this::reverse).toList();
    }

    private boolean isNumberLine(String elem) {
        return elem.matches("[0-9]+");
    }

    private boolean isNotEmptyOrSpace(String elem) {
        return !elem.isEmpty() && !elem.equals(" ");
    }

    private void addRequiredStack(List<Stack<String>> stacks, int size) {
        if (stacks.size() < size) {
            Stack<String> stack = new Stack<>();
            stacks.add(stack);
        }
    }

    private Stack<String> reverse(Stack<String> stack) {
        Stack<String> reverseStack = new Stack<>();
        while (!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        return reverseStack;
    }

    public List<Move> getMoves(List<String> textLines) {
        return textLines.stream().filter(l -> l.contains(MOVE_LINE_KEY_WORD)).map(SupplyStackerParser::parseMoveTextLine).toList();
    }

    private static Move parseMoveTextLine(String textLine) {
        String[] numbers = textLine.split("[a-z ]+");
        return new Move(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]), Integer.parseInt(numbers[3]));
    }
}
