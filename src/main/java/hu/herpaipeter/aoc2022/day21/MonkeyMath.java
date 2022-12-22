package hu.herpaipeter.aoc2022.day21;

import java.util.List;

public class MonkeyMath {
    public long calculate(List<MonkeyStatement> statements, String rootStatementName) {
        MonkeyStatement root = statements.stream()
                .filter(s -> s.getName().equalsIgnoreCase(rootStatementName))
                .findFirst().orElse(new MonkeyStatement("", new MonkeyLiteral(0L)));
        return root.getExpression().evaluate(statements);
    }
}
