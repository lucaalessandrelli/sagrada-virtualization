package it.polimi.ingsw.model.gameData;

import java.util.Random;

//express property of dice and cells of Windows Pattern Card
public class Property {
    private Colour colour;
    private int number;

    public Property(){
        this.colour = Colour.WHITE;
        this.number = 0;
    }

    //constructor for colour
    public Property(Colour colour){
        this.colour = colour;
        int randomNum;
        Random rand = new Random();
        randomNum = 1+ rand.nextInt(6);
        this.number = randomNum;
    }

    //constructor for number
    public Property(int value){
        this.colour = Colour.WHITE;
        this.number = value;
    }

    //getter method
    public Colour getColour() {
        return this.colour;
    }
    //getter method
    public int getNumber() {
        return this.number;
    }
    //setter method
    protected void setColour(Colour z){
        this.colour = z;
    }
    //setter method
    public void setNumber(int k){
        this.number = k;
    }

    public void show(){
        System.out.print(this.colour.toString() + this.number);
    }
}
