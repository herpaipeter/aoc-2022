package hu.herpaipeter.aoc2022.day14;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.*;
import java.util.stream.Collectors;

public class SandFall {

    private static final int SAND_INITIAL_COL = 500;

    private final Set<Point> rocks;
    private final int bottomRockRow;
    private final int floor;
    private Set<Point> sands = new HashSet<>();

    public SandFall(List<List<Point>> lines) {
        this(lines, false);
    }

    public SandFall(List<List<Point>> lines, boolean hasFloor) {
        this.rocks = getRocks(lines);
        this.bottomRockRow = rocks.stream().max(Comparator.comparingInt(Point::row)).orElse(new Point(0,0)).row();
        this.floor = hasFloor ? this.bottomRockRow + 2 : 0;
    }

    private Set<Point> getRocks(List<List<Point>> lines) {
        return lines.stream()
                .flatMap(line -> new RockMapper().map(line).stream())
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
        int bottom = 0 < floor ? floor : bottomRockRow;
        for (int i = 0; i < bottom; i++) {
            Point down = position.add(new Point(1, 0));
            Point leftDiagonal = position.add(new Point(1, -1));
            Point rightDiagonal = position.add(new Point(1, 1));
            if (!rocks.contains(down) && !sands.contains(down)) {
                position = down;
            } else if (!rocks.contains(leftDiagonal) && !sands.contains(leftDiagonal)) {
                position = leftDiagonal;
            } else if (!rocks.contains(rightDiagonal) && !sands.contains(rightDiagonal)) {
                position = rightDiagonal;
            } else {
                sands.add(position);
                break;
            }
            if (0 < floor && i == floor - 1) {
                sands.add(position.add(new Point(-1,0)));
            }
        }
    }

    public Set<Point> getSandPositions() {
        return sands;
    }
}
