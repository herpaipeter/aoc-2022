package hu.herpaipeter.aoc2022.day12;

import java.util.*;
import java.util.stream.IntStream;

public class AStarAlgorithm {

    List<List<Integer>> getDistances(List<String> map, Point start) {
        Set<Point> paths = new HashSet<>();
        Set<Point> visited = new HashSet<>();
        List<List<Integer>> distances = new ArrayList<>();
        IntStream.range(0,map.size()).forEach(i -> {
            distances.add(new ArrayList<>());
            IntStream.range(0, map.get(i).length()).forEach(j -> distances.get(i).add(Integer.MAX_VALUE));
        });
        distances.get(start.row()).set(start.col(), 0);
        paths.add(start);
        while (!paths.isEmpty()) {
            Point actual = paths.stream().min(Comparator.comparingInt(p -> distances.get(p.row()).get(p.col()))).get();
            paths.remove(actual);
            visited.add(actual);
            List.of(new Point(-1,0), new Point(1,0), new Point(0, -1), new Point(0,1))
                    .forEach(p -> {
                        Point next = actual.add(p);
                        if (isValidMapPoint(map, next) && isEdgeBetween(map, actual, next) && !visited.contains(next) &&
                                distances.get(actual.row()).get(actual.col()) + 1 < distances.get(next.row()).get(next.col()))
                            {
                                distances.get(next.row()).set(next.col(), distances.get(actual.row()).get(actual.col()) + 1);
                                paths.add(next);
                            }
                    });
        }
        return distances;
    }

    public Point findPosition(List<String> map, char poiChar) {
        Point poi = null;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); ++j) {
                if (map.get(i).charAt(j) == poiChar) {
                    poi = new Point(i, j);
                }
            }
        }
        return poi;
    }

    public List<Point> findAllPosition(List<String> map, char poiChar) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); ++j) {
                if (map.get(i).charAt(j) == poiChar) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

    private boolean isEdgeBetween(List<String> map, Point point1, Point point2) {
        char char1 = map.get(point1.row()).charAt(point1.col());
        char char2 = map.get(point2.row()).charAt(point2.col());
        char1 = char1 == 'S' ? 'a' : char1 == 'E' ? 'z' : char1;
        char2 = char2 == 'S' ? 'a' : char2 == 'E' ? 'z' : char2;
        return char2 - char1 <= 1;
    }

    private boolean isValidMapPoint(List<String> map, Point point) {
        return 0 <= point.row() && point.row() < map.size() && 0 <= point.col() && point.col() < map.get(0).length();
    }

}
