package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientInterface;
import it.polimi.ingsw.network.client.Client;

import java.rmi.RemoteException;

public class ClientBox {
    private ClientInterface client;
    private String name;
    private String typeConnection;
    public ClientBox(ClientInterface c, String n,String t){
        client=c;
        name=n;
        typeConnection=t;
    }

    public void ping() throws RemoteException {
        client.ping();
    }

    public String getName() {
        return name;
    }

    public void updatePlayers(String finalPlayersIn) throws RemoteException {
        client.updatePlayers(finalPlayersIn);
    }

    public void setNumMatch(int numOfMatch) throws RemoteException {
        client.setNumMatch(numOfMatch);
    }

    public void updateTurn(String s) throws RemoteException {
        client.updateTurn(s);
    }

    public void updateWindows(String allWindows) throws RemoteException {
        client.updateWindows(allWindows);
    }

    public void updateDraftPool(String draftPool) throws RemoteException {
        client.updateDraftPool(draftPool);
    }
}
