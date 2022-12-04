package hu.herpaipeter.aoc2022.day04;

public interface RangeIntersectionValidator {

    boolean valid(ClosedRange range1, ClosedRange range2, ClosedRange intersection);

}
