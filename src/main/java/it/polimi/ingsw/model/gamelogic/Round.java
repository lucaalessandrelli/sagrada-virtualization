package it.polimi.ingsw.model.gamelogic;


import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Table;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.turn.Turn;

import java.util.*;

public class Round {
    private int roundNumber;
    private PlayersContainer players;
    private List<Dice> lastDice;
    private Table table;

    public Round(List<Player> playerList, int roundNumber, Table table) {
        this.roundNumber = roundNumber;
        players = new PlayersContainer(playerList);
        this.table=table;
    }

    public void start() throws NotEnoughPlayersException {
        Player p = null;
        Iterator<Player> i = players.getIterator();
        while(i.hasNext()){
            p = i.next();
            if (p.isActive()){
                Turn t = new Turn(p,getRoundNumber(),players.isFirstBracket());
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

}
