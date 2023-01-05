package hu.herpaipeter.aoc2022.day23;

import hu.herpaipeter.aoc2022.day09.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ElfMovementTest {

    @Test
    void no_elf_should_move_nowhere() {
        ElfMovement elfMovement = new ElfMovement(List.of());
        assertEquals(List.of(), elfMovement.moveAll(1));
    }

    @Test
    void one_elf_should_stay() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0)));
        assertEquals(List.of(new Point(0, 0)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_not_next_to_each_other_should_stay() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(2, 0)));
        assertEquals(List.of(new Point(0, 0), new Point(2, 0)), elfMovement.moveAll(1));
    }

    @Test
    void one_elf_with_other_in_adjacent_but_not_upper_three_positions_should_move_north() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(-1, 0)));
        assertEquals(new Point(1, 0), elfMovement.moveAll(1).get(0));
    }

    @Test
    void one_elf_with_other_in_adjacent_upper_but_free_bottom_positions_should_move_south() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(1, 0)));
        assertEquals(new Point(-1, 0), elfMovement.moveAll(1).get(0));
    }

    @Test
    void one_elf_with_other_in_adjacent_upper_and_bottom_but_free_left_positions_should_move_west() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(-1, 0), new Point(1, 0)));
        assertEquals(new Point(0, -1), elfMovement.moveAll(1).get(0));
    }

    @Test
    void one_elf_with_other_in_adjacent_upper_and_bottom_and_left_but_free_right_positions_should_move_east() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, -1)));
        assertEquals(new Point(0, 1), elfMovement.moveAll(1).get(0));
    }

    @Test
    void one_elf_with_all_four_directions_blocked_should_stay() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)));
        assertEquals(new Point(0, 0), elfMovement.moveAll(1).get(0));
    }

    @Test
    void two_elves_next_to_each_other_vertical_should_move_north_and_south() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(1, 0)));
        assertEquals(List.of(new Point(-1, 0), new Point(2, 0)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_next_to_each_other_horizontal_should_move_both_north() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(0, 1)));
        assertEquals(List.of(new Point(1, 0), new Point(1, 1)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_next_to_each_other_diagonal_first_northern_should_move_north_and_south() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(1, 0), new Point(0, 1)));
        assertEquals(List.of(new Point(2, 0), new Point(-1, 1)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_next_to_each_other_diagonal_first_southern_should_move_south_and_north() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(1, 1)));
        assertEquals(List.of(new Point(-1, 0), new Point(2, 1)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_should_not_move_to_the_same_position() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(0, 1), new Point(2, 0), new Point(3, 0)));
        assertEquals(List.of(new Point(0, 0), new Point(1, 1), new Point(2, 0), new Point(4, 0)), elfMovement.moveAll(1));
    }

    @Test
    void two_elves_next_to_each_other_horizontal_should_move_both_first_north_then_south() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(0, 0), new Point(0, 1)));
        assertEquals(List.of(new Point(0, 0), new Point(0, 1)), elfMovement.moveAll(2));
    }

    @Test
    void change_direction_probe_test() {
        ElfMovement elfMovement = new ElfMovement(List.of(new Point(1, 2), new Point(1, 3), new Point(3, 2), new Point(4, 2), new Point(4, 3)));
        assertEquals(List.of(new Point(0, 2), new Point(2, 4), new Point(3, 0), new Point(5, 2), new Point(4, 4)), elfMovement.moveAll(3));
    }

    @Test
    void larger_example_after_round_10() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day23", "larger_example.txt");
        List<String> inputTxtRound10 = FileReader.readAoCInputFileLines("day23", "larger_example_round_10.txt");
        List<Point> elfPositions = new ElfPositionParser().getElfPositions(inputTxt);
        List<Point> elfPositionsRound10 = new ElfPositionParser().getElfPositions(inputTxtRound10);
        ElfMovement elfMovement = new ElfMovement(elfPositions);
        List<Point> points = elfMovement.moveAll(10);
        int minRow = points.stream().min(Comparator.comparingInt(Point::row)).get().row();
        int maxRow = points.stream().max(Comparator.comparingInt(Point::row)).get().row();
        int minCol = points.stream().min(Comparator.comparingInt(Point::col)).get().col();
        int maxCol = points.stream().max(Comparator.comparingInt(Point::col)).get().col();
        assertEquals(new HashSet<>(elfPositionsRound10),new HashSet<>(points));
        assertEquals(110, (maxRow - minRow + 1) * (maxCol - minCol + 1) - points.size());
    }

    @Test
    void input_file_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day23");
        List<Point> elfPositions = new ElfPositionParser().getElfPositions(inputTxt);
        ElfMovement elfMovement = new ElfMovement(elfPositions);
        List<Point> points = elfMovement.moveAll(10);
        int minRow = points.stream().min(Comparator.comparingInt(Point::row)).get().row();
        int maxRow = points.stream().max(Comparator.comparingInt(Point::row)).get().row();
        int minCol = points.stream().min(Comparator.comparingInt(Point::col)).get().col();
        int maxCol = points.stream().max(Comparator.comparingInt(Point::col)).get().col();
        int emptyGroundTiles = (maxRow - minRow + 1) * (maxCol - minCol + 1) - points.size();
        System.out.println("Day 23 part 1: " + emptyGroundTiles);
    }

    @Test
    void larger_example_no_movement_rounds() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day23", "larger_example.txt");
        List<Point> elfPositions = new ElfPositionParser().getElfPositions(inputTxt);
        ElfMovement elfMovement = new ElfMovement(elfPositions);
        assertEquals(20, elfMovement.getNoMovesRounds());
    }

    @Test
    void input_file_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day23");
        List<Point> elfPositions = new ElfPositionParser().getElfPositions(inputTxt);
        ElfMovement elfMovement = new ElfMovement(elfPositions);
        System.out.println("Day 23 part 2: " + elfMovement.getNoMovesRounds());
    }
}
