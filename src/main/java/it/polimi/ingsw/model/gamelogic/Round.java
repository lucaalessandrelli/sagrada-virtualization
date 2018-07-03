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

/**
 * This class handle the timeout for a move and set the move to the object Turn, which verify the it correctness
 */
public class Round {
    private static final String PATH ="it.polimi.ingsw.turn.";

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
        this.match = m;
    }

    /**
     * This method starts the round,notify all players who has to move and it does a thread.sleep(), if the player will not do the move
     * it will be set inactive. If he does a right move, will be called an interrupt on the sleep and the round goes on with the turn of next
     * player.
     * @throws NotEnoughPlayersException
     */
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

    /**
     * This method set last dices on round track at the end of round.
     */
    private void setLastDice() {
        table.setLastDices(roundNumber);
    }

    /**
     *
     * @return name of player who has turn
     */
    public String getCurrTurn() {
        return currTurn.getUsername();
    }

    /**
     *
     * @return round number
     */
    private int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * sends the current state of move
     */
    private void updateAfterMove(){
        players.notifyChanges();
        String name = turn.getState().getClass().getName();
        name = name.replace(PATH,"");
        currTurn.notifyState(name);
    }

    /**
     * Set chosen position to the Turn
     * @param p chosen position
     */
    public void setTurn(Pos p){
        try {
            turn.receiveMove(p);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    /**
     * Set pass to the Turn
     * @param s pass string
     */
    public void setTurn(String s){
        try {
            turn.receiveMove(s);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    /**
     * Set chosen tool card to the Turn
     * @param t chosen toolcard
     */
    public void setTurn(ToolCard t){
        try {
            turn.receiveMove(t);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    /**
     * Set chosen dice to the Turn and its position in the model
     * @param d chosen dice
     * @param p dice position in draft pool, roundtrack or window pattern card
     */
    public void setTurn(Dice d, Pos p){
        try {
            turn.receiveMove(d,p);
            updateAfterMove();
        } catch (WrongMoveException e) {
            currTurn.wrongMove(e.getMessage());
        }
    }

    /**
     * used when a player uses tool card 8
     * @param p
     */
    public void inactivatePlayer(Player p) {
        if (currTurn != p) {
            exec.scheduleWithFixedDelay(() -> p.setActivity(false), 0, 500, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Used when a move ends.
     */
    public  void interrupt(){
        match.interrupt();
    }

}
