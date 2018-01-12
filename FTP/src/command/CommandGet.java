package command;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import core.FileClient;

public class CommandGet extends Command {

    public CommandGet(CommandManager manager) {
        super(manager, "get");
    }

    @Override
    public void execute(FileClient client, String args) {
        File[] listFiles = client.getDir().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals(args);
            }
        });
        try {
            if (listFiles != null && listFiles.length > 0) {
                File file = listFiles[0];
                if (!file.isDirectory()) {
                byte[] buf = new byte[1024];
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                while (in.read(buf) == 1024) client.send(buf);
                client.send(buf);
                client.send("");
                in.close();
                } else {
                    client.send("This is a directory.");
                }
            } else {
                client.send("Unknown file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
