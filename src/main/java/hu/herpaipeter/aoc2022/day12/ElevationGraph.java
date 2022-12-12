package hu.herpaipeter.aoc2022.day12;

import java.util.List;

public class ElevationGraph {

    private List<MapPoint> points;

    public ElevationGraph(List<MapPoint> points) {
        this.points = points;
    }

    public List<MapPoint> Points() {
        return points;
    }

    public List<MapEdge> edges() {
        return List.of();
    }
}
