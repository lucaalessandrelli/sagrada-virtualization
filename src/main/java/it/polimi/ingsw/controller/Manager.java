package it.polimi.ingsw.controller;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<ClientsContainer> clients;
    private List<Match> matches;
    private int numOfMatch;
    private WaitingRoom lobby;
    private ClientHandler clientHandler;

    public Manager() {
        clients = new ArrayList<>();
        matches = new ArrayList<>();
        numOfMatch = 0;
        ClientsContainer c = new ClientsContainer(this);
        clients.add(c);
        lobby = new WaitingRoom(5000, this, clients.get(numOfMatch));
        clientHandler = new ClientHandler();
    }

    public void createMatch(WaitingRoom lobby) {
        matches.add(new Match(lobby.getPlayerList(), this, numOfMatch));
        clients.get(numOfMatch).notifyIdMatch(numOfMatch);
        clients.get(numOfMatch).setMatchStarted(true);
        /*START MATCH*/
        matches.get(numOfMatch).start();
        lobby.getPlayerList().forEach(player -> clientHandler.addPlayer(player.getUsername(), numOfMatch));
        numOfMatch++;
        ClientsContainer c = new ClientsContainer(this);
        clients.add(c);
        lobby.restore(clients.get(numOfMatch));
    }

    public synchronized void addPlayerInQueue(ClientInterface client) throws RemoteException {
        lobby.addPlayer(client);
    }

    public synchronized boolean checkIfPlayerIsLogged(String name) throws RemoteException {
        for (ClientsContainer cli : clients) {
            if (cli.findClient(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPlayerIsPlaying(String name) {
        return (clientHandler.isAPlayerIn(name));
    }


    public void notEnoughPlayer() {
        //notify and disconnect players
    }

    public void remove(String name){
        for (ClientsContainer cli : clients) {
            if (cli.remove(name)) {
                return;
            }
        }
    }

    public void notifyEnd(String name) {
    }
}
