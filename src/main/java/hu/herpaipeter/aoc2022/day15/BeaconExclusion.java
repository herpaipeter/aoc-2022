package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.List;
import java.util.stream.Collectors;

public class BeaconExclusion {

    public long getNoOfBeaconFreePointsInRow(List<Sensor> sensors, int row) {
        List<ElfSegment> elfSegments = getElfSegments(sensors, row);
        int size = sensors.stream().map(Sensor::beacon).filter(b -> b.row() == row).collect(Collectors.toSet()).size();
        return elfSegments.stream().mapToLong(ElfSegment::length).sum() - size;
    }

    private List<ElfSegment> getElfSegments(List<Sensor> sensors, int row) {
        List<ElfSegment> segments = sensors.stream().map(s -> s.intersectRow(row)).toList();
        List<ElfSegment> filteredOutEmpty = segments.stream().filter(s -> !s.equals(ElfSegment.empty())).toList();
        return ElfSegment.unionHorizontal(filteredOutEmpty);
    }

    public Point getNoBeaconPosition(List<Sensor> sensors, int from, int to) {
        for (Sensor sensor : sensors) {
            List<Point> outerPerimeter = sensor.getOuterPerimeter(from, to);
            for (Point point : outerPerimeter) {
                boolean isInside = sensors.stream().anyMatch(other -> (other != sensor && other.isInside(point)));
                if (!isInside)
                    return point;
            }
        }
        return new Point(0,0);
    }

}
