package it.polimi.ingsw.model.gameData;

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
    //this method returns a public objective card
    public ObjectiveCard askForPublic(){
        //take a public card from the ones available in the file
        return null;
    }

    //this method returns a private objective card
    public ObjectiveCard askForPrivate(){
        //take a private card from the ones available in the file
        return null;
    }

    //Left for future implementation
    public int finalpoints(){
        return 1;
    }
}
