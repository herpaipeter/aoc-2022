package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day12.Point;

import java.util.List;

public class SensorBeaconParser {

    public List<Sensor> parseSensors(List<String> input) {
        if (input.isEmpty())
            return List.of();
        return input.stream().map(this::getSensor).toList();
    }

    private Sensor getSensor(String inputText) {
        String[] sensorAndBeacon = inputText.split(":");
        String sensorCoords = sensorAndBeacon[0].substring("Sensor at ".length());
        String beaconCoords = sensorAndBeacon[1].substring(" closest beacon is at ".length());
        String[] sensorXY = sensorCoords.split(", ");
        String[] beaconXY = beaconCoords.split(", ");
        return new Sensor(new Point(Integer.parseInt(sensorXY[1].substring(2)), Integer.parseInt(sensorXY[0].substring(2))),
                new Point(Integer.parseInt(beaconXY[1].substring(2)), Integer.parseInt(beaconXY[0].substring(2))));
    }
}
