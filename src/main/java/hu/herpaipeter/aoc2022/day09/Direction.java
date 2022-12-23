package hu.herpaipeter.aoc2022.day09;

public enum Direction {
    Right(new Point(0, 1)),
    Left(new Point(0, -1)),
    Up(new Point(1, 0)),
    Down(new Point(-1, 0));

    private final Point vector;

    Direction(Point vector) {
        this.vector = vector;
    }

    public Point getVector() {
        return vector;
    }

    public Direction getOpposite() {
        Direction result;
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
