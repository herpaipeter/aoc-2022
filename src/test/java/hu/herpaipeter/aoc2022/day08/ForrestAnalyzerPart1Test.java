package hu.herpaipeter.aoc2022.day08;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ForrestAnalyzerPart1Test {

    @Test
    void empty_forrest_should_have_0_visible_tree() {
        assertEquals(0, ForrestAnalyzer.getVisibleTreesCount(List.of()));
    }

    @Test
    void forrest_1x1_should_have_1_visible_tree() {
        assertEquals(1, ForrestAnalyzer.getVisibleTreesCount(List.of("1")));
    }

    @Test
    void forrest_2x2_should_have_4_visible_tree() {
        assertEquals(4, ForrestAnalyzer.getVisibleTreesCount(List.of("11", "11")));
    }

    @Test
    void forrest_3x3_same_heights_should_have_8_visible_tree() {
        assertEquals(8, ForrestAnalyzer.getVisibleTreesCount(List.of("111", "111", "111")));
    }

    @Test
    void forrest_3x3_middle_higher_should_have_9_visible_tree() {
        assertEquals(9, ForrestAnalyzer.getVisibleTreesCount(List.of("111", "121", "111")));
    }

    @Test
    void forrest_4x4_same_heights_should_have_12_visible_tree() {
        assertEquals(12, ForrestAnalyzer.getVisibleTreesCount(List.of("1111", "1111", "1111", "1111")));
    }

    @Test
    void forrest_4x4_middle_one_heigher_should_have_13_visible_tree() {
        assertEquals(13, ForrestAnalyzer.getVisibleTreesCount(List.of("1111", "1211", "1111", "1111")));
        assertEquals(13, ForrestAnalyzer.getVisibleTreesCount(List.of("1111", "1121", "1111", "1111")));
        assertEquals(13, ForrestAnalyzer.getVisibleTreesCount(List.of("1111", "1111", "1211", "1111")));
        assertEquals(13, ForrestAnalyzer.getVisibleTreesCount(List.of("1111", "1111", "1121", "1111")));
    }

    @Test
    void forrest_5x5_middle_one_heigher_should_have_17_visible_tree() {
        assertEquals(17, ForrestAnalyzer.getVisibleTreesCount(List.of("11111", "11111", "11211", "11111", "11111")));
    }

    @Test
    void forrest_5x5_high_fence_and_middle_should_have_16_visible_tree() {
        assertEquals(16, ForrestAnalyzer.getVisibleTreesCount(List.of("22222", "21112", "21212", "21112", "22222")));
    }

    @Test
    void forrest_5x5_sample_test() {
        assertEquals(21, ForrestAnalyzer.getVisibleTreesCount(List.of("30373", "25512", "65332", "33549", "35390")));
    }

    @Test
    void file_result_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day08");
        System.out.println("part1: " + ForrestAnalyzer.getVisibleTreesCount(inputTxt));
    }

}
