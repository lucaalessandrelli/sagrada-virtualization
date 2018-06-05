package it.polimi.ingsw;

import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamelogic.NotEnoughPlayersException;
import it.polimi.ingsw.model.gamelogic.Round;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.network.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private  Manager manager;
    private int id;
    private List<Player> playerList;
    private Server server;
    private Table table;
    private List<Round> roundList;
    private int roundNumber;
    private Round currRound;

    public Match(List<Player> playerList, Manager manager, int id) {
        //this need to be passed by copy from the server
        this.playerList = playerList;
        this.id = id;
        this.roundList = new ArrayList<>();
        this.manager=manager;
    }

    public void start() {
        table = new Table(playerList);
        for (Player p : playerList){
            p.notifyPlayer();
        }
        //table.inizialize();

        /*SETTING ROUNDS*/
        for(roundNumber = 1; roundNumber <= 10; roundNumber++) {
            //reminder to implement exception management later (in case the match end before)

            try {
                this.startNextRound();
                changeOrder();
                this.table.fillDraftPool();
                for (Player p : playerList){
                    p.notifyPlayer();
                }
            } catch (NotEnoughPlayersException e) {
                //manager.notEnoughPlayer(e.getMessage()); //il giocatore rimasto vince
            }
        }

        //match is now ended - call methods to calculate player points
        this.computePlayerPoints();
        for(Player p : playerList){
            manager.matchEnded(p.getUsername());
        }
    }

    private void computePlayerPoints() {
        for(Player player: playerList) {
            player.calculatePoints();
        }
    }

    private void startNextRound() throws NotEnoughPlayersException {
            currRound = new Round(this.playerList, roundNumber,table);
            roundList.add(currRound);
            // call on the round just created a method that start the round
            roundList.get(roundNumber-1).go();

    }

    private synchronized void changeOrder(){
        List<Player> tmpList = new ArrayList();
        Player tmp;
        tmp = playerList.get(0);
        for (int i = 1; i<playerList.size();i++){
            tmpList.add(playerList.get(i));
        }
        tmpList.add(tmp);
        playerList = tmpList;
    }

    //Getter methods
    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public List<Round> getRoundList() {
        return this.roundList;
    }

    public Round getCurrRound() {
        return currRound;
    }

    public void setPlayerActivity(String name,boolean b) {
        for (Player p : playerList){
            if(p.getUsername().equals(name)){
                p.setActivity(b);
            }
        }
    }
}