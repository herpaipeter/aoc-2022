package hu.herpaipeter.aoc2022.day08;

import java.util.List;

public class ForrestAnalyzer {
    public int getVisibleTreesCount(List<String> map) {
        if (map.isEmpty())
            return 0;
        int visible = square(map.size()) - (2 < map.size() ? square(map.size() - 2) : 0);
        for (int i = 1; i < map.size() - 1; i++) {
            for (int j = 1; j < map.get(i).length() - 1; j++) {
                boolean higherAbove = true;
                boolean higherBelow = true;
                boolean higherLeft = true;
                boolean higherRight = true;

                for (int z = 0; z < i; z++)
                    higherAbove &= map.get(z).charAt(j) < map.get(i).charAt(j);
                for (int z = i + 1; z < map.size(); z++)
                    higherBelow &= map.get(z).charAt(j) < map.get(i).charAt(j);
                for (int z = 0; z < j; z++)
                    higherLeft &= map.get(i).charAt(z) < map.get(i).charAt(j);
                for (int z = j + 1; z < map.get(i).length(); z++)
                    higherRight &= map.get(i).charAt(z) < map.get(i).charAt(j);

                if (higherAbove || higherBelow || higherLeft || higherRight)
                    visible++;
            }
        }
        return visible;
    }

    public static int square(int num) {
        return (int)Math.pow(num, 2);
    }

    public int getScenicScore(List<String> map, int row, int col) {
        if (2 < map.size() && row != 0 && row != map.size() - 1 && col != 0 && col != map.get(0).length() - 1) {
            char tree = map.get(row).charAt(col);
            int up = 0;
            int down = 0;
            int left = 0;
            int right = 0;
            for (int i = row - 1; 0 <= i; i--) {
                up++;
                if (tree <= map.get(i).charAt(col))
                    break;
            }
            for (int i = row + 1; i < map.size(); i++) {
                down++;
                if (tree <= map.get(i).charAt(col))
                    break;
            }
            for (int i = col - 1; 0 <= i; i--) {
                left++;
                if (tree <= map.get(row).charAt(i))
                    break;
            }
            for (int i = col + 1; i < map.get(0).length(); i++) {
                right++;
                if (tree <= map.get(row).charAt(i))
                    break;
            }
            return up * down * left * right;
        }
        return 0;
    }
}
