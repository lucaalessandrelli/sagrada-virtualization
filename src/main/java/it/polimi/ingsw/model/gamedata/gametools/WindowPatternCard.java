package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.Property;

import java.util.ArrayList;
import java.util.List;

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
        return this.num;
    }


    public int getDifficulty() {
        return this.difficulty;
    }

    public String getName(){
        return this.name;
    }


    public List<List<Cell>> getMatr(){
        return this.matr;
    }

    public Dice getDice(Pos p){
        if (!(this.getCell(p).isOccupied()))
            throw new IllegalStateException();
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
                if(y.getDice().areEquals(d))
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
        Pos p = new Pos(x,y);
        if(this.getCell(p).isOccupied() || d == null)
            return false;
        else{
            this.getCell(p).setOccupation(true);
            this.getCell(p).setDice(d);
            return true;
        }
    }

    //verify if the cell in position p is occupied
    public boolean isIn(Pos p){
        return this.getCell(p).isOccupied();
    }


    public boolean findDice(Dice d, Pos p){
        return (this.getCell(p).getDice().areEquals(d));
    }

    public void addRestr(char c,int x, int y) {
        Pos p = new Pos(x, y);
        if (Character.isLetter(c)) {
            Property pro = new Property(Colour.isIn(c),false);
            this.getCell(p).setProperty(pro);
        } else if (Character.isDigit(c)) {
            Property pro = new Property(Character.getNumericValue(c));
            this.getCell(p).setProperty(pro);
        }
    }

    public void removeDice(Pos pos){
        this.getCell(pos).setOccupation(false);
    }

    public void resetSelection() {
        matr.forEach(cells -> cells.forEach(cell -> cell.getDice().deSelect()));
    }

    public boolean isEmpty() {
        for (List<Cell> cells:matr) {
            for (Cell c:cells) {
                if(c.isOccupied())
                    return false;
            }
        }
        return true;
    }
}


