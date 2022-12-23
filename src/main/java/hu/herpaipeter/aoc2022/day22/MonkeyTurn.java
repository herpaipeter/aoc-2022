package hu.herpaipeter.aoc2022.day22;

public class MonkeyTurn implements MonkeyStep {

    private UDDirection direction;

    public MonkeyTurn(UDDirection direction) {
        this.direction = direction;
    }

    @Override
    public void doStepOn(MonkeyMap map) {
        map.turn(direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonkeyTurn that = (MonkeyTurn) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return direction.hashCode();
    }
}
