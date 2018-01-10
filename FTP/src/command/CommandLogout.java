package command;

import java.io.IOException;

import core.FileClient;
import state.UnauthentifiedState;

public class CommandLogout extends Command {

    public CommandLogout(CommandManager manager) {
        super(manager, "logout");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            client.send("you are disconnected");
            client.setState(new UnauthentifiedState(client));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
