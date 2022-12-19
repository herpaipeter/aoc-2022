package hu.herpaipeter.aoc2022.day17;

import java.util.List;

public class Rock {
    private List<String> lines;

    public Rock(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getShape() {
        return lines;
    }

    public int getHeight() {
        return lines.size();
    }
}
