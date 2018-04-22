package package1;

//The strategy pattern will be implemented later
public class ObjectiveCard extends Card{
    private int points;
    private String type;

    @Override
    public void show() {
        System.out.println("Name :" + this.getName() + "\nDescription :"
        + this.getDescription() + "\nIdentification number:"
        + this.getID() + "\nPoints:"
        + this.points + "\nType: "
        + this.type);
    }

    //Left for future implementation
    public int finalpoints(){
        return 1;
    }
}
