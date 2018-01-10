package command;

import java.io.IOException;

import core.FileClient;

public class CommandPwd extends Command {

    public CommandPwd(CommandManager manager) {
        super(manager, "pwd");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            client.send("Current directory : " + client.getDir().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
