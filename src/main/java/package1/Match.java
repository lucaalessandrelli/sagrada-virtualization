package package1;

import java.lang.management.PlatformLoggingMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Match {
    private int id;
    private List<Player> playerList;
    private List<Player> connectedPlayerList;
    private Server server;
    private Table table;
    private List<Round> roundList;
    private int roundNumber;

    public Match(List<Player> playerList, Server server, int id) {
        //this need to be passed by copy from the server
        this.playerList = playerList;
        this.server = server;
        this.id = id;
        this.roundNumber = 1;
        this.roundList = new ArrayList<Round>();
    }

    public void start() {
        //do i need to give playerList to the table?
        // who starts up all the cards for the table??? match or table?
        // who call methods on players to let them chose the window pattern card?
        table = new Table();

        //set connectedPlayerList
        connectedPlayerList = this.getPlayerList();

        /*SETTING ROUNDS*/
        //need to call a method that manage rounds
        this.startNextRound();

    }

    //method called by the previous round right before it ends
    public void startNextRound() {
            roundList.add(new Round(this, this.connectedPlayerList, roundNumber));
            /* call on the round just created a method that start the round
            roundList.get(roundNumber).startRound();
             */
            roundNumber++;
    }

    //method called by the last round to communicate the match ended and player points need to be calculated
    public void matchEnded() {
        //call methods on players to let them calculate their points
        /*for (Player player: playerList) {
            player.calculatePoints();
        }*/
    }

    //method called by the server to communicate the match that a particular player has lost the connection
    public void disconnectedPlayer(Player player) {
        connectedPlayerList.remove(player);
        //communicate to the rounds that the player has disconnected
        /*roundList.get(roundNumber-1).removePlayer(player);
         */
    }

    //method called by the server to communicate the match that a particular player has reconnected to the match
    public void reconnectPayer(Player player) {
        connectedPlayerList.add(player);
        //communicate to round that the player has reconnected
        /*roundList.get(roundNumber-1).addPlayer(player);
        */
    }

    //Getter methods
    public List<Player> getPlayerList() {
        return clonePlayerList(playerList);
    }

    public List<Player> getConnectedPlayerList() {
        return this.cloneConnectedPlayerList(connectedPlayerList);
    }

    public List<Player> clonePlayerList(List<Player> playerList) {
        List<Player> cloneList = new ArrayList<>();

        for(Player player: playerList) {
            cloneList.add(player.clonePlayer());
        }

        return cloneList;
    }

    public List<Player> cloneConnectedPlayerList(List<Player> connectedPlayerList) {
        List<Player> cloneList = new ArrayList<>();

        for(Player player: connectedPlayerList) {
            cloneList.add(player.clonePlayer());
        }

        return cloneList;
    }

    public List<Round> getRoundList() {
        return roundList;
    }
}
