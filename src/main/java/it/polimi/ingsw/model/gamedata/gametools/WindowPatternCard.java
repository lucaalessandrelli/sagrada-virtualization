package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WindowPatternCard {
    private int num;
    private List<List<Cell>> matr = new ArrayList<>(4);
    private int difficulty;
    private String name;
    private String player;


    public WindowPatternCard(){
        this.num = 0;
        this.difficulty = 10;
        this.name ="Card not valid";
        Cell y;
        Pos p;
        for(int i = 0; i < 4; i++){
            ArrayList<Cell> x = new ArrayList<>(5);
            this.matr.add(x);
            for(int j = 0; j < 5; j++){
                y = new Cell();
                p = new Pos(i,j);
                y.setPos(p);
                y.setOccupation(false);
                this.matr.get(i).add(y);
            }
        }
    }

    public WindowPatternCard(int num, int difficulty, String name){
        this.num = num;
        this.difficulty = difficulty;
        this.name = name;
        Cell y;
        Pos p;
        for(int i = 0; i < 4; i++){
            ArrayList<Cell> x = new ArrayList<>(4);
            this.matr.add(x);
            for(int j = 0; j < 5; j++){
                y = new Cell();
                p = new Pos(i,j);
                y.setPos(p);
                y.setOccupation(false);
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


    public List<List<Cell>> getMatr(){
        return this.matr;
    }

    public Dice getDice(Pos p){
        try {
            if (!(this.getCell(p).isOccupied()))
                throw new EmptyCellException();
        } catch (EmptyCellException e){
            System.out.println(e.message);
        }
        return (this.getCell(p).getDice());
    }

    public Cell getCell(Pos p){
        return (this.matr.get(p.getX()).get(p.getY()));
    }

    public String getPlayer(){
        return this.player;
    }

    //find the dice d in the WindowPatternCard
    public boolean findDice(Dice d){
        for(List<Cell> x: this.matr){
            for (Cell y: x) {
                if(y.getDice().equals(d))
                    return true;
            }
        }
        return false;
    }


    public void setNum(int x){
        this.num = x;
    }

    public void setDifficulty(int x){
        this.difficulty = x;
    }

    public void setName(String x){
        this.name = x;
    }

    public void setMatr(List<List<Cell>> x){
        this.matr = x;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    //place the dice
    public boolean placeDice(Dice d, int x, int y){
        /*Here we need the validation of the rules*/
        Pos p = new Pos(x,y);
        if(this.getCell(p).isOccupied() || d == null)
            return false;
        else{
            this.getCell(p).setOccupation(true);
            this.getCell(p).setDice(d);
            return true;
        }
    }

    //verify is the cell in position p is occupied
    public boolean isIn(Pos p){
        if(this.getCell(p).isOccupied())
            return true;
        return false;
    }


    public boolean findDice(Dice d, Pos p){
        return (this.getCell(p).getDice().equals(d));
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

    public void removeDice(Pos pos){
        this.getCell(pos).setOccupation(false);
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

    public void resetSelection() {
        matr.forEach(cells -> cells.forEach(cell -> cell.getDice().deSelect()));
    }

    public boolean isEmpty() {
        for (List<Cell> cells:matr) {
            for (Cell c:cells) {
                if(c.isOccupied())
                    return true;
            }
        }
        return false;
    }
}


