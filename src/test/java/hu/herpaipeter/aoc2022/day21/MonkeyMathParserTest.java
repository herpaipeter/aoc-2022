package hu.herpaipeter.aoc2022.day21;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyMathParserTest {

    @Test
    void empty_list_should_return_empty_statements_list() {
        MonkeyMathParser parser = new MonkeyMathParser();
        assertEquals(List.of(), parser.getStatements(List.of()));
    }

    @Test
    void one_line_literal_should_return_a_list_of_one_statement_contains_a_monkey_literal() {
        MonkeyMathParser parser = new MonkeyMathParser();
        assertEquals(List.of(new MonkeyStatement("a", new MonkeyLiteral(1))), parser.getStatements(List.of("a: 1")));
    }

    @Test
    void one_line_multichar_literal_should_return_a_list_of_one_statement_contains_a_monkey_literal() {
        MonkeyMathParser parser = new MonkeyMathParser();
        assertEquals(List.of(new MonkeyStatement("root", new MonkeyLiteral(1))), parser.getStatements(List.of("root: 1")));
    }

    @Test
    void one_line_operation_string_should_return_a_list_of_one_statement_contains_a_monkey_operation() {
        MonkeyMathParser parser = new MonkeyMathParser();
        assertEquals(List.of(new MonkeyStatement("root", new MonkeyExpOperation("pppw","sjmn","+"))), parser.getStatements(List.of("root: pppw + sjmn")));
    }

    @Test
    void two_line_operation_string_should_return_a_list_of_two_statements() {
        MonkeyMathParser parser = new MonkeyMathParser();
        assertEquals(List.of(new MonkeyStatement("cczh", new MonkeyExpOperation("sllz","lgvd","+")),
                                new MonkeyStatement("zczc", new MonkeyLiteral(2))),
                    parser.getStatements(List.of("cczh: sllz + lgvd", "zczc: 2")));
    }
}
