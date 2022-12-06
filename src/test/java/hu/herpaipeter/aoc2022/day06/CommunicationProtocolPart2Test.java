package hu.herpaipeter.aoc2022.day06;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CommunicationProtocolPart2Test {

    CommunicationProtocol protocol;

    @BeforeEach
    void setUp() {
        protocol = new CommunicationProtocol();
    }

    @Test
    void empty_string_should_return_minus_1() {
        assertEquals(-1, protocol.getMessageStart(""));
    }

    @Test
    void string_length_less_than_14_should_return_minus_1() {
        assertEquals(-1, protocol.getMessageStart("abcdefghijlkl"));
    }

    @Test
    void string_14_chars_long_with_repeated_chars_should_return_minus_1() {
        assertEquals(-1, protocol.getMessageStart("abcdefghijklma"));
    }

    @Test
    void string_14_chars_long_with_different_chars_should_return_14() {
        assertEquals(14, protocol.getMessageStart("abcdefghijklmn"));
    }

    @Test
    void string_15_chars_long_with_same_chars_in_the_first_14_should_return_15() {
        assertEquals(15, protocol.getMessageStart("abcdefghijklmao"));
    }

    @Test
    void long_input_test() {
        assertEquals(19, protocol.getMessageStart("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
        assertEquals(23, protocol.getMessageStart("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(23, protocol.getMessageStart("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(29, protocol.getMessageStart("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(26, protocol.getMessageStart("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }

    @Test
    void file_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day06");
        System.out.println(protocol.getMessageStart(inputTxt.get(0)));
    }

}
