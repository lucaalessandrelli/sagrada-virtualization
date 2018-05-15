package it.polimi.ingsw.model.gameData;

public class Dice {
    private Property prop;

    public Dice(){
        this.prop = new Property(Colour.WHITE);
    }

    public Dice(Property x){
        this.prop = x;
    }


    public Dice(Colour y){
        this.prop = new Property(y);
    }

    //getter
    public Colour getColour(){
        Colour x = this.prop.getColour();
        return x;
    }
    //getter
    public int getNumber(){
        return  prop.getNumber();
    }

    public Dice clone() throws CloneNotSupportedException {
        Property x;
        x = this.prop.clone();
        Dice y = new Dice(x);
        return y;
    }
}
