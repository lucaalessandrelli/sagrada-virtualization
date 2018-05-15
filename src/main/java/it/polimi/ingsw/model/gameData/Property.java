package it.polimi.ingsw.model.gameData;

//express property of dice and cells of Windows Pattern Card
public class Property {
    private Colour colour;
    private int number;

    public Property(){
        this.colour = Colour.WHITE;
        this.number = 0;
    }

    //constructor for dice
    public Property(Colour colour){
        this.colour = colour;
    }
    //constructor for Window Pattern Card
    public  Property(Colour colour, int value){
        this.colour = colour;
        this.number = value;
    }

    public Property clone() throws CloneNotSupportedException {
        Property x = (Property) super.clone();
        return x;
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
    public void setColour(Colour z){
        this.colour = z;
    }
    //setter method
    public void setNumber(int k){
        this.number = k;
    }
}
