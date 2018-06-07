package it.polimi.ingsw.model.gamelogic;


import it.polimi.ingsw.Match;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.util.*;

public class Round {
    private int roundNumber;
    private PlayersContainer players;
    private Table table;
    private long timeSleep;
    private Turn turn;
    private Player currTurn;
    private Iterator<Player> iterator;
    private Match match;

    public Round(List<Player> playerList, int roundNumber, Table table,Match m) {
        this.roundNumber = roundNumber;
        this.players = new PlayersContainer(playerList);
        this.table=table;
        int t =30;
        timeSleep = t*1000;
        match = m;
    }

    public void go() {
        Player p;
        iterator = players.getIterator();
        while(iterator.hasNext()){
            p = iterator.next();
            currTurn = p;
            if (p.isActive()){
                turn = new Turn(p, this, getRoundNumber(), players.isFirstBracket(), table);
                turn.startTurn();
                players.notifyTurn(p.getUsername(),timeSleep);
                    try {
                        Thread.sleep(timeSleep);
                        p.setActivity(false);
                    } catch (InterruptedException e) {
                        players.notifyChanges();
                    }
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

    public void notifyPlayers(){
        players.notifyChanges();
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void setTurn(Pos p){
        try {
            turn.receiveMove(p);
            players.notifyChanges();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(String s){
        try {
            turn.receiveMove(s);
            players.notifyChanges();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(ToolCard t){
        try {
            turn.receiveMove(t);
            players.notifyChanges();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(Dice d, Pos p){
        try {
            turn.receiveMove(d,p);
            players.notifyChanges();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }
    public  void interrupt(){
        match.interrupt();
    }

}
