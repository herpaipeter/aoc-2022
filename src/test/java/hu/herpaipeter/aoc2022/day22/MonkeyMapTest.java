package hu.herpaipeter.aoc2022.day22;

import hu.herpaipeter.aoc2022.day09.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyMapTest {

    @Test
    void position_by_no_move_should_be_at_top_left_tile() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        assertEquals(new Point(0,0), monkeyMap.getPosition());
    }

    @Test
    void map_begins_not_first_col_position_by_no_move_should_be_at_top_left_tile() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 1)), List.of());
        assertEquals(new Point(0,1), monkeyMap.getPosition());
    }

    @Test
    void move_one_should_be_the_next_point_to_the_right() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1)), List.of());
        monkeyMap.move(1);
        assertEquals(new Point(0,1), monkeyMap.getPosition());
    }

    @Test
    void move_two_should_be_the_next_point_by_two_to_the_right() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2)), List.of());
        monkeyMap.move(2);
        assertEquals(new Point(0,2), monkeyMap.getPosition());
    }

    @Test
    void default_direction_should_be_right() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        assertEquals(UDDirection.Right, monkeyMap.getDirection());
    }

    @Test
    void turn_from_direction_right_to_right_should_be_direction_down() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        monkeyMap.turn(UDDirection.Right);
        assertEquals(UDDirection.Down, monkeyMap.getDirection());
    }

    @Test
    void turn_from_direction_right_to_left_should_be_direction_up() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        monkeyMap.turn(UDDirection.Left);
        assertEquals(UDDirection.Up, monkeyMap.getDirection());
    }

    @Test
    void turn_left_four_times_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        monkeyMap.turn(UDDirection.Left);
        assertEquals(UDDirection.Up, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Left);
        assertEquals(UDDirection.Left, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Left);
        assertEquals(UDDirection.Down, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Left);
        assertEquals(UDDirection.Right, monkeyMap.getDirection());
    }

    @Test
    void turn_right_four_times_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0)), List.of());
        monkeyMap.turn(UDDirection.Right);
        assertEquals(UDDirection.Down, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Right);
        assertEquals(UDDirection.Left, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Right);
        assertEquals(UDDirection.Up, monkeyMap.getDirection());
        monkeyMap.turn(UDDirection.Right);
        assertEquals(UDDirection.Right, monkeyMap.getDirection());
    }

    @Test
    void the_wall_should_stop_the_move() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2)),
                                            List.of(new Point(0, 3)));
        monkeyMap.move(3);
        assertEquals(new Point(0,2), monkeyMap.getPosition());
    }

    @Test
    void cant_go_trough_the_wall_should_stop_before() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 3)),
                                            List.of(new Point(0, 2)));
        monkeyMap.move(3);
        assertEquals(new Point(0,1), monkeyMap.getPosition());
    }

    @Test
    void turn_and_move_should_go_down() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                                                    new Point(1, 0), new Point(1, 1), new Point(1, 2),
                                                    new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                                            List.of());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(1,0), monkeyMap.getPosition());
    }

    @Test
    void move_in_circle_always_right_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                                                    new Point(1, 0), new Point(1, 1), new Point(1, 2),
                                                    new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                                            List.of());
        monkeyMap.move(1);
        assertEquals(new Point(0,1), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(1,1), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(1,0), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(0,0), monkeyMap.getPosition());
    }

    @Test
    void move_in_circle_always_left_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                                                    new Point(1, 0), new Point(1, 1), new Point(1, 2),
                                                    new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                                            List.of());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(1,0), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Left);
        monkeyMap.move(1);
        assertEquals(new Point(1,1), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Left);
        monkeyMap.move(1);
        assertEquals(new Point(0,1), monkeyMap.getPosition());
        monkeyMap.turn(UDDirection.Left);
        monkeyMap.move(1);
        assertEquals(new Point(0,0), monkeyMap.getPosition());
    }

    @Test
    void move_out_of_the_map_right_should_wrap_around_in_left() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                                                    new Point(1, 0), new Point(1, 1), new Point(1, 2),
                                                    new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                                            List.of());
        monkeyMap.move(3);
        assertEquals(new Point(0,0), monkeyMap.getPosition());
    }

    @Test
    void move_out_of_the_map_left_should_wrap_around_in_right() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 0), new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        assertEquals(new Point(0,2), monkeyMap.getPosition());
    }

    @Test
    void move_out_of_the_map_bottom_should_wrap_around_in_upper() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 0), new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of());
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(3);
        assertEquals(new Point(0,0), monkeyMap.getPosition());
    }

    @Test
    void move_out_of_the_map_upper_should_wrap_around_in_bottom() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 0), new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of());
        monkeyMap.turn(UDDirection.Left);
        monkeyMap.move(1);
        assertEquals(new Point(2,0), monkeyMap.getPosition());
    }

    @Test
    void move_out_of_the_map_but_wall_on_the_wrap_side_should_stay() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 0)));
        monkeyMap.move(2);
        monkeyMap.turn(UDDirection.Right);
        monkeyMap.move(1);
        monkeyMap.turn(UDDirection.Left);
        monkeyMap.move(1);
        assertEquals(new Point(1,2), monkeyMap.getPosition());
    }

    @Test
    void list_of_steps_with_one_move_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 0)));
        monkeyMap.runSteps(List.of(new MonkeyMove(2)));
        assertEquals(new Point(0,2), monkeyMap.getPosition());
    }

    @Test
    void list_of_steps_with_one_turn_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 0)));
        monkeyMap.runSteps(List.of(new MonkeyTurn(UDDirection.Right)));
        assertEquals(UDDirection.Down, monkeyMap.getDirection());
    }

    @Test
    void list_of_steps_test() {
        MonkeyMap monkeyMap = new MonkeyMap(List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 0)));
        monkeyMap.runSteps(List.of(new MonkeyMove(2), new MonkeyTurn(UDDirection.Right), new MonkeyMove(1),
                                    new MonkeyTurn(UDDirection.Left), new MonkeyMove(1)));
        assertEquals(new Point(1,2), monkeyMap.getPosition());
        assertEquals(UDDirection.Right, monkeyMap.getDirection());
    }

    @Test
    void example_part_1_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day22", "example.txt");
        MonkeyMapParser parser = new MonkeyMapParser(inputTxt);
        MonkeyMap monkeyMap = new MonkeyMap(parser.getOpenTiles(), parser.getWalls());
        monkeyMap.runSteps(parser.getPath());
        Point position = monkeyMap.getPosition();
        UDDirection direction = monkeyMap.getDirection();
        assertEquals(6032, 1000 * (position.row() + 1) + 4 * (position.col() + 1) + direction.ordinal());
    }

    @Test
    void input_file_part_1_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day22");
        MonkeyMapParser parser = new MonkeyMapParser(inputTxt);
        MonkeyMap monkeyMap = new MonkeyMap(parser.getOpenTiles(), parser.getWalls());
        monkeyMap.runSteps(parser.getPath());
        Point position = monkeyMap.getPosition();
        UDDirection direction = monkeyMap.getDirection();
        System.out.println("Day 22 part 1: " + Integer.toString(1000 * (position.row() + 1) + 4 * (position.col() + 1) + direction.ordinal()));
        //System.out.println(155000600);
    }
}
