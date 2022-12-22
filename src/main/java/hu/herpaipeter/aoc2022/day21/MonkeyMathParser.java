package hu.herpaipeter.aoc2022.day21;

import java.util.List;

import static hu.herpaipeter.aoc2022.common.ParserUtils.findMatch;

public class MonkeyMathParser {

    private static final String MONKEY_NAME_PATTERN = "[a-z]*:";
    private static final String LITERAL_PATTERN = "\\b[0-9]+\\b";
    private static final String OPERATION_PATTERN = "\\b[a-z]+ [+\\-\\*/] [a-z]+\\b";

    public List<MonkeyStatement> getStatements(List<String> statementLines) {
        return statementLines.stream().map(this::getStatement).toList();
    }

    private MonkeyStatement getStatement(String statementLine) {
        String statementName = getStatementName(statementLine);
        String literal = findMatch(statementLine, LITERAL_PATTERN);
        String[] operationParts = getOperation(statementLine);
        if (!literal.isEmpty())
            return new MonkeyStatement(statementName, new MonkeyLiteral(Long.parseLong(literal)));
        else
            return new MonkeyStatement(statementName, new MonkeyExpOperation(operationParts[0], operationParts[2], operationParts[1]));
    }

    private String[] getOperation(String statementLine) {
        String operationText = findMatch(statementLine, OPERATION_PATTERN);
        return operationText.split(" ");
    }

    private String getStatementName(String statementLine) {
        String statementName = findMatch(statementLine, MONKEY_NAME_PATTERN);
        statementName = statementName.substring(0, statementName.length() - 1);
        return statementName;
    }
}
