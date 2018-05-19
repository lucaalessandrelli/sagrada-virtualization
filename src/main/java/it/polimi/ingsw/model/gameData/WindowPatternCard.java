package it.polimi.ingsw.model.gameData;

import java.util.ArrayList;

public class WindowPatternCard {
    private int num;
    private ArrayList<ArrayList<Cell>> matr = new ArrayList<>(5);
    private int difficulty;
    private String name;

    public WindowPatternCard(int num, int difficulty, String name){
        this.num = num;
        this.difficulty = difficulty;
        this.name = name;
        for(int i = 0; i < 5; i++){
            ArrayList<Cell> x = new ArrayList<>(4);
            this.matr.add(x);
            for(int j = 0; j < 4; j++){
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


    public ArrayList<ArrayList<Cell>> getMatr(){
        return this.matr;
    }
    /*
    public Cell[] getCell(){
        return;
    }
*/
    private void setNum(int x){
        this.num = x;
    }

    private void setDifficulty(int x){
        this.difficulty = x;
    }

    private void setName(String x){
        this.name = x;
    }

    private void setMatr(ArrayList<ArrayList<Cell>> x){
        this.matr = x;
    }


    //shows every attribute of the Card, also the scheme
    public void show(){
        System.out.println("ID number :" + num + "\n" + "Difficulty :" + difficulty + "\n" + "Name :" + name + "Scheme :\n" + "\n");
        int x = this.matr.size();
        int y = this.matr.get(0).size();
        for(int i = 0; i < x; i++){
            System.out.println(" " + i + " ");
            for (int j = 0; j < y; j++) {
                if (i == 0) {
                    System.out.println(" " + j + " ");
                }
                else
                    System.out.println(" " + "matr[i][j]" + " ");
                if( j == matr.get(i).size()) {
                    System.out.println("\n");
                }
            }
        }
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
}