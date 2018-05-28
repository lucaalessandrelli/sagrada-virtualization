package it.polimi.ingsw.controller;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<ClientContainer> clients;
    private List<Match> matches;
    private int numOfMatch;
    private WaitingRoom lobby;
    private ClientHandler clientHandler;

    public Manager() {
        clients = new ArrayList<>();
        matches = new ArrayList<>();
        numOfMatch = 0;
        ClientContainer c = new ClientContainer(this);
        clients.add(c);
        lobby = new WaitingRoom(5000, this, clients.get(numOfMatch));
        clientHandler = new ClientHandler();
    }

    public void createMatch(WaitingRoom lobby) {
        matches.add(new Match(lobby.getPlayerList(), this, numOfMatch));
        clients.get(numOfMatch).notifyIdMatch(numOfMatch);
        /*START MATCH*/
        matches.get(numOfMatch).start();
        lobby.getPlayerList().forEach(player -> clientHandler.addPlayer(player.getUsername(), numOfMatch));
        numOfMatch++;
        ClientContainer c = new ClientContainer(this);
        clients.add(c);
        lobby.restore(clients.get(numOfMatch));
    }

    public synchronized void addPlayerInQueue(ClientInterface client) throws RemoteException {
        lobby.addPlayer(client);
    }

    public synchronized boolean checkIfPlayerIsLogged(String name) throws RemoteException {
        for (ClientContainer cli : clients) {
            if (cli.findClient(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPlayerIsPlaying(String name) {
        return (clientHandler.isAPlayerIn(name));
    }


    public void notEnoughPlayer(String winnerName) {
        //notify and disconnect players
    }

    public void remove(String name){
        for (ClientContainer cli : clients) {
            if (cli.remove(name)) {
                return;
            }
        }
    }
}
