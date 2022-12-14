package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RockMapper {

    public Set<Point> map(List<Point> line) {
        Set<Point> result = new HashSet<>();
        if (1 == line.size())
            result.add(line.get(0));
        if (1 < line.size()) {
            for (int linePoint = 0; linePoint < line.size() - 1; linePoint++) {
                Point from = line.get(linePoint);
                Point to = line.get(linePoint + 1);
                if (isHorizontal(from, to)) {
                    result.addAll(getHorizontalPoints(from, to));
                } else {
                    result.addAll(getVerticalPoints(from, to));
                }
            }
        }
        return result;
    }

    private Set<Point> getVerticalPoints(Point from, Point to) {
        Set<Point> result = new HashSet<>();
        int fromRow = from.row();
        int toRow = to.row();
        int direction = getDirection(fromRow, toRow);
        for (int i = fromRow; i != toRow; i += direction) {
            result.add(new Point(i, from.col()));
        }
        result.add(new Point(toRow, from.col()));
        return result;
    }

    private Set<Point> getHorizontalPoints(Point from, Point to) {
        Set<Point> result = new HashSet<>();
        int fromCol = from.col();
        int toCol = to.col();
        int direction = getDirection(fromCol, toCol);
        for (int i = fromCol; i != toCol; i += direction) {
            result.add(new Point(from.row(), i));
        }
        result.add(new Point(from.row(), toCol));
        return result;
    }

    private int getDirection(int fromCol, int toCol) {
        return (int) Math.signum(toCol - fromCol);
    }

    private boolean isHorizontal(Point from, Point to) {
        return from.row() == to.row();
    }
}
