package hu.herpaipeter.aoc2022.day09;

import java.util.ArrayList;
import java.util.List;

public class RopeMovement {

    private static final int DEFAULT_ROPE_KNOTS_SIZE = 2;
    private static final int HEAD_INDEX = 0;

    private List<Point> knots = new ArrayList<>();
    private List<Point> tailRoute = new ArrayList<>();

    public RopeMovement() {
        for (int i = 0; i < DEFAULT_ROPE_KNOTS_SIZE; i++)
            knots.add(Point.origo());
        tailRoute.add(Point.origo());
    }

    public RopeMovement(Point initialHead, Point initialTail) {
        knots.add(initialHead);
        knots.add(initialTail);
        tailRoute.add(initialTail);
    }

    public RopeMovement(int size) {
        for (int i = 0; i < size; i++)
            knots.add(Point.origo());
        tailRoute.add(Point.origo());
    }

    public Point getHeadPosition() {
        return knots.get(HEAD_INDEX);
    }

    public Point getTailPosition() {
        return knots.get(knots.size() - 1);
    }

    public List<Point> getTailRoute() {
        return tailRoute;
    }

    public void move(Motion motion) {
        for (int i = 0; i < motion.steps(); i++)
            setNewKnotPositions(motion.direction().getVector());
    }

    private void setNewKnotPositions(Point direction) {
        knots.set(HEAD_INDEX, knots.get(HEAD_INDEX).add(direction));
        for (int k = 0; k < knots.size() - 1; k++) {
            if (isKnotsNotTouching(k, k+1)) {
                Point newTailPoint = knots.get(k+1).add(getMotionDirection(knots.get(k), knots.get(k+1)));
                setNewKnotPoint(k+1, newTailPoint);
            }
        }
    }

    private Point getMotionDirection(Point point1, Point point2) {
        return new Point((int) Math.signum(point1.row() - point2.row()), (int) Math.signum(point1.col() - point2.col()));
    }

    private boolean isKnotsNotTouching(int knot1Ind, int knot2Ind) {
        return 1 < knots.get(knot1Ind).distanceTo(knots.get(knot2Ind));
    }

    private void setNewKnotPoint(int knotIndex, Point newPoint) {
        if (!knots.get(knotIndex).equals(newPoint)) {
            knots.set(knotIndex, newPoint);
            if (knotIndex == knots.size() - 1)
                tailRoute.add(newPoint);
        }
    }

    public void moveByCommands(List<String> commands) {
        for (String command : commands)
            move(parseMotion(command));
    }

    private Motion parseMotion(String command) {
        String[] comm = command.split(" ");
        int steps = Integer.parseInt(comm[1]);
        Motion motion = null;
        switch (comm[0]) {
            case "R" -> motion = new Motion(Direction.Right, steps);
            case "L" -> motion = new Motion(Direction.Left, steps);
            case "U" -> motion = new Motion(Direction.Up, steps);
            case "D" -> motion = new Motion(Direction.Down, steps);
        }
        return motion;
    }

    public Point getKnotPosition(int knotIndex) {
        return knots.get(knotIndex);
    }
}
