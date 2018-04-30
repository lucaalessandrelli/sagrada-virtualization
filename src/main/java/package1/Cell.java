package package1;

public class Cell {
    private Property property;
    private Pos pos;
    private boolean occupied = false;

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

    //getter method
    public Property getProperty(){
        return this.property;
    }
    //getter method
    public Pos getPosition(){
        return this.pos;
    }
    //getter method
    public Boolean isOccupied(){
        return this.occupied;}
    //setter method
    public void setOccupation(boolean x){
        occupied = x;}
}
