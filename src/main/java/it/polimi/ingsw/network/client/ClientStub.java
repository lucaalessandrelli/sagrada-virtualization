package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

public class ClientStub implements ClientInterface {
    private String name;
    private Client client;

    public ClientStub(Client c) {
        client=c;
        name=client.getName();

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

    }

    @Override
    public void updateDraftPool(String draftPool) throws RemoteException {

    }

    @Override
    public void updatePlayers(String playersIn) throws RemoteException {

    }

    @Override
    public void updateTurn(String whoIsTurn) throws RemoteException {

    }
}
