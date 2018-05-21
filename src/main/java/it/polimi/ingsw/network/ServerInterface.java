package it.polimi.ingsw.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    boolean login(String name,ClientInterface client) throws RemoteException;
    String command(String cmd) throws RemoteException;
    void disconnect(String name, ClientInterface client)throws RemoteException;
}
