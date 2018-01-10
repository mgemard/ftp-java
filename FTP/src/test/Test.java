package test;

import java.io.IOException;

import core.FileServer;

public class Test {

    
    public static void main(String[] args) throws IOException {
        new FileServer(1234);
        
        // tapez dans le terminal
        // nc 127.0.0.1 1234
    }

}
