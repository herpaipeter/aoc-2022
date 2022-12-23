package hu.herpaipeter.aoc2022.day22;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonkeyMap {

    private List<Point> mapTiles;
    private Point position;
    private List<Point> walls;
    private UDDirection direction = UDDirection.Right;

    public MonkeyMap(List<Point> openTiles, List<Point> walls) {
        position = openTiles.get(0);
        this.walls = walls;
        this.mapTiles = new ArrayList<>(openTiles);
        this.mapTiles.addAll(walls);
    }

    public Point getPosition() {
        return position;
    }

    public void move(int steps) {
        boolean canMove = true;
        for (int i = 0; i < steps && canMove; i++) {
            Point newPosition = getNewPosition();
            if (walls.contains(newPosition))
                canMove = false;
            else
                position = newPosition;
        }
    }

    private Point getNewPosition() {
        Point newPosition = position.add(direction.getVector());
        if (!mapTiles.contains(newPosition))
            newPosition = getNewWrapPosition();
        return newPosition;
    }

    private Point getNewWrapPosition() {
        Point newPosition = null;
        if (direction.equals(UDDirection.Right))
            newPosition = mapTiles.stream().filter(p -> p.row() == position.row()).sorted(Comparator.comparingInt(Point::col)).findFirst().get();
        else if (direction.equals(UDDirection.Left))
            newPosition = mapTiles.stream().filter(p -> p.row() == position.row()).sorted(Comparator.comparingInt(Point::col).reversed()).findFirst().get();
        else if (direction.equals(UDDirection.Down))
            newPosition = mapTiles.stream().filter(p -> p.col() == position.col()).sorted(Comparator.comparingInt(Point::row)).findFirst().get();
        else if (direction.equals(UDDirection.Up))
            newPosition = mapTiles.stream().filter(p -> p.col() == position.col()).sorted(Comparator.comparingInt(Point::row).reversed()).findFirst().get();
        return newPosition;
    }

    public UDDirection getDirection() {
        return direction;
    }

    public void turn(UDDirection turnDirection) {
        if (direction.equals(UDDirection.Right)) {
            direction = turnDirection.equals(UDDirection.Right) ? UDDirection.Down : UDDirection.Up;
        } else if (direction.equals(UDDirection.Up)) {
            direction = turnDirection.equals(UDDirection.Right) ? UDDirection.Right : UDDirection.Left;
        } else if (direction.equals(UDDirection.Left)) {
            direction = turnDirection.equals(UDDirection.Right) ? UDDirection.Up : UDDirection.Down;
        } else if (direction.equals(UDDirection.Down)) {
            direction = turnDirection.equals(UDDirection.Right) ? UDDirection.Left : UDDirection.Right;
        }
    }

    public void runSteps(List<MonkeyStep> steps) {
        steps.forEach(s -> s.doStepOn(this));
    }
}
