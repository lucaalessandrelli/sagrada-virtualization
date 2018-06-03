package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class RmiConnection implements ConnectionHandler {
    static int PORT_RMI = 56789;
    ServerInterface server;
    Client client;
    String address;
    ClientInterface stub;

    public RmiConnection(Client client,String addr){
        this.client = client;
        address = addr;
        try {
            Registry registry = LocateRegistry.getRegistry(addr,PORT_RMI);
            ClientStub obj = new ClientStub(client.getQueue(),client.getName());
            stub = (ClientInterface) UnicastRemoteObject.exportObject(obj,0);
            server = (ServerInterface) registry.lookup("server");
        } catch (Exception e) {
            client.setServiceMessage("alert Server not available");
        }

    }
      public void connect(){
        if(!client.connected()) {
            try {
                String name = client.getName();
                if (server.login(name,stub)) {
                    client.setConnected(true);
                    client.setServiceMessage("Connected, Welcome!");
                } else {
                    client.setConnected(false);
                    client.setServiceMessage("alert Already connected");

                }
            } catch (Exception e) {
                client.setServiceMessage("alert Server not available");
            }
        }
    }

    public void disconnect() {
        client.setConnected(false);
        try {
            server.disconnect(client.getName(),stub);
        } catch (RemoteException e) {
            client.setServiceMessage("alert Server not available");
        }
        client.setServiceMessage("Disconnected form server");

    }

    @Override
    public void sendCommand(String cmd) {
        try {
            server.command(cmd);
        } catch (RemoteException e) {
            client.setServiceMessage("alert Server not available");
        }
    }



}
