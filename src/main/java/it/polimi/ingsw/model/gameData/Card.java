package it.polimi.ingsw.model.gameData;

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
        String x = new String();
        x = this.name;
        return x;
    }

    public String getDescription() {
        String x = new String();
        x = this.description;
        return x;
    }

    public int getID(){
        int x;
        x = this.idnumber;
        return x;
    }
}
