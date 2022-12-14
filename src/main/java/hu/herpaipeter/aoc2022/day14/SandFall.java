package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.*;
import java.util.stream.Collectors;

public class SandFall {

    private static final int SAND_INITIAL_COL = 500;
    private static final Point DIRECTION_DOWN = new Point(1, 0);
    private static final Point DIRECTION_LEFT_DIAGONAL = new Point(1, -1);
    private static final Point DIRECTION_RIGHT_DIAGONAL = new Point(1, 1);

    private final Set<Point> rocks;
    private final int bottomRockRow;
    private final int floor;
    private Set<Point> sands = new HashSet<>();

    public SandFall(List<List<Point>> lines) {
        this(lines, false);
    }

    public SandFall(List<List<Point>> lines, boolean hasFloor) {
        this.rocks = getRocks(lines);
        this.bottomRockRow = getMaxRow();
        this.floor = hasFloor ? this.bottomRockRow + 2 : 0;
    }

    private int getMaxRow() {
        return rocks.stream().max(Comparator.comparingInt(Point::row)).orElse(new Point(0,0)).row();
    }

    private Set<Point> getRocks(List<List<Point>> lines) {
        return lines.stream()
                .flatMap(line -> RockMapper.getRockPositions(line).stream())
                .collect(Collectors.toSet());
    }

    public void fallSands(int times) {
        for (int i = 0; i < times; i++) {
            fallASand();
        }
    }

    public void fallSandsUntilNoMore() {
        int prevSandSize = -1;
        while(prevSandSize < sands.size())
        {
            prevSandSize = sands.size();
            fallASand();
        }
    }

    public void fallASand() {
        Point position = new Point(0, SAND_INITIAL_COL);
        int bottom = getBottom();
        for (int i = 0; i < bottom; i++) {
            Point nextPosition = getNextPosition(position);
            if (!nextPosition.equals(position))
                position = nextPosition;
            else {
                sands.add(position);
                break;
            }
        }
    }

    private Point getNextPosition(Point position) {
        Point down = position.add(DIRECTION_DOWN);
        Point leftDiagonal = position.add(DIRECTION_LEFT_DIAGONAL);
        Point rightDiagonal = position.add(DIRECTION_RIGHT_DIAGONAL);
        if (isPositionNotOccupied(down))
            return down;
        else if (isPositionNotOccupied(leftDiagonal))
            return leftDiagonal;
        else if (isPositionNotOccupied(rightDiagonal))
            return rightDiagonal;
        else
            return position;
    }

    private boolean isPositionNotOccupied(Point position) {
        return !rocks.contains(position) && !sands.contains(position) && (floor == 0 || position.row() != floor);
    }

    private int getBottom() {
        return 0 < floor ? floor : bottomRockRow;
    }

    public Set<Point> getSandPositions() {
        return sands;
    }
}
