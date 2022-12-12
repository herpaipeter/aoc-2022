package hu.herpaipeter.aoc2022.day12;

import hu.herpaipeter.aoc2022.day11.Monkey;
import hu.herpaipeter.aoc2022.day11.MonkeyKeepAway;
import hu.herpaipeter.aoc2022.day11.MonkeyKeepAwayInputParser;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HillClimbingParserTest {

    @Test
    void empty_string_should_return_empty_graph() {
        HillClimbingParser parser = new HillClimbingParser();
        assertEquals(0, parser.buildGrid(List.of()).Points().size());
    }

    @Test
    void start_string_should_return_one_point() {
        HillClimbingParser parser = new HillClimbingParser();
        List<MapPoint> points = parser.buildGrid(List.of("S")).Points();
        assertEquals(new MapPoint(0,0,'S'), points.get(0));
    }

//    @Test
//    void two_chars_should_return_2_point_and_1_edge() {
//        HillClimbingParser parser = new HillClimbingParser();
//        ElevationGraph graph = parser.buildGrid(List.of("Sa"));
//        MapPoint start = new MapPoint(0, 0, 'S');
//        MapPoint pointA = new MapPoint(0, 0, 'S');
//        assertEquals(2, graph.Points().size());
//        assertEquals(List.of(start, pointA), graph.Points());
//        assertEquals(List.of(new MapEdge(start, pointA)), graph.edges());
//    }

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
