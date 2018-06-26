package it.polimi.ingsw.network.client;

import java.net.Socket;
import java.util.Scanner;

public class SocketListener extends Thread {
    private Socket socket;
    private MessageQueue queue;
    private Scanner in;

    public SocketListener(MessageQueue q, Socket socket, Scanner in) {
        this.socket=socket;
        this.queue=q;
        this.in=in;
    }

    @Override
    public void run() {
        try {
            while(socket.isConnected()) {
                String answer = in.nextLine();
                queue.add(answer);
            }
        } catch (Exception e) {
            this.interrupt();
        }

    }
}
