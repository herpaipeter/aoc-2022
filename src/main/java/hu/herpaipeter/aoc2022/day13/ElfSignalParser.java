package hu.herpaipeter.aoc2022.day13;

import java.util.ArrayList;
import java.util.List;

public class ElfSignalParser {

    public ElfSignalList parseElfSignalList(String text) {
        if (text.isEmpty())
            return null;
        List<ElfSignal> elfSignals = new ArrayList<>();
        if (hasContent(text)) {
            String list = getTextContent(text);
            for (int nextFirstPos = 0; nextFirstPos < list.length();) {
                nextFirstPos = addNextSubTextToSignals(elfSignals, list, nextFirstPos);
            }
        }
        return new ElfSignalList(elfSignals);
    }

    private int addNextSubTextToSignals(List<ElfSignal> elfSignals, String list, int nextFirstPos) {
        int closePos;
        String subText;
        if (list.charAt(nextFirstPos) == '[') {
            subText = getSublist(list, nextFirstPos);
            elfSignals.add(parseElfSignalList(subText));
        } else {
            subText = getSubInteger(list, nextFirstPos);
            elfSignals.add(parseElfSignalInteger(subText));
        }
        closePos = nextFirstPos + subText.length() - 1;
        if (closePos + 1 < list.length() && list.charAt(closePos + 1) == ',')
            nextFirstPos = closePos + 2;
        else
            nextFirstPos = list.length();
        return nextFirstPos;
    }

    private String getSubInteger(String list, int nextFirstPos) {
        int closePos = nextFirstPos + 1;
        for (int i = nextFirstPos; i < list.length() && isDigit(list, i); i++) {
            closePos = i;
        }
        return list.substring(nextFirstPos, closePos + 1);
    }

    private boolean isDigit(String list, int i) {
        return 48 <= list.charAt(i) && list.charAt(i) <= 57;
    }

    private String getSublist(String list, int nextFirstPos) {
        int closePos = nextFirstPos + 1;
        int depth = 1;
        for (int i = nextFirstPos + 1; i < list.length() && 0 < depth; i++) {
            closePos = i;
            if (list.charAt(i) == '[')
                depth++;
            else if (list.charAt(i) == ']')
                depth--;
        }
        return list.substring(nextFirstPos, closePos + 1);
    }

    private String getTextContent(String text) {
        return text.substring(1, text.length() - 1);
    }

    private boolean hasContent(String text) {
        return 2 < text.length();
    }

    private ElfSignalInteger parseElfSignalInteger(String number) {
        return new ElfSignalInteger(Integer.parseInt(number));
    }

}
