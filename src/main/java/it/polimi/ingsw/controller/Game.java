package it.polimi.ingsw.controller;

import com.sun.deploy.ClientContainer;
import it.polimi.ingsw.Match;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.network.client.Client;

public class Game {
    private ClientsContainer clients;
    private Match match;
    private int id;

    Game(ClientsContainer c, Match m, int i){
        clients=c;
        match=m;
        id=i;
    }
    void reconnect(ClientBox cb){
        match.setPlayerActivity(cb.getName(),true);
        clients.reconnect(cb);
    }

    void notifyGame(){
        clients.notifyIdMatch(id);
        clients.setMatchStarted(true);

    }

    public void start() {
        match.start();
    }

    public boolean findClient(String name) {
        return clients.findClient(name);
    }

    public boolean remove(String name) {
        return clients.remove(name);
    }

    public Round getCurrRound() {
        return match.getCurrRound();
    }

    public void setPlayerActivity(String name, boolean b) {
        match.setPlayerActivity(name,b);
    }
}
