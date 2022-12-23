package hu.herpaipeter.aoc2022.day15;

import hu.herpaipeter.aoc2022.day09.Point;
import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BeaconExclusionTest {

    @Test
    void empty_list_should_return_zero() {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        assertEquals(0, beaconExclusion.getNoOfBeaconFreePointsInRow(List.of(), 0));
    }

    @Test
    void one_manhatten_distance_middle_should_return_3() {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        List<Sensor> sensors = List.of(new Sensor(new Point(0, 0), new Point(1, 0)));
        assertEquals(3, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, 0));
    }

    @Test
    void one_manhatten_distance_upper_should_return_1() {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        List<Sensor> sensors = List.of(new Sensor(new Point(0, 0), new Point(1, 0)));
        assertEquals(1, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, -1));
    }

    @Test
    void two_sensors_middle_should_return_common_part() {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        Sensor sensor1 = new Sensor(new Point(0, 0), new Point(1, 1));
        Sensor sensor2 = new Sensor(new Point(0, 2), new Point(1, 1));
        List<Sensor> sensors = List.of(sensor1, sensor2);
        assertEquals(7, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, 0));
    }

    @Test
    void two_sensors_upper_from_horizontal_middle_should_return_common_part() {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        Sensor sensor1 = new Sensor(new Point(0, 0), new Point(1, 1));
        Sensor sensor2 = new Sensor(new Point(0, 2), new Point(1, 1));
        List<Sensor> sensors = List.of(sensor1, sensor2);
        assertEquals(5, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, -1));
        assertEquals(2, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, -2));
        assertEquals(0, beaconExclusion.getNoOfBeaconFreePointsInRow(sensors, -3));
    }

    @Test
    void example_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day15", "example.txt");
        List<Sensor> sensors = new SensorBeaconParser().parseSensors(inputTxt);
        assertEquals(26, new BeaconExclusion().getNoOfBeaconFreePointsInRow(sensors,10));
    }

    @Test
    void part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day15");
        List<Sensor> sensors = new SensorBeaconParser().parseSensors(inputTxt);
        System.out.println("day 15 part 1: " + new BeaconExclusion().getNoOfBeaconFreePointsInRow(sensors,2000000));
    }

    @Test
    void example_part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day15", "example.txt");
        List<Sensor> sensors = new SensorBeaconParser().parseSensors(inputTxt);
        Point noBeaconPosition = new BeaconExclusion().getNoBeaconPosition(sensors, 0, 20);
        assertEquals(new Point(11, 14), noBeaconPosition);
        assertEquals(56000011,4000000 * (long)noBeaconPosition.col() + (long)noBeaconPosition.row());
    }

    @Test
    void part_2() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day15");
        List<Sensor> sensors = new SensorBeaconParser().parseSensors(inputTxt);
        Point noBeaconPosition = new BeaconExclusion().getNoBeaconPosition(sensors, 0, 4000000);
        System.out.println("day 15 part 2: " + noBeaconPosition);
        System.out.println("day 15 part 2: " + (4000000L * (long)noBeaconPosition.col() + (long)noBeaconPosition.row()));
    }
}
