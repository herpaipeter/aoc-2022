package hu.herpaipeter.aoc2022.day08;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ForrestAnalyzerPart2Test {

    @Test
    void empty_forrest_should_have_scenic_score_0() {
        assertEquals(0, ForrestAnalyzer.getScenicScore(List.of(), 0, 0));
    }

    @Test
    void forrest_1x1_should_have_scenic_score_0() {
        assertEquals(0, ForrestAnalyzer.getScenicScore(List.of("1"), 0, 0));
    }

    @Test
    void forrest_3x3_middle_higher_should_have_scenic_score_1() {
        assertEquals(1, ForrestAnalyzer.getScenicScore(List.of("111", "121", "111"), 1, 1));
    }

    @Test
    void forrest_3x3_side_tree_should_have_scenic_score_0() {
        assertEquals(0, ForrestAnalyzer.getScenicScore(List.of("111", "121", "111"), 2, 2));
    }

    @Test
    void forrest_4x4_middle_one_heigher_should_have_scenic_score_4() {
        assertEquals(4, ForrestAnalyzer.getScenicScore(List.of("1111", "1211", "1111", "1111"), 1, 1));
        assertEquals(4, ForrestAnalyzer.getScenicScore(List.of("1111", "1121", "1111", "1111"), 1, 2));
        assertEquals(4, ForrestAnalyzer.getScenicScore(List.of("1111", "1111", "1211", "1111"), 2, 1));
        assertEquals(4, ForrestAnalyzer.getScenicScore(List.of("1111", "1111", "1121", "1111"), 2, 2));
    }

    @Test
    void forrest_5x5_high_fence_and_middle_should_have_scenic_score_16() {
        assertEquals(16, ForrestAnalyzer.getScenicScore(List.of("22222", "21112", "21212", "21112", "22222"), 2, 2));
    }

    @Test
    void forrest_5x5_sample_test() {
        List<String> map = List.of("30373", "25512", "65332", "33549", "35390");
        assertEquals(8, ForrestAnalyzer.getScenicScore(map, 3, 2));
        int maxTreeScore = IntStream.range(0, map.size()).map(row -> IntStream.range(0, map.get(0).length()).map(col -> ForrestAnalyzer.getScenicScore(map, row, col)).max().orElse(0)).max().getAsInt();
        assertEquals(8, maxTreeScore);
    }

    @Test
    void file_result_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day08");
        int maxTreeScore = IntStream.range(0, inputTxt.size()).map(row -> IntStream.range(0, inputTxt.get(0).length()).map(col -> ForrestAnalyzer.getScenicScore(inputTxt, row, col)).max().orElse(0)).max().getAsInt();
        System.out.println("part2: " + maxTreeScore);
    }

}
