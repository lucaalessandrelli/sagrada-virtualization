package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;


/**
 * This class is used to make invisible, from server side, the problems of the communication. The idea is to have an object
 * that represent the main info of the connected client. This class has a ClientInterface attribute which provide to the real
 * communication.
 */
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

    /**
     * Send the list of connected players.
     * @param finalPlayersIn players' name.
     * @throws RemoteException
     */
    void updatePlayers(String finalPlayersIn) throws RemoteException {
        finalPlayersIn = CONNECTED + finalPlayersIn;
        client.updatePlayers(finalPlayersIn);
    }

    /**
     * Sends the number of the match where the player will play.
     * @param numOfMatch
     * @throws RemoteException
     */
    public void setNumMatch(int numOfMatch) throws RemoteException {
        String num = String.valueOf(numOfMatch);
        client.setNumMatch(MATCH+num);
    }

    /**
     * Send the name of the player who has to move.
     * @param s message
     * @throws RemoteException
     */
    public void updateTurn(String s) throws RemoteException {
        client.updateTurn(s);
    }

    /**
     * Send an update message
     * @param updateMove message
     * @throws RemoteException
     */
    public void update(String updateMove) throws RemoteException {
        client.update(updateMove);
    }

    /**
     * Send the timer.
     * @param tempTime timer.
     * @throws RemoteException
     */
    public void setTimer(long tempTime) throws RemoteException {
        tempTime = tempTime/1000;
        String timer = String.valueOf(tempTime);
        client.setTimer(TIME+timer);
    }

    /**
     * Send an alert for a Wrong move.
     * @param s alert message.
     * @throws RemoteException
     */
    public void wrongMove(String s) throws RemoteException {
        client.updateMessage(ALERT+s);
    }

    /**
     * Sill send the message for the choose of the pattern card.
     * @param windows all the four pattern card from which choose.
     * @throws RemoteException
     */
    public void chooseWindow(String windows) throws RemoteException {
        client.updateMessage(windows);
    }

    public ClientInterface getInterface() {
        return client;
    }
}
