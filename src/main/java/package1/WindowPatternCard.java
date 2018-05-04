package package1;

import java.util.ArrayList;
import java.util.Random;

public class WindowPatternCard {
    private int num;
    private Cell[][] matr = new Cell[4][5];
    private int difficulty;
    private String name;
    //private int cont = 12; I'm not sure about this so the method and this attribute is all commented

    public WindowPatternCard(int num, int difficulty, String name){
        this.num = num;
        this.difficulty = difficulty;
        this.name = name;
    }

    public int getNum(){
        return this.num;
    }

    //getter
    public int getDifficulty(){
        return this.difficulty;
    }

    public Cell[][] getMatr(){ return this.matr;}

    //shows every attribute of the Card, also the scheme
    public void show(){
        System.out.println("ID number :" + num + "\n" + "Difficulty :" + difficulty + "\n" + "Name :" + name + "Scheme :\n" + "\n");
        for(int i = 0; i <= matr.length; i++){
            System.out.println(" " + i + " ");
            for (int j = 0; j <= matr[i].length ; j++) {
                if (i == 0) {
                    System.out.println(" " + j + " ");
                }
                else
                    System.out.println(" " + "matr[i][j]" + " ");
                if( j == matr[i].length) {
                    System.out.println("\n");
                }
            }
        }
    }

    //Pull out 4 WindowPatternCard given to the player to select one of them
   /* !!!!Is pseudo-code!!!!!
      public ArrayList<WindowPatternCard> pullOut(){
        int dimension = 4;
        ArrayList<WindowPatternCard> tmp = new ArrayList<WindowPatternCard>(dimension);
        int randomNum;
        Random rand = new Random();
        for(int k = 0; k < dimension; k++) {
            randomNum = rand.nextInt((cont));
            Here I need to take from the configuration file the randomNum card
            Here I need to "sign" the card so that in the next cycle I won't take the same card I took before
            this.cont--;
            tmp.add(z); Supposing that z is the selected card from this "turn"
        }
        return tmp;
    }
    */

    //place the dice
    public Boolean placeDice(Dice d, int x, int y){
        /*Here we need the validation of the rules*/
        if((matr[x][y] != null) && matr[x][y].isOccupied())
            return false;
        else{
            matr[x][y] = new Cell();
            matr[x][y].setOccupation(true);
            matr[x][y].getProperty().setColour(d.getColour());
            matr[x][y].getProperty().setNumber(d.getNumber());
            return true;
        }
    }
}