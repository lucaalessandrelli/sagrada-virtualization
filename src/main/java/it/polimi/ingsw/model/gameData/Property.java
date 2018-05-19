package it.polimi.ingsw.model.gameData;

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
        this.number = 0;
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
    protected void setNumber(int k){
        this.number = k;
    }

    public void show(){
        System.out.print(this.colour.toString() + this.number);
    }
}
