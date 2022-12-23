package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day09.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SensorTest {

    @Test
    void sensor_beacon_distance_zero_outer_perimeter() {
        Sensor sensor = new Sensor(new Point(0, 0), new Point(0,0));
        assertEquals(Set.of(new Point(-1,0), new Point(0,1), new Point(1,0), new Point(0,-1)),
                new HashSet<>(sensor.getOuterPerimeter()));
    }

    @Test
    void sensor_beacon_distance_two_outer_perimeter() {
        Sensor sensor = new Sensor(new Point(0, 0), new Point(0,1));
        assertEquals(Set.of(new Point(-2,0), new Point(-1,1), new Point(0,2), new Point(1,1),
                            new Point(2,0), new Point(1,-1), new Point(0,-2), new Point(-1,-1)),
                new HashSet<>(sensor.getOuterPerimeter()));
    }

    @Test
    void point_should_be_inside_sensor_beacon_distance() {
        Sensor sensor = new Sensor(new Point(0, 0), new Point(0,1));
        assertTrue(sensor.isInside(new Point(1,0)));
        assertTrue(sensor.isInside(new Point(0,1)));
        assertTrue(sensor.isInside(new Point(-1,0)));
        assertTrue(sensor.isInside(new Point(0,-1)));
    }

    @Test
    void point_should_be_outside_sensor_beacon_distance() {
        Sensor sensor = new Sensor(new Point(0, 0), new Point(0,1));
        assertFalse(sensor.isInside(new Point(1,1)));
        assertFalse(sensor.isInside(new Point(1,-1)));
        assertFalse(sensor.isInside(new Point(-1,1)));
        assertFalse(sensor.isInside(new Point(-1,-1)));
    }
}
