package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

public class ClientStub implements ClientInterface {
    private String name;
    private MessageQueue queue;

    public ClientStub(MessageQueue q, String n) {
        queue = q;
        name=n;

    }


    public void disconnect() throws RemoteException {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean ping() throws RemoteException {
        return true;
    }

    @Override
    public String getTypeConnection() throws RemoteException {
        return "(RMI)";
    }

    @Override
    public void updateWindows(String allWindows) throws RemoteException {
        queue.add(allWindows);
    }

    @Override
    public void updateDraftPool(String draftPool) throws RemoteException {
        queue.add(draftPool);
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
    public void setNumMatch(int num) throws RemoteException {

    }
}
