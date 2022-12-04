package hu.herpaipeter.aoc2022.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosedRangeTest {

    @Test
    void same_start_end_length_one() {
        ClosedRange range = new ClosedRange(0, 0);
        assertEquals(1, range.length());
    }

    @Test
    void end_less_than_start_length_zero() {
        ClosedRange range = new ClosedRange(0, -1);
        assertEquals(0, range.length());
    }

    @Test
    void end_less_than_start_diff_bigger_length_zero() {
        ClosedRange range = new ClosedRange(5, -1);
        assertEquals(0, range.length());
    }

    @Test
    void lenght_more_than_one() {
        ClosedRange range = new ClosedRange(3, 5);
        assertEquals(3, range.length());
    }

    @Test
    void two_ranges_no_overlap() {
        ClosedRange range = new ClosedRange(0, 1);
        ClosedRange range2 = new ClosedRange(2, 3);
        assertEquals(ClosedRange.empty(), range.getIntersection(range2));
    }

    @Test
    void not_in_order_two_ranges_no_overlap() {
        ClosedRange range = new ClosedRange(3, 4);
        ClosedRange range2 = new ClosedRange(1, 2);
        assertEquals(ClosedRange.empty(), range.getIntersection(range2));
    }

    @Test
    void two_ranges_intersection_overlap() {
        ClosedRange range = new ClosedRange(0, 1);
        ClosedRange range2 = new ClosedRange(1, 2);
        assertEquals(new ClosedRange(1, 1), range.getIntersection(range2));
    }

    @Test
    void not_in_order_two_ranges_overlap() {
        ClosedRange range = new ClosedRange(3, 4);
        ClosedRange range2 = new ClosedRange(1, 3);
        assertEquals(new ClosedRange(3,3), range.getIntersection(range2));
    }

    @Test
    void second_range_inside_first() {
        ClosedRange range = new ClosedRange(1, 5);
        ClosedRange range2 = new ClosedRange(2, 3);
        assertEquals(new ClosedRange(2,3), range.getIntersection(range2));
    }

    @Test
    void first_range_inside_second() {
        ClosedRange range = new ClosedRange(2, 3);
        ClosedRange range2 = new ClosedRange(1, 5);
        assertEquals(new ClosedRange(2,3), range.getIntersection(range2));
    }
}
