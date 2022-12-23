package hu.herpaipeter.aoc2022.day22;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MonkeyMapParser {

    private List<String> inputLines;
    private List<Point> openTiles = new ArrayList<>();
    private List<Point> walls = new ArrayList<>();

    public MonkeyMapParser(List<String> inputLines) {
        this.inputLines = inputLines;
        IntStream.range(0, inputLines.size())
                .forEach(row -> IntStream.range(0,inputLines.get(row).length())
                                        .forEach(col -> addField(inputLines, row, col)));
    }

    private void addField(List<String> inputLines, int row, int col) {
        if (inputLines.get(row).charAt(col) == '.')
            openTiles.add(new Point(row, col));
        else if (inputLines.get(row).charAt(col) == '#')
            walls.add(new Point(row, col));
    }

    public List<Point> getOpenTiles() {
        return openTiles;
    }

    public List<Point> getWalls() {
        return walls;
    }

    public List<MonkeyStep> getPath() {
        List<MonkeyStep> steps = new ArrayList<>();
        if (isNotEmptyPathLine()) {
            String stepsLine = inputLines.get(inputLines.size() - 1);
            List<String> moveTexts = Arrays.stream(stepsLine.split("[R|L]")).filter(s -> !s.isEmpty()).toList();
            List<String> turnTexts = Arrays.stream(stepsLine.split("[0-9]+")).filter(s -> !s.isEmpty()).toList();
            for (int i = 0; i < Math.max(moveTexts.size(), turnTexts.size()); i++) {
                if (i < moveTexts.size()) {
                    steps.add(new MonkeyMove(Integer.parseInt(moveTexts.get(i))));
                }
                if (i < turnTexts.size()) {
                    if (turnTexts.get(i).equals("R"))
                        steps.add(new MonkeyTurn(UDDirection.Right));
                    else if (turnTexts.get(i).equals("L"))
                        steps.add(new MonkeyTurn(UDDirection.Left));
                }
            }
        }
        return steps;
    }

    private boolean isNotEmptyPathLine() {
        return 2 < inputLines.size() && inputLines.get(inputLines.size() - 2).isEmpty() && !inputLines.get(inputLines.size() - 1).isEmpty();
    }
}
