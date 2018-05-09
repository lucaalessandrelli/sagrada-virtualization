package package1;

import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class MatchTimerTask extends TimerTask {
    private Timer timer;
    private List<Player> playerList;
    private Match match;
    private WaitingRoom room;
    private long time;
    private long tempTime;

    public MatchTimerTask(List<Player> playerList, Match match, WaitingRoom room, long time, Timer timer) {
        this.playerList = playerList;
        this.match = match;
        this.room = room;
        this.time = time;
        this.tempTime = time;
        this.timer = timer;
    }

    @Override
    public void run() {
        tempTime -= 1000;
        if(playerList.size() >= 2 && tempTime == 0) {
            //change state
            room.changeState(match);
            System.out.println("Now the state is: StartedMatch");
            timer.cancel();
        } else if(playerList.size() < 2 && tempTime == 0) {
            tempTime = time;
            System.out.println("Timer:"+ tempTime);
        } else {
            System.out.println("Timer:"+ tempTime);
        }
    }
}
