package hu.herpaipeter.aoc2022.day21;

import java.util.List;

public class MonkeyLiteral implements MonkeyExpression {
    private long value;

    public MonkeyLiteral(long value) {
        this.value = value;
    }

    @Override
    public long evaluate(List<MonkeyStatement> statements) {
        return value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonkeyLiteral that = (MonkeyLiteral) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(value);
    }
}
