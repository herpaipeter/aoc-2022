package hu.herpaipeter.aoc2022.day07;

import java.util.List;

public class FilesystemBuilder {
    public DeviceFilesystem build(List<String> commands) {
        DeviceFilesystem filesystem = new DeviceFilesystem();
        for (String command : commands) {
            if (command.startsWith("$ cd")) {
                String parameter = command.substring("$ cd".length() + 1).trim();
                if (parameter.equals("/"))
                    filesystem.changeDirectory(filesystem.getRoot());
                if (parameter.equals(".."))
                    filesystem.changeDirectory(filesystem.getActiveDirectory().getParent());
                if (parameter.matches("[a-z]+"))
                    filesystem.changeDirectory(filesystem.getActiveDirectory().getDirectories().stream().filter(d -> d.getName().equals(parameter)).findFirst().get());
            } else if (command.startsWith("dir")) {
                filesystem.createDirectory(command.substring("dir".length() + 1).trim());
            } else if (command.matches("[0-9]+ [a-z.]+")) {
                String[] file = command.split(" ");
                filesystem.createFile(new ElfFile(file[1], Long.parseLong(file[0])));
            }
        }
        return filesystem;
    }
}
