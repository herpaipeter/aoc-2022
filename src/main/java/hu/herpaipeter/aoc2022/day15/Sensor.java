package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day12.Point;

public record Sensor(Point sensor, Point beacon) {
    public ElfSegment intersectRow(int row) {
        int distance = Math.abs(sensor.row() - beacon.row()) + Math.abs(sensor.col() - beacon.col());
        int colDistance = distance - Math.abs(sensor.row() - row);
        if (0 <= colDistance) {
            int col1 = sensor.col() + colDistance;
            int col2 = sensor.col() - colDistance;
            return new ElfSegment(new Point(row, col1), new Point(row, col2));
        }
        return ElfSegment.empty();
    }
}
