package it.polimi.ingsw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public String login(String name) throws RemoteException;
}
