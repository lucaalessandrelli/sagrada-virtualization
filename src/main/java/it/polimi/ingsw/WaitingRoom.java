package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientsContainer;
import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.ClientInterface;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WaitingRoom {
    private Manager manager;
    private Timer timer;
    private MatchTimerTask task;
    private long time;
    private ClientsContainer playerList;

    public WaitingRoom(long time, Manager manager, ClientsContainer clientContainer) {
        playerList = clientContainer;
        this.time = time;
        timer = new Timer();
        this.manager = manager;
        task = new MatchTimerTask(this,time, timer);
    }

    //Modifier methods
    public void addPlayer(ClientInterface player) {
        if (playerList.sizeContainer() == 1) {
            playerList.addClient(player);
            //start the timer
            timer.schedule(task, 1000,1000);
        } else if (playerList.sizeContainer() < 4) {
            playerList.addClient(player);
            playerList.notifyTimer(task.getTempTime());
            if(playerList.sizeContainer() == 4) {
                //NOTIFY THE SERVER IT NEEDS TO CREATE A REAL MATCH AND ADD IT TO THE LIST OF MATCHES
                this.notifyManager();
            }
        }
    }

    /*public void removePlayer(Player player) {
        playerList.remove(player);

        if (playerList.size() < 2) { //if n°players drops below 2 timer needs to be reset and stopped
            //resetTimer
            this.resetTimer();
        }
    }*/

    public void resetTimer() {
        timer.cancel();
        timer = new Timer();
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


    public void notifyManager() {
        manager.createMatch(this);
    }

    public ClientsContainer getCliets() {
        return playerList;
    }

    /*public ArrayList<Player> clonePlayerList() {
        ArrayList<Player> cloneList = new ArrayList<>();

        for (Player player: this.playerList) {
            cloneList.add(player.clonePlayer());
        }

        return cloneList;
    }*/
}
