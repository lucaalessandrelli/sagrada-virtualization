package it.polimi.ingsw.model.gameData;

public class ToolCard extends Card {
    private boolean used;
    private int cost;

    public ToolCard() {
        this.used = false;
        this.cost = 1;
    }

    @Override
    public void show() {
        System.out.println("Name :" + this.getName() + "\nDescription :"
                            + this.getDescription() + "\nIdentification number:"
                            + this.getID() + "\nIs already been used? " + this.used);
    }

    public void setUsed() {
        this.used = true;
        this.cost = 2;
    }

    /*public void setCost(int cost) {
        this.cost = cost;
    }
    */

    public int getCost() {
        return this.cost;
    }


}
