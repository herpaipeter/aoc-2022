package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.ArrayList;
import java.util.List;

public record ElfSegment(Point start, Point end) {

    public long length() {
        return Math.abs(start.row() - end.row()) + Math.abs(start.col() - end.col()) + 1;
    }

    public static ElfSegment empty() {
        return new ElfSegment(null, null);
    }

    public boolean contains(Point point) {
        if (start.row() == end.row() && start.row() == point.row())
            return start.col() <= point.col() && point.col() <= end.col() ||
                    end.col() <= point.col() && point.col() <= start.col();
        else if (start.col() == end.col() && start.col() == point.col())
            return start.row() <= point.row() && point.row() <= end.row() ||
                    end.row() <= point.row() && point.row() <= start.row();
        return false;
    }

    public ElfSegment intersectHorizontal(ElfSegment segment) {
        if (start.row() == end.row() && start.row() == segment.start().row() && end.row() == segment.end().row()) {
            if (end.col() < segment.start.col() || segment.end.col() < start.col())
                return empty();
            else
                return new ElfSegment(new Point(start.row(),Math.max(start.col(), segment.start().col())), new Point(end.row(), Math.min(end.col(), segment.end().col())));
        }
        return empty();
    }

    public List<ElfSegment> unionHorizontal(ElfSegment segment) {
        if (start.row() == end.row() && start.row() == segment.start().row() && end.row() == segment.end().row()) {
            if (end.col() < segment.start.col() || segment.end.col() < start.col())
                return List.of(this, segment);
            else
                return List.of(new ElfSegment(new Point(start.row(),Math.min(start.col(), segment.start().col())), new Point(end.row(), Math.max(end.col(), segment.end().col()))));
        }
        return List.of(this, segment);
    }

    public static List<ElfSegment> unionHorizontal(List<ElfSegment> segments) {
        if (segments.isEmpty())
            return List.of();
        else if (1 == segments.size())
            return List.of(segments.get(0));
        else if (2 == segments.size()) {
            List<ElfSegment> sortedSegments = segments.stream().map(ElfSegment::normalizeHorizontal).sorted(new ElfSegmentHorizontalStartComparator()).toList();
            return sortedSegments.get(0).unionHorizontal(sortedSegments.get(1));
        }
        else {
            List<ElfSegment> sortedSegments = segments.stream().map(ElfSegment::normalizeHorizontal).sorted(new ElfSegmentHorizontalStartComparator()).toList();
            List<ElfSegment> union = new ArrayList<>(sortedSegments.get(0).unionHorizontal(sortedSegments.get(1)));
            for (int i = 2; i < sortedSegments.size(); i++) {
                ElfSegment segment = union.get(union.size() - 1);
                List<ElfSegment> newElfSegments = segment.unionHorizontal(sortedSegments.get(i));
                union.remove(segment);
                union.addAll(newElfSegments);
            }
            return union;
        }
    }


    public ElfSegment normalizeHorizontal() {
        return start.col() > end.col() ? new ElfSegment(end, start) : this;
    }

    public ElfSegment normalizeVertical() {
        return start.row() > end.row() ? new ElfSegment(end, start) : this;
    }

    public static List<ElfSegment> intersectHorizontal(List<ElfSegment> segments) {
        if (2 == segments.size()) {
            ElfSegment segment = segments.get(0).intersectHorizontal(segments.get(1));
            if (!segment.equals(ElfSegment.empty())) {
                return List.of(segment);
            }
        }
        if (3 == segments.size()) {
            List<ElfSegment> result = new ArrayList<>();
            ElfSegment segment1 = segments.get(0).intersectHorizontal(segments.get(1));
            ElfSegment segment2 = segments.get(0).intersectHorizontal(segments.get(2));
            ElfSegment segment3 = segments.get(1).intersectHorizontal(segments.get(2));
            if (!segment1.equals(ElfSegment.empty())) {
                result.add(segment1);
            }
            if (!segment2.equals(ElfSegment.empty())) {
                result.add(segment1);
            }
            if (!segment3.equals(ElfSegment.empty())) {
                result.add(segment1);
            }
        }
        return List.of();
    }

}
