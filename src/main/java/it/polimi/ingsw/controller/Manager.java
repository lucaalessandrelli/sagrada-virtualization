package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.network.ClientInterface;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager {
    private HashMap<Integer,Game> games;
    private ClientsContainer clients;
    private int numOfMatch;
    private WaitingRoom lobby;
    private ClientHandler clientHandler;
    private InputAnalyzer analyzer;
    private long timerCard;
    private long timerMove;

    public Manager(int timerRoom,int timerCard, int timerMove) {
        long timerRoom1 = timerRoom * 1000;
        this.timerCard=timerCard*1000;
        this.timerMove=timerMove*1000;
        games = new HashMap<>();
        numOfMatch = 0;
        clients = new ClientsContainer(this);
        lobby = new WaitingRoom(timerRoom1, this, clients);
        clientHandler = new ClientHandler();
        analyzer = new InputAnalyzer(this);
    }

    public void createMatch(WaitingRoom lobby) {
        Game g = new Game(clients,new Match(lobby.getPlayerList(), this, numOfMatch,timerCard,timerMove),numOfMatch);
        g.notifyGame();
        games.put(numOfMatch, g);
        /*START MATCH*/
        lobby.getPlayerList().forEach(player -> clientHandler.addPlayer(player.getUsername(), numOfMatch));
        g.start();
        numOfMatch++;
        clients = new ClientsContainer(this);
        lobby.restore(clients);
    }

    public synchronized void addPlayerInQueue(ClientInterface client) {
        lobby.addPlayer(client);
    }

    public synchronized boolean checkIfPlayerIsLogged(String name) {
        if(clients.findClient(name)){
            return true;
        }
        for (Map.Entry<Integer, Game> pair : games.entrySet()) {
            if (pair.getValue().findClient(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPlayerIsPlaying(String name) {
        return (clientHandler.isAPlayerIn(name));
    }


    public void remove(String name){
        clients.remove(name);
        for (Map.Entry<Integer,Game> pair : games.entrySet()) {
            if (pair.getValue().remove(name)) {
                return;
            }
        }
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

    void setPlayerActivity(String name, boolean b) {
        int numMatch = clientHandler.getGame(name);
        games.get(numMatch).setPlayerActivity(name,b);
    }

    public void reconnectPlayer(ClientInterface c) {
        try {
            ClientBox clientB = new ClientBox(c,c.getName());
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

    void setPlayerWindow(int num, String name, String window) {
        games.get(num).setPlayerWindow(name,Integer.parseInt(window));
    }

    void disconnectPlayer(int num, String name) {
        games.get(num).remove(name);
    }

    public void endGame(int id) {
        games.get(id).setEnd();
    }
    public void checkEndGame(){
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
        exec.scheduleWithFixedDelay(() -> {
            for (Map.Entry<Integer,Game> pair : games.entrySet()) {
                if(pair.getValue().endedMatch()&&pair.getValue().sizeClient()==0){
                    games.remove(pair.getValue());
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    void revenge(String name) {
        for (Map.Entry<Integer,Game> pair : games.entrySet()){
            if(pair.getValue().findClient(name)){
                lobby.addPlayer(pair.getValue().getClientBox(name));
            }
        }
    }
}
