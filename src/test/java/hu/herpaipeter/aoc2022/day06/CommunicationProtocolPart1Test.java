package hu.herpaipeter.aoc2022.day06;

import hu.herpaipeter.aoc2022.day05.SupplyStackerParser;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CommunicationProtocolPart1Test {

    CommunicationProtocol protocol;

    @BeforeEach
    void setUp() {
        protocol = new CommunicationProtocol();
    }

    @Test
    void empty_string_should_return_minus_1() {
        assertEquals(-1, protocol.getMarkerStart(""));
    }

    @Test
    void string_length_less_than_4_should_return_minus_1() {
        assertEquals(-1, protocol.getMarkerStart("abc"));
    }

    @Test
    void string_length_4_with_repeated_chars_should_return_minus_1() {
        assertEquals(-1, protocol.getMarkerStart("abca"));
    }

    @Test
    void string_length_4_with_different_chars_should_return_4() {
        assertEquals(4, protocol.getMarkerStart("abcd"));
    }

    @Test
    void string_length_5_with_repeated_chars_in_the_first_4_should_return_5() {
        assertEquals(5, protocol.getMarkerStart("abcad"));
    }

    @Test
    void long_input_test() {
        assertEquals(5, protocol.getMarkerStart("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(6, protocol.getMarkerStart("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(10, protocol.getMarkerStart("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(11, protocol.getMarkerStart("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }

    @Test
    void file_result() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day06");
        System.out.println(protocol.getMarkerStart(inputTxt.get(0)));
    }

}
