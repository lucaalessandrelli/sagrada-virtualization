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

    @Override
    public Cell clone() throws CloneNotSupportedException {
        Cell x = (Cell) super.clone();
        x.setPos((Pos) x.getPosition().clone());
        x.setProperty((Property)x.getProperty().clone());
        return x;
    }

    //getter method
    public Property getProperty(){
        Property x = this.property;
        return x;
    }
    //getter method
    public Pos getPosition(){
        Pos x = this.pos;
        return x;
    }
    //getter method
    public Boolean isOccupied(){
        return this.occupied;}
    //setter method
    public void setOccupation(boolean x){
        occupied = x;}
    //setter method
    public void setPos(Pos x) {
        this.pos = x;
    }
    //setter method
    public void setProperty(Property x){
        this.property = x;
    }
}
