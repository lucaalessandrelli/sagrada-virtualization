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
        return prop.getColour();
    }
    //getter
    public int getNumber(){
        return  prop.getNumber();
    }
}
