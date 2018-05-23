package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamedata.Property;
import it.polimi.ingsw.model.gamelogic.Move;

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

    public void setNumber(int number){
        this.prop.setNumber(number);
    }

    public void setDice(Dice d){
        this.prop.setNumber(d.getNumber());
        this.prop.setColour(d.getColour());
    }

    public void rollDice(){
        this.prop.rollDice();
    }
}
