package hu.herpaipeter.aoc2022.day04;

import java.util.List;

public class CampCleanupAssignment {
    public int getNumberOfFullyContains(List<String> pairs) {
        int sum = 0;
        for (String pair : pairs) {
            String[] ranges = pair.split(",");
            ClosedRange range1 = getClosedRange(ranges[0]);
            ClosedRange range2 = getClosedRange(ranges[1]);
            ClosedRange intersection = range1.getIntersection(range2);
            sum += intersection.equals(range1) || intersection.equals(range2) ? 1 : 0;
        }
        return sum;
    }

    private static ClosedRange getClosedRange(String rangeStr) {
        String[] range = rangeStr.split("-");
        return new ClosedRange(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
    }

    public int getNumberOfPartiallyContains(List<String> pairs) {
        int sum = 0;
        for (String pair : pairs) {
            String[] ranges = pair.split(",");
            ClosedRange range1 = getClosedRange(ranges[0]);
            ClosedRange range2 = getClosedRange(ranges[1]);
            ClosedRange intersection = range1.getIntersection(range2);
            sum += 0 < intersection.length() ? 1 : 0;
        }
        return sum;
    }
}
