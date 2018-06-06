package it.polimi.ingsw.network.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketListener extends Thread {
    Socket socket;
    MessageQueue queue;

    public SocketListener(MessageQueue q, Socket socket) {
        this.socket=socket;
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            while(socket.isConnected()) {
                String answer = in.nextLine();
                queue.add(answer);
            }
        } catch (Exception e) {
            this.interrupt();
        }

    }
}
