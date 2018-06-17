package it.polimi.ingsw.network.client;

import java.net.Socket;
import java.util.Scanner;

public class SocketListener extends Thread {
    Socket socket;
    MessageQueue queue;
    Scanner in;

    public SocketListener(MessageQueue q, Socket socket, Scanner in) {
        this.socket=socket;
        this.queue=q;
        this.in=in;
    }

    @Override
    public void run() {
        try {
            //Scanner in = new Scanner(socket.getInputStream());
            while(socket.isConnected()) {
                String answer = in.nextLine();
                queue.add(answer);
            }
        } catch (Exception e) {
            this.interrupt();
        }

    }
}
