package hu.herpaipeter.aoc2022.day12;

import java.util.List;

public class HillClimbingParser {
    public ElevationGraph buildGrid(List<String> lines) {
        MapPoint mapPoint = null;
        MapPoint mapPoint2 = null;
        if (!lines.isEmpty()) {
            if (1 == lines.get(0).length())
                mapPoint = new MapPoint(0, 0, lines.get(0).charAt(0));
            if (2 == lines.get(0).length()) {
                mapPoint = new MapPoint(0, 0, lines.get(0).charAt(0));
                mapPoint2 = new MapPoint(0, 1, lines.get(0).charAt(1));
                MapEdge mapEdge = new MapEdge(mapPoint, mapPoint2);
            }
        }
        return new ElevationGraph(mapPoint != null ? List.of(mapPoint) : List.of());
    }
}
