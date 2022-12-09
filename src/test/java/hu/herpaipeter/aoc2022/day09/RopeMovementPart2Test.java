package hu.herpaipeter.aoc2022.day09;


import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RopeMovementPart2Test {

    @Test
    void knots_3_no_move_should_everything_stay_in_place() {
        RopeMovement ropeMovement = new RopeMovement(3);
        assertEquals(ElfPoint.origo(), ropeMovement.getHeadPosition());
        assertEquals(ElfPoint.origo(), ropeMovement.getKnotPosition(1));
        assertEquals(ElfPoint.origo(), ropeMovement.getKnotPosition(2));
        assertEquals(List.of(ElfPoint.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void move_1_right_should_move_only_head() {
        RopeMovement ropeMovement = new RopeMovement(3);
        ropeMovement.move(new Motion(Direction.Right, 1));
        assertEquals(new ElfPoint(0, 1), ropeMovement.getHeadPosition());
        assertEquals(ElfPoint.origo(), ropeMovement.getKnotPosition(1));
        assertEquals(ElfPoint.origo(), ropeMovement.getKnotPosition(2));
        assertEquals(List.of(ElfPoint.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void move_2_right_should_move_head_and_first_knot() {
        RopeMovement ropeMovement = new RopeMovement(3);
        ropeMovement.move(new Motion(Direction.Right, 2));
        assertEquals(new ElfPoint(0, 2), ropeMovement.getHeadPosition());
        assertEquals(new ElfPoint(0, 1), ropeMovement.getKnotPosition(1));
        assertEquals(ElfPoint.origo(), ropeMovement.getKnotPosition(2));
        assertEquals(List.of(ElfPoint.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void small_test_example() {
        RopeMovement ropeMovement = new RopeMovement(10);
        ropeMovement.moveByCommands(List.of("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2"));
        assertEquals(List.of(ElfPoint.origo()), ropeMovement.getTailRoute());
    }

    @Test
    void large_test_example() {
        RopeMovement ropeMovement = new RopeMovement(10);
        ropeMovement.moveByCommands(List.of("R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"));
        assertEquals(36, new HashSet<>(ropeMovement.getTailRoute()).size());
    }

    @Test
    void file_result_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day09");
        RopeMovement ropeMovement = new RopeMovement(10);
        ropeMovement.moveByCommands(inputTxt);
        System.out.println("part2: " + new HashSet<>(ropeMovement.getTailRoute()).size());
    }

}
