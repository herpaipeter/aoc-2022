package hu.herpaipeter.aoc2022.day21;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MonkeyMathTest {

    @Test
    void empty_list_should_return_0() {
        MonkeyMath monkeyMath = new MonkeyMath();
        assertEquals(0, monkeyMath.calculate(List.of(), "root"));
    }

    @Test
    void literal_expression_should_return_literal_value() {
        MonkeyMath monkeyMath = new MonkeyMath();
        assertEquals(1, monkeyMath.calculate(List.of(new MonkeyStatement("root", new MonkeyLiteral(1))), "root"));
    }

    @Test
    void literals_and_addition_operation_should_return_the_sum_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "+"));
        MonkeyStatement literal1 = new MonkeyStatement("a", new MonkeyLiteral(1));
        MonkeyStatement literal2 = new MonkeyStatement("b", new MonkeyLiteral(2));
        assertEquals(3, monkeyMath.calculate(List.of(root, literal1, literal2), "root"));
    }

    @Test
    void literals_and_multi_operation_should_return_the_product_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "*"));
        MonkeyStatement literal1 = new MonkeyStatement("a", new MonkeyLiteral(2));
        MonkeyStatement literal2 = new MonkeyStatement("b", new MonkeyLiteral(3));
        assertEquals(6, monkeyMath.calculate(List.of(root, literal1, literal2), "root"));
    }

    @Test
    void literals_and_subtraction_operation_should_return_the_difference_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "-"));
        MonkeyStatement literal1 = new MonkeyStatement("a", new MonkeyLiteral(2));
        MonkeyStatement literal2 = new MonkeyStatement("b", new MonkeyLiteral(1));
        assertEquals(1, monkeyMath.calculate(List.of(root, literal1, literal2), "root"));
    }

    @Test
    void literals_and_division_operation_should_return_the_quotient_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "/"));
        MonkeyStatement literal1 = new MonkeyStatement("a", new MonkeyLiteral(4));
        MonkeyStatement literal2 = new MonkeyStatement("b", new MonkeyLiteral(2));
        assertEquals(2, monkeyMath.calculate(List.of(root, literal1, literal2), "root"));
    }

    @Test
    void three_additions_and_four_literals_should_return_the_sum_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "+"));
        MonkeyStatement operation1 = new MonkeyStatement("a", new MonkeyExpOperation("c", "d", "+"));
        MonkeyStatement operation2 = new MonkeyStatement("b", new MonkeyExpOperation("e", "f", "+"));
        MonkeyStatement literal1 = new MonkeyStatement("c", new MonkeyLiteral(1));
        MonkeyStatement literal2 = new MonkeyStatement("d", new MonkeyLiteral(2));
        MonkeyStatement literal3 = new MonkeyStatement("e", new MonkeyLiteral(3));
        MonkeyStatement literal4 = new MonkeyStatement("f", new MonkeyLiteral(4));
        assertEquals(10, monkeyMath.calculate(List.of(root, operation1, operation2, literal1, literal2, literal3, literal4), "root"));
    }

    @Test
    void three_additions_and_four_literals_but_root_not_teh_first_should_return_the_sum_of_literals() {
        MonkeyMath monkeyMath = new MonkeyMath();
        MonkeyStatement root = new MonkeyStatement("root", new MonkeyExpOperation("a", "b", "+"));
        MonkeyStatement operation1 = new MonkeyStatement("a", new MonkeyExpOperation("c", "d", "+"));
        MonkeyStatement operation2 = new MonkeyStatement("b", new MonkeyExpOperation("e", "f", "+"));
        MonkeyStatement literal1 = new MonkeyStatement("c", new MonkeyLiteral(1));
        MonkeyStatement literal2 = new MonkeyStatement("d", new MonkeyLiteral(2));
        MonkeyStatement literal3 = new MonkeyStatement("e", new MonkeyLiteral(3));
        MonkeyStatement literal4 = new MonkeyStatement("f", new MonkeyLiteral(4));
        assertEquals(10, monkeyMath.calculate(List.of(literal1, literal2, literal3, literal4, operation1, operation2, root), "root"));
    }

    @Test
    void example_part_1_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day21", "example.txt");
        List<MonkeyStatement> statements = new MonkeyMathParser().getStatements(inputTxt);
        assertEquals(152, new MonkeyMath().calculate(statements, "root"));
    }

    @Test
    void input_file_part_1_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day21");
        List<MonkeyStatement> statements = new MonkeyMathParser().getStatements(inputTxt);
        System.out.println("Day 21 part 1: " + new MonkeyMath().calculate(statements, "root"));
    }

    @Test
    void example_part_2_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day21", "example.txt");
        List<MonkeyStatement> statements = new ArrayList<>(new MonkeyMathParser().getStatements(inputTxt));
        MonkeyStatement humnStatement = statements.stream().filter(s -> s.getName().equalsIgnoreCase("humn")).findFirst().get();
        MonkeyLiteral humn = (MonkeyLiteral) humnStatement.getExpression();
        int indexOf = statements.indexOf(humnStatement);
        for (long i = humn.getValue() + 299; i < humn.getValue() + 303; i += 1) {
            System.out.println("Example part 2, literal: " + i);
            statements.set(indexOf, new MonkeyStatement("humn", new MonkeyLiteral(i)));
            System.out.println("Example part 2, pppw branch: " + new MonkeyMath().calculate(statements, "pppw"));
            System.out.println("Example part 2, sjmn branch: " + new MonkeyMath().calculate(statements, "sjmn"));
        }
    }

    @Test
    void input_file_part_2_test() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day21");
        List<MonkeyStatement> statements = new ArrayList<>(new MonkeyMathParser().getStatements(inputTxt));
        MonkeyStatement humnStatement = statements.stream().filter(s -> s.getName().equalsIgnoreCase("humn")).findFirst().get();
        MonkeyLiteral humn = (MonkeyLiteral) humnStatement.getExpression();
        int indexOf = statements.indexOf(humnStatement);
        for (long i = humn.getValue() + 3353687995643L; i < humn.getValue() + 3353687995646L; i += 1L) {
            System.out.println("Input file part 2, literal: " + i);
            statements.set(indexOf, new MonkeyStatement("humn", new MonkeyLiteral(i)));
            System.out.println("Input file part 2, vtsj branch: " + new MonkeyMath().calculate(statements, "vtsj"));
            System.out.println("Input file part 2, tfjf branch: " + new MonkeyMath().calculate(statements, "tfjf"));
        }
    }
}

