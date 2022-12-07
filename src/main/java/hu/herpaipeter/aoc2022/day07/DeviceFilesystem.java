package hu.herpaipeter.aoc2022.day07;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeviceFilesystem {

    private Directory root;
    private Directory activeDirectory;

    public DeviceFilesystem() {
        root = new Directory("/", null);
    }

    public Directory getRoot() {
        return root;
    }

    public DeviceFilesystem(Directory root) {
        this.root = root;
    }

    public List<Directory> getDirectories() {
        return getSubdirectories(root);
    }

    private List<Directory> getSubdirectories(Directory directory) {
        List<Directory> directories = new ArrayList<>();
        directories.add(directory);
        directories.addAll(directory.getDirectories().stream().map(this::getSubdirectories).flatMap(Collection::stream).toList());
        return directories;
    }

    public void createDirectory(String name) {
        activeDirectory.add(new Directory(name, activeDirectory));
    }

    public Directory getActiveDirectory() {
        return activeDirectory;
    }

    public void changeDirectory(Directory directory) {
        activeDirectory = directory;
    }

    public void createFile(ElfFile elfFile) {
        activeDirectory.add(elfFile);
    }
}
