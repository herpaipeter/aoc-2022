package hu.herpaipeter.aoc2022.day22;

import hu.herpaipeter.aoc2022.day09.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyMapParserTest {

    @Test
    void empty_string_list_should_return_empty_map_and_empty_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of());
        assertEquals(List.of(), parser.getOpenTiles());
        assertEquals(List.of(), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void one_line_open_tiles_should_return_only_open_tiles() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("."));
        assertEquals(List.of(new Point(0,0)), parser.getOpenTiles());
        assertEquals(List.of(), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void one_line_multi_open_tiles_should_return_only_open_tiles_in_a_row() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("....."));
        assertEquals(List.of(new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(0,4)), parser.getOpenTiles());
        assertEquals(List.of(), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void two_line_multi_open_tiles_should_return_open_tiles_in_two_row() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("...", "..."));
        assertEquals(List.of(new Point(0,0), new Point(0,1), new Point(0,2),
                                new Point(1,0), new Point(1,1), new Point(1,2)),
                            parser.getOpenTiles());
        assertEquals(List.of(), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void two_line_multi_open_tiles_with_empty_space_should_return_shifted_open_tiles_in_two_row() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(" ...", "  ..."));
        assertEquals(List.of(new Point(0,1), new Point(0,2), new Point(0,3),
                        new Point(1,2), new Point(1,3), new Point(1,4)),
                parser.getOpenTiles());
        assertEquals(List.of(), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void one_wall_should_return_only_walls_with_one_point() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("#"));
        assertEquals(List.of(), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 0)), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void two_walls_should_return_only_walls_with_two_points() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("##"));
        assertEquals(List.of(), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 0), new Point(0, 1)), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void two_line_of_walls_should_return_only_walls_in_two_rows() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of("##", "##"));
        assertEquals(List.of(), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)), parser.getWalls());
        assertEquals(List.of(), parser.getPath());
    }

    @Test
    void after_map_empty_line_then_number_should_return_one_move_element_of_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(".#", "#.", "", "10"));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1)), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 1), new Point(1, 0)), parser.getWalls());
        assertEquals(List.of(new MonkeyMove(10)), parser.getPath());
    }

    @Test
    void after_map_empty_line_then_R_should_return_one_right_turn_element_of_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(".#", "#.", "", "R"));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1)), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 1), new Point(1, 0)), parser.getWalls());
        assertEquals(List.of(new MonkeyTurn(UDDirection.Right)), parser.getPath());
    }

    @Test
    void after_map_empty_line_then_L_should_return_one_left_turn_element_of_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(".#", "#.", "", "L"));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1)), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 1), new Point(1, 0)), parser.getWalls());
        assertEquals(List.of(new MonkeyTurn(UDDirection.Left)), parser.getPath());
    }

    @Test
    void after_map_empty_line_then_number_and_R_should_return_one_move_and_one_right_turn_element_of_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(".#", "#.", "", "10R"));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1)), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 1), new Point(1, 0)), parser.getWalls());
        assertEquals(List.of(new MonkeyMove(10), new MonkeyTurn(UDDirection.Right)), parser.getPath());
    }

    @Test
    void move_textline_should_return_all_steps_of_path() {
        MonkeyMapParser parser = new MonkeyMapParser(List.of(".#", "#.", "", "10R5"));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1)), parser.getOpenTiles());
        assertEquals(List.of(new Point(0, 1), new Point(1, 0)), parser.getWalls());
        assertEquals(List.of(new MonkeyMove(10), new MonkeyTurn(UDDirection.Right), new MonkeyMove(5)), parser.getPath());
    }

}
