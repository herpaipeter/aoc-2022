package hu.herpaipeter.aoc2022.day04;

import java.util.List;

public class CampCleanupAssignment {
    public long getNumberOfFullyContains(List<String> pairs) {
        return pairs.stream().filter(this::isFullyContainsOneRange).count();
    }

    private boolean isFullyContainsOneRange(String pair) {
        return isValidIntersection(pair, new FullRangeIntersection());
    }

    private static ClosedRange getClosedRange(String rangeStr) {
        String[] range = rangeStr.split("-");
        return new ClosedRange(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
    }

    public long getNumberOfPartiallyContains(List<String> pairs) {
        return pairs.stream().filter(this::isPartialIntersection).count();
    }

    private boolean isPartialIntersection(String pair) {
        return isValidIntersection(pair, new PartialRangeIntersection());
    }

    private boolean isValidIntersection(String pair, RangeIntersectionValidator validator) {
        String[] ranges = pair.split(",");
        ClosedRange range1 = getClosedRange(ranges[0]);
        ClosedRange range2 = getClosedRange(ranges[1]);
        ClosedRange intersection = range1.getIntersection(range2);
        return validator.valid(range1, range2, intersection);
    }
}
