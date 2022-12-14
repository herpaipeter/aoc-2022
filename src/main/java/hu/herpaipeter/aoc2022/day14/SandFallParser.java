package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.ArrayList;
import java.util.List;

public class SandFallParser {
    public List<List<Point>> getLines(List<String> input) {
        List<List<Point>> result = new ArrayList<>();
        for (String s : input) {
            String[] points = s.split(" -> ");
            List<Point> pointList = new ArrayList<>();
            for (String value : points) {
                String[] pointTxt = value.split(",");
                Point point = new Point(Integer.parseInt(pointTxt[1]), Integer.parseInt(pointTxt[0]));
                pointList.add(point);
            }
            result.add(pointList);
        }
        return result;
    }
}
