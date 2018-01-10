package command;

import core.FileClient;

public abstract class Command {

    // Nom de la commande à invoquer : ls, get, pwd, etc.
    private String name;

    private CommandManager manager;

    public Command(CommandManager manager, String name) {
        this.name = name;
        this.manager = manager;
        manager.addCommand(this);
    }

    public String getName() {
        return name;
    }

    protected CommandManager getManager() {
        return manager;
    }

    public abstract void execute(FileClient client, String args);

}
