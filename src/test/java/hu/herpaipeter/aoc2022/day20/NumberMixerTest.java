package hu.herpaipeter.aoc2022.day20;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NumberMixerTest {

    @Test
    void empty_list_should_return_empty_mix() {
        NumberMixer numberMixer = new NumberMixer(List.of());
        assertEquals(List.of(), numberMixer.mix());
    }

    @Test
    void one_0_list_should_return_list_of_0() {
        NumberMixer numberMixer = new NumberMixer(List.of(0));
        assertEquals(List.of(0L), numberMixer.mix());
    }

    @Test
    void two_element_list_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(0,1));
        assertEquals(List.of(0L,1L), numberMixer.mix());
    }

    @Test
    void three_element_list_order_012_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(0,1,2));
        /*
         * 0 1 2
         * 0 2 1
         * 0 1 2  0 2 1*/
        assertEquals(List.of(0L,2L,1L), numberMixer.mix());
    }

    @Test
    void three_element_list_order_021_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(0,2,1));
        /*
        * 0 2 1
        * 0 1 2   0 2 1
        * 0 1 2*/
        assertEquals(List.of(0L,1L,2L), numberMixer.mix());
    }

    @Test
    void three_element_list_order_201_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(2,0,1));
        /*
        * 2 0 1
        * 0 2 1   0 1 2
        * 0 1 2
        * 0 2 1*/
        assertEquals(List.of(0L,2L,1L), numberMixer.mix());
    }

    @Test
    void negative_number_element_list_order_01minus2_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(0,1,-2));
        /*
         * 0 1 -2
         * 0 -2 1
         * 0 1 -2  0 -2 1*/
        assertEquals(List.of(0L,-2L,1L), numberMixer.mix());
    }

    @Test
    void negative_number_element_list_order_0minus21_should_return_right_order_list() {
        NumberMixer numberMixer = new NumberMixer(List.of(0,-2,1));
        /*
         * 0 -2 1
         * 0 1 -2  0 -2 1
         * 0 1 -2 */
        assertEquals(List.of(0L,1L,-2L), numberMixer.mix());
    }

    @Test
    void simple_example_test() {
        NumberMixer numberMixer = new NumberMixer(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(List.of(1L, 2L, -3L, 4L, 0L, 3L, -2L), numberMixer.mix());
    }

    @Test
    void simple_example_multi_1_test() {
        NumberMixer numberMixer = new NumberMixer(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(List.of(0L, -2434767459L, 3246356612L, -1623178306L, 2434767459L, 1623178306L, 811589153L), numberMixer.multiMix(811589153, 1));
    }

    @Test
    void simple_example_multi_2_test() {
        NumberMixer numberMixer = new NumberMixer(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(List.of(0L, 2434767459L, 1623178306L, 3246356612L, -2434767459L, -1623178306L, 811589153L), numberMixer.multiMix(811589153, 2));
    }

    @Test
    void simple_example_multi_10_test() {
        NumberMixer numberMixer = new NumberMixer(List.of(1, 2, -3, 3, -2, 0, 4));
        assertEquals(List.of(0L, -2434767459L, 1623178306L, 3246356612L, -1623178306L, 2434767459L, 811589153L), numberMixer.multiMix(811589153, 10));
    }
}
