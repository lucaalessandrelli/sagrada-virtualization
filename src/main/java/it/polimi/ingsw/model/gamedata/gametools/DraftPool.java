package it.polimi.ingsw.model.gamedata.gametools;

import java.util.ArrayList;
import java.util.List;

public class DraftPool {
    private List<Dice> diceList;

    public DraftPool(){
        diceList = new ArrayList<>(0);
    }

    //the number x is the number of players present in the match
    public DraftPool(int x){
        int y = (x * 2) + 1;
        this.diceList = new ArrayList<>(y);
    }

    //getter method
    public List<Dice> getDraftPool() {
        return this.diceList;
    }

    //get dice from array at position i
    public Dice chooseDice(int i){
        if(i >= 0 && i < diceList.size()) {
            return diceList.get(i);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    //add new dices that have been drown out from the bag
    public void addNewDices(List<Dice> newDice){
        this.diceList = newDice;
    }

    //gives back the number of dices remaining in the draftpool
    public int getNumOfDices(){
        return diceList.size();
    }

    /**
     * Returns true if the dices passed (d) is the same as the one that is at the position where of the draftpool
     * @param d A dice
     * @param where The position
     * @return a boolean
     */
    public boolean findDice(Dice d, int where){
        return this.diceList.get(where).equals(d);
    }

    public boolean findDice(Dice d){
        for (Dice x: this.diceList){
            if (x.equals(d))
                return true;
        }
        return false;
    }

    /**
     * Removes a dice from the array if is present
     * @param where The position where the dice is
     */
    public void removeDice(int where){
        this.diceList.remove(where);
    }

    public void addDice(int index, Dice dice) {
        this.diceList.add(index,dice);
    }

    public void resetSelection() {
        diceList.forEach(Dice::deSelect);
    }
}