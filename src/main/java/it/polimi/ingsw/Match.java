package it.polimi.ingsw;

import it.polimi.ingsw.controller.Manager;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamelogic.NotEnoughPlayersException;
import it.polimi.ingsw.model.gamelogic.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Match extends Thread {
    private  Manager manager;
    private int id;
    private List<Player> playerList;
    private Table table;
    private List<Round> roundList;
    private int roundNumber;
    private Round currRound;
    private long timerCard;
    private long timerMove;

    public Match(List<Player> playerList, Manager manager, int id, long timerCard, long timerMove) {
        this.timerCard=timerCard;
        this.timerMove=timerMove;
        this.playerList = playerList;
        this.id = id;
        this.roundList = new ArrayList<>();
        this.manager=manager;
        playerList.forEach(player -> player.timerChoose(timerCard));
    }
    

    @Override
    public void run() {
        table = new Table(playerList);
        table.initialize();
        table.selectWindowCards();
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
        exec.scheduleWithFixedDelay(() -> {
            for(Player p : playerList){
                if(p.getWindowPatternCard()==null){
                    return;
                }
            }
            this.interrupt();

        }, 0, 500, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(timerCard);
            playerList.stream().filter(player -> player.getWindowPatternCard()==null)
                    .forEach(player -> table.setWindow(player.getUsername()));
            exec.shutdown();
        } catch (InterruptedException e) {
            exec.shutdown();
        }
        playerList.forEach(Player::notifyPlayer);
        /*SETTING ROUNDS*/
        for(roundNumber = 1; roundNumber <= 10; roundNumber++) {
            //reminder to implement exception management later (in case the match end before)
            try {
                this.startNextRound();
                changeOrder();
                this.table.fillDraftPool();
                playerList.forEach(Player::notifyPlayer);
            } catch (NotEnoughPlayersException e) {
                playerList.stream().filter(Player::isActive)
                        .forEach(player -> player.notifyScore("score "+ player.getUsername()+player.calculatePoints()));
                playerList.forEach(player -> manager.matchEnded(player.getUsername()));
                return;
            }
        }

        //match is now ended - call methods to calculate player points

        //send points and name winner(to do)
        playerList.forEach(player -> player.notifyScore(computePlayerPoints()));
        playerList.forEach(player -> manager.matchEnded(player.getUsername()));
        manager.endGame(id);
    }

    private String computePlayerPoints() {
        StringBuilder builder = new StringBuilder();
        builder.append("score ");
        for(Player player: playerList) {
            builder.append(player.getUsername()).append(" ");
            builder.append(player.calculatePoints()).append(",").append(" ");
        }
        return builder.toString();
    }

    private void startNextRound() throws NotEnoughPlayersException {
            currRound = new Round(this.playerList, roundNumber,table,this,timerMove);
            roundList.add(currRound);
            // call on the round just created a method that start the round
            roundList.get(roundNumber-1).go();

    }

    private synchronized void changeOrder(){
        ArrayList tmpList = new ArrayList();
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
    public synchronized void setPlayerWindow(String name,int idCard){
        for(Player p : playerList){
            if (p.getUsername().equals(name)) {
                table.setWindow(p,idCard);
            }
        }
    }
}