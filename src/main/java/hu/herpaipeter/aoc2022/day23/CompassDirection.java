package hu.herpaipeter.aoc2022.day23;

import hu.herpaipeter.aoc2022.day09.Point;

public enum CompassDirection {

    North(new Point(1,0)),
    NorthWest(new Point(1,-1)),
    NorthEast(new Point(1,1)),
    South(new Point(-1,0)),
    SouthWest(new Point(-1,-1)),
    SouthEast(new Point(-1,1)),
    West(new Point(0,-1)),
    East(new Point(0,1)),
    ;

    private Point direction;

    CompassDirection(Point direction) {
        this.direction = direction;
    }

    public Point getDirection() {
        return direction;
    }
}
