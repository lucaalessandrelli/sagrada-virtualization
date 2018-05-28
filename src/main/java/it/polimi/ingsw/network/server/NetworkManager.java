package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.InputAnalyzer;
import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkManager {
    static int PORT_RMI = 56789;
    static int PORT_SOCKET = 45678;

    private ArrayList<ClientInterface> clients;
    private Server server;

    public NetworkManager(Server s) {
        clients = new ArrayList<>();
        server = s;
    }

    public void start(){
        try {
            Registry registry = LocateRegistry.createRegistry(PORT_RMI);
            registry.bind("server", server);
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

        public SocketContainer(Socket socket, ServerInterface s){
            this.socket = socket;
            analyzer = new InputAnalyzer();
            server = s;
            connected =false;
        }
        @Override
        public void run() {
            try {
                Scanner in = new Scanner(socket.getInputStream());
                pr = new PrintWriter(socket.getOutputStream(),true);
                String message = in.nextLine();
                String cmd = analyzer.analyse(message);
                while(!connected) {
                    if (cmd.equals("login")) {
                        String name = analyzer.getData(message);
                        setName(name);
                        if (server.login(name, this)) {
                            pr.println("Connected, Welcome!");
                            connected = true;
                        } else {
                            pr.println("Already connected");
                            message = in.nextLine();
                            cmd = analyzer.analyse(message);
                        }

                    }
                }
                while (connected) {
                    message = in.nextLine();
                    cmd = analyzer.analyse(message);
                    if (cmd.equals("exit")) {
                        String asw = server.command(cmd);
                        pr.println(asw);
                    }else if(cmd.equals("disconnect")){
                        server.disconnect(name, this);
                        connected = false;
                        pr.println("Disconnected form server");
                        in.close();
                        pr.close();
                        socket.close();
                    } else {
                        pr.println(server.command(cmd));

                    }
                }

            } catch (Exception e) {
                try {
                    server.disconnect(name,this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }

        private void setName(String name){
            this.name =name;
        }


        @Override
        public void disconnect() throws RemoteException {
            server.disconnect(name, this);
            pr.println("Disconnected form server");
            pr.close();
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Connection already closed");
            }
        }

        @Override
        public String getName() throws RemoteException {
            return name;
        }

        @Override
        public String ping() throws RemoteException {
            return null;
        }

        @Override
        public String getTypeConnection() {
            return "(Socket)";
        }
    }
}
