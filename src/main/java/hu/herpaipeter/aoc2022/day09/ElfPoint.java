package hu.herpaipeter.aoc2022.day09;

public record ElfPoint(int row, int col) {

    private final static ElfPoint ORIGO = new ElfPoint(0, 0);

    public static ElfPoint origo() {
        return ORIGO;
    }

    @Override
    public String toString() {
        return "ElfPoint{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElfPoint elfPoint = (ElfPoint) o;

        if (row != elfPoint.row) return false;
        return col == elfPoint.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    public ElfPoint add(ElfPoint point) {
        return new ElfPoint(row + point.row, col + point.col);
    }

    public int distance(ElfPoint point) {
        return Math.max(Math.abs(point.row - row), Math.abs(point.col - col));
    }
}
