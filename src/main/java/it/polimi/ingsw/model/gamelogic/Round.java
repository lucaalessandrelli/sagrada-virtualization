package it.polimi.ingsw.model.gamelogic;


import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.turn.Turn;

import java.util.*;

public class Round extends Thread{
    private int roundNumber;
    private PlayersContainer players;
    private Table table;
    private long timeSleep;
    private Turn turn;
    private Player currTurn;

    public Round(List<Player> playerList, int roundNumber, Table table) {
        this.roundNumber = roundNumber;
        this.players = new PlayersContainer(playerList);
        this.table=table;
        int t =5;
        timeSleep = t*1000;
    }

    public void go() throws NotEnoughPlayersException {
        Player p;
        Iterator<Player> i = players.getIterator();
        while(i.hasNext()){
            p = i.next();
            currTurn = p;
            if (p.isActive()){
                turn = new Turn(p, this, getRoundNumber(), players.isFirstBracket(), table);
                players.notifyTurn(p.getUsername());
                try {
                    Thread.sleep(timeSleep);
                    p.setActivity(false);
                   // p.notifyPlayer("Time out"); messaggio per tutti
                } catch (InterruptedException e) {
                    players.notifyChanges();
                    //go on next player
                }

            }
            if(!players.checkActivity()){
                throw new NotEnoughPlayersException(players.getLastPlayer().getUsername());
            }
        }
        setLastDice();
    }


    private void setLastDice() {
        table.setLastDices(roundNumber);
    }

    public String getCurrTurn() {
        return currTurn.getUsername();
    }


    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void setTurn(Pos p){
        turn.receiveMove(p);
    }

    public void setTurn(String s){
        turn.receiveMove(s);
    }

    public void setTurn(ToolCard t){
        turn.receiveMove(t);
    }

    public void setTurn(Dice d, Pos p){
        turn.receiveMove(d,p);
    }

}
