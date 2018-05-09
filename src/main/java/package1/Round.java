package package1;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private Match match;
    private int roundNumber;
    private List<Player> playerList;
    private DiceBag diceBag;
    private boolean status;
    private Dice lastDice;

    public Round(Match match, List<Player> playerList, int roundNumber) {
        this.match = match;
        this.playerList = playerList;
        this.roundNumber = roundNumber;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setLastDice(Dice lastDice) {
        this.lastDice = lastDice;
    }

    public Dice getLastDice() {
        return this.lastDice;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

}
