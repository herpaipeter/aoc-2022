package hu.herpaipeter.aoc2022.day23;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.List;

import static hu.herpaipeter.aoc2022.day23.CompassDirection.*;

public class ElfMovement {
    private List<CompassDirection> tryMoveOrder = List.of(North, South, West, East);
    private List<Point> elves;
    private boolean moved = true;

    public ElfMovement(List<Point> elves) {
        this.elves = elves;
    }

    public boolean isMoved() {
        return moved;
    }

    public int getNoMovesRounds() {
        if (elves.isEmpty())
            return 0;
        int times = 0;
        //boolean changed = true;
        List<Point> startPositions = new ArrayList<>(elves);
        while (isMoved()) {
            startPositions = filterSameNewPositions(startPositions, getNewPositions(startPositions, times));
            times++;
        }
        return times;
    }

    public List<Point> moveAll(int times) {
        if (elves.isEmpty())
            return List.of();
        List<Point> startPositions = new ArrayList<>(elves);
        for (int i = 0; i < times; i++)
            startPositions = filterSameNewPositions(startPositions, getNewPositions(startPositions, i));
        return startPositions;
    }

    private List<Point> filterSameNewPositions(List<Point> startPositions, List<Point> newPositions) {
        List<Point> stepPositions = new ArrayList<>();
        for (int i = 0; i < newPositions.size(); i++) {
            Point newPosition = newPositions.get(i);
            if (1 < newPositions.stream().filter(p -> p.equals(newPosition)).count())
                stepPositions.add(startPositions.get(i));
            else
                stepPositions.add(newPositions.get(i));
        }
        return stepPositions;
    }

    private List<Point> getNewPositions(List<Point> startPositions, int firstTryMove) {
        List<Point> newPositions = new ArrayList<>();
        int noMoves = 0;
        for (Point elf : startPositions) {
            if (hasNeighbour(startPositions, elf)) {
                int i = 0;
                for (; i < tryMoveOrder.size(); i++) {
                    CompassDirection direction = tryMoveOrder.get((i + firstTryMove) % tryMoveOrder.size());
                    if (canMoveTo(startPositions, elf, direction)) {
                        newPositions.add(elf.add(direction.getDirection()));
                        break;
                    }
                }
                if (i == tryMoveOrder.size()) {
                    newPositions.add(elf);
                    noMoves++;
                }
            } else {
                newPositions.add(elf);
                noMoves++;
            }
        }
        moved = noMoves != newPositions.size();
        return newPositions;
    }

    private boolean canMoveTo(List<Point> startPositions, Point elf, CompassDirection direction) {
        switch (direction) {
            case North -> {
                return canMoveToNorth(startPositions, elf);
            }
            case South -> {
                return canMoveToSouth(startPositions, elf);
            }
            case West -> {
                return canMoveToWest(startPositions, elf);
            }
            case East -> {
                return canMoveToEast(startPositions, elf);
            }
        }
        return false;
    }

    private boolean hasNeighbour(List<Point> startPositions, Point elf) {
        return startPositions.contains(elf.add(North.getDirection()))
                || startPositions.contains(elf.add(NorthEast.getDirection()))
                || startPositions.contains(elf.add(NorthWest.getDirection()))
                || startPositions.contains(elf.add(West.getDirection()))
                || startPositions.contains(elf.add(East.getDirection()))
                || startPositions.contains(elf.add(South.getDirection()))
                || startPositions.contains(elf.add(SouthWest.getDirection()))
                || startPositions.contains(elf.add(SouthEast.getDirection()));
    }

    private boolean canMoveToEast(List<Point> startPositions, Point position) {
        return !startPositions.contains(position.add(East.getDirection()))
                && !startPositions.contains(position.add(NorthEast.getDirection()))
                && !startPositions.contains(position.add(SouthEast.getDirection()));
    }

    private boolean canMoveToWest(List<Point> startPositions, Point position) {
        return !startPositions.contains(position.add(West.getDirection()))
                && !startPositions.contains(position.add(NorthWest.getDirection()))
                && !startPositions.contains(position.add(SouthWest.getDirection()));
    }

    private boolean canMoveToSouth(List<Point> startPositions, Point position) {
        return !startPositions.contains(position.add(South.getDirection()))
                && !startPositions.contains(position.add(SouthWest.getDirection()))
                && !startPositions.contains(position.add(SouthEast.getDirection()));
    }

    private boolean canMoveToNorth(List<Point> startPositions, Point position) {
        return !startPositions.contains(position.add(North.getDirection()))
            && !startPositions.contains(position.add(NorthEast.getDirection()))
            && !startPositions.contains(position.add(NorthWest.getDirection()));
    }
}
