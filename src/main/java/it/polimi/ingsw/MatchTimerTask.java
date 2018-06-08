package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientsContainer;

import java.util.TimerTask;

public class MatchTimerTask extends TimerTask {
    private WaitingRoom lobby;
    private long time;
    private long tempTime;

    public MatchTimerTask(WaitingRoom lobby, long time) {
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
        //out.println("Now the state is: StartedMatch");
        else if (playerList.sizeContainer() < 2 && tempTime == 0) {
            tempTime = time;
            //out.println("Timer:"+ tempTime);
        }

    }

    synchronized public long getTempTime () {
            return tempTime;
        }


}
