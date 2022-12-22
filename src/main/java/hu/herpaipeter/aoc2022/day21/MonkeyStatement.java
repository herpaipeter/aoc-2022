package hu.herpaipeter.aoc2022.day21;

public class MonkeyStatement {

    private String name;
    private MonkeyExpression monkeyExpression;

    public MonkeyStatement(String name, MonkeyExpression monkeyExpression) {
        this.name = name;
        this.monkeyExpression = monkeyExpression;
    }

    public String getName() {
        return name;
    }

    public MonkeyExpression getExpression() {
        return monkeyExpression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonkeyStatement statement = (MonkeyStatement) o;

        if (!name.equals(statement.name)) return false;
        return monkeyExpression.equals(statement.monkeyExpression);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + monkeyExpression.hashCode();
        return result;
    }
}
