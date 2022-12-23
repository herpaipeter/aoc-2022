package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.List;

public record Sensor(Point sensor, Point beacon) {

    public ElfSegment intersectRow(int row) {
        int colDistance = getDistance() - Math.abs(sensor.row() - row);
        if (0 <= colDistance) {
            int col1 = sensor.col() + colDistance;
            int col2 = sensor.col() - colDistance;
            return new ElfSegment(new Point(row, col1), new Point(row, col2));
        }
        return ElfSegment.empty();
    }

    private int getDistance() {
        return Math.abs(sensor.row() - beacon.row()) + Math.abs(sensor.col() - beacon.col());
    }

    private int getDistanceTo(Point point) {
        return Math.abs(sensor.row() - point.row()) + Math.abs(sensor.col() - point.col());
    }

    public List<Point> getOuterPerimeter(int from, int to) {
        int outerDistance = getDistance() + 1;
        List<Point> perimeter = new ArrayList<>();
        for (int i = -outerDistance; i <= outerDistance; i++) {
            if (from <= sensor.row() + i && sensor.row() + i <= to) {
                int colRight = sensor.col() + (outerDistance - Math.abs(i));
                int colLeft = sensor.col() - (outerDistance - Math.abs(i));
                if (from <= colRight && colRight <= to)
                    perimeter.add(new Point(sensor.row() + i, colRight));
                if (Math.abs(i) != outerDistance && from <= colLeft && colLeft <= to)
                    perimeter.add(new Point(sensor.row() + i, colLeft));
            }
        }
        return perimeter;
    }

    public List<Point> getOuterPerimeter() {
        return getOuterPerimeter(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isInside(Point point) {
        return getDistanceTo(point) <= getDistance();
    }
}
