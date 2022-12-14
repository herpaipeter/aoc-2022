package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RockMapperTest {

    @Test
    void no_lines_no_rocks() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(), rockMapper.map(List.of()));
    }

    @Test
    void one_point_line_should_map_one_rock() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10)), rockMapper.map(List.of(new Point(10,10))));
    }

    @Test
    void two_points_horizontal_line_should_map_rocks_between() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10), new Point(10,11),new Point(10,12)), rockMapper.map(List.of(new Point(10,10), new Point(10,12))));
    }

    @Test
    void two_points_vertical_line_should_map_rocks_between() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10), new Point(11,10),new Point(12,10)), rockMapper.map(List.of(new Point(10,10), new Point(12,10))));
    }

    @Test
    void two_points_reverse_order_horizontal_line_should_map_rocks_between() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10), new Point(10,11),new Point(10,12)), rockMapper.map(List.of(new Point(10,12), new Point(10,10))));
    }

    @Test
    void two_points_reverse_vertical_line_should_map_rocks_between() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10), new Point(11,10),new Point(12,10)), rockMapper.map(List.of(new Point(12,10), new Point(10,10))));
    }

    @Test
    void three_points_two_lines_should_map_rocks_between() {
        RockMapper rockMapper = new RockMapper();
        assertEquals(Set.of(new Point(10,10), new Point(10,11),new Point(10,12), new Point(11,12), new Point(12,12)),
                rockMapper.map(List.of(new Point(10,10), new Point(10,12), new Point(12, 12))));
    }
}
