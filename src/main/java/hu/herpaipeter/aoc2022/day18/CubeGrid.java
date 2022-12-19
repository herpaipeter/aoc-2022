package hu.herpaipeter.aoc2022.day18;

import java.util.List;

public class CubeGrid {

    public int getSurface(List<Point3D> cubes) {
        if (cubes.size() < 2)
            return cubes.size() * 6;
        int surfaceSize = 0;
        for (int i = 0; i < cubes.size(); i++) {
            Point3D cube = cubes.get(i);
            Point3D cubeXP = new Point3D(cube.x() + 1, cube.y(), cube.z());
            Point3D cubeXM = new Point3D(cube.x() - 1, cube.y(), cube.z());
            Point3D cubeYP = new Point3D(cube.x(), cube.y() + 1, cube.z());
            Point3D cubeYM = new Point3D(cube.x(), cube.y() - 1, cube.z());
            Point3D cubeZP = new Point3D(cube.x(), cube.y(), cube.z() + 1);
            Point3D cubeZM = new Point3D(cube.x(), cube.y(), cube.z() - 1);
            int contacts = (cubes.contains(cubeXP) ? 1 : 0) + (cubes.contains(cubeXM) ? 1 : 0) +
                    (cubes.contains(cubeYP) ? 1 : 0) + (cubes.contains(cubeYM) ? 1 : 0) +
                    (cubes.contains(cubeZP) ? 1 : 0) + (cubes.contains(cubeZM) ? 1 : 0);
            surfaceSize += (6 - contacts);
        }
        return surfaceSize;
    }

    private int surfaceTouchingCount(Point3D cube1, Point3D cube2) {
        Point3D cubeXP = new Point3D(cube1.x() + 1, cube1.y(), cube1.z());
        Point3D cubeXM = new Point3D(cube1.x() - 1, cube1.y(), cube1.z());
        Point3D cubeYP = new Point3D(cube1.x(), cube1.y() + 1, cube1.z());
        Point3D cubeYM = new Point3D(cube1.x(), cube1.y() - 1, cube1.z());
        Point3D cubeZP = new Point3D(cube1.x(), cube1.y(), cube1.z() + 1);
        Point3D cubeZM = new Point3D(cube1.x(), cube1.y(), cube1.z() - 1);
        return (cube2.equals(cubeXP) ? 1 : 0) + (cube2.equals(cubeXM) ? 1 : 0) +
                (cube2.equals(cubeYP) ? 1 : 0) + (cube2.equals(cubeYM) ? 1 : 0) +
                (cube2.equals(cubeZP) ? 1 : 0) + (cube2.equals(cubeZM) ? 1 : 0);
    }

    public int getExteriorSurface(List<Point3D> cubes) {
        if (cubes.size() < 6)
            return 0;
        return (cubes.size() - 1) * 6;
//        Map<Integer, List<Point3D>> zLayers = cubes.stream().collect(Collectors.groupingBy(p -> p.z()));
//        zLayers.values().stream().map(zList -> {
//            Map<Integer, List<Point3D>> yLayer = zList.stream().collect(Collectors.groupingBy(p -> p.y()));
//            yLayer.values().stream().map(yList -> {
//                yList.stream().sorted(Comparator.comparingInt(Point3D::x)).toList();
//            });
//        });
    }
}
