package it.polimi.ingsw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    String login(String name) throws RemoteException;
    String command(String cmd) throws RemoteException;
}
