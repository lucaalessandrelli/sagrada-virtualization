package it.polimi.ingsw.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String getName()throws RemoteException;
    void ping() throws RemoteException;
    String getTypeConnection() throws RemoteException;
    void update(String allWindows) throws RemoteException;
    void updatePlayers(String playersIn) throws RemoteException;
    void updateTurn(String whoIsTurn) throws RemoteException;
    void setNumMatch(String num) throws RemoteException;
    void updateMessage(String message) throws RemoteException;
    void setTimer(String timer)throws RemoteException;
}
