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
    private List<Dice> lastDice;
    private Table table;
    private long timeSleep;
    private Turn turn;

    public Round(List<Player> playerList, int roundNumber, Table table) {
        this.roundNumber = roundNumber;
        this.players = new PlayersContainer(playerList);
        this.table=table;
        int t =5;
        timeSleep = t*1000;
    }

    public void go() throws NotEnoughPlayersException {
        Player p = null;
        Iterator<Player> i = players.getIterator();
        while(i.hasNext()){
            p = i.next();
            if (p.isActive()){
                turn = new Turn(p, this, getRoundNumber(), players.isFirstBracket(), table);
                p.notifyPlayer("It's your turn");
                try {
                    Thread.sleep(timeSleep);
                    p.notifyPlayer("Time out");
                } catch (InterruptedException e) {
                    //go on next player
                }

            }
            if(!players.checkActivity()){
                throw new NotEnoughPlayersException();
            }
        }
        setLastDice(p);
    }


    private void setLastDice(Player p) {

       // this.lastDice = p.getLastDice();

    }

    public List<Dice> getLastDice() {
        return this.lastDice;
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
