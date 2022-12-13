package hu.herpaipeter.aoc2022.day13;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ElfSignalParserTest {

    @Test
    void empty_string_should_return_null() {
        ElfSignalParser parser = new ElfSignalParser();
        assertNull(parser.parseElfSignalList(""));
    }

    @Test
    void empty_brackets_string_should_return_empty_signal() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of()), parser.parseElfSignalList("[]"));
    }

    @Test
    void one_element_string_should_return_list_with_one_integer() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalInteger(1))), parser.parseElfSignalList("[1]"));
    }

    @Test
    void two_elements_string_should_return_list_with_two_integers() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalInteger(2))), parser.parseElfSignalList("[1,2]"));
    }

    @Test
    void list_of_list_string_should_parse_into_embedded_list() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalList(List.of()))), parser.parseElfSignalList("[[]]"));
    }

    @Test
    void list_of_integer_and_list_string() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalInteger(1), new ElfSignalList(List.of()))), parser.parseElfSignalList("[1,[]]"));
    }

    @Test
    void list_of_list_and_integer_string() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalList(List.of()), new ElfSignalInteger(1))), parser.parseElfSignalList("[[],1]"));
    }

    @Test
    void list_of_lists_string_should_parse_into_embedded_lists() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalList(List.of()), new ElfSignalList(List.of()))), parser.parseElfSignalList("[[],[]]"));
    }

    @Test
    void three_depth_list_embedding() {
        ElfSignalParser parser = new ElfSignalParser();
        assertEquals(new ElfSignalList(List.of(new ElfSignalList(List.of(new ElfSignalList(List.of()))), new ElfSignalList(List.of()))), parser.parseElfSignalList("[[[]],[]]"));
    }

    @Test
    void complex_example() {
        ElfSignalParser parser = new ElfSignalParser();
        ElfSignalList elfSignalList = parser.parseElfSignalList("[1,[2,[3,[4,[5,6,7]]]],8,9]");
        assertEquals("[1,[2,[3,[4,[5,6,7]]]],8,9]", elfSignalList.toString());
    }
}
