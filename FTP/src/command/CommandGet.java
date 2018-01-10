package command;

import java.io.IOException;

import core.FileClient;

public class CommandGet extends Command {

    public CommandGet(CommandManager manager) {
        super(manager, "get");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            // TODO not implemented yet 
            // use buffered reader
            client.send("get is not implemented yet !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
