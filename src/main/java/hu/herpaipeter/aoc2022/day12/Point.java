package hu.herpaipeter.aoc2022.day12;

public record Point(int row, int col) {

    public Point add(Point other) {
        return new Point(row + other.row(),col + other.col());
    }
}
