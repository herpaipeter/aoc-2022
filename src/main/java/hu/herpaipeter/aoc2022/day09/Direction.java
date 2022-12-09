package hu.herpaipeter.aoc2022.day09;

public enum Direction {
    Right(new ElfPoint(0, 1)),
    Left(new ElfPoint(0, -1)),
    Up(new ElfPoint(1, 0)),
    Down(new ElfPoint(-1, 0));

    private final ElfPoint vector;

    Direction(ElfPoint vector) {
        this.vector = vector;
    }

    public ElfPoint getVector() {
        return vector;
    }
}
