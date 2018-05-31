package it.polimi.ingsw.network.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketListener implements Runnable {
    Socket socket;
    Client client;

    public SocketListener(Client client, Socket socket) {
        this.socket=socket;
        this.client=client;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            AnswerAnalyzer analyzer = new AnswerAnalyzer(client);
            while(client.connected()) {
                String answer = in.nextLine();
                analyzer.analyze(answer);
            }
        } catch (IOException e) {

        }

    }
}
