package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ServerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiConnection implements ConnectionHandler,Serializable {
    static int PORT_RMI = 56789;
    ServerInterface server;
    Client client;

    public RmiConnection(Client client){
        this.client = client;
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT_RMI);
            server = (ServerInterface) registry.lookup("server");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
      public void connect(){
        if(!client.connected()) {
            try {
                if (server.login(client.getName(),client)) {
                    client.setConnected(true);
                    System.out.println("Connected, Welcome!");
                } else {
                    client.setConnected(false);
                    System.out.println("Already connected");

                }
            } catch (RemoteException e) {
                System.out.println("server not up");
            }
        }
    }

    @Override
    public void disconnect() {
        client.setConnected(false);
        try {
            server.disconnect(client.getName(),client);
        } catch (RemoteException e) {
            System.out.println("server not up");
        }
        System.out.println("Disconnected form server");

    }

    @Override
    public String sendCommand(String cmd) {
        try {
            String asw =server.command(cmd);
            return asw;
        } catch (RemoteException e) {
            return "server is not up";
        }
    }

    @Override
    public String ping() {
        return "pong";
    }
}
