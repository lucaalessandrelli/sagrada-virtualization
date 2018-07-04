package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Pos;
import java.util.ArrayList;
import java.util.List;

public class RoundTrack {
    private List<List<Dice>> diceOnRoundTrack;

    public RoundTrack(){
        diceOnRoundTrack = new ArrayList<>();
    }

    public List<Dice> getDiceOnRoundtrack(int numRound){
        return diceOnRoundTrack.get(numRound-1);
    }

    public void setDiceOnRoundTrack(int numRound, List<Dice> dice){
        diceOnRoundTrack.add(numRound-1, dice);
    }

    public List<List<Dice>> getRoundTrack(){
        return diceOnRoundTrack;
    }

    public boolean findDice(Dice dice, Pos pos){
        return this.getDice(pos).equals(dice);
    }

    public Dice switchDice(Dice dice) {
        for (List<Dice> aDiceOnRoundTrack : diceOnRoundTrack) {
            for (int j = 0; j < aDiceOnRoundTrack.size(); j++) {
                if (dice.equals(aDiceOnRoundTrack.get(j))) {
                    Dice tmp = aDiceOnRoundTrack.get(j);
                    aDiceOnRoundTrack.remove(j);
                    aDiceOnRoundTrack.add(j, dice);
                    return tmp;
                }
            }
        }

        throw new IllegalStateException();
    }
    public Dice getDice(Pos pos){
        return this.diceOnRoundTrack.get(pos.getX()).get(pos.getY());
    }

    public void removeDice(Pos pos) {
        this.diceOnRoundTrack.get(pos.getX()).remove(pos.getY());
    }

    public void addDice(Pos pos,Dice dice) {
        this.diceOnRoundTrack.get(pos.getX()).add(pos.getY(), dice);
    }

    public void setDice(Dice d,Pos p){
        this.getDice(p).setDice(d);
    }

    public void resetSelection() {
        diceOnRoundTrack.forEach(dice -> dice.forEach(Dice::deSelect));
    }
}
