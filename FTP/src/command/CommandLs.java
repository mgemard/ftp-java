package command;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import core.FileClient;

public class CommandLs extends Command {

    public CommandLs(CommandManager manager) {
        super(manager, "ls");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            for (String fileName : client.getDir().list()) {
                File file = new File(client.getDir() + fileName);
                if (file.isFile()) {
                    client.send("\t" + fileName);
                }
                if (file.isDirectory()) {
                    client.send("\t" + fileName + "\\");
                }
            }
        } catch (

        IOException e) {
            e.printStackTrace();
        }
    }

}
