package state;
import java.io.IOException;

import core.FileClient;

public class AuthentifiedState extends ClientState {
    
    public AuthentifiedState(FileClient client) {
        super(client);
    }

    public void execute(String line) {
        // On est authentifié : on demande au CommandManager d'exécuter la commande reçue
        client.execute(line);
        try {
            client.send(">");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
