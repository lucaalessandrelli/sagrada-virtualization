package it.polimi.ingsw.controller;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.model.gamelogic.Round;
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
    private InputAnalyzer analyzer;

    public Manager() {
        clients = new ArrayList<>();
        matches = new ArrayList<>();
        numOfMatch = 0;
        ClientsContainer c = new ClientsContainer(this);
        clients.add(c);
        lobby = new WaitingRoom(30000, this, clients.get(numOfMatch));
        clientHandler = new ClientHandler();
        analyzer = new InputAnalyzer(this);
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

    public void analyze(String cmd) {
        analyzer.analyse(cmd);
    }

    void move(int match,String name, String move){
        Round round = matches.get(match).getCurrRound();
        if(round.getCurrTurn().equals(name)){
            Proc processor = new Proc(round,move);
            processor.process();
        }
        //cerca il nome del giocatore e manda una risposta
    }

    public void setPlayerInactive(String name) {
        int numMatch = clientHandler.getGame(name);
        matches.get(numMatch).setPlayerInactive(name);
    }
}
