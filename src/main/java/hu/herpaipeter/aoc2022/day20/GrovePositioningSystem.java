package hu.herpaipeter.aoc2022.day20;

import java.util.List;

public class GrovePositioningSystem {

    private List<Integer> initial;

    public GrovePositioningSystem(List<Integer> initial) {
        this.initial = initial;
    }

    public long getSumAfterZero(List<Integer> positions) {
        List<Long> mixed = new NumberMixer(initial).mix();
        int zeroPosition = mixed.indexOf(0L);
        return positions.stream().mapToLong(p -> mixed.get((zeroPosition + p) % mixed.size())).sum();
    }

    public long getSumAfterZeroExtended(List<Integer> positions, long decryptionKey, int mixCount) {
        List<Long> mixed = new NumberMixer(initial).multiMix(decryptionKey, mixCount);
        int zeroPosition = mixed.indexOf(0L);
        return positions.stream().mapToLong(p -> mixed.get((zeroPosition + p) % mixed.size())).sum();
    }
}
