package hu.herpaipeter.aoc2022.day21;

import java.util.List;
import java.util.Optional;

public class MonkeyExpOperation implements MonkeyExpression {

    private String variable1;
    private String variable2;
    private String operation;

    public MonkeyExpOperation(String variable1, String variable2, String operation) {
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.operation = operation;
    }

    @Override
    public long evaluate(List<MonkeyStatement> statements) {
        Optional<MonkeyStatement> first = statements.stream().filter(s -> s.getName().equals(variable1)).findFirst();
        Optional<MonkeyStatement> second = statements.stream().filter(s -> s.getName().equals(variable2)).findFirst();
        if (first.isPresent() && second.isPresent()) {
            switch (operation) {
                case "+":
                    return first.get().getExpression().evaluate(statements) + second.get().getExpression().evaluate(statements);
                case "*":
                    return first.get().getExpression().evaluate(statements) * second.get().getExpression().evaluate(statements);
                case "-":
                    return first.get().getExpression().evaluate(statements) - second.get().getExpression().evaluate(statements);
                case "/":
                    return first.get().getExpression().evaluate(statements) / second.get().getExpression().evaluate(statements);
            }
        }
        return 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonkeyExpOperation that = (MonkeyExpOperation) o;

        if (!variable1.equals(that.variable1)) return false;
        if (!variable2.equals(that.variable2)) return false;
        return operation.equals(that.operation);
    }

    @Override
    public int hashCode() {
        int result = variable1.hashCode();
        result = 31 * result + variable2.hashCode();
        result = 31 * result + operation.hashCode();
        return result;
    }
}
