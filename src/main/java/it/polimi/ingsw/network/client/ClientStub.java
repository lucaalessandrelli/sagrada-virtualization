package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

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
