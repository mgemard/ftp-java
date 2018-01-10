package state;
import java.io.File;
import java.io.IOException;

import auth.AuthenticationManager;
import auth.User;
import core.FileClient;

public class UnauthentifiedState extends ClientState {
    
    // nombre d'échecs
    private int nbEchecs = 0;
    // nombre maximal d'échecs admis
    private static final int MAX_ECHECS = 3;
    
    private String login;
    
    public UnauthentifiedState(FileClient client) {
        super(client);
        try {
            client.send("Login :");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(String line) {
        if (login != null) {
            // Si le login est différent de null, c'est que la ligne est le mot de passe renseigné par l'utilisateur
            User user = AuthenticationManager.getInstance().getUser(login, line);
            if (user != null) {
                // Si l'authentification est réussie, on passe à l'état "AuthentifiedState"
                client.setState(new AuthentifiedState(client));
                client.setDir(new File(user.getDirectory()));
                try {
                    client.send("OK. You're authentified.");
                    try {
                        client.send(">");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Echec d'authentification
                // On remet le login à null pour pouvoir lire un nouveau couple d'identifiants
                login = null;
                try {
                    // On notifie le client
                    client.send("Wrong login or password.");
                    // On incrémente le compteur d'échecs
                    nbEchecs++;
                    // S'il est trop élevé, on passe dans l'état "WaitState", qui impose un délai de 30 secondes au client
                    if (nbEchecs >= MAX_ECHECS) client.setState(new WaitState(client));
                    else client.send("Login :");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Si le login est null, c'est que la ligne est le login renseigné par l'utilisateur
            // On retiens le login
            login = line;
            try {
                // On demande le mot de passe
                client.send("Password :");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
