package hu.herpaipeter.aoc2022.day22;

import hu.herpaipeter.aoc2022.day09.Point;

public enum UDDirection {
    Right(new Point(0, 1)),
    Down(new Point(1, 0)),
    Left(new Point(0, -1)),
    Up(new Point(-1, 0));

    private final Point vector;

    UDDirection(Point vector) {
            this.vector = vector;
        }

    public Point getVector() {
        return vector;
    }

    public UDDirection getOpposite() {
        UDDirection result;
        switch (this) {
            case Right -> result = Left;
            case Left -> result = Right;
            case Up -> result = Down;
            case Down -> result = Up;
            default -> result = null;
        }
        return result;
    }
}
