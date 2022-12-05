package hu.herpaipeter.aoc2022.day04;

public record ClosedRange(int from, int to) {

    static final ClosedRange EMPTY_RANGE = new ClosedRange(0, -1);

    public static ClosedRange empty() {
        return EMPTY_RANGE;
    }

    public int length() {
        if (to < from)
            return 0;
        return to - from + 1;
    }

    public ClosedRange getIntersection(ClosedRange range) {
        if (isDisjunctiveRange(range))
            return empty();
        return new ClosedRange(Math.max(range.from, this.from), Math.min(range.to, this.to));
    }

    private boolean isDisjunctiveRange(ClosedRange range) {
        return this.to < range.from || range.to < this.from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClosedRange that = (ClosedRange) o;

        if (from != that.from) return false;
        return to == that.to;
    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        return result;
    }

    @Override
    public String toString() {
        return "ClosedRange{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
