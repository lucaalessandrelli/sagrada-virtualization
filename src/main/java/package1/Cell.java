package package1;

public class Cell {
    private Property property;
    private Pos pos;

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

}
