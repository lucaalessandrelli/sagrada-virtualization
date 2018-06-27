package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WaitingRoom {
    private Manager manager;
    private MatchTimerTask task;
    private long time;
    private ClientsContainer playerList;

    public WaitingRoom(long time, Manager manager, ClientsContainer clientContainer) {
        playerList = clientContainer;
        this.time = time;
        this.manager = manager;
        task = new MatchTimerTask(this,time);
    }

    //Modifier methods
    public void addPlayer(ClientInterface player) {
        if (playerList.sizeContainer() == 1) {
            playerList.addClient(player,task.getTempTime());
            playerList.notifyPlayers();
            //start the timer
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleWithFixedDelay(task,0,1000,TimeUnit.MILLISECONDS);
        } else if (playerList.sizeContainer() < 4) {
            playerList.addClient(player,task.getTempTime());
            playerList.notifyPlayers();
            if(playerList.sizeContainer() == 4) {
                //NOTIFY THE SERVER IT NEEDS TO CREATE A REAL MATCH AND ADD IT TO THE LIST OF MATCHES
                this.notifyManager();
            }
        }
    }

    void resetTimer() {
        task = new MatchTimerTask(this,time);
    }

    public void restore(ClientsContainer clientContainer) {
        this.playerList = clientContainer;
        //resetTimer
        this.resetTimer();
    }

    //Getter methods
    public List<Player> getPlayerList() {
        return playerList.getPlayerList();
    }


    void notifyManager() {
        manager.createMatch(this);
    }

    ClientsContainer getClients() {
        return playerList;
    }

}
