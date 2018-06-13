package it.polimi.ingsw.controller;

import it.polimi.ingsw.Match;
import it.polimi.ingsw.WaitingRoom;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Game> games;
    private ClientsContainer clients;
    private int numOfMatch;
    private WaitingRoom lobby;
    private ClientHandler clientHandler;
    private InputAnalyzer analyzer;
    private long timerRoom;
    private long timerCard;
    private long timerMove;

    public Manager(int timerRoom,int timerCard, int timerMove) {
        this.timerRoom=timerRoom*1000;
        this.timerCard=timerCard*1000;
        this.timerMove=timerMove*1000;
        games = new ArrayList<>();
        numOfMatch = 0;
        clients = new ClientsContainer(this);
        lobby = new WaitingRoom(this.timerRoom, this, clients);
        clientHandler = new ClientHandler();
        analyzer = new InputAnalyzer(this);

    }

    public void createMatch(WaitingRoom lobby) {
        Game g = new Game(clients,new Match(lobby.getPlayerList(), this, numOfMatch,timerCard,timerMove),numOfMatch);
        g.notifyGame();
        games.add(g);
        /*START MATCH*/
        lobby.getPlayerList().forEach(player -> clientHandler.addPlayer(player.getUsername(), numOfMatch));
        g.start();
        numOfMatch++;
        clients = new ClientsContainer(this);
        lobby.restore(clients);
    }

    public synchronized void addPlayerInQueue(ClientInterface client) throws RemoteException {
        lobby.addPlayer(client);
    }

    public synchronized boolean checkIfPlayerIsLogged(String name) throws RemoteException {
        if(clients.findClient(name)){
            return true;
        }
        for (Game g : games) {
            if (g.findClient(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPlayerIsPlaying(String name) {
        return (clientHandler.isAPlayerIn(name));
    }


    public void remove(String name){
        for (Game g : games) {
            if (g.remove(name)) {
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
        Round round = games.get(match).getCurrRound();
        if(round.getCurrTurn().equals(name)){
            Proc processor = new Proc(round,move);
            processor.process();
        }else{
            setPlayerActivity(name,true);
        }
    }

    public void setPlayerActivity(String name,boolean b) {
        int numMatch = clientHandler.getGame(name);
        games.get(numMatch).setPlayerActivity(name,b);
    }

    public void reconnectPlayer(ClientInterface c) {
        try {
            ClientBox clientB = new ClientBox(c,c.getName(),c.getTypeConnection());
            int num = clientHandler.getGame(clientB.getName());
            clientB.setNumMatch(num);
            games.get(num).reconnect(clientB);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void matchEnded(String username) {
        clientHandler.removePlayer(username);
    }

    public void setPlayerWindow(int num, String name, String window) {
        games.get(num).setPlayerWindow(name,Integer.parseInt(window));
    }
}
