package hu.herpaipeter.aoc2022.day07;

import java.util.List;

public class FilesystemBuilder {

    private static final String FILE_DESCRIPTOR_MASK = "[0-9]+ [a-z.]+";
    private static final String CD_COMMAND_PREFIX = "$ cd";
    private static final String DIRECTORY_PREFIX = "dir";
    private static final String ROOT_DIRECTORY = "/";
    private static final String PARENT_DIRECTORY = "..";
    private static final String DIRECTORY_NAME_MASK = "[a-z]+";

    public static DeviceFilesystem build(List<String> commands) {
        DeviceFilesystem filesystem = new DeviceFilesystem();
        for (String command : commands) {
            if (command.startsWith(CD_COMMAND_PREFIX)) {
                changeDirectory(filesystem, command);
            } else if (command.startsWith(DIRECTORY_PREFIX)) {
                createDirectory(filesystem, command);
            } else if (command.matches(FILE_DESCRIPTOR_MASK)) {
                createFile(filesystem, command);
            }
        }
        return filesystem;
    }

    private static void createFile(DeviceFilesystem filesystem, String command) {
        String[] file = command.split(" ");
        filesystem.createFile(new ElfFile(file[1], Long.parseLong(file[0])));
    }

    private static void createDirectory(DeviceFilesystem filesystem, String command) {
        filesystem.createDirectory(command.substring(DIRECTORY_PREFIX.length() + 1).trim());
    }

    private static void changeDirectory(DeviceFilesystem filesystem, String command) {
        String parameter = command.substring(CD_COMMAND_PREFIX.length() + 1).trim();
        if (parameter.equals(ROOT_DIRECTORY))
            filesystem.changeDirectory(filesystem.getRoot());
        else if (parameter.equals(PARENT_DIRECTORY))
            filesystem.changeDirectory(filesystem.getActiveDirectory().getParent());
        else if (parameter.matches(DIRECTORY_NAME_MASK))
            filesystem.changeDirectory(findSubdirectoryByName(filesystem, parameter));
    }

    private static Directory findSubdirectoryByName(DeviceFilesystem filesystem, String parameter) {
        return filesystem.getActiveDirectory().getDirectories().stream().filter(d -> d.getName().equals(parameter)).findFirst().get();
    }
}
