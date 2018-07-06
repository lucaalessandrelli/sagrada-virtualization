package it.polimi.ingsw.model.gamedata;

public enum Colour {
    RED("R"),
    YELLOW("Y"),
    GREEN("G"),
    BLUE("B"),
    PURPLE("P"),
    WHITE("W");

    private String name;

    Colour(String mystring) {
        this.name = mystring;
    }

    public static Colour isIn(char c) {
        switch (c){
            case 'R':
                return Colour.RED;
            case 'Y':
                return Colour.YELLOW;
            case 'B':
                return Colour.BLUE;
            case 'G':
                return Colour.GREEN;
            case 'P':
                return Colour.PURPLE;
            case 'W':
                return Colour.WHITE;
            default:
                return Colour.WHITE;
        }
    }

    @Override
    public String toString(){
        return name;
    }

    public boolean equals(Colour c){
        return this.toString().equals(c.toString());
    }

}
