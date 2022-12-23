package hu.herpaipeter.aoc2022.day12;

import hu.herpaipeter.aoc2022.day09.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HillClimbingParserTest {

    @Test
    void example_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12", "example.txt");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        Point start = starAlgorithm.findPosition(inputTxt, 'S');
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        List<List<Integer>> distances = new AStarAlgorithm().getDistances(inputTxt, start, endPoint);
        System.out.println("example: " + distances.get(endPoint.row()).get(endPoint.col()));
    }

    @Test
    void file_input_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        Point start = starAlgorithm.findPosition(inputTxt, 'S');
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        List<List<Integer>> distances = new AStarAlgorithm().getDistances(inputTxt, start, endPoint);
        System.out.println("part1: " + distances.get(endPoint.row()).get(endPoint.col()));
    }

    @Test
    void file_input_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day12");
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        List<Point> starts = starAlgorithm.findAllPosition(inputTxt, 'a');
        Point endPoint = starAlgorithm.findPosition(inputTxt,'E');
        Map<Point, Integer> distancesMapReverse = new AStarAlgorithm().getDistancesMapReverse(inputTxt, endPoint);
        Integer result = distancesMapReverse.entrySet().stream().filter(p -> starts.contains(p.getKey())).map(Map.Entry::getValue).min(Integer::compareTo).get();
        System.out.println("part2: " + result);
    }
}
