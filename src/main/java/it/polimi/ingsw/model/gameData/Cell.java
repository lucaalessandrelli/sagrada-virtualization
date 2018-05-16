package it.polimi.ingsw.model.gameData;

public class Cell {
    private Property property;
    private Pos pos;
    private boolean occupied = false;
    private Dice dice;

    public Cell(){
        Property x = new Property();
        Pos z = new Pos();
        this.property = x;
        this.pos = z;
    }

    public Cell(Property property, Pos pos){
        this.property = property;
        this.pos = pos;
    }

    @Override
    public Cell clone() throws CloneNotSupportedException {
        Cell x = (Cell) super.clone();
        x.setPos((Pos) this.getPosition().clone());
        x.setProperty((Property)this.getProperty().clone());
        return x;
    }

    //getter method
    public Property getProperty() throws CloneNotSupportedException {
        Property x = (Property) super.clone();
        x.setColour((Colour) this.property.getColour());
        x.setNumber((int) this.property.getNumber());
        return x;
    }

    //getter method
    public Pos getPosition(){
        Pos x = this.pos;
        return x;
    }

    //getter method
    public Dice getDice(){
       return this.dice;
    }

    public Boolean isOccupied(){
        return this.occupied;
    }
    //setter method
    public void setOccupation(boolean x){
        occupied = x;
    }
    //setter method
    public void setPos(Pos x) {
        this.pos = x;
    }
    //setter method
    public void setProperty(Property x){
        this.property = x;
    }
    //setter method
    public void setDice(Dice x){
        this.dice = x;
    }
}
