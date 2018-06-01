package it.polimi.ingsw.network.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection implements ConnectionHandler {
    static int PORT_SOCKET = 45678;

    Client client;
    Socket socket;
    InputComposer inputComposer;
    SocketListener listner;

    public SocketConnection(Client client, String addr) {
        this.client = client;
        inputComposer = new InputComposer(client);
        try {
            socket = new Socket(addr, PORT_SOCKET);
        } catch (IOException e) {
            System.out.println("Connection lost");
        }
    }

    public void startListener(){
        listner = new SocketListener(client.getQueue(), socket);
        new Thread(listner).start();
    }

    @Override
    public void connect() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            if (!client.connected()) {
                String rqst = inputComposer.compose("login");
                pr.println(rqst);
                /*String answ = in.nextLine();
                if (answ.equals("Connected, Welcome!")) {
                    client.setConnected(true);
                }*/
                //client.setServiceMessage(answ);
            }
            startListener();
        } catch (Exception e) {
            System.out.println("alert Server not available");
        }


    }


    /*public void disconnect() {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            client.setConnected(false);
            String rqst = inputComposer.compose("disconnect");
            pr.println(rqst);
            pr.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    public void sendCommand(String cmd) {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            cmd = inputComposer.compose(cmd);
            pr.println(cmd);
        } catch (Exception e) {
            System.out.println("alert Server not available");
        }

    }

    @Override
    public boolean ping() {
        return true;
    }
}
