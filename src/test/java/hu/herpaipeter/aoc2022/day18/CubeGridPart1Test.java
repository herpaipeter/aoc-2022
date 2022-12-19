package hu.herpaipeter.aoc2022.day18;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CubeGridPart1Test {

    @Test
    void empty_cube_list_should_return_0() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(0, cubeGrid.getSurface(List.of()));
    }

    @Test
    void one_cube_list_should_return_6() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(6, cubeGrid.getSurface(List.of(new Point3D(0,0, 0))));
    }

    @Test
    void two_not_contacting_cubes_list_should_return_12() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(12, cubeGrid.getSurface(List.of(new Point3D(0,0, 0), new Point3D(1,1, 1))));
    }

    @Test
    void two_contacting_cubes_list_should_return_10() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(10, cubeGrid.getSurface(List.of(new Point3D(0,0, 0), new Point3D(0,0, 1))));
    }

    @Test
    void four_in_a_block_list_should_return_24() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(16, cubeGrid.getSurface(List.of(new Point3D(0,0, 0), new Point3D(1,0, 0), new Point3D(0,1, 0), new Point3D(1,1, 0))));
    }

    @Test
    void example_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day18", "example.txt");
        List<Point3D> point3DS = getPoint3DS(inputTxt);
        assertEquals(64, new CubeGrid().getSurface(point3DS));
    }

    @Test
    void bigger_example_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day18", "bigger_example.txt");
        List<Point3D> point3DS = getPoint3DS(inputTxt);
        assertEquals(108, new CubeGrid().getSurface(point3DS));
        //part2 90
    }

    @Test
    void file_part_1() {
        List<String> inputTxt = FileReader.readAoCInputFileLines("day18");
        List<Point3D> point3DS = getPoint3DS(inputTxt);
        System.out.println("day 18 part 1:" + new CubeGrid().getSurface(point3DS));
    }

    private List<Point3D> getPoint3DS(List<String> inputTxt) {
        return inputTxt.stream().map(l -> {
            String[] split = l.split(",");
            return new Point3D(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }).toList();
    }
}
