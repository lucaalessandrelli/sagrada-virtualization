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
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",PORT_RMI);
            ClientStub obj = new ClientStub(client.getQueue(),client.getName());
            stub = (ClientInterface) UnicastRemoteObject.exportObject(obj,0);
            server = (ServerInterface) registry.lookup("server");
        } catch (Exception e) {
            System.out.println("alert Server not available");
        }

    }
      public void connect(){
        if(!client.connected()) {
            try {
                String name = client.getName();
                if (server.login(name,stub)) {
                    client.setConnected(true);
                    System.out.println("Connected, Welcome!");
                } else {
                    client.setConnected(false);
                    System.out.println("alert Already connected");

                }
            } catch (RemoteException e) {
                System.out.println("alert Server not available");
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
            String asw =server.command(cmd);
            if(asw.equals("service Disconnected form server")){
                disconnect();
            }
        } catch (RemoteException e) {
            client.setServiceMessage("Server not available");
        }
    }

    @Override
    public boolean ping() {
        return true;
    }


}
