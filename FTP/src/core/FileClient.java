package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import command.CommandManager;
import command.UnknownCommandException;
import state.ClientState;
import state.UnauthentifiedState;

public class FileClient extends Thread {

    private Socket socket;

    private ClientState state;

    private BufferedReader inReader;

    private BufferedWriter outReader;

    private BufferedInputStream inputStream;

    private BufferedOutputStream outputStream;

    private File dir;

    private CommandManager manager;

    public FileClient(Socket socket, CommandManager manager, String directory) throws IOException {
        this.socket = socket;
        this.inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outReader = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.inputStream = new BufferedInputStream(socket.getInputStream());
        this.outputStream = new BufferedOutputStream(socket.getOutputStream());
        // Initialisé à état non authentifié
        this.state = new UnauthentifiedState(this);
        this.dir = new File(directory);
        this.manager = manager;
    }

    public void run() {
        try {
            String line = null;
            while ((line = inReader.readLine()) != null) {
                state.execute(line);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public void send(String string) throws IOException {
        outReader.write(string + "\n");
        outReader.flush();
    }
    
    public void send(byte[] buf) throws IOException {
        outputStream.write(buf);
        outputStream.flush();
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    public File getDir() {
        return dir;
    }

    public void execute(String line) {
        try {
            manager.execute(this, line);
        } catch (UnknownCommandException e) {
            try {
                send("Commande inconnue");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void terminate() {
        try {
            inReader.close();
            outReader.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDir(File newDir) {
        this.dir = newDir;
    }

}
