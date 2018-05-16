package it.polimi.ingsw.model.gameData;

import java.util.ArrayList;

//The strategy pattern will be implemented later
public class ObjectiveCard extends Card{
    private int points;
    private String type;
    private String name;
    private String description;
    private int idnumber;
    private ArrayList<String> rules = new ArrayList<String>();


    public ObjectiveCard(){
        this.points = 0;
        this.type = null;
        this.name = null;
        this.description = null;
        this.idnumber = 0;
    }

    public ObjectiveCard(int points, String type, String name, String description, int idnumber) {
        this.points = points;
        this.type = type;
        this.name = name;
        this.description = description;
        this.idnumber = idnumber;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getID(){
        return this.idnumber;
    }

    public int getPoints(){
        return this.points;
    }

    public String getType(){
        return this.type;
    }

    public ArrayList<String> getRules(){
        return this.rules;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setID(int id) {
        this.idnumber = id;
    }

    @Override
    public void show() {
        System.out.println("Name :" + this.name + "\nDescription :"
        + this.description + "\nIdentification number:"
        + this.idnumber + "\nPoints:"
        + this.points + "\nType: "
        + this.type);
    }

    //Left for future implementation
    public int finalpoints(){
        return 1;
    }
}
