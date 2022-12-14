package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RockMapper {

    public static Set<Point> getRockPositions(List<Point> line) {
        Set<Point> result = new HashSet<>();
        if (1 == line.size())
            result.add(line.get(0));
        if (1 < line.size()) {
            for (int linePoint = 0; linePoint < line.size() - 1; linePoint++)
                result.addAll(getPositions(line.get(linePoint), line.get(linePoint + 1)));
        }
        return result;
    }

    private static Set<Point> getPositions(Point from, Point to) {
        Set<Point> result = new HashSet<>();
        Point direction = getDirection(from, to);
        for (Point i = from; !i.equals(to); i = i.add(direction))
            result.add(i);
        result.add(to);
        return result;
    }

    private static Point getDirection(Point from, Point to) {
        return new Point(getDirection(from.row(), to.row()), getDirection(from.col(),to.col()));
    }

    private static int getDirection(int from, int to) {
        return (int) Math.signum(to - from);
    }

}
