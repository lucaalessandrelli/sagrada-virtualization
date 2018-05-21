package it.polimi.ingsw.model.gameData.gameTools;

import it.polimi.ingsw.model.gameData.Rules;


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

    public ObjectiveCard(int points, String type, String name, String description, int idnumber) {
        this.points = points;
        this.type = type;
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

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public void show() {
        System.out.print("Name :" + this.getName() + "\nDescription :"
        + this.getDescription() + "\nIdentification number:"
        + this.getID() + "\nPoints:"
        + this.points + "\nType: "
        + this.type);
    }

    //Left for future implementation
    public int finalpoints(WindowPatternCard w){
        int result = 0;
        for (String x: this.rules.getRules()) {
            result = result + ((this.rules.verify(x,w))*points);
        }
        return result;
    }


}
