package command;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import core.FileClient;

public class CommandCd extends Command {

    public CommandCd(CommandManager manager) {
        super(manager, "cd");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {

            // Stream<Path> list = Files.list(path);
            // client.setDir(new File(client.getDir().getPath().concat(args)));

            client.setDir(new File(client.getDir() + args + "\\"));

            // switch (args) {
            // case "..":
            // client.setDir(client.getDir().getParentFile());
            // break;
            // default:
            // client.setDir(client.getDir().getParentFile());
            // break;
            // }
            //
        } catch (InvalidPathException e) {
            try {
                client.send("Introuvable");
            } catch (IOException e1) {
            }
        }
    }
}
