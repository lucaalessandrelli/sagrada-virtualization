package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;

import java.util.ArrayList;
import java.util.List;

public class ToolCard extends Card {
    private boolean used;
    private int cost;
    private Colour colour;
    private List<String> stateList;
    private List<String> automatedoperationlist = new ArrayList<>();
    private List<String> cMethods = new ArrayList<>();
    private List<List<String>> pMethods = new ArrayList<>();

    public ToolCard() {
        this.used = false;
        this.cost = 1;
    }

    public ToolCard exactToolCard(int i){
        CardContainer cardContainer = new CardContainer();
        return cardContainer.readTools("src/main/resources/tool_cards_formalization.xml", i-1);
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
        for (String x: this.cMethods){
            System.out.println("CMethods: " + x);
        }
        for(int i = 0; i < this.pMethods.size(); i++){
            for(int j = 0; j < this.pMethods.get(i).size();j++){
                System.out.println(i + "_" + j + "_Methods: " + this.pMethods.get(i).get(j));
            }
        }
    }

    public void setUsed() {
        this.used = true;
        this.cost = 2;
    }

    public boolean isUsed() {
        return used;
    }

    public int getCost() {
        return cost;
    }

    public List<String> getNameCMethods(){
        return cMethods;
    }

    public List<List<String>> getNamePMethods(){
        return pMethods;
    }

    public List<String> getAutomatedoperationlist(){
        return automatedoperationlist;
    }

    public List<String> getStateList(){
        return stateList;
    }

    public void setColour(Colour colour){
        this.colour = colour;
    }


    public void setNameCMethods(List<String> x){
         this.cMethods = x;
    }

    public void setNamePMethods(List<List<String>> x){
        this.pMethods = x;
    }

    public void setAutomatedoperationlist(List<String> x){
        this.automatedoperationlist = x;
    }

    public void setStateList(List<String> x){
        this.stateList = x;
    }

}
