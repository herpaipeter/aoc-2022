package hu.herpaipeter.aoc2022.day07;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FilesystemBuilderTest {

    @Test
    void empty_command_list_should_return_root_directory() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of());
        assertEquals(List.of(new Directory("/", null)), filesystem.getDirectories());
        assertNull(filesystem.getActiveDirectory());
    }

    @Test
    void cd_root_should_change_active_directory() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /"));
        Directory root = new Directory("/", null);
        assertIterableEquals(List.of(root), filesystem.getDirectories());
        assertEquals(root, filesystem.getActiveDirectory());
    }

    @Test
    void ls_command_should_not_change_the_filesystem() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls"));
        Directory root = new Directory("/", null);
        assertIterableEquals(List.of(root), filesystem.getDirectories());
        assertEquals(root, filesystem.getActiveDirectory());
    }

    @Test
    void dir_list_item_should_create_new_subdirectory_in_the_active_directory() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls", "dir a"));
        Directory root = new Directory("/", null);
        Directory dirA = new Directory("a", root);
        root.add(dirA);
        assertIterableEquals(List.of(root, dirA), filesystem.getDirectories());
        assertEquals(root, filesystem.getActiveDirectory());
    }

    @Test
    void number_and_filename_list_item_should_create_new_subdirectory_in_the_active_directory() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls", "10 b.txt"));
        Directory root = new Directory("/", null);
        ElfFile file = new ElfFile("b.txt", 10);
        root.add(file);
        assertIterableEquals(List.of(root), filesystem.getDirectories());
        assertEquals(root, filesystem.getActiveDirectory());
        assertIterableEquals(List.of(file), filesystem.getActiveDirectory().getFiles());
    }

    @Test
    void cd_directory_item_should_change_active_directory() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls", "dir a", "$ cd a"));
        Directory root = new Directory("/", null);
        Directory dirA = new Directory("a", root);
        root.add(dirA);
        assertIterableEquals(List.of(root, dirA), filesystem.getDirectories());
        assertEquals(dirA, filesystem.getActiveDirectory());
    }

    @Test
    void cd_directory_item_from_multi_subs_should_change_active_directory_to_the_correct_sub() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls", "dir a", "dir b", "$ cd b"));
        Directory root = new Directory("/", null);
        Directory dirA = new Directory("a", root);
        Directory dirB = new Directory("b", root);
        root.add(dirA);
        root.add(dirB);
        assertIterableEquals(List.of(root, dirA, dirB), filesystem.getDirectories());
        assertEquals(dirB, filesystem.getActiveDirectory());
    }

    @Test
    void cd_two_dots_item_should_change_active_directory_to_parent() {
        FilesystemBuilder builder = new FilesystemBuilder();
        DeviceFilesystem filesystem = builder.build(List.of("$ cd /", "$ ls", "dir a", "$ cd a", "$ cd .."));
        Directory root = new Directory("/", null);
        Directory dirA = new Directory("a", root);
        root.add(dirA);
        assertIterableEquals(List.of(root, dirA), filesystem.getDirectories());
        assertEquals(root, filesystem.getActiveDirectory());
    }

    @Test
    void multi_subdirectory_and_file_test() {
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
        assertIterableEquals(List.of(root, dirA, dirE, dirD), filesystem.getDirectories());
    }

}
