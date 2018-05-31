package it.polimi.ingsw.network;


import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.network.server.NetworkManager;
import it.polimi.ingsw.network.server.Server;

public class ServerMain {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Manager manager = new Manager();
            server.setManager(manager);
            NetworkManager networkManager = new NetworkManager(server);
            networkManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
