package package1;

import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class MatchTimerTask extends TimerTask {
    private WaitingRoom lobby;
    private long time;
    private long tempTime;
    private Timer timer;
    private List<Player> playerList;

    public MatchTimerTask(WaitingRoom lobby, long time, Timer timer) {
        this.lobby = lobby;
        this.time = time;
        this.tempTime = time;
        this.timer = timer;
    }

    @Override
    public void run() {
        tempTime -= 1000;
        playerList = lobby.getPlayerList();

        if(playerList.size() >= 2 && tempTime == 0) {
            lobby.resetTimer();
            //CREATE A REAL MATCH
            lobby.notifyServer();
            System.out.println("Now the state is: StartedMatch");
        } else if(playerList.size() < 2 && tempTime == 0) {
            tempTime = time;
            System.out.println("Timer:"+ tempTime);
        } else {
            System.out.println("Timer:"+ tempTime);
        }
    }
}
