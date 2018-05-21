package it.polimi.ingsw.network;


import it.polimi.ingsw.network.server.NetworkManager;
import it.polimi.ingsw.network.server.Server;

public class ServerMain {

    public static void main(String args[]) {
        try {
            Server server = new Server();
            NetworkManager networkManager = new NetworkManager(server);
            networkManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
