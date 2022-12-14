package hu.herpaipeter.aoc2022.day12;

public record Point(int row, int col) {

    public Point add(Point other) {
        return new Point(row + other.row(),col + other.col());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return col == point.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
