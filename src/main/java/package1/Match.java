package package1;

import java.util.List;
import java.util.*;

public class Match {
    private Server server;
    private int id;
    private Table table;
    private List<Player> playerList;
    private State state;
    //round array declaration
    private Round[] roundArray;

    //When Match is created its state is "WaitingRoom"
    public Match(int id/*, Server server*/){
        //this.server = server;
        this.id = id;
        //this.table = new Table();
        playerList = new ArrayList<Player>();
        state = new WaitingRoom(5000);
        //round array initialize
        roundArray = new Round[10];
    }

    //getter method
    public int getId() {
        return this.id;
    }

    //getter method
    public Table getTable() {
        return this.table;
    }

    //returns the list of players assigned to a match
    public List<Player> getPlayerList() {
        return playerList;
    }


    //get current status of Match(State)
    public State getState() {
        return state;
    }

    //Setter method
    public void setState(State state) {
        this.state = state;
    }

    //add a Player to a Match
    public void addPlayer(Player player){
        state.addPlayer(playerList, player, this);
    }

    //remove a Player from a Match
    public void removePlayer(Player player){
        state.removePlayer(playerList, player, this);
    }

    //add to the hashTable players and id match
    public void registerMatch() {
        //server.addRecord(playerList, id);
    }

    //remove from the hashTable players and id match and deletes the match instance
    public void delete() {
        //server.removeRecord(id);
        //server.deleteMatch(this);
    }
}
