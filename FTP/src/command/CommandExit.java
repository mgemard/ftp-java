package command;

import java.io.IOException;

import core.FileClient;
import state.UnauthentifiedState;

public class CommandExit extends Command {

    public CommandExit(CommandManager manager) {
        super(manager, "exit");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            client.send("you are disconnected");
            client.terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
