package it.polimi.ingsw.model.gamedata.gametools;

import java.util.ArrayList;

public class DraftPool {
    private ArrayList<Dice> draftPool;

    public DraftPool(){
        draftPool = new ArrayList<>(0);
    }

    //the number x is the number of players present in the match
    public DraftPool(int x){
        int y = (x * 2) + 1;
        this.draftPool = new ArrayList<>(y);
    }

    //getter method
    public ArrayList<Dice> getDraftPool() {
        return this.draftPool;
    }

    //get dice from array at position i
    public Dice chooseDice(int i) throws IndexOutOfBoundsException{
        try {
            if (draftPool.size() > 1) {
                Dice d = draftPool.get(i);
                return d;
            }
            else{
                throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("The size of the array is under 1");
            throw e;
        }
    }

    //show all dice in
    public void showDraftPool(){
        System.out.print("This is the draftpool:" );
        for (Dice x: this.draftPool) {
            x.show();
        }
    }

    //add new dices that have been drown out from the bag
    public void addNewDices(ArrayList<Dice> newDice){
        this.draftPool = newDice;
    }

    //gives back the number of dices remaining in the draftpool
    public int getNumOfDices(){
        return draftPool.size();
    }

    /*/**
     * Returns true if the dices passed (d) is the same as the one that is at the position where of the draftpool
     * @param d A dice
     * @param where The position
     * @return a boolean
     */
    /*public boolean findDice(Dice d, int where){
        return this.draftPool.get(where).equals(d);
    }*/

    public boolean findDice(Dice d){
        for (Dice x: this.draftPool){
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
        this.draftPool.remove(where);
    }


    public void setDice(Dice d, int where){
        this.draftPool.get(where).setDice(d);
    }
}