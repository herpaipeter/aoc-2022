package hu.herpaipeter.aoc2022.day18;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CubeGridPart2Test {

    @Test
    void empty_cube_list_exterior_surface_should_return_0() {
        CubeGrid cubeGrid = new CubeGrid();
        assertEquals(0, cubeGrid.getExteriorSurface(List.of()));
    }

    @Test
    void smallest_inside_hole_exterior_surface_should_return_30() {
        CubeGrid cubeGrid = new CubeGrid();
        List<Point3D> point3DS = getPoint3DS(List.of("1,0,0", "-1,0,0", "0,1,0","0,-1,0", "0,0,1", "0,0,-1"));
        assertEquals(30, cubeGrid.getExteriorSurface(point3DS));
    }

//    @Test
//    void two_cubes_inside_hole_exterior_surface_should_return_30() {
//        CubeGrid cubeGrid = new CubeGrid();
//        List<Point3D> point3DS = getPoint3DS(List.of("1,0,1", "2,0,1",
//                                                     "1,1,0", "2,1,0", "0,0,0", "3,0,0", "1,-1,0", "2,-1,0",
//                                                     "1,0,-1", "2,0,-1"));
//        assertEquals(42, cubeGrid.getExteriorSurface(point3DS));
//    }

    private List<Point3D> getPoint3DS(List<String> inputTxt) {
        return inputTxt.stream().map(l -> {
            String[] split = l.split(",");
            return new Point3D(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }).toList();
    }
}
