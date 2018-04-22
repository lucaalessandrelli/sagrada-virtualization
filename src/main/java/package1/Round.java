package package1;

import java.util.ArrayList;

public class Round {
    private ArrayList<Player> playerList;
    private DiceBag diceBag;
    private boolean status;
    private Dice lastDice;
    private TurnHandler currentTurn;

    public Round() {

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

}
