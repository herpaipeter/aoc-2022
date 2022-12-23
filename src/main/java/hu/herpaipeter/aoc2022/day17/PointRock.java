package hu.herpaipeter.aoc2022.day17;


import hu.herpaipeter.aoc2022.day09.Point;

import java.util.Comparator;
import java.util.List;

public class PointRock {

    private List<Point> points;

    public PointRock(List<Point> points) {
        this.points = points;
    }

    public List<Point> getShape() {
        return points;
    }

    public int getMinRow() {
        return points.stream().min(Comparator.comparingInt(Point::row)).orElse(new Point(0,0)).row();
    }

    public int getMaxRow() {
        return points.stream().max(Comparator.comparingInt(Point::row)).orElse(new Point(0,0)).row();
    }

    public int getMinCol() {
        return points.stream().min(Comparator.comparingInt(Point::col)).orElse(new Point(0,0)).col();
    }

    public int getMaxCol() {
        return points.stream().max(Comparator.comparingInt(Point::col)).orElse(new Point(0,0)).col();
    }

    public PointRock setStartPosition(int row, int col) {
        return new PointRock(points.stream().map(p -> p.add(new Point(row, col))).toList());
    }

    public PointRock moveDown(int row) {
        return new PointRock(points.stream().map(p -> p.add(new Point(-row, 0))).toList());
    }

    public PointRock moveRight(int col) {
        return new PointRock(points.stream().map(p -> p.add(new Point(0, col))).toList());
    }

    public PointRock moveLeft(int col) {
        return new PointRock(points.stream().map(p -> p.add(new Point(0, -col))).toList());
    }
}
