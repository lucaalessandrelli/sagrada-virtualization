package it.polimi.ingsw.model.gameData.gameTools;

import it.polimi.ingsw.model.gameData.Colour;
import it.polimi.ingsw.model.gameData.Property;
import it.polimi.ingsw.model.gameLogic.Move;

import java.util.Random;

public class Dice implements Move {
    private Property prop;

    public Dice(){
        this.prop = new Property(Colour.WHITE,true);
    }

    public Dice(Property x){
        this.prop = x;
    }


    public Dice(Colour y){
        this.prop = new Property(y,true);
    }

    //getter
    public Colour getColour(){
        return prop.getColour();
    }
    //getter
    public int getNumber(){
        return  prop.getNumber();
    }


    public boolean equals(Dice d) {
        if (this.prop.getNumber() == d.prop.getNumber()){
            if(this.prop.getColour().equals(d.prop.getColour()))
                return true;
        }
        return false;
    }

    public void show(){
        this.prop.show();
    }
}
