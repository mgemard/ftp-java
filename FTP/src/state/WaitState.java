package state;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import core.FileClient;

public class WaitState extends ClientState {

    public static final int SECONDS = 30;
    private Timer timer;

    public WaitState(final FileClient client) {
        super(client);
        timer = new Timer();
        // On arme un Timer, qui changera l'état du client à "Unauthentified", il pourra alors essayer de nouveau
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("loo");
                client.setState(new UnauthentifiedState(client));
            }
        }, SECONDS * 1000);
        try {
            client.send("Too many failed attempts. Wait " + SECONDS + " seconds before trying again.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(String line) {
        try {
            // S'il essaie de se connecter alors qu'il est "temporisé", le compteur est remis à zéro
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {
            public void run() {
                client.setState(new UnauthentifiedState(client));
            }
        }, SECONDS * 1000);
            client.send("Too many failed attempts. Wait " + SECONDS + " seconds before trying again.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
