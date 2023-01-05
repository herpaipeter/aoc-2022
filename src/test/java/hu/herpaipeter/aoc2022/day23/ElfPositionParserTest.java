package hu.herpaipeter.aoc2022.day23;

import hu.herpaipeter.aoc2022.day09.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ElfPositionParserTest {

    @Test
    void empty_string_list_should_return_empty_point_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(List.of(), parser.getElfPositions(List.of()));
    }

    @Test
    void one_empty_string_or_dots_list_should_return_empty_point_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(List.of(), parser.getElfPositions(List.of("")));
        assertEquals(List.of(), parser.getElfPositions(List.of(".")));
        assertEquals(List.of(), parser.getElfPositions(List.of("..")));
    }

    @Test
    void one_hashmark_string_list_should_return_one_point_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(List.of(new Point(0,0)), parser.getElfPositions(List.of("#")));
    }

    @Test
    void one_hashmark_with_dots_prefix_string_list_should_return_one_point_with_proper_col_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(List.of(new Point(0,1)), parser.getElfPositions(List.of(".#")));
    }

    @Test
    void two_hashmarks_with_dots_prefix_string_list_should_return_two_points_with_proper_col_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(List.of(new Point(0,1), new Point(0,2)), parser.getElfPositions(List.of(".##")));
    }

    @Test
    void two_strings_list_should_return_two_points_with_proper_row_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(Set.of(new Point(0,0), new Point(1,0)), new HashSet<>(parser.getElfPositions(List.of("#", "#"))));
    }

    @Test
    void two_strings_list_should_return_two_points_with_proper_row_and_col_number_list() {
        ElfPositionParser parser = new ElfPositionParser();
        assertEquals(Set.of(new Point(0, 2), new Point(1,1)), new HashSet<>(parser.getElfPositions(List.of(".#", "..#"))));
    }
}
