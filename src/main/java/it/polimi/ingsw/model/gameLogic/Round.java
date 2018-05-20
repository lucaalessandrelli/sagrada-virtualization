package it.polimi.ingsw.model.gameLogic;


import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.turn.Turn;

import java.util.*;

public class Round {
    private int roundNumber;
    private PlayersContainer players;
    private List<Dice> lastDice;

    public Round(List<Player> playerList, int roundNumber) {
        this.roundNumber = roundNumber;
        players = new PlayersContainer(playerList);
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
