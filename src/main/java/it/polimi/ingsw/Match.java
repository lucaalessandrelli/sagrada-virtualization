package it.polimi.ingsw;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.network.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private int id;
    private List<Player> playerList;
    private Server server;
    private Table table;
    private ArrayList<Round> roundList;
    private int roundNumber;

    public Match(ArrayList<Player> playerList, Server server, int id) {
        //this need to be passed by copy from the server
        this.playerList = playerList;
        this.server = server;
        this.id = id;
        this.roundList = new ArrayList<Round>();
    }

    public void start() {
        table = new Table(playerList);

        /*SETTING ROUNDS*/
        for(roundNumber = 1; roundNumber <= 10; roundNumber++) {
            //reminder to implement exception management later (in case the match end before)
            this.table.fillDraftPool();
            this.startNextRound();
        }

        //match is now ended - call methods to calculate player points
        this.computePlayerPoints();
    }

    private void computePlayerPoints() {
        for(Player player: playerList) {
            //player.calculatePoints();
        }
    }

    private void startNextRound() {
            roundList.add(new Round(this.playerList, roundNumber,table));
            /* call on the round just created a method that start the round
            roundList.get(roundNumber-1).startRound();
             */
    }

    //Getter methods
    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public List<Round> getRoundList() {
        return this.roundList;
    }
}