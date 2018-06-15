package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;

public class ClientBox {
    private ClientInterface client;
    private String name;
    private String typeConnection;
    final static private String connected="connectedPlayers";
    final static private String match="match ";
    final static private String time="timer ";
    final static private String alert="alert ";


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
        finalPlayersIn = connected + finalPlayersIn;
        client.updatePlayers(finalPlayersIn);
    }

    public void setNumMatch(int numOfMatch) throws RemoteException {
        String num = String.valueOf(numOfMatch);
        client.setNumMatch(match+num);
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
        client.setTimer(time+timer);
    }

    public void wrongMove(String s) throws RemoteException {
        client.updateMessage(alert+s);
    }

    public void chooseWindow(String windows) throws RemoteException {
        client.updateMessage(windows);
    }

    public ClientInterface getInterface() {
        return client;
    }
}
