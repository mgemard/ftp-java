package core;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import command.CommandCd;
import command.CommandExit;
import command.CommandGet;
import command.CommandHelp;
import command.CommandLogout;
import command.CommandLs;
import command.CommandManager;
import command.CommandPwd;

public class FileServer {
    
    private List<FileClient> clients = new ArrayList<>();
    
    private CommandManager manager = new CommandManager();

    public FileServer(int port) throws IOException {
        
        // On �coute sur le port TCP
        ServerSocket server = new ServerSocket(port);
            
        // Boucle infinie
        while (true) {
            // On accepte la connexion entrante
            Socket socket = server.accept();
            // On démarre un nouveau client
            FileClient client = new FileClient(socket, manager, "C:/");
            clients.add(client);
            // On lance l'exécution du client (en Thread)
            client.start();
        }
    }

}
