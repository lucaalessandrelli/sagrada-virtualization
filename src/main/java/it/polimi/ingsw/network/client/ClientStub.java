package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

/**
 * This class is used when the client choose an rmi connection. When he try to log in to server, passes this object like the reference of itself
 * for the communication
 */
public class ClientStub implements ClientInterface {
    private String name;
    private MessageQueue queue;

    ClientStub(MessageQueue q, String n) {
        queue = q;
        name=n;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void ping() throws RemoteException {
        //this is used to verify if the rmi connection is alive
    }

    @Override
    public String getTypeConnection() throws RemoteException {
        return "(RMI)";
    }

    @Override
    public void update(String updateMove) throws RemoteException {
        queue.add(updateMove);
    }


    @Override
    public void updatePlayers(String playersIn) throws RemoteException {
        queue.add(playersIn);
    }

    @Override
    public void updateTurn(String whoIsTurn) throws RemoteException {
        queue.add(whoIsTurn);
    }

    @Override
    public void setNumMatch(String num) throws RemoteException {
        queue.add(num);
    }

    @Override
    public void updateMessage(String message) throws RemoteException {
        queue.add(message);
    }

    @Override
    public void setTimer(String timer) throws RemoteException {
        queue.add(timer);
    }
}
