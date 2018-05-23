package it.polimi.ingsw.model.gamedata.gametools;

public abstract class  Card {
    private String name;
    private String description;
    private int idnumber;

    public Card(){
        this.name = "No name";
        this.description = "No description";
        this.idnumber = -1;
    }

    public Card(String name, String description, int id){
        this.name = name;
        this.description = description;
        this.idnumber = id;
    }

    public void show(){
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setID(int id) {
        this.idnumber = id;
    }

}
