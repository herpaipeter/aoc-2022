package hu.herpaipeter.aoc2022.day10;

import java.util.ArrayList;
import java.util.List;

public class CrtScreen {

    public List<String> drawDisplay(List<Integer> registers) {
        List<String> display = new ArrayList<>();
        if (!registers.isEmpty()) {
            for (int i = 0; i < registers.size(); i++) {
                int row = i / 40;
                int col = i % 40;
                while (display.size() < row + 1)
                    display.add("");
                if (col == registers.get(i) || col == registers.get(i) - 1 || col == registers.get(i) + 1) {
                    display.set(row, display.get(row) + "#");
                } else {
                    display.set(row, display.get(row) + ".");
                }
            }
        }
        return display;
    }
}
