package command;

import java.io.IOException;

import core.FileClient;

public class CommandHelp extends Command {

    public CommandHelp(CommandManager manager) {
        super(manager, "help");
    }

    @Override
    public void execute(FileClient client, String args) {
        try {
            client.send("Available commands");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        getManager().getAvailableCommands().stream().forEach(map -> {
            try {
                client.send("\t" + map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
