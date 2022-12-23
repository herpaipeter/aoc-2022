package hu.herpaipeter.aoc2022.day12;

import hu.herpaipeter.aoc2022.day09.Point;

import java.util.*;
import java.util.stream.IntStream;

public class AStarAlgorithm {

    Map<Point, Integer> getDistancesMapReverse(List<String> map, Point start) {
        Set<Point> paths = new HashSet<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Integer> distances = new HashMap<>();
        initializeDistanceValuesToMax(map, distances);
        distances.put(start, 0);
        paths.add(start);
        while (!paths.isEmpty()) {
            Point actual = getMinDistanceValuePoint(paths, distances);
            paths.remove(actual);
            visited.add(actual);
            calculateNeighbourgsDistance(map, paths, visited, distances, actual);
        }
        return distances;
    }

    List<List<Integer>> getDistances(List<String> map, Point start, Point end) {
        Set<Point> paths = new HashSet<>();
        Set<Point> visited = new HashSet<>();
        List<List<Integer>> distances = new ArrayList<>();
        initializeDistanceValuesToMax(map, distances);
        distances.get(start.row()).set(start.col(), 0);
        paths.add(start);
        while (!paths.isEmpty()) {
            Point actual = getMinDistanceValuePoint(paths, distances);
            paths.remove(actual);
            visited.add(actual);
            if (actual.equals(end))
                break;
            calculateNeighbourgsDistance(map, paths, visited, distances, actual);
        }
        return distances;
    }

    private Point getMinDistanceValuePoint(Set<Point> paths, List<List<Integer>> distances) {
        return paths.stream().min(Comparator.comparingInt(p -> distances.get(p.row()).get(p.col()))).get();
    }

    private Point getMinDistanceValuePoint(Set<Point> paths, Map<Point, Integer> distances) {
        return paths.stream().min(Comparator.comparingInt(distances::get)).get();
    }

    private void initializeDistanceValuesToMax(List<String> map, List<List<Integer>> distances) {
        IntStream.range(0, map.size()).forEach(i -> {
            distances.add(new ArrayList<>());
            IntStream.range(0, map.get(i).length()).forEach(j -> distances.get(i).add(Integer.MAX_VALUE));
        });
    }

    private void initializeDistanceValuesToMax(List<String> map, Map<Point, Integer> distances) {
        IntStream.range(0, map.size()).forEach(i ->
                IntStream.range(0, map.get(i).length()).forEach(j -> distances.put(new Point(i, j), Integer.MAX_VALUE)));
    }

    private void calculateNeighbourgsDistance(List<String> map, Set<Point> paths, Set<Point> visited, List<List<Integer>> distances, Point actual) {
        List.of(new Point(-1,0), new Point(1,0), new Point(0, -1), new Point(0,1))
                .forEach(p -> {
                    Point next = actual.add(p);
                    if (isNextPointBetter(map, visited, distances, actual, next))
                        {
                            distances.get(next.row()).set(next.col(), distances.get(actual.row()).get(actual.col()) + 1);
                            paths.add(next);
                        }
                });
    }

    private void calculateNeighbourgsDistance(List<String> map, Set<Point> paths, Set<Point> visited, Map<Point, Integer> distances, Point actual) {
        List.of(new Point(-1,0), new Point(1,0), new Point(0, -1), new Point(0,1))
                .forEach(p -> {
                    Point next = actual.add(p);
                    if (isNextPointBetter(map, visited, distances, actual, next))
                    {
                        distances.put(next, distances.get(actual) + 1);
                        paths.add(next);
                    }
                });
    }

    private boolean isNextPointBetter(List<String> map, Set<Point> visited, List<List<Integer>> distances, Point actual, Point next) {
        return isValidMapPoint(map, next) && isEdgeBetween(map, actual, next) && !visited.contains(next) &&
                distances.get(actual.row()).get(actual.col()) + 1 < distances.get(next.row()).get(next.col());
    }

    private boolean isNextPointBetter(List<String> map, Set<Point> visited, Map<Point, Integer> distances, Point actual, Point next) {
        return isValidMapPoint(map, next) && isEdgeBetweenReverse(map, actual, next) && !visited.contains(next) &&
                distances.get(actual) + 1 < distances.get(next);
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

    private boolean isEdgeBetweenReverse(List<String> map, Point point1, Point point2) {
        char char1 = map.get(point1.row()).charAt(point1.col());
        char char2 = map.get(point2.row()).charAt(point2.col());
        char1 = char1 == 'S' ? 'a' : char1 == 'E' ? 'z' : char1;
        char2 = char2 == 'S' ? 'a' : char2 == 'E' ? 'z' : char2;
        return char1 - char2 <= 1;
    }

    private boolean isValidMapPoint(List<String> map, Point point) {
        return 0 <= point.row() && point.row() < map.size() && 0 <= point.col() && point.col() < map.get(0).length();
    }

}
