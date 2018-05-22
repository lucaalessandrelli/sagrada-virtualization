package it.polimi.ingsw.network.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection implements ConnectionHandler {
    static int PORT_SOCKET = 45678;

    Client client;
    Socket socket;
    InputComposer inputComposer;

    public SocketConnection(Client client, String addr) {
        this.client = client;
        inputComposer = new InputComposer(client);
        try {
            socket = new Socket(addr,PORT_SOCKET);
        } catch (IOException e) {
           System.out.println("Connection lost");
        }
    }

    @Override
    public void connect() {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
            Scanner in = new Scanner(socket.getInputStream());
            if(!client.connected()){
                String rqst =inputComposer.compose("login");
                pr.println(rqst);
                String asw= in.nextLine();
                if(asw.equals("Connected, Welcome!")){
                    client.setConnected(true);
                    System.out.println(asw);
                }else{
                    System.out.println(asw);
                }
            }
        } catch (Exception e) {
            System.out.println("Server not available");
        }


    }

    @Override
    public void disconnect() {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
            Scanner in = new Scanner(socket.getInputStream());
            client.setConnected(false);
            String rqst = inputComposer.compose("disconnect");
            pr.println(rqst);
            String asw = in.nextLine();
            System.out.println(asw);
            pr.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String sendCommand(String cmd) {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
            cmd = inputComposer.compose(cmd);
            pr.println(cmd);
            return in.nextLine();
        } catch (Exception e) {
            System.out.println("Server not available");
        }

        return null;
    }

    @Override
    public String ping() {
        return null;
    }

}
