package it.polimi.ingsw.model.gameData.gameTools;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.Property;
import it.polimi.ingsw.model.gameData.gameTools.Dice;

public class Cell {
    private Property property;
    private Pos pos;
    private boolean occupied = false;
    private Dice dice;

    public Cell(){
        this.property = new Property();
        this.pos = new Pos();
        this.dice = new Dice();
    }

    public Cell(Property property, Pos pos){
        this.property = property;
        this.pos = pos;
    }

    //getter method
    public Property getProperty(){
        return this.property;
    }

    //getter method
    public Pos getPosition(){
        return this.pos;
    }

    //getter method
    public Dice getDice(){
       return this.dice;
    }

    public boolean isOccupied(){
        return this.occupied;
    }
    //setter method
    protected void setOccupation(boolean x){
        occupied = x;
    }
    //setter method
    protected void setPos(Pos x) {
        this.pos = x;
    }
    //setter method
    protected void setProperty(Property x){
        this.property = x;
    }
    //setter method
    protected void setDice(Dice x){
        this.dice = x;
    }

    public void show(){
        if(this.isOccupied())
            this.dice.show();
        else
            System.out.print("/");
    }
}
