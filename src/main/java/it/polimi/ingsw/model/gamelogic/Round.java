package it.polimi.ingsw.model.gamelogic;


import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.turn.Turn;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Round {
    private int roundNumber;
    private PlayersContainer players;
    private Table table;
    private Turn turn;
    private Player currTurn;
    private Match match;
    private long timerMove;
    private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

    public Round(List<Player> playerList, int roundNumber, Table table, Match m, long timerMove) {
        this.roundNumber = roundNumber;
        this.players = new PlayersContainer(playerList);
        this.table=table;
        this.timerMove =timerMove;
        match = m;
    }

    public void go() throws NotEnoughPlayersException {
        Player p;
        Iterator<Player> iterator = players.getIterator();
        while(iterator.hasNext()){
            p = iterator.next();
            currTurn = p;
            if (p.isActive()) {
                turn = new Turn(p, this, getRoundNumber(), players.isFirstBracket(), table);
                turn.startTurn();
                players.notifyTurn(p.getUsername(), timerMove);
                try {
                    Thread.sleep(timerMove);
                    p.setActivity(false);
                } catch (InterruptedException e) {
                    players.notifyChanges();
                }
                if (!players.checkActivity()) {
                    throw new NotEnoughPlayersException();
                }
                table.resetSelection();
            }
        }
        setLastDice();
        if(!exec.isTerminated()){
            exec.shutdown();
        }
    }


    private void setLastDice() {
        table.setLastDices(roundNumber);
    }

    public String getCurrTurn() {
        return currTurn.getUsername();
    }

    private int getRoundNumber() {
        return this.roundNumber;
    }

    private void updateAfterMove(){
        players.notifyChanges();
        String name = turn.getState().getClass().getName();
        String[] pkg= name.split(".");
        name=pkg[pkg.length-1];
        currTurn.notifyState(name);
    }

    public void setTurn(Pos p){
        try {
            turn.receiveMove(p);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(String s){
        try {
            turn.receiveMove(s);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(ToolCard t){
        try {
            turn.receiveMove(t);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void setTurn(Dice d, Pos p){
        try {
            turn.receiveMove(d,p);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    public void inactivatePlayer(Player p) {
        if (currTurn != p) {
            exec.scheduleWithFixedDelay(() -> p.setActivity(false), 0, 500, TimeUnit.MILLISECONDS);
        }
    }

    public  void interrupt(){
        match.interrupt();
    }

}
