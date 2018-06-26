package it.polimi.ingsw.controller;


import it.polimi.ingsw.Match;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.network.ClientInterface;

public class Game {
    private ClientsContainer clients;
    private Match match;
    private int id;
    private boolean ended;

    Game(ClientsContainer c, Match m, int i){
        clients=c;
        match=m;
        id=i;
        ended=false;
    }
    void reconnect(ClientBox cb){
        match.setPlayerActivity(cb.getName(),true);
        clients.reconnect(cb);
        //match.update();

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


    public void setPlayerWindow(String name, int idWindow) {
        match.setPlayerWindow(name,idWindow);
    }

    public void setEnd() {
        ended=true;
    }

    public boolean endedMatch() {
        return ended;
    }

    public int sizeClient() {
        return clients.sizeContainer();
    }

    public ClientInterface getClientBox(String name) {
        return clients.getClientBox(name);
    }
}
