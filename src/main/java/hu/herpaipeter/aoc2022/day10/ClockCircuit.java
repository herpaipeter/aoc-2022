package hu.herpaipeter.aoc2022.day10;

import hu.herpaipeter.aoc2022.day07.Directory;

import java.util.ArrayList;
import java.util.List;

public class ClockCircuit {

    private List<Integer> cycles = new ArrayList<>();
    private int register = 1;

    public void run(List<String> instructions) {
        boolean registerChanged = false;
        for (String instruction : instructions) {
            if (instruction.equalsIgnoreCase("noop")) {
                cycles.add(register);
                registerChanged = false;
            } else if (instruction.startsWith("addx")) {
                int value = Integer.parseInt(instruction.substring("addx".length() + 1).trim());
                cycles.add(register);
                cycles.add(register);
                register += value;
                registerChanged = true;
            }
        }
        if (registerChanged) {
            cycles.add(register);
        }
    }

    public int getRegister() {
        return register;
    }

    public List<Integer> getCycles() {
        return cycles;
    }

    public int getSignalStrength(int cycle) {
        if (cycle == 0)
            return 0;
        return cycles.get(cycle - 1) * cycle;
    }

    public int getSignalStrengthSum(List<Integer> cycles) {
        return cycles.stream().mapToInt(this::getSignalStrength).sum();
    }
}
