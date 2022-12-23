package hu.herpaipeter.aoc2022.day09;


import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RopeMovementPart1Test {

    RopeMovement ropeMovement;

    @BeforeEach
    void setUp() {
        ropeMovement = new RopeMovement();
    }

    @Test
    void no_move_should_everything_stay_in_place() {
        assertEquals(Point.origo(), ropeMovement.getHeadPosition());
        assertEquals(Point.origo(), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void from_same_position_head_one_right_should_tail_stay() {
        ropeMovement.move(new Motion(Direction.Right, 1));
        assertEquals(new Point(0,1), ropeMovement.getHeadPosition());
        assertEquals(Point.origo(), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void same_position_head_two_right_should_followed_by_tail() {
        ropeMovement.move(new Motion(Direction.Right, 2));
        assertEquals(new Point(0,2), ropeMovement.getHeadPosition());
        assertEquals(new Point(0,1), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo(), new Point(0,1)), ropeMovement.getTailRoute());
    }

    @Test
    void same_position_head_two_left_should_followed_by_tail() {
        ropeMovement.move(new Motion(Direction.Left, 2));
        assertEquals(new Point(0,-2), ropeMovement.getHeadPosition());
        assertEquals(new Point(0,-1), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo(), new Point(0,-1)), ropeMovement.getTailRoute());
    }

    @Test
    void same_position_head_two_up_should_followed_by_tail() {
        ropeMovement.move(new Motion(Direction.Up, 2));
        assertEquals(new Point(2,0), ropeMovement.getHeadPosition());
        assertEquals(new Point(1,0), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo(), new Point(1,0)), ropeMovement.getTailRoute());
    }

    @Test
    void head_moves_up_to_diagonal_touching_tail_should_stay() {
        RopeMovement ropeMovement = new RopeMovement(new Point(0,1), Point.origo());
        ropeMovement.move(new Motion(Direction.Up, 1));
        assertEquals(new Point(1,1), ropeMovement.getHeadPosition());
        assertEquals(Point.origo(), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void head_moves_to_right_diagonal_touching_tail_should_stay() {
        RopeMovement ropeMovement = new RopeMovement(new Point(1,0), Point.origo());
        ropeMovement.move(new Motion(Direction.Right, 1));
        assertEquals(new Point(1,1), ropeMovement.getHeadPosition());
        assertEquals(Point.origo(), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void head_moves_from_diagonal_touching_to_right_tail_should_follow() {
        RopeMovement ropeMovement = new RopeMovement(new Point(1,1), Point.origo());
        ropeMovement.move(new Motion(Direction.Right, 1));
        assertEquals(new Point(1,2), ropeMovement.getHeadPosition());
        assertEquals(new Point(1,1), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo(), new Point(1,1)), ropeMovement.getTailRoute());
    }

    @Test
    void head_moves_from_diagonal_touching_to_up_tail_should_follow() {
        RopeMovement ropeMovement = new RopeMovement(new Point(1,1), Point.origo());
        ropeMovement.move(new Motion(Direction.Up, 1));
        assertEquals(new Point(2,1), ropeMovement.getHeadPosition());
        assertEquals(new Point(1,1), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo(), new Point(1,1)), ropeMovement.getTailRoute());
    }

    @Test
    void dont_move_by_empty_string_command() {
        ropeMovement.moveByCommands(List.of());
        assertEquals(Point.origo(), ropeMovement.getHeadPosition());
        assertEquals(Point.origo(), ropeMovement.getTailPosition());
        assertEquals(List.of(Point.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void move_right_by_string_command() {
        ropeMovement.moveByCommands(List.of("R 1"));
        assertEquals(new Point(0, 1), ropeMovement.getHeadPosition());
    }

    @Test
    void move_left_by_string_command() {
        RopeMovement ropeMovement = new RopeMovement();
        ropeMovement.moveByCommands(List.of("L 1"));
        assertEquals(new Point(0, -1), ropeMovement.getHeadPosition());
    }

    @Test
    void move_up_by_string_command() {
        ropeMovement.moveByCommands(List.of("U 1"));
        assertEquals(new Point(1, 0), ropeMovement.getHeadPosition());
    }

    @Test
    void move_down_by_string_command() {
        ropeMovement.moveByCommands(List.of("D 1"));
        assertEquals(new Point(-1, 0), ropeMovement.getHeadPosition());
    }

    @Test
    void move_twice_by_string_command() {
        ropeMovement.moveByCommands(List.of("R 1", "U 1"));
        assertEquals(new Point(1, 1), ropeMovement.getHeadPosition());
    }

    @Test
    void sample_test() {
        ropeMovement.moveByCommands(List.of("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2"));
        assertEquals(new Point(2, 2), ropeMovement.getHeadPosition());
        assertEquals(new Point(2, 1), ropeMovement.getTailPosition());
        assertEquals(13, new HashSet<>(ropeMovement.getTailRoute()).size());
    }

    @Test
    void file_result_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day09");
        ropeMovement.moveByCommands(inputTxt);
        System.out.println("part1: " + new HashSet<>(ropeMovement.getTailRoute()).size());
    }

}
