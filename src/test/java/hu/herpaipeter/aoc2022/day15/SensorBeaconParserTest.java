package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day09.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SensorBeaconParserTest {

    @Test
    void empty_input_should_return_empty_list() {
        SensorBeaconParser parser = new SensorBeaconParser();
        assertEquals(List.of(), parser.parseSensors(List.of()));
    }

    @Test
    void one_line_input_should_return_one_sensor_list() {
        SensorBeaconParser parser = new SensorBeaconParser();
        assertEquals(List.of(new Sensor(new Point(18,2), new Point(15,-2))), parser.parseSensors(List.of("Sensor at x=2, y=18: closest beacon is at x=-2, y=15")));
    }

    @Test
    void two_lines_input_should_return_a_list_of_two_sensors() {
        SensorBeaconParser parser = new SensorBeaconParser();
        List<Sensor> sensors = parser.parseSensors(List.of("Sensor at x=2, y=18: closest beacon is at x=-2, y=15", "Sensor at x=9, y=16: closest beacon is at x=10, y=16"));
        assertEquals(List.of(new Sensor(new Point(18,2), new Point(15,-2)), new Sensor(new Point(16,9), new Point(16,10))), sensors);
    }
}
