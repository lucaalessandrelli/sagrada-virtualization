package it.polimi.ingsw.model.gamedata.gametools;


import it.polimi.ingsw.model.gamedata.Colour;

import java.util.ArrayList;
import java.util.Random;

public class DiceBag {
    private ArrayList<Dice> dicecontainer = new ArrayList<>(90);
    private int numPlayers;

    public DiceBag(){
        int i,j;
        Colour coll[] = new Colour[5];
        coll[0] = Colour.BLUE;
        coll[1] = Colour.GREEN;
        coll[2] = Colour.PURPLE;
        coll[3] = Colour.RED;
        coll[4] = Colour.YELLOW;
        for(i = 0; i <5; i++){
            for(j = 0; j <18; j++){
                Dice x = new Dice(coll[i]);
                dicecontainer.add(x);
            }
        }
    }

    //setter method
    public void setNumPlayers(int n){
        if(n > 0 && n < 5)
            this.numPlayers = n;
        else
            System.out.println("Invalid number of players");
    }

    //getter method
    public int remainingDices(){
        return dicecontainer.size();
    }

    //pull out (2numPlayers + 1) dice from bag (Tested, should work)
    public ArrayList<Dice> pullOut(){
        int dimension = (2*numPlayers) + 1;
        ArrayList<Dice> result = new ArrayList<>(dimension);
        randomPullOut(dimension,result,dicecontainer.size());
        return result;
    }

    public void addDice(Dice dice){
        this.dicecontainer.add(dice);
    }


    //pull out number dice from bag (Tested, should work)
    public ArrayList<Dice> pullOut(int number){
        ArrayList<Dice> tmp = new ArrayList<>(number);
        randomPullOut(number,tmp,dicecontainer.size());
        return tmp;
    }

    public void randomPullOut(int number, ArrayList<Dice> tmp, int cont){
        int randomNum;
        Random rand = new Random();
        Dice randomdice;
        for(int k = 0; k < number; k++) {
            randomNum = rand.nextInt((cont));
            randomdice = dicecontainer.get(randomNum);
            tmp.add(randomdice);
            dicecontainer.remove(randomNum);
            cont--;
        }
    }
}
