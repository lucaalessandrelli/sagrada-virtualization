package it.polimi.ingsw.model.gameData;

public enum Colour {
    RED("RED"),
    YELLOW("YELLOW"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    PURPLE("PURPLE"),
    WHITE("WHITE");

    private String name;
    private Colour(String mystring) {
        this.name = mystring;
    }

    @Override
    public String toString(){
        return name;
    }

}
