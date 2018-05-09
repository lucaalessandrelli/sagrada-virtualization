package package1;

import java.lang.management.PlatformLoggingMXBean;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class WaitingRoom implements State {
    private Timer timer;
    private TimerTask task;
    private long time;
    //private long tempTime;

    public WaitingRoom(long time) {
        this.time = time;
        //this.tempTime = time;
        timer = new Timer();
    }

    public void addPlayer(List<Player> playerList, Player player,Match match) {
        if (playerList.size() == 1) {
            //start the timer
            task = new MatchTimerTask(playerList, match, this,time, timer);
            //this.startTimer(playerList,match);
            timer.schedule(task, 1000,1000);

            playerList.add(player);
        } else if (playerList.size() < 4) {
            playerList.add(player);
            //change state
            if(playerList.size() == 4) {
                this.changeState(match);
            }
        }
    }

    public void removePlayer(List<Player> playerList, Player player,Match match) {
        playerList.remove(player);

        //if the player to be remover is the last one then the match can be deleted
        if (playerList.size() == 0) {
            match.delete();
        } else if (playerList.size() < 2) { //if n°players drops below 2 timer needs to be reset and stopped
            //resetTimer
            timer.cancel();
            //tempTime = time;
            timer = new Timer();
        }
    }

    public void createRounds(List<Player> playerList, Match match,List<Round> roundList) {
        //do nothing, in the waitingRoom there is no need to create Rounds
    }

    public void changeState(Match match) {
        match.setState(new StartedMatch());
        match.registerMatch();
        //create Table, the game is about to start!
        //match.setTable(new Table());
        //The match is now in StartedMatch state so rounds need to be initialized;
        match.initializeRounds();
        match.start();
    }
}
