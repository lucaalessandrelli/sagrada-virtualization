package it.polimi.ingsw.model.gameData.gameTools;

import it.polimi.ingsw.model.gameLogic.Move;

import java.util.ArrayList;

public class ToolCard extends Card implements Move {
    private boolean used;
    private int cost;
    private ArrayList<String> stateList;
    private ArrayList<String> automatedoperationlist;
    private ArrayList<String> cMethods;
    private ArrayList<String> pMethods;

    public ToolCard() {
        this.used = false;
        this.cost = 1;
    }

    @Override
    public void show() {
        System.out.print("Name :" + this.getName() + "\nDescription :"
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
        return cost;
    }

    public ArrayList<String> getNameCMethods(){
        return pMethods;
    }

    public ArrayList<String> getNamePMethods(){
        return cMethods;
    }

    public ArrayList<String> getAutomatedoperationlist(){
        return automatedoperationlist;
    }

    public ArrayList<String> getStateList(){
        return stateList;
    }
}
