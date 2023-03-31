package hu.herpaipeter.aoc2022.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringValidatorTest {

    @Test
    void empty_string_should_be_unique() {
        assertTrue(StringValidator.containsUniqueChars(""));
    }

    @Test
    void one_char_should_be_unique() {
        assertTrue(StringValidator.containsUniqueChars("a"));
    }

    @Test
    void two_same_chars_should_not_be_unique() {
        assertFalse(StringValidator.containsUniqueChars("aa"));
    }

    @Test
    void two_different_chars_should_be_unique() {
        assertTrue(StringValidator.containsUniqueChars("ab"));
    }

    @Test
    void aba_should_not_be_unique() {
        assertFalse(StringValidator.containsUniqueChars("aba"));
    }

    @Test
    void abb_should_not_be_unique() {
        assertFalse(StringValidator.containsUniqueChars("abb"));
    }

    @Test
    void three_different_chars_should_be_unique() {
        assertTrue(StringValidator.containsUniqueChars("abc"));
    }
}
