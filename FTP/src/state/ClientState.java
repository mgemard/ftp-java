package state;
import core.FileClient;

public abstract class ClientState {
    
    protected FileClient client;
    
    public ClientState(FileClient client) {
        this.client = client;
    }

    public abstract void execute(String line);
}
