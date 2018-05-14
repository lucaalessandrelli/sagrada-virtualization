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

    public WaitingRoom(long time) {
        playerList = new ArrayList<>();
        this.time = time;
        timer = new Timer();
    }

    //Modifier methods
    public void addPlayer(Player player) {
        if (playerList.size() == 1) {
            playerList.add(player);
            //start the timer
            task = new MatchTimerTask(this,time, timer);
            timer.schedule(task, 1000,1000);
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
            this.resetTimer();
        }
    }

    public void resetTimer() {
        timer.cancel();
        timer = new Timer();
    }

    public void restore() {
        this.playerList = new ArrayList<Player>();
        //resetTimer
        this.resetTimer();
    }

    //Getter methods
    public List<Player> getPlayerList() {
        //return clonePlayerList();
        return this.playerList;
    }

    //Setter methods
    public void setServer(Server server){
        this.server = server;
    }

    public void notifyServer() {
        server.createMatch(this);
    }

    /*public List<Player> clonePlayerList() {
        List<Player> cloneList = new ArrayList<>();

        for (Player player: this.playerList) {
            cloneList.add(player.clonePlayer());
        }

        return cloneList;
    }*/
}
