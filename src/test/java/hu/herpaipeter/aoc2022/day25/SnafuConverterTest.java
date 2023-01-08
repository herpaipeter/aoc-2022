package hu.herpaipeter.aoc2022.day25;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SnafuConverterTest {

    @Test
    void snafu_zero_should_be_zero() {
        assertEquals(0, SnafuConverter.toDecimal("0"));
    }

    @Test
    void snafu_one_should_be_one() {
        assertEquals(1, SnafuConverter.toDecimal("1"));
    }

    @Test
    void snafu_two_should_be_two() {
        assertEquals(2, SnafuConverter.toDecimal("2"));
    }

    @Test
    void snafu_eq_should_be_minus_two() {
        assertEquals(-2, SnafuConverter.toDecimal("="));
    }

    @Test
    void snafu_minus_should_be_minus_one() {
        assertEquals(-1, SnafuConverter.toDecimal("-"));
    }

    @Test
    void snafu_1eq_should_be_3() {
        assertEquals(3, SnafuConverter.toDecimal("1="));
    }

    @Test
    void snafu_1minus_should_be_4() {
        assertEquals(4, SnafuConverter.toDecimal("1-"));
    }

    @Test
    void snafu_10_should_be_5() {
        assertEquals(5, SnafuConverter.toDecimal("10"));
    }

    @Test
    void snafu_11_should_be_6() {
        assertEquals(6, SnafuConverter.toDecimal("11"));
    }

    @Test
    void snafu_12_should_be_7() {
        assertEquals(6, SnafuConverter.toDecimal("11"));
    }

    @Test
    void snafu_2eq_should_be_8() {
        assertEquals(8, SnafuConverter.toDecimal("2="));
    }

    @Test
    void snafu_100_should_be_25() {
        assertEquals(25, SnafuConverter.toDecimal("100"));
    }

    @Test
    void example_test() {
        assertEquals(1747, SnafuConverter.toDecimal("1=-0-2"));
        assertEquals(906, SnafuConverter.toDecimal("12111"));
        assertEquals(198, SnafuConverter.toDecimal("2=0="));
        assertEquals(11, SnafuConverter.toDecimal("21"));
        assertEquals(201, SnafuConverter.toDecimal("2=01"));
        assertEquals(31, SnafuConverter.toDecimal("111"));
        assertEquals(1257, SnafuConverter.toDecimal("20012"));
        assertEquals(32, SnafuConverter.toDecimal("112"));
        assertEquals(353, SnafuConverter.toDecimal("1=-1="));
        assertEquals(107, SnafuConverter.toDecimal("1-12"));
        assertEquals(7, SnafuConverter.toDecimal("12"));
        assertEquals(3, SnafuConverter.toDecimal("1="));
        assertEquals(37, SnafuConverter.toDecimal("122"));
    }

    @Test
    void dec_zero_should_be_snafu_zero() {
        assertEquals("0", SnafuConverter.toSnafu(0));
    }

    @Test
    void dec_one_should_be_snafu_one() {
        assertEquals("1", SnafuConverter.toSnafu(1));
    }

    @Test
    void dec_two_should_be_snafu_two() {
        assertEquals("2", SnafuConverter.toSnafu(2));
    }

    @Test
    void dec_3_should_be_snafu_1eq() {
        assertEquals("1=", SnafuConverter.toSnafu(3));
    }

    @Test
    void dec_4_should_be_snafu_1minus() {
        assertEquals("1-", SnafuConverter.toSnafu(4));
    }

    @Test
    void dec_5_should_be_snafu_10() {
        assertEquals("10", SnafuConverter.toSnafu(5));
    }

    @Test
    void dec_6_should_be_snafu_11() {
        assertEquals("11", SnafuConverter.toSnafu(6));
    }

    @Test
    void dec_7_should_be_snafu_12() {
        assertEquals("12", SnafuConverter.toSnafu(7));
    }

    @Test
    void dec_8_should_be_snafu_2eq() {
        assertEquals("2=", SnafuConverter.toSnafu(8));
    }

    @Test
    void dec_9_should_be_snafu_2minus() {
        assertEquals("2-", SnafuConverter.toSnafu(9));
    }

    @Test
    void dec_10_11_12_should_be_snafu_20_21_22() {
        assertEquals("20", SnafuConverter.toSnafu(10));
        assertEquals("21", SnafuConverter.toSnafu(11));
        assertEquals("22", SnafuConverter.toSnafu(12));
    }

    @Test
    void dec_13_should_be_snafu_1eqeq() {
        assertEquals("1==", SnafuConverter.toSnafu(13));
    }

    @Test
    void decimal_to_snafu_example_test() {
        assertEquals("1=0", SnafuConverter.toSnafu(15));
        assertEquals("1-0", SnafuConverter.toSnafu(20));
        assertEquals("1=11-2", SnafuConverter.toSnafu(2022));
        assertEquals("1-0---0", SnafuConverter.toSnafu(12345));
        assertEquals("1121-1110-1=0", SnafuConverter.toSnafu(314159265));
    }

    @Test
    void example_sum_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day25", "example.txt");
        long sum = inputTxt.stream().map(SnafuConverter::toDecimal).mapToLong(Long::longValue).sum();
        assertEquals(4890L, sum);
        assertEquals("2=-1=0", SnafuConverter.toSnafu(sum));
    }

    @Test
    void input_file_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day25");
        long sum = inputTxt.stream().map(SnafuConverter::toDecimal).mapToLong(Long::longValue).sum();
        System.out.println("Day 25 part 1 sum: " + sum);
        System.out.println("Day 25 part 1 SNAFU: " + SnafuConverter.toSnafu(sum));
    }
}
