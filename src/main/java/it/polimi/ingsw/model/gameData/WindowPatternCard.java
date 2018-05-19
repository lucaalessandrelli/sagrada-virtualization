package it.polimi.ingsw.model.gameData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.print("This is x:" + x + "\nThis is y:" + y);
        x = (int) c;
        System.out.print("This is x:" + x + "\nThis is y:" + y);
        if (x >= 65 && x<=90) {
            Property pro = new Property(Colour.isIn(c));
            this.getCell(p).setProperty(pro);
            return true;
        } else if (x >= 48 && x <= 57) {
            Property pro = new Property(Integer.valueOf(c));
            this.getCell(p).setProperty(pro);
            return true;
        }
        return false;
    }

    //shows every attribute of the Card, also the scheme
    public void show(){
        System.out.print("ID number: " + num + "\n" + "Difficulty: " + difficulty + "\n" + "Name: " + name + "\n" +
                "Scheme: \n" + "\n");
        int x = this.matr.size();
        int y = this.matr.get(0).size();
        for(int i = 0; i < x; i++){
            if(i != 0)
                System.out.print(" " + i + " ");
            for (int j = 0; j < y; j++) {
                if (i == 0) {
                    System.out.print(" " + j + " ");
                }
                else
                    System.out.print(j + " " + matr.get(i).get(j) + " ");
                if( j == matr.get(i).size()-1) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println("\n");
    }


}

