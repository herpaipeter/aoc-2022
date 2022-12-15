package hu.herpaipeter.aoc2022.day15;

import java.util.Comparator;

public class ElfSegmentHorizontalStartComparator implements Comparator<ElfSegment> {
    @Override
    public int compare(ElfSegment segment1, ElfSegment segment2) {
        return Integer.compare(segment1.start().col(), segment2.start().col());
    }
}
