package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day12.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ElfSegmentTest {

    @Test
    void horizontal_length_test() {
        assertEquals(1, new ElfSegment(new Point(0,0), new Point(0,0)).length());
        assertEquals(2, new ElfSegment(new Point(0,0), new Point(0,1)).length());
        assertEquals(3, new ElfSegment(new Point(0,-1), new Point(0,1)).length());
    }

    @Test
    void diagonal_length_test() {
        assertEquals(3, new ElfSegment(new Point(0,0), new Point(1,1)).length());
    }

    @Test
    void horizontal_segment_should_contains_point_in_the_same_row_if_is_between_cols() {
        assertTrue(new ElfSegment(new Point(0,0), new Point(0,10)).contains(new Point(0, 0)));
        assertTrue(new ElfSegment(new Point(0,0), new Point(0,10)).contains(new Point(0, 5)));
        assertTrue(new ElfSegment(new Point(0,10), new Point(0,0)).contains(new Point(0, 0)));
    }

    @Test
    void vertical_segment_should_contains_point_in_the_same_row_if_is_between_cols() {
        assertTrue(new ElfSegment(new Point(0,0), new Point(10,0)).contains(new Point(5, 0)));
        assertTrue(new ElfSegment(new Point(10,0), new Point(0,0)).contains(new Point(5, 0)));
    }

    @Test
    void segments_intersection_no() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 3), new Point(0, 4));
        assertEquals(ElfSegment.empty(), segment1.intersectHorizontal(segment2));
    }

    @Test
    void segments_intersection_commutative() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 0), new Point(0, 3));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 5));
        assertEquals(new ElfSegment(new Point(0, 2), new Point(0, 3)), segment1.intersectHorizontal(segment2));
        assertEquals(new ElfSegment(new Point(0, 2), new Point(0, 3)), segment2.intersectHorizontal(segment1));
    }

    @Test
    void two_segments_list_intersection() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 0), new Point(0, 3));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 5));
        ElfSegment result1 = new ElfSegment(new Point(0, 2), new Point(0, 3));
        assertEquals(List.of(result1), ElfSegment.intersectHorizontal(List.of(segment1, segment2)));
    }

    @Test
    void two_segments_list_no_intersection() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 0), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 3), new Point(0, 5));
        assertEquals(List.of(), ElfSegment.intersectHorizontal(List.of(segment1, segment2)));
    }

    @Test
    void segments_horizontal_union_no_interaction_should_return_segments() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 3), new Point(0, 4));
        assertEquals(List.of(segment1, segment2), segment1.unionHorizontal(segment2));
    }

    @Test
    void segments_horizontal_union_interaction_should_return_one_segment() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 4));
        assertEquals(List.of(new ElfSegment(new Point(0, 1), new Point(0, 4))), segment1.unionHorizontal(segment2));
    }

    @Test
    void segments_horizontal_union_inside_should_return_one_segment() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 0), new Point(0, 10));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 4));
        assertEquals(List.of(new ElfSegment(new Point(0, 0), new Point(0, 10))), segment1.unionHorizontal(segment2));
    }

    @Test
    void union_empty_segment_should_return_empty() {
        assertEquals(List.of(), ElfSegment.unionHorizontal(List.of()));
    }

    @Test
    void union_one_segment_should_return_the_same() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 0), new Point(0, 10));
        assertEquals(List.of(segment1), ElfSegment.unionHorizontal(List.of(segment1)));
    }

    @Test
    void union_two_segment_should_return_their_union() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 4));
        assertEquals(List.of(new ElfSegment(new Point(0, 1), new Point(0, 4))), ElfSegment.unionHorizontal(List.of(segment1, segment2)));
    }

    @Test
    void union_three_segment_should_return_their_union() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 4));
        ElfSegment segment3 = new ElfSegment(new Point(0, 6), new Point(0, 7));
        assertEquals(List.of(new ElfSegment(new Point(0, 1), new Point(0, 4)), segment3), ElfSegment.unionHorizontal(List.of(segment1, segment2, segment3)));
    }

    @Test
    void union_three_segment_reversed_order_should_return_their_union() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 6), new Point(0, 7));
        ElfSegment segment2 = new ElfSegment(new Point(0, 2), new Point(0, 4));
        ElfSegment segment3 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        assertEquals(List.of(new ElfSegment(new Point(0, 1), new Point(0, 4)), segment1), ElfSegment.unionHorizontal(List.of(segment1, segment2, segment3)));
    }

    @Test
    void union_three_segment_second_third_common_should_return_their_union() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 4), new Point(0, 6));
        ElfSegment segment3 = new ElfSegment(new Point(0, 6), new Point(0, 7));
        assertEquals(List.of(segment1, new ElfSegment(new Point(0, 4), new Point(0, 7))), ElfSegment.unionHorizontal(List.of(segment1, segment2, segment3)));
    }

    @Test
    void union_three_separate_segment_should_return_the_same_three() {
        ElfSegment segment1 = new ElfSegment(new Point(0, 1), new Point(0, 2));
        ElfSegment segment2 = new ElfSegment(new Point(0, 4), new Point(0, 5));
        ElfSegment segment3 = new ElfSegment(new Point(0, 6), new Point(0, 8));
        assertEquals(List.of(segment1, segment2, segment3), ElfSegment.unionHorizontal(List.of(segment1, segment2, segment3)));
    }
}
