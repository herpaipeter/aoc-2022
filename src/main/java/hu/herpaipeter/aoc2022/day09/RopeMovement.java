package hu.herpaipeter.aoc2022.day09;

import java.util.ArrayList;
import java.util.List;

public class RopeMovement {

    private List<ElfPoint> knots = new ArrayList<>();
    private List<ElfPoint> tailRoute = new ArrayList<>();

    public RopeMovement() {
        for (int i = 0; i < 2; i++)
            knots.add(ElfPoint.origo());
        tailRoute.add(ElfPoint.origo());
    }

    public RopeMovement(ElfPoint initialHead, ElfPoint initialTail) {
        knots.add(initialHead);
        knots.add(initialTail);
        tailRoute.add(initialTail);
    }

    public RopeMovement(int size) {
        for (int i = 0; i < size; i++)
            knots.add(ElfPoint.origo());
        tailRoute.add(ElfPoint.origo());
    }

    public ElfPoint getHeadPosition() {
        return knots.get(0);
    }

    public ElfPoint getTailPosition() {
        return knots.get(knots.size() - 1);
    }

    public List<ElfPoint> getTailRoute() {
        return tailRoute;
    }

    public void move(Motion motion) {
        for (int i = 0; i < motion.steps(); i++) {
            knots.set(0, knots.get(0).add(motion.direction().getVector()));
            for (int k = 0; k < knots.size() - 1; k++) {
                if (isKnotsNotTouching(k, k+1)) {
                    ElfPoint newTailPoint = knots.get(k+1).add(new ElfPoint((int) Math.signum(knots.get(k).row() - knots.get(k+1).row()), (int) Math.signum(knots.get(k).col() - knots.get(k+1).col())));
                    setNewKnotPoint(k+1, newTailPoint);
                }
            }
        }
    }

    private boolean isKnotsNotTouching(int knot1Ind, int knot2Ind) {
        return 1 < knots.get(knot1Ind).distance(knots.get(knot2Ind));
    }

    private void setNewKnotPoint(int knotIndex, ElfPoint newPoint) {
        if (!knots.get(knotIndex).equals(newPoint)) {
            knots.set(knotIndex, newPoint);
            if (knotIndex == knots.size() - 1)
                tailRoute.add(newPoint);
        }
    }

    private boolean isHeadAndTailNotTouching() {
        return 1 < getHeadPosition().distance(getTailPosition());
    }

    public void moveByCommands(List<String> commands) {
        for (String command : commands) {
            String[] comm = command.split(" ");
            switch (comm[0]) {
                case "R" -> move(new Motion(Direction.Right, Integer.parseInt(comm[1])));
                case "L" -> move(new Motion(Direction.Left, Integer.parseInt(comm[1])));
                case "U" -> move(new Motion(Direction.Up, Integer.parseInt(comm[1])));
                case "D" -> move(new Motion(Direction.Down, Integer.parseInt(comm[1])));
            }
        }
    }

    public ElfPoint getKnotPosition(int knotIndex) {
        return knots.get(knotIndex);
    }
}
