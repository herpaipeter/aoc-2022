package hu.herpaipeter.aoc2022.day06;

public class CommunicationProtocol {

    public int getMarkerStart(String datastream) {
        for (int i = 0; i < datastream.length() - 3; i++) {
            if (StringValidator.containsUniqueChars(datastream.substring(i, i + 4)))
                return i + 4;
        }
        return -1;
    }

    public int getMessageStart(String datastream) {
        for (int i = 0; i < datastream.length() - 13; i++) {
            if (StringValidator.containsUniqueChars(datastream.substring(i, i + 14)))
                return i + 14;
        }
        return -1;
    }
}
