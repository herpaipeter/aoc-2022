package hu.herpaipeter.aoc2022.day04;

public class FullRangeIntersection implements RangeIntersectionValidator {
    @Override
    public boolean valid(ClosedRange range1, ClosedRange range2, ClosedRange intersection) {
        return intersection.equals(range1) || intersection.equals(range2);
    }
}
