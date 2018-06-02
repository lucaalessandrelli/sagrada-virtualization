package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientsContainer;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.network.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.*;

public class MatchTimerTask extends TimerTask {
    private WaitingRoom lobby;
    private long time;
    private long tempTime;
    private Timer timer;

    public MatchTimerTask(WaitingRoom lobby, long time, Timer timer) {
        this.lobby = lobby;
        this.time = time;
        this.tempTime = time;
        this.timer = timer;
    }

    @Override
    public void run() {
        tempTime -= 1000;
        ClientsContainer playerList = lobby.getCliets();

        if (playerList.sizeContainer() >= 2 && tempTime == 0) {
            lobby.resetTimer();
            //CREATE A REAL MATCH
            lobby.notifyManager();
            out.println("Now the state is: StartedMatch");
            if (playerList.sizeContainer() < 2 && tempTime == 0) {
                tempTime = time;
                //out.println("Timer:"+ tempTime);
            } else {
                //out.println("Timer:"+ tempTime);
            }
        }
    }

        synchronized public long getTempTime () {
            return tempTime;
        }


}
