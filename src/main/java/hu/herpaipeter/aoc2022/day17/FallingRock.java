package hu.herpaipeter.aoc2022.day17;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FallingRock {

    private static final int ROCK_START_OFFSET_ROW = 4;
    private static final int ROCK_START_OFFSET_COL = 2;
    private ChamberEnvironment chamberEnvironment;
    List<PointRock> rockTypes;
    List<Point> chamber = new ArrayList<>();
    long jetPatternIndex = 0;

    public FallingRock(ChamberEnvironment chamberEnvironment) {
        this.chamberEnvironment = chamberEnvironment;
        rockTypes = convertToPointRocks(chamberEnvironment.rockTypes());
    }

    public List<Point> getChamber() {
        return chamber;
    }

    public int getChamberRocksHeight() {
        return chamber.stream().max(Comparator.comparingInt(Point::row)).orElse(new Point(0,0)).row();
    }

    public void dropRocks(int rocks) {
        for (int i = 0; i < rocks; i++) {
            PointRock rock = rockTypes.get(i % rockTypes.size());
            PointRock rockInChamber = rock.setStartPosition(getChamberRocksHeight() + ROCK_START_OFFSET_ROW, ROCK_START_OFFSET_COL);
            moveRock(rockInChamber);
        }
    }

    private List<PointRock> convertToPointRocks(List<Rock> rockTypes) {
        return rockTypes.stream().map(this::convertToPointRock).toList();
    }

    private PointRock convertToPointRock(Rock rock) {
        List<String> shape = rock.getShape();
        List<Point> points = new ArrayList<>();
        for (int i = 0;  i < shape.size(); i++)
            for (int j = 0; j < shape.get(i).length(); j++)
                if (shape.get(i).charAt(j) == '#')
                    points.add(new Point(i, j));
        return new PointRock(points);
    }

    private void moveRock(PointRock rockInChamber) {
        boolean rockCanMove = true;
        while (rockCanMove) {
            rockInChamber = moveSideways(rockInChamber);
            PointRock nextDownPosition = rockInChamber.moveDown(1);
            if(notContact(nextDownPosition)) {
                rockInChamber = nextDownPosition;
            } else {
                mergeRockIntoChamber(rockInChamber);
                rockCanMove = false;
            }
            jetPatternIndex++;
        }
    }

    private PointRock moveSideways(PointRock rockInChamber) {
        if (0 < chamberEnvironment.jetPattern().length()) {
            char jet = getJet();
            PointRock nextSidePosition = jet == '>' ? rockInChamber.moveRight(1) : rockInChamber.moveLeft(1);

            if (notContact(nextSidePosition))
                rockInChamber = nextSidePosition;
        }
        return rockInChamber;
    }

    private char getJet() {
        String jetPattern = chamberEnvironment.jetPattern();
        return jetPattern.charAt((int) (jetPatternIndex % jetPattern.length()));
    }

    private void mergeRockIntoChamber(PointRock rock) {
        chamber.addAll(rock.getShape());
    }

    private boolean notContact(PointRock rock) {
        return rock.getShape().stream().noneMatch(p -> chamber.contains(p)) && rock.getMinRow() > 0 &&
                rock.getMinCol() >= 0 && chamberEnvironment.width() - 1 >= rock.getMaxCol();
    }

}
