package package1;

//express property of dice and cells of Windows Pattern Card
public class Property {
    private Colour colour;
    private int number;


    //constructor for dice
    public Property(Colour colour){
        this.colour = colour;
    }
    //constructor for Window Pattern Card
    public  Property(Colour colour, int value){
        this.colour = colour;
        number = value;
    }
    //getter method
    public String getColour() {
        return colour.toString();
    }
    //getter method
    public int getNumber() {
        return number;
    }
}
