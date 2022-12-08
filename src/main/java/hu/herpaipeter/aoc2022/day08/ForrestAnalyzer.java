package hu.herpaipeter.aoc2022.day08;

import java.util.List;
import java.util.stream.IntStream;

public class ForrestAnalyzer {
    public static long getVisibleTreesCount(List<String> map) {
        return getEdgesTrees(map) + getInsideVisibleTrees(map);
    }

    private static int getEdgesTrees(List<String> map) {
        return 1 < map.size() ? 2 * map.size() + 2 * map.get(0).length() - 4 : map.size();
    }

    private static long getInsideVisibleTrees(List<String> map) {
        return IntStream.range(1, map.size() - 1).mapToLong(row -> getRowVisibleTrees(map, row)).sum();
    }

    private static long getRowVisibleTrees(List<String> map, int row) {
        return IntStream.range(1, map.get(row).length() - 1).filter(col -> isTreeVisible(map, row, col)).count();
    }

    private static boolean isTreeVisible(List<String> map, int row, int col) {
        char treeHeight = map.get(row).charAt(col);
        return isHigherFromAnyDirection(map, row, col, treeHeight);
    }

    private static boolean isHigherFromAnyDirection(List<String> map, int row, int col, char treeHeight) {
        return isHigherAbove(map, row, col, treeHeight) ||
                isHigherBelow(map, row, col, treeHeight) ||
                isHigherLeft(map, row, col, treeHeight) ||
                isHigherRight(map, row, col, treeHeight);
    }

    private static boolean isHigherAbove(List<String> map, int row, int col, char treeHeight) {
        return IntStream.range(0, row).allMatch(p -> map.get(p).charAt(col) < treeHeight);
    }

    private static boolean isHigherBelow(List<String> map, int row, int col, char treeHeight) {
        return IntStream.range(row + 1, map.size()).allMatch(p -> map.get(p).charAt(col) < treeHeight);
    }

    private static boolean isHigherLeft(List<String> map, int row, int col, char treeHeight) {
        return IntStream.range(0, col).allMatch(p -> map.get(row).charAt(p) < treeHeight);
    }

    private static boolean isHigherRight(List<String> map, int row, int col, char treeHeight) {
        return IntStream.range(col + 1, map.get(row).length()).allMatch(p -> map.get(row).charAt(p) < treeHeight);
    }

    public static int getScenicScore(List<String> map, int row, int col) {
        if (2 < map.size() && isInsideTree(map, row, col)) {
            return getViewDistanceUp(map, row, col) *
                    getViewDistanceDown(map, row, col) *
                    getViewDistanceLeft(map, row, col) *
                    getViewDistanceRight(map, row, col);
        }
        return 0;
    }

    private static boolean isInsideTree(List<String> map, int row, int col) {
        return row != 0 && row != map.size() - 1 && col != 0 && col != map.get(0).length() - 1;
    }

    private static int getViewDistanceUp(List<String> map, int row, int col) {
        char tree = map.get(row).charAt(col);
        int up = 0;
        for (int i = row - 1; 0 <= i; i--) {
            up++;
            if (tree <= map.get(i).charAt(col))
                break;
        }
        return up;
    }

    private static int getViewDistanceDown(List<String> map, int row, int col) {
        char tree = map.get(row).charAt(col);
        return findForwardViewDistance(map, row, col, tree);
    }

    private static int findForwardViewDistance(List<String> map, int row, int col, char tree) {
        int down = 0;
        for (int i = row + 1; i < map.size(); i++) {
            down++;
            if (tree <= map.get(i).charAt(col))
                break;
        }
        return down;
    }

    private static int getViewDistanceLeft(List<String> map, int row, int col) {
        char tree = map.get(row).charAt(col);
        int left = 0;
        for (int i = col - 1; 0 <= i; i--) {
            left++;
            if (tree <= map.get(row).charAt(i))
                break;
        }
        return left;
    }

    private static int getViewDistanceRight(List<String> map, int row, int col) {
        char tree = map.get(row).charAt(col);
        int right = 0;
        for (int i = col + 1; i < map.get(0).length(); i++) {
            right++;
            if (tree <= map.get(row).charAt(i))
                break;
        }
        return right;
    }
}
