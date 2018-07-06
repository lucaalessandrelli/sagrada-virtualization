package it.polimi.ingsw.model.gamedata.gametools;

import it.polimi.ingsw.model.gamedata.Colour;

import java.util.ArrayList;
import java.util.List;

public class ToolCard extends Card {
    private boolean used;
    private int cost;
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

    void setNameCMethods(List<String> x){
         this.cMethods = x;
    }

    public void setNamePMethods(List<List<String>> x){
        this.pMethods = x;
    }

    void setAutomatedoperationlist(List<String> x){
        this.automatedoperationlist = x;
    }

    void setStateList(List<String> x){
        this.stateList = x;
    }

}
