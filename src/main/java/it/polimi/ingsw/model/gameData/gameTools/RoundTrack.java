package it.polimi.ingsw.model.gameData.gameTools;

import java.util.ArrayList;

public class RoundTrack {
    private ArrayList<ArrayList<Dice>> diceOnRoundTrack;

    public RoundTrack(){
        diceOnRoundTrack = new ArrayList<>();
    }

    public ArrayList<Dice> getDiceOnRoundtrack(int numRound){
        return diceOnRoundTrack.get(numRound);
    }
    public void setDiceOnRoundTrack(int numRound, ArrayList<Dice> dice){
        diceOnRoundTrack.add(numRound, dice);
    }
    public ArrayList<ArrayList<Dice>> getRoundTrack(){
        return diceOnRoundTrack;
    }
    public boolean findDice(Dice dice){
        for(int i=0; i<diceOnRoundTrack.size();i++){
            for(int j=0; j<diceOnRoundTrack.get(i).size();j++){
                if(dice.equals(diceOnRoundTrack.get(i).get(j))) {
                    return true;
                }
            }
        }
        return false;
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
}
