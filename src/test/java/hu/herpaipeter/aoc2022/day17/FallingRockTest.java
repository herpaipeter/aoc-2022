package hu.herpaipeter.aoc2022.day17;

import hu.herpaipeter.aoc2022.day12.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FallingRockTest {

    private static List<Rock> fiveRocks = List.of(
            new Rock(List.of("####")),
            new Rock(List.of(" # ", "###", " # ")),
            new Rock(List.of("###", "  #", "  #")),
            new Rock(List.of("#", "#", "#", "#")),
            new Rock(List.of("##", "##")));

    @Test
    void no_rock_should_return_no_height() {
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(), ""));
        fallingRock.dropRocks(0);
        assertEquals(0, fallingRock.getChamberRocksHeight());
    }

    @Test
    void one_flat_rock_should_return_1() {
        Rock rock = new Rock(List.of("#"));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), ""));
        fallingRock.dropRocks(1);
        assertEquals(1, fallingRock.getChamberRocksHeight());
    }

    @Test
    void one_two_height_rock_should_return_2() {
        Rock rock = new Rock(List.of("#", "#"));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), ""));
        fallingRock.dropRocks(1);
        assertEquals(2, fallingRock.getChamberRocksHeight());
    }

    @Test
    void one_three_height_rock_should_return_3_rock_width_doesnt_matter() {
        Rock rock = new Rock(List.of(" # ", "###", " # "));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), ""));
        fallingRock.dropRocks(1);
        assertEquals(3, fallingRock.getChamberRocksHeight());
    }

    @Test
    void two_rocks_should_return_sum_of_its_heights() {
        Rock rock1 = new Rock(List.of(" # ", " # "));
        Rock rock2 = new Rock(List.of(" # ", "###", " # "));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock1, rock2), ""));
        fallingRock.dropRocks(2);
        assertEquals(5, fallingRock.getChamberRocksHeight());
    }

    @Test
    void two_rocks_and_more_fall_should_repeat_rock_by_order() {
        Rock rock1 = new Rock(List.of(" # ", " # "));
        Rock rock2 = new Rock(List.of(" # ", "###", " # "));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock1, rock2), ""));
        fallingRock.dropRocks(3);
        assertEquals(7, fallingRock.getChamberRocksHeight());
    }

    @Test
    void second_rock_fits_inside_first_should_return_lower_height() {
        Rock rock1 = new Rock(List.of("###", "  #", "  #"));
        Rock rock2 = new Rock(List.of(" # ", "###", " # "));
        /* #
        * ###
        *  ##
        *   #
        * ###*/
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock1, rock2), ""));
        fallingRock.dropRocks(2);
        assertEquals(5, fallingRock.getChamberRocksHeight());
    }

    @Test
    void one_flat_rock_jet_moves_right_should_return_1_and_be_in_the_right_corner() {
        Rock rock = new Rock(List.of("#"));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), ">>>>>>>>>>>>>"));
        fallingRock.dropRocks(1);
        assertEquals(1, fallingRock.getChamberRocksHeight());
        assertEquals(List.of(new Point(1,6)), fallingRock.getChamber());
    }

    @Test
    void one_flat_rock_jet_moves_left_should_return_1_and_be_in_the_left_corner() {
        Rock rock = new Rock(List.of("#"));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), "<<<<<<<<<<<<<"));
        fallingRock.dropRocks(1);
        assertEquals(1, fallingRock.getChamberRocksHeight());
        assertEquals(List.of(new Point(1,0)), fallingRock.getChamber());
    }

    @Test
    void jet_pattern_should_be_cyclic() {
        Rock rock = new Rock(List.of("#"));
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, List.of(rock), "<"));
        fallingRock.dropRocks(1);
        assertEquals(1, fallingRock.getChamberRocksHeight());
        assertEquals(List.of(new Point(1,0)), fallingRock.getChamber());
    }

    @Test
    void example_test() {
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, fiveRocks, ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"));
        fallingRock.dropRocks(2022);
        assertEquals(3068, fallingRock.getChamberRocksHeight());
    }

    @Test
    void file_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day17");
        FallingRock fallingRock = new FallingRock(new ChamberEnvironment(7, fiveRocks, inputTxt.get(0)));
        fallingRock.dropRocks(2022);
        System.out.println("Day 17 part 1: " + fallingRock.getChamberRocksHeight());
    }
}
