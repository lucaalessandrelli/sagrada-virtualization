package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;
import it.polimi.ingsw.model.gamelogic.Move;

import java.util.ArrayList;

public class ToolCard extends Card implements Move {
    private boolean used;
    private int cost;
    private Colour colour;
    private ArrayList<String> stateList = new ArrayList<>();
    private ArrayList<String> automatedoperationlist = new ArrayList<>();
    private ArrayList<String> cMethods = new ArrayList<>();
    private ArrayList<ArrayList<String>> pMethods = new ArrayList<>();

    public ToolCard() {
        this.used = false;
        this.cost = 1;
    }

    @Override
    public void show() {
        System.out.println("Name :" + this.getName() + "\nDescription :"
                            + this.getDescription() + "\nIdentification number:"
                            + this.getID() + "\nIs already been used? " + this.used);
        for (String x: this.automatedoperationlist){
            System.out.println("Automated: " + x);
        }
        for(String x : this.stateList){
            System.out.println("Statelist: " + x);
        }
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
        return cMethods;
    }

    public ArrayList<ArrayList<String>> getNamePMethods(){
        return pMethods;
    }

    public ArrayList<String> getAutomatedoperationlist(){
        return automatedoperationlist;
    }

    public ArrayList<String> getStateList(){
        return stateList;
    }

    public void setColour(Colour colour){
        this.colour = colour;
    }

}
