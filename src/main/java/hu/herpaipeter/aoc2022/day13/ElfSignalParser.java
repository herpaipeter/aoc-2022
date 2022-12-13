package hu.herpaipeter.aoc2022.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElfSignalParser {
    public ElfSignalList parseElfSignal(String string) {
        if (string.isEmpty())
            return null;
        else
            return new ElfSignalList(parseElfSignalWithoutBrakets(string.substring(1, string.length() - 1)));
    }
    private List<ElfSignal> parseElfSignalWithoutBrakets(String string) {
        List<ElfSignal> signals = new ArrayList<>();
        if (string.isEmpty())
            return signals;
        if (string.charAt(0) == '[') {
            if (string.charAt(1) == ']') {
                signals.add(new ElfSignalList(List.of()));
                if (2 < string.length() && string.charAt(2) == ',') {
                    signals.addAll(parseElfSignalWithoutBrakets(string.substring(3)));
                }
            } else {
                int closePos = 0;
                int depth = 1;
                for (int j = 1; j < string.length() && 0 < depth; j++) {
                    closePos = j;
                    if (string.charAt(j) == '[')
                        depth++;
                    else if (string.charAt(j) == ']')
                        depth--;
                }
                signals.add(new ElfSignalList(parseElfSignalWithoutBrakets(string.substring(1, closePos))));
                if (closePos < string.length() - 1 && string.charAt(closePos + 1) == ',') {
                    signals.addAll(parseElfSignalWithoutBrakets(string.substring(closePos + 2)));
                }
            }
        } else {
            int nextOpen = string.indexOf('[');
            if (nextOpen == -1) {
                signals.addAll(parseIntegerList(string));
            } else {
                signals.addAll(parseIntegerList(string.substring(0, nextOpen - 1)));
                signals.addAll(parseElfSignalWithoutBrakets(string.substring(nextOpen)));
            }
        }
        return signals;
    }

    private List<ElfSignal> parseIntegerList(String items) {
        String[] integers = items.split(",");
        return Arrays.stream(integers).map(i -> new ElfSignalInteger(Integer.parseInt(i))).map(ElfSignal.class::cast).toList();
    }
}
