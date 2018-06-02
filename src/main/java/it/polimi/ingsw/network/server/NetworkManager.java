package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.InputAnalyzer;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkManager {
    final static int PORT_RMI = 56789;
    final static int PORT_SOCKET = 45678;

    private ArrayList<ClientInterface> clients;
    private Server server;

    public NetworkManager(Server s) {
        clients = new ArrayList<>();
        server = s;
    }

    public void start(){
        try {
            Registry registry = LocateRegistry.createRegistry(PORT_RMI);
            ServerInterface stub = (ServerInterface)UnicastRemoteObject.exportObject(server,PORT_RMI);
            registry.bind("server", stub);
            try (ServerSocket serverSocket = new ServerSocket(PORT_SOCKET)) {
                SocketContainer sc;
                System.out.println("Server is up");
                do  {
                    Socket socket = serverSocket.accept();
                    sc = new SocketContainer(socket, server);
                    new Thread(sc).start();
                }while (20 > clients.size());
                System.out.println("Too many users connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class SocketContainer implements ClientInterface, Runnable{
        private Socket socket;
        private InputAnalyzer analyzer;
        private ServerInterface server;
        private String name;
        private boolean connected;
        private PrintWriter pr;
        private Scanner in;

        public SocketContainer(Socket socket, ServerInterface s){
            this.socket = socket;
            analyzer = new InputAnalyzer();
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
                    String name = analyzer.getData(message);
                    setName(name);
                    if (server.login(name, this)) {
                        pr.println("Connected, Welcome!");
                        connected = true;
                    } else {
                        pr.println("Already connected");
                        message = in.nextLine();
                    }
                }
            }
        }

        private void receiveCommand() throws Exception {
                String message;
                message = in.nextLine();
                if (message.startsWith("exit")) {
                    String asw = server.command("exit");
                    pr.println(asw);
                    server.disconnect(name, this);
                    connected = false;
                    in.close();
                    pr.close();
                    socket.close();
                }else if(message.startsWith("play")){

                } else {
                    pr.println(server.command(message));

                }
        }


        private void setName(String name){
            this.name =name;
        }



        /*public void disconnect() throws RemoteException {
            server.disconnect(name, this);
            pr.println("Disconnected form server");
            pr.close();
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Connection already closed");
            }
        }*/

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean ping()  {
            if(socket.isConnected()){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public String getTypeConnection() {
            return "(Socket)";
        }

        @Override
        public void updateWindows(String allWindows) throws RemoteException {
            pr.println(allWindows);

        }

        @Override
        public void updateDraftPool(String draftPool) throws RemoteException {
            pr.println(draftPool);

        }

        @Override
        public void updatePlayers(String playersIn) throws RemoteException {
            pr.println(playersIn);
        }

        @Override
        public void updateTurn(String whoIsTurn) {
            pr.println(whoIsTurn);

        }

        @Override
        public void setNumMatch(int num) throws RemoteException {

        }
    }
}
