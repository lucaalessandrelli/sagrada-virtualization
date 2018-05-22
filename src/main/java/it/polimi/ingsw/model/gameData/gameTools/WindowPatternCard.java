package it.polimi.ingsw.model.gameData.gameTools;

import it.polimi.ingsw.model.gameData.Colour;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.Property;

import java.util.ArrayList;

public class WindowPatternCard {
    private int num;
    private ArrayList<ArrayList<Cell>> matr = new ArrayList<>(4);
    private int difficulty;
    private String name;


    public WindowPatternCard(){
        this.num = 0;
        this.difficulty = 0;
        this.name ="Card not valid";
        for(int i = 0; i < 4; i++){
            ArrayList<Cell> x = new ArrayList<>(5);
            this.matr.add(x);
            for(int j = 0; j < 5; j++){
                Cell y = new Cell();
                this.matr.get(i).add(y);
            }
        }
    }
    public WindowPatternCard(int num, int difficulty, String name){
        this.num = num;
        this.difficulty = difficulty;
        this.name = name;
        for(int i = 0; i < 4; i++){
            ArrayList<Cell> x = new ArrayList<>(4);
            this.matr.add(x);
            for(int j = 0; j < 5; j++){
                Cell y = new Cell();
                this.matr.get(i).add(y);
            }
        }
    }

    public int getNum(){
        int x = this.num;
        return this.num;
    }


    public int getDifficulty() {
        int x = this.difficulty;
        return x;
    }

    public String getName(){
        return this.name;
    }


    public ArrayList<ArrayList<Cell>> getMatr(){
        return this.matr;
    }
    /*
    public Cell[] getCell(){
        return;
    }
*/
    protected void setNum(int x){
        this.num = x;
    }

    protected void setDifficulty(int x){
        this.difficulty = x;
    }

    protected void setName(String x){
        this.name = x;
    }

    protected void setMatr(ArrayList<ArrayList<Cell>> x){
        this.matr = x;
    }

    //place the dice
    public boolean placeDice(Dice d, int x, int y){
        /*Here we need the validation of the rules*/
        if((matr.get(x).get(y) != null) && matr.get(x).get(y).isOccupied())
            return false;
        else{
            matr.get(x).get(y).setOccupation(true);
            matr.get(x).get(y).setDice(d);
            return true;
        }
    }

    //verify is the cell in position p is occupied
    public boolean isIn(Pos p){
        if(this.matr.get(p.getX()).get(p.getY()).isOccupied())
            return true;
        return false;
    }

    public Dice getDice(Pos p){
        return (this.matr.get(p.getX()).get(p.getY()).getDice());
    }

    public Cell getCell(Pos p){
        return (this.matr.get(p.getX()).get(p.getY()));
    }

    //find the dice d in the WindowPatternCard
    public boolean findDice(Dice d){
        for(ArrayList<Cell> x: this.matr){
            for (Cell y: x) {
                if(y.getDice().equals(d))
                    return true;
            }
        }
        return false;
    }

    public boolean addRestr(char c,int x, int y) {
        Pos p = new Pos(x, y);
        if (Character.isLetter(c)) {
            Property pro = new Property(Colour.isIn(c),false);
            this.getCell(p).setProperty(pro);
            return true;
        } else if (Character.isDigit(c)) {
            Property pro = new Property(Character.getNumericValue(c));
            this.getCell(p).setProperty(pro);
            return true;
        }
        return false;
    }

    //shows every attribute of the Card, also the scheme
    public void show(){
        System.out.print("\nWindow Pattern \nID number: " + num + "\n" + "Difficulty: " + difficulty + "\n" + "Name: " + name + "\n" +
                "Scheme: \n" + "\n");
        this.showDices();
        this.showRestrictions();
        System.out.println("\n");
    }

    private void showDices(){
        int i, j;
        int x = this.matr.size();
        int y = this.matr.get(0).size();

        System.out.println("Showing dices placed on the board: ");

        //shows dices placed on the board
        for(i = 0; i <= x; i++){
            for(j = 0; j <= y; j++){
                if ( i == 0 && j == 0)
                    System.out.print("   ");
                else if( i == 0)
                    System.out.print("  " + (j-1));
                else if( j == 0)
                    System.out.print("  " + (i-1));
                else {
                    System.out.print("  ");
                    matr.get(i-1).get(j-1).show();
                }
            }
            System.out.print("\n");
        }
    }

    private void showRestrictions(){
        int x = this.matr.size();
        int y = this.matr.get(0).size();

        System.out.println("Showing restrictions  on the board: ");

        //shows the restrictions
        for(int i = 0; i <= x; i++){
            for(int j = 0; j <= y; j++){
                if ( i == 0 && j == 0)
                    System.out.print("   ");
                else if( i == 0)
                    System.out.print("  " + (j-1));
                else if( j == 0)
                    System.out.print("  " + (i-1));
                else {
                    System.out.print("  ");
                    matr.get(i-1).get(j-1).getProperty().show();
                }
            }
            System.out.print("\n");
        }
    }
}


