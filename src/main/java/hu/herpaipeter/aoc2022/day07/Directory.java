package hu.herpaipeter.aoc2022.day07;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private final String name;
    private final Directory parent;
    private List<ElfFile> files;
    private List<Directory> directories;

    public Directory(String name, Directory parent) {
        this(name, parent, new ArrayList<>());
    }

    public Directory(String name, Directory parent, List<ElfFile> files) {
        this(name, parent, new ArrayList<>(), files);
    }

    public Directory(String name, Directory parent, List<Directory> directories, List<ElfFile> files) {
        this.name = name;
        this.parent = parent;
        this.directories = directories;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public List<ElfFile> getFiles() {
        return files;
    }

    public void add(Directory directory) {
        directories.add(directory);
    }

    public void add(ElfFile file) {
        files.add(file);
    }

    public long size() {
        return directories.stream().mapToLong(Directory::size).sum() +
                files.stream().mapToLong(ElfFile::size).sum();
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", parent.name=" + parent.name +
                ", files=" + files +
                ", directories=" + directories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Directory directory = (Directory) o;

        if (!name.equals(directory.name)) return false;
        if (parent != null ? !parent.name.equals(directory.parent.name) : directory.parent != null) return false;
        if (!files.equals(directory.files)) return false;
        return directories.equals(directory.directories);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (parent != null ? parent.name.hashCode() : 0);
        result = 31 * result + files.hashCode();
        result = 31 * result + directories.hashCode();
        return result;
    }
}
