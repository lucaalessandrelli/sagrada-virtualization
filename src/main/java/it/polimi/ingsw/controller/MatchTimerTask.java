package it.polimi.ingsw.controller;

import java.util.TimerTask;

/**
 * Timer to handle the waiting room.
 */
public class MatchTimerTask extends TimerTask {
    private WaitingRoom lobby;
    private long time;
    private long tempTime;

    MatchTimerTask(WaitingRoom lobby, long time) {
        this.lobby = lobby;
        this.time = time;
        this.tempTime = time;
    }

    @Override
    public void run() {
        tempTime -= 1000;
        ClientsContainer playerList = lobby.getClients();

        if (playerList.sizeContainer() >= 2 && tempTime == 0) {
            lobby.resetTimer();
            //CREATE A REAL MATCH
            lobby.notifyManager();
        }
        else if (playerList.sizeContainer() < 2 && tempTime == 0) {
            tempTime = time;
        }

    }

    synchronized long getTempTime () {
            return tempTime;
        }


}
