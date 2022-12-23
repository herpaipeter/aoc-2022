package hu.herpaipeter.aoc2022.day09;

public record Point(int row, int col) {

    private final static Point ORIGO = new Point(0, 0);

    public static Point origo() {
        return ORIGO;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ')';
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

    public Point add(Point point) {
        return new Point(row + point.row, col + point.col);
    }

    public int distanceTo(Point point) {
        return Math.max(Math.abs(point.row - row), Math.abs(point.col - col));
    }
}
