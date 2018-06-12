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
    public boolean findDice(Dice dice, Pos pos){/*
        for(int i=0; i<diceOnRoundTrack.size();i++){
            for(int j=0; j<diceOnRoundTrack.get(i).size();j++){
                if((diceOnRoundTrack.get(i).get(j)).equals(dice)) {
                    return true;
                }
            }
        }*/
        return this.getDice(pos).equals(dice);
    }
    public Dice switchDice(Dice dice) throws NullPointerException {
        for(int i=0; i<diceOnRoundTrack.size();i++) {
            for (int j = 0; j < diceOnRoundTrack.get(i).size(); j++) {
                if (dice.equals(diceOnRoundTrack.get(i).get(j))) {
                    Dice tmp = diceOnRoundTrack.get(i).get(j);
                    diceOnRoundTrack.get(i).remove(j);
                    diceOnRoundTrack.get(i).add(j,dice);
                    return tmp;
                }
            }
        }
        throw new NullPointerException();
    }
    public Dice getDice(Pos pos){
        return this.diceOnRoundTrack.get(pos.getX()).get(pos.getY());
    }

    public void setDice(Dice d,Pos p){
        this.getDice(p).setDice(d);
    }

    public void resetSelection() {
        diceOnRoundTrack.forEach(dice -> dice.forEach(dice1 -> dice1.deSelect()));
    }
}
