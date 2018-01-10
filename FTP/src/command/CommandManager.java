package command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import core.FileClient;

public class CommandManager {

    // Liste des commandes classées par nom
    private Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        // Création de toutes les commandes
        new CommandLs(this);
        new CommandCd(this);
        new CommandPwd(this);
        new CommandHelp(this);
        new CommandGet(this);
        new CommandLogout(this);
        new CommandExit(this);
    }

    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public void execute(FileClient client, String line) throws UnknownCommandException {
        if (line != null) {
            // On sépare le premier mot du reste
            String[] tokens = line.split(" ", 2);
            // Le premier mot est la commande
            String commandName = tokens[0];
            // Le reste sont les arguments
            String commandArgs = "";
            if (tokens.length > 1)
                commandArgs = tokens[1];
            // On cherche la commande correspondants
            Command command = commands.get(commandName);
            // Si elle n'existe pas, on lance une exception
            if (command == null)
                throw new UnknownCommandException(line);
            // Sinon, on appelle la commande avec les arguments
            command.execute(client, commandArgs);
        }
    }

    public Set<String> getAvailableCommands() {
        // Liste des commandes disponibles
        return commands.keySet();
    }

}
