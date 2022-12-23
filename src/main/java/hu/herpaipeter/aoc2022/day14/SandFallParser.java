package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.Arrays;
import java.util.List;

public class SandFallParser {

    private static final int ROW_INDEX = 1;
    private static final int COL_INDEX = 0;
    private static final String LINE_TEXT_POINTS_SEPARATOR = " -> ";
    private static final String POINT_TEXT_COORDS_SEPARATOR = ",";

    public static List<List<Point>> getLines(List<String> input) {
        return input.stream().map(SandFallParser::parseLine).toList();
    }

    private static List<Point> parseLine(String lineText) {
        String[] points = lineText.split(LINE_TEXT_POINTS_SEPARATOR);
        return Arrays.stream(points).map(SandFallParser::parsePoint).toList();
    }

    private static Point parsePoint(String pointText) {
        String[] pointTxt = pointText.split(POINT_TEXT_COORDS_SEPARATOR);
        return new Point(Integer.parseInt(pointTxt[ROW_INDEX]), Integer.parseInt(pointTxt[COL_INDEX]));
    }
}
