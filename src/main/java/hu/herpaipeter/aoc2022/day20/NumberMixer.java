package hu.herpaipeter.aoc2022.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NumberMixer {
    private List<Integer> initialNumbers;

    public NumberMixer(List<Integer> initialNumbers) {
        this.initialNumbers = initialNumbers;
    }

    public List<Long> mix() {
        List<MixerLong> originalLongs = convertToMixerLong(initialNumbers, 1L);
        List<MixerLong> result = new ArrayList<>(originalLongs);
        iterateElements(originalLongs, result);
        return result.stream().map(MixerLong::number).toList();
    }

    private void iterateElements(List<MixerLong> originalLongs, List<MixerLong> result) {
        for (int i = 0; i < originalLongs.size(); i++) {
            MixerLong element = originalLongs.get(i);
            int resultIndex = result.indexOf(element);
            long nextPos = resultIndex + element.number();
            if (originalLongs.size() - 1 < nextPos)
                nextPos = nextPos - (originalLongs.size() - 1) * (nextPos / (originalLongs.size() - 1));
            if (element.number() != 0 && nextPos <= 0) {
                nextPos = nextPos - (originalLongs.size() - 1) * (nextPos / (originalLongs.size() - 1)) + (originalLongs.size() - 1);
            }
            result.remove(resultIndex);
            result.add((int) nextPos, element);
        }
    }

    public List<Long> multiMix(long decryptionKey, int mixCount) {
        List<MixerLong> longs = convertToMixerLong(initialNumbers, decryptionKey);
        List<MixerLong> result = new ArrayList<>(longs);
        for (int count = 0; count < mixCount; count++)
            iterateElements(longs, result);
        return result.stream().map(MixerLong::number).toList();
    }

    private List<MixerLong> convertToMixerLong(List<Integer> initial, long decryptionKey) {
        return IntStream.range(0, initial.size())
                .mapToObj(i -> new MixerLong(initial.get(i) * decryptionKey, i)).toList();
    }

}
