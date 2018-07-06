package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Rules;


//The strategy pattern will be implemented later
public class ObjectiveCard extends Card {
    private int points;
    private String type;
    private Rules rules;


    public ObjectiveCard(){
        this.points = 0;
        this.type = "";
        this.rules = new Rules();
    }

    //getter
    public int getPoints(){ return this.points; }

    public String getType(){
        return this.type;
    }

    public Rules getRules(){
        return this.rules;
    }

    //setter
    public void setType(String type) {
        this.type = type;
    }

    protected void setPoints(int points) {
        this.points = points;
    }

    public int finalpoints(WindowPatternCard w){
        int result = 0;
        for (String x: this.rules.getRules()) {
            result = result + ((this.rules.verify(x,w))*points);
        }
        return result;
    }


}
