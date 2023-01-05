package hu.herpaipeter.aoc2022.day23;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.List;

public class ElfPositionParser {

    public List<Point> getElfPositions(List<String> inputLines) {
        if (inputLines.isEmpty())
            return List.of();
        List<Point> result = new ArrayList<>();
        for (int j = 0; j < inputLines.size(); j++) {
            for (int i = 0; i < inputLines.get(j).length(); i++) {
                if (inputLines.get(j).charAt(i) == '#')
                    result.add(new Point(inputLines.size() - 1 - j, i));
            }
        }
        return result;
    }
}
