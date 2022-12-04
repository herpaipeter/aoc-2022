package hu.herpaipeter.aoc2022.day04;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CampCleanupAssignmentPart2Test {

    CampCleanupAssignment assignment;

    @BeforeEach
    void setUp() {
        assignment = new CampCleanupAssignment();
    }

    @Test
    void empty_list_should_return_zero() {
        assertEquals(0, assignment.getNumberOfPartiallyContains(List.of()));
    }

    @Test
    void one_pair_no_overlap_should_return_zero() {
        assertEquals(0, assignment.getNumberOfPartiallyContains(List.of("1-1,2-2")));
    }

    @Test
    void one_pair_partially_overlap_should_return_one() {
        assertEquals(1, assignment.getNumberOfPartiallyContains(List.of("1-2,2-3")));
    }

    @Test
    void more_partially_overlap_test() {
        assertEquals(4, assignment.getNumberOfPartiallyContains(List.of("2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6", "2-6,4-8")));
    }

    @Test
    void file_result() {
        List<String> rangePairs = FileReader.readStringLines("src\\test\\java\\hu\\herpaipeter\\aoc2022\\day04\\input.txt");
        System.out.println(assignment.getNumberOfPartiallyContains(rangePairs));
    }

}
