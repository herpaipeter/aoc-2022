package hu.herpaipeter.aoc2022.day06;

public class CommunicationProtocol {

    private static final int MARKER_LENGTH = 4;
    private static final int MESSAGE_LENGTH = 14;

    public int getMarkerStart(String datastream) {
        for (int i = 0; i < datastream.length() - MARKER_LENGTH + 1; i++) {
            if (StringValidator.containsUniqueChars(datastream.substring(i, i + MARKER_LENGTH)))
                return i + MARKER_LENGTH;
        }
        return -1;
    }

    public int getMessageStart(String datastream) {
        for (int i = 0; i < datastream.length() - MESSAGE_LENGTH + 1; i++) {
            if (StringValidator.containsUniqueChars(datastream.substring(i, i + MESSAGE_LENGTH)))
                return i + MESSAGE_LENGTH;
        }
        return -1;
    }
}
