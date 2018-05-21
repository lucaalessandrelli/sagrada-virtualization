package it.polimi.ingsw.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    void disconnect() throws RemoteException;
    String getName()throws RemoteException;
    String ping() throws RemoteException;
    String getTypeConnection() throws RemoteException;
}
