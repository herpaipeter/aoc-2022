package hu.herpaipeter.aoc2022.day12;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HillClimbingParserTest {

    @Test
    void example_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12", "example.txt");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        Point start = starAlgorithm.findPosition(inputTxt, 'S');
        List<List<Integer>> distances = new AStarAlgorithm().getDistances(inputTxt, start);
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        System.out.println("example: " + distances.get(endPoint.row()).get(endPoint.col()));
    }

    @Test
    void file_input_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        Point start = starAlgorithm.findPosition(inputTxt, 'S');
        List<List<Integer>> distances = new AStarAlgorithm().getDistances(inputTxt, start);
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        System.out.println("part1: " + distances.get(endPoint.row()).get(endPoint.col()));
    }

    @Test
    void file_input_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        List<Point> starts = starAlgorithm.findAllPosition(inputTxt, 'a');
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        Integer result = starts.stream().map(s -> new AStarAlgorithm().getDistances(inputTxt, s)).map(d -> d.get(endPoint.row()).get(endPoint.col())).min(Integer::compareTo).get();
        System.out.println("part2: " + result);
    }
}
