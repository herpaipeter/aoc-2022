package hu.herpaipeter.aoc2022.day22;

public class MonkeyMove implements MonkeyStep {

    private int steps;

    public MonkeyMove(int steps) {
        this.steps = steps;
    }

    @Override
    public void doStepOn(MonkeyMap map) {
        map.move(steps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonkeyMove that = (MonkeyMove) o;

        return steps == that.steps;
    }

    @Override
    public int hashCode() {
        return steps;
    }

}
