package hu.herpaipeter.aoc2022.day07;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeviceFilesystemTest {

    @Test
    void default_filesystem_should_have_a_root() {
        DeviceFilesystem filesystem = new DeviceFilesystem();
        assertEquals(new Directory("/", null), filesystem.getRoot());
    }

    @Test
    void empty_directory_size_should_be_0() {
        DeviceFilesystem filesystem = new DeviceFilesystem(new Directory("/", null, List.of()));
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(1, directories.size());
        assertEquals(0, directories.get(0).size());
    }

    @Test
    void directory_size_with_one_empty_file_should_be_0() {
        DeviceFilesystem filesystem = new DeviceFilesystem(new Directory("/", null, List.of(new ElfFile("file", 0))));
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(1, directories.size());
        assertEquals(0, directories.get(0).size());
    }

    @Test
    void directory_with_one_not_empty_file_should_return_the_file_size() {
        DeviceFilesystem filesystem = new DeviceFilesystem(new Directory("/", null, List.of(new ElfFile("file", 1))));
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(1, directories.size());
        assertEquals(1, directories.get(0).size());
    }

    @Test
    void directory_with_two_files_should_return_files_size_sum() {
        Directory directory = new Directory("/", null, List.of(new ElfFile("file1", 1), new ElfFile("file2", 2)));
        DeviceFilesystem filesystem = new DeviceFilesystem(directory);
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(1, directories.size());
        assertEquals(3, directories.get(0).size());
    }

    @Test
    void directory_with_subdirectory_should_return_subdirectory_size() {
        Directory directory = new Directory("/", null, List.of());
        Directory subdirectory = new Directory("sub", directory, List.of(new ElfFile("file", 10)));
        directory.add(subdirectory);
        DeviceFilesystem filesystem = new DeviceFilesystem(directory);
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(2, directories.size());
        assertEquals(10, directories.get(0).size());
    }

    @Test
    void directory_with_subdirectories_should_return_subdirectory_size_sum() {
        Directory directory = new Directory("/", null, List.of());
        Directory subdirectory = new Directory("sub1", directory, List.of(new ElfFile("file1", 10)));
        Directory subdirectory2 = new Directory("sub2", directory, List.of(new ElfFile("file2", 20)));
        directory.add(subdirectory);
        directory.add(subdirectory2);
        DeviceFilesystem filesystem = new DeviceFilesystem(directory);
        List<Directory> directories = filesystem.getDirectories();
        assertEquals(3, directories.size());
        assertEquals(30, directories.get(0).size());
    }

    @Test
    void complex_filesystem_directory_sizes_test() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(
                List.of("$ cd /",
                        "$ ls",
                        "dir a",
                        "14848514 b.txt",
                        "8504156 c.dat",
                        "dir d",
                        "$ cd a",
                        "$ ls",
                        "dir e",
                        "29116 f",
                        "2557 g",
                        "62596 h.lst",
                        "$ cd e",
                        "$ ls",
                        "584 i",
                        "$ cd ..",
                        "$ cd ..",
                        "$ cd d",
                        "$ ls",
                        "4060174 j",
                        "8033020 d.log",
                        "5626152 d.ext",
                        "7214296 k"));
        Directory root = new Directory("/", null);
        Directory dirA = new Directory("a", root);
        root.add(dirA);
        Directory dirE = new Directory("e", dirA);
        dirA.add(dirE);
        dirE.add(new ElfFile("i", 584));
        dirA.add(new ElfFile("f", 29116));
        dirA.add(new ElfFile("g", 2557));
        dirA.add(new ElfFile("h.lst", 62596));
        root.add(new ElfFile("b.txt", 14848514));
        root.add(new ElfFile("c.dat", 8504156));
        Directory dirD = new Directory("d", root);
        root.add(dirD);
        dirD.add(new ElfFile("j", 4060174));
        dirD.add(new ElfFile("d.log", 8033020));
        dirD.add(new ElfFile("d.ext", 5626152));
        dirD.add(new ElfFile("k", 7214296));
        assertEquals(4, filesystem.getDirectories().size());
        assertEquals(584, dirE.size());
        assertEquals(94853, dirA.size());
        assertEquals(24933642, dirD.size());
        assertEquals(48381165, root.size());
    }

    @Test
    void file_result_part_1() {
        FilesystemBuilder builder = new FilesystemBuilder();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day07");
        DeviceFilesystem filesystem = builder.build(inputTxt);
        long sum = filesystem.getDirectories().stream().mapToLong(Directory::size).filter(s -> s <= 100000).sum();
        System.out.println("part1: " + sum);
    }

    @Test
    void file_result_part_2() {
        FilesystemBuilder builder = new FilesystemBuilder();
        List<String> inputTxt = FileReader.readAoCInputFileLines("day07");
        DeviceFilesystem filesystem = builder.build(inputTxt);
        long usedSpace = filesystem.getRoot().size();
        long freeSpace = 70000000 - usedSpace;
        OptionalLong min = filesystem.getDirectories().stream().mapToLong(Directory::size).filter(s -> 30000000 <= freeSpace + s).min();
        System.out.println("part2: " + min.getAsLong());
    }
}
