package package1;

public class Cell {
    private Property property;
    private Pos pos;

    public Cell(Property property, Pos pos){
        this.property = property;
        this.pos = pos;
    }

    public Property getProperty(){
        return this.property;
    }

    public Pos getPosition(){
        return this.pos;
    }

}
