package it.polimi.ingsw.model.gameData;


import java.util.ArrayList;
import java.util.Random;

public class DiceBag {
    private ArrayList<Dice> dice = new ArrayList<Dice>(90);
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
                dice.add(x);
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
        return dice.size();
    }

    //pull out (2numPlayers + 1) dice from bag (Tested, should work)
    public ArrayList<Dice> pullOut(){
        int dimension = (2*numPlayers) + 1;
        int cont = dice.size();
        ArrayList<Dice> tmp = new ArrayList<Dice>(dimension);
        Dice z;
        int randomNum;
        Random rand = new Random();
        for(int k = 0; k < dimension; k++) {
            randomNum = rand.nextInt((cont));
            z = dice.get(randomNum);
            dice.remove(randomNum);
            cont--;
            tmp.add(z);
        }
        return tmp;
    }

    /*method for toolCard n 11
    public Dice putIn(Dice d){

    }*/
}
