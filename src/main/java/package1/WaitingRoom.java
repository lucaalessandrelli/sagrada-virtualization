package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WaitingRoom {
    private Server server;
    private List<Player> playerList;
    private Timer timer;
    private TimerTask task;
    private long time;

    public WaitingRoom(long time, Server server) {
        this.server = server;
        playerList = new ArrayList<>();
        this.time = time;
        //this.tempTime = time;
        timer = new Timer();
    }

    //Modifier methods
    public void addPlayer(Player player) {
        if (playerList.size() == 1) {
            //start the timer
            task = new MatchTimerTask(this,time, timer);
            //this.startTimer(playerList,match);
            timer.schedule(task, 1000,1000);
            playerList.add(player);

        } else if (playerList.size() < 4) {
            playerList.add(player);
            if(playerList.size() == 4) {
                //NOTIFY THE SERVER IT NEEDS TO CREATE A REAL MATCH AND ADD IT TO THE LIST OF MATCHES
                this.notifyServer();
            }
        }
    }

    public void removePlayer(Player player) {
        playerList.remove(player);

        if (playerList.size() < 2) { //if n°players drops below 2 timer needs to be reset and stopped
            //resetTimer
            timer.cancel();
            //tempTime = time;
            timer = new Timer();
        }
    }

    public void restore() {
        for (Player player: playerList) {
            playerList.remove(player);
        }
        //resetTimer
        timer.cancel();
        //tempTime = time;
        timer = new Timer();
    }

    //Getter methods
    public List<Player> getPlayerList() {
        /*
        MAKE A CLONE COPY OF playerList

        return clonePlayerList(playerList);
        */
        return playerList;
    }

    //Setter methods


    public void notifyServer() {
        server.createMatch(this);
    }

    public List<Player> clonePlayerList(List<Player> playerList) {
        List<Player> cloneList = new ArrayList<>();

        for (Player player: playerList) {
            //cloneList.add(player.clonePlayer(player));
        }

        return cloneList;
    }
}
