package it.polimi.ingsw.network;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    /*void disconnect() throws RemoteException;*/
    String getName()throws RemoteException;
    boolean ping() throws RemoteException;
    String getTypeConnection() throws RemoteException;
    void update(String allWindows) throws RemoteException;
    void updatePlayers(String playersIn) throws RemoteException;
    void updateTurn(String whoIsTurn) throws RemoteException;
    void setNumMatch(String num) throws RemoteException;
    void updateMessage(String message) throws RemoteException;
    void setTimer(String timer)throws RemoteException;
}
