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
        String num = String.valueOf(numOfMatch);
        client.setNumMatch("match "+num);
    }

    public void updateTurn(String s) throws RemoteException {
        client.updateTurn(s);
    }

    public void update(String updateMove) throws RemoteException {
        client.update(updateMove);
    }

    public void setTimer(long tempTime) throws RemoteException {
        tempTime = tempTime/1000;
        String timer = String.valueOf(tempTime);
        client.setTimer("timer "+timer);
    }

    public void wrongMove(String s) throws RemoteException {
        client.updateMessage("alert "+s);
    }
}
