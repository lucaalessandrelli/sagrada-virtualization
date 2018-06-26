package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

public class ClientBox {
    private ClientInterface client;
    private String name;
    private static final String CONNECTED="connectedPlayers";
    private static final String MATCH="match ";
    private static final String TIME="timer ";
    private static final String ALERT="alert ";


    ClientBox(ClientInterface c, String n){
        client=c;
        name=n;
    }

    void ping() throws RemoteException {
        client.ping();
    }

    public String getName() {
        return name;
    }

    void updatePlayers(String finalPlayersIn) throws RemoteException {
        finalPlayersIn = CONNECTED + finalPlayersIn;
        client.updatePlayers(finalPlayersIn);
    }

    public void setNumMatch(int numOfMatch) throws RemoteException {
        String num = String.valueOf(numOfMatch);
        client.setNumMatch(MATCH+num);
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
        client.setTimer(TIME+timer);
    }

    public void wrongMove(String s) throws RemoteException {
        client.updateMessage(ALERT+s);
    }

    public void chooseWindow(String windows) throws RemoteException {
        client.updateMessage(windows);
    }

    public ClientInterface getInterface() {
        return client;
    }
}
