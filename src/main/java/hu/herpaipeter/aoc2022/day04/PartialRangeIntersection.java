package hu.herpaipeter.aoc2022.day04;

public class PartialRangeIntersection implements RangeIntersectionValidator {
    @Override
    public boolean valid(ClosedRange range1, ClosedRange range2, ClosedRange intersection) {
        return 0 < intersection.length();
    }
}
