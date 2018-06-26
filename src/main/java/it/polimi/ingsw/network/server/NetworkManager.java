package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.InputAnalyzer;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class NetworkManager {
    static final int PORTRMI = 56789;
    static final int PORTSOCKET = 45678;
    static final String ALERT = "alert ";

    private ArrayList<ClientInterface> clients;
    private Server server;

    NetworkManager(Server s) {
        clients = new ArrayList<>();
        server = s;
    }

    public void start(){
        try {
            Registry registry = LocateRegistry.createRegistry(PORTRMI);
            ServerInterface stub = (ServerInterface)UnicastRemoteObject.exportObject(server,PORTRMI);
            registry.bind("server", stub);
            try (ServerSocket serverSocket = new ServerSocket(PORTSOCKET)) {
                SocketContainer sc;
                out.println("Server is up");
                do  {
                    Socket socket = serverSocket.accept();
                    sc = new SocketContainer(socket, server);
                    new Thread(sc).start();
                }while (20 > clients.size());
                out.println("Too many users connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class SocketContainer implements ClientInterface, Runnable{
        private Socket socket;
        private ServerInterface server;
        private String name;
        private boolean connected;
        private PrintWriter pr;
        private Scanner in;

        SocketContainer(Socket socket, ServerInterface s){
            this.socket = socket;
            server = s;
            connected =false;
        }
        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                pr = new PrintWriter(socket.getOutputStream(),true);
                loginPlayer();
                while (connected) {
                    receiveCommand();
                }
            } catch (Exception e) {
                try {
                    server.disconnect(name,this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }

        private void loginPlayer() throws IOException {
            String message = in.nextLine();
            while (!connected) {
                if (message.startsWith("login")) {
                    InputAnalyzer analyzer = new InputAnalyzer();
                    String nameP = analyzer.getData(message);
                    setName(nameP);
                    if (server.login(nameP, this)) {
                        connected = true;
                    } else {
                        pr.println(ALERT+"Already connected");
                        message = in.nextLine();
                    }
                }
            }
        }

        private void receiveCommand() throws IOException {
                String message;
                message = in.nextLine();
                server.command(message);
        }


        private void setName(String name){
            this.name =name;
        }


        @Override
        public String getName() {
            return name;
        }

        @Override
        public void ping()  {
            pr.println("ping");
        }

        @Override
        public String getTypeConnection() {
            return "(Socket)";
        }

        @Override
        public void update(String updateMove) {
            pr.println(updateMove);

        }

        @Override
        public void updatePlayers(String playersIn) {
            pr.println(playersIn);
        }

        @Override
        public void updateTurn(String whoIsTurn) {
            pr.println(whoIsTurn);

        }

        @Override
        public void setNumMatch(String num) {
            pr.println(num);
        }

        @Override
        public void updateMessage(String message) {
            pr.println(message);
        }

        @Override
        public void setTimer(String timer) {
            pr.println(timer);
        }
    }
}
