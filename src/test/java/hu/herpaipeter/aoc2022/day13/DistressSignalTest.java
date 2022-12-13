package hu.herpaipeter.aoc2022.day13;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DistressSignalTest {

    @Test
    void empty_list_signals_should_be_same() {
        ElfSignal left = new ElfSignalList(List.of());
        ElfSignal right = new ElfSignalList(List.of());
        assertEquals(0, left.compare(right));
    }

    @Test
    void right_empty_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalList(List.of())));
        ElfSignal right = new ElfSignalList(List.of());
        assertEquals(1, left.compare(right));
    }

    @Test
    void left_empty_should_be_right() {
        ElfSignal left = new ElfSignalList(List.of());
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalList(List.of())));
        assertEquals(-1, left.compare(right));
    }

    @Test
    void same_integer_element_should_be_same() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(1)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(1)));
        assertEquals(0, left.compare(right));
    }

    @Test
    void left_less_element_should_be_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(0)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(1)));
        assertEquals(-1, left.compare(right));
    }

    @Test
    void right_less_element_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(1)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(0)));
        assertEquals(1, left.compare(right));
    }

    @Test
    void integer_list_same_should_be_same() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(3)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(3)));
        assertEquals(0, left.compare(right));
    }

    @Test
    void first_diff_element_less_should_be_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(3), new ElfSignalInteger(1)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(5), new ElfSignalInteger(1)));
        assertEquals(-1, left.compare(right));
    }

    @Test
    void first_diff_element_more_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(5), new ElfSignalInteger(1)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(1), new ElfSignalInteger(3), new ElfSignalInteger(1)));
        assertEquals(1, left.compare(right));
    }

    @Test
    void compare_different_type_should_be_converted() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(9)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalInteger(8), new ElfSignalInteger(7), new ElfSignalInteger(6)))));
        assertEquals(1, left.compare(right));
    }

    @Test
    void compare_same_but_left_run_out_element_should_be_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4))), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4))), new ElfSignalInteger(4), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        assertEquals(-1, left.compare(right));
    }

    @Test
    void compare_same_but_right_run_out_element_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4))), new ElfSignalInteger(4), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4))), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        assertEquals(1, left.compare(right));
    }

    @Test
    void compare_same_integer_list_but_right_run_out_element_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalInteger(4), new ElfSignalInteger(4), new ElfSignalInteger(4)));
        assertEquals(1, left.compare(right));
    }

    @Test
    void empty_list_right_run_out_should_be_not_right() {
        ElfSignal left = new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalList(List.of())))));
        ElfSignal right = new ElfSignalList(List.of(new ElfSignalList(List.of())));
        assertEquals(1, left.compare(right));
    }

    @Test
    void test_example() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day13", "example.txt");
        ElfSignalList left = new ElfSignalParser().parseElfSignal(inputTxt.get(0));
        ElfSignalList right = new ElfSignalParser().parseElfSignal(inputTxt.get(1));
        assertEquals(-1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(3));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(4));
        assertEquals(-1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(6));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(7));
        assertEquals(1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(9));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(10));
        assertEquals(-1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(12));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(13));
        assertEquals(1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(15));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(16));
        assertEquals(-1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(18));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(19));
        assertEquals(1, left.compare(right));

        left = new ElfSignalParser().parseElfSignal(inputTxt.get(21));
        right = new ElfSignalParser().parseElfSignal(inputTxt.get(22));
        assertEquals(1, left.compare(right));
    }

    @Test
    void file_input_example() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day13", "example.txt");
        ElfSignalParser parser = new ElfSignalParser();
        List<Integer> rightPairs = new ArrayList<>();
        for (int i = 0; i < inputTxt.size(); i += 3) {
            ElfSignalList left = parser.parseElfSignal(inputTxt.get(i));
            ElfSignalList right = parser.parseElfSignal(inputTxt.get(i + 1));
            if (left.compare(right) == -1)
                rightPairs.add(i/3 + 1);
        }
        System.out.println("example: " + rightPairs.stream().mapToInt(Integer::intValue).sum());
    }

    @Test
    void file_input_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day13");
        ElfSignalParser parser = new ElfSignalParser();
        List<Integer> rightPairs = new ArrayList<>();
        for (int i = 0; i < inputTxt.size(); i += 3) {
            ElfSignalList left = parser.parseElfSignal(inputTxt.get(i));
            ElfSignalList right = parser.parseElfSignal(inputTxt.get(i + 1));
            if (left.compare(right) == -1)
                rightPairs.add(i/3 + 1);
        }
        System.out.println("part1: " + rightPairs.stream().mapToInt(Integer::intValue).sum());
    }

    @Test
    void file_input_example_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day13", "example.txt");
        ElfSignalParser parser = new ElfSignalParser();
        inputTxt.addAll(List.of("[[2]]", "[[6]]"));
        List<ElfSignalList> elfSignalLists = inputTxt.stream().filter(s -> !s.isEmpty()).map(parser::parseElfSignal).sorted(ElfSignalList::compare).toList();
        int index2 = elfSignalLists.indexOf(parser.parseElfSignal("[[2]]"));
        int index6 = elfSignalLists.indexOf(parser.parseElfSignal("[[6]]"));
        System.out.println("example part2: " + (index2 + 1) * (index6 + 1));
    }

    @Test
    void file_input_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day13");
        ElfSignalParser parser = new ElfSignalParser();
        inputTxt.addAll(List.of("[[2]]", "[[6]]"));
        List<ElfSignalList> elfSignalLists = inputTxt.stream().filter(s -> !s.isEmpty()).map(parser::parseElfSignal).sorted(ElfSignalList::compare).toList();
        int index2 = elfSignalLists.indexOf(parser.parseElfSignal("[[2]]"));
        int index6 = elfSignalLists.indexOf(parser.parseElfSignal("[[6]]"));
        System.out.println("part2: " + (index2 + 1) * (index6 + 1));
    }
}
