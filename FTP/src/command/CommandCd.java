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

            String dir = client.getDir().getAbsolutePath();
            if (!dir.endsWith("/")) dir += "/";

            File newDir = new File(dir + args);
            if (newDir.exists() && newDir.isDirectory()) {
                client.setDir(newDir);
                client.send("OK.");
            } else {
                client.send(args + " n'est pas un dossier valide.");
            }
        } catch (IOException e) {
            try {
                client.send("Introuvable");
            } catch (IOException e1) {
            }
        }
    }
}
