package package1;

import java.util.ArrayList;

public class DraftPool {
    private ArrayList<Dice> draftPool;

    //the number x, is the number of players present in the match
    public DraftPool(int x){
        ArrayList<Dice> tmp = new ArrayList<Dice>((x*2)+1);
        Dice k = new Dice();
        for(int i = 0; i < (x*2)+1; i++){
            tmp.add(k);
        }
        this.draftPool = tmp;
    }

    //getter method
    public ArrayList<Dice> getDraftPool() {
        return draftPool;
    }

    //get dice from array at position i
    public Dice chooseDice(int i) throws IndexOutOfBoundsException{
        try {
            if (draftPool.size() > 1) {
                Dice d = draftPool.get(i);
                draftPool.remove(i);
                return d;
            }
            else{
                IndexOutOfBoundsException e = new IndexOutOfBoundsException();
                throw e;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("The size of the array is under 1");
            throw e;
        }
    }

    //show all dice in
    public void showDraftPool(){
    }

    //add new dices that have been drown out from the bag
    public void addNewDices(ArrayList<Dice> newDice){
        draftPool = newDice;
    }

    //gives back the number of dices remaining in the drafpool
    public int getNumOfDices(){
        return draftPool.size();
    }

}
