package hu.herpaipeter.aoc2022.day04;

public class ClosedRange {
    final int from;
    final int to;

    static final ClosedRange EMPTY_RANGE = new ClosedRange(0,-1);

    public ClosedRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public static ClosedRange empty() {
        return EMPTY_RANGE;
    }

    public int length() {
        if (to < from)
            return 0;
        return to - from + 1;
    }

    public ClosedRange getIntersection(ClosedRange range) {
        if (this.from <= range.from && range.to <= this.to)
            return new ClosedRange(range.from, range.to);
        else if (range.from <= this.from && this.to <= range.to)
            return new ClosedRange(this.from, this.to);
        else if (this.from <= range.from && range.from <= this.to)
            return new ClosedRange(range.from, this.to);
        else if (this.from <= range.to && range.from <= this.to)
            return new ClosedRange(this.from, range.to);
        return empty();
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
