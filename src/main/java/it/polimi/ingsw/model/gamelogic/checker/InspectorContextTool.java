package it.polimi.ingsw.model.gamelogic.checker;

import it.polimi.ingsw.model.gamedata.gametools.*;

import java.util.ArrayList;

public class InspectorContextTool extends InspectorTool {
    RuleEngine ruleEngine;
    ToolCard tool;

    public InspectorContextTool(Dice d, ToolCard t, WindowPatternCard w, DraftPool p, RoundTrack r){
        ruleEngine = new RuleEngineC(d,w,p,r);
        tool = t;
    }
    //check(Dice,Pos,toolcard); new Rule engine();
    public boolean check(){
        ArrayList<String> nameMethods = tool.getNameCMethods();
        return doMethods(nameMethods, ruleEngine);
    }
    //check for toolcard 11 (oldDice,oldPos1, newDice, newPos) guarda meglio
    public boolean checkColourDice(Dice oldDice, Dice newDice){
        if(oldDice.getColour().toString().equals(newDice.getColour().toString())){
            return true;
        }
        return false;
    }
    //check for toolcard 1 (oldDice,oldPos1, newDice, newPos) check same position
    public boolean checkNumDice(Dice oldDice, Dice newDice){
        int oldNum = oldDice.getNumber();
        int newNum = newDice.getNumber();
        if(oldNum==1 && newNum==2){
            return true;
        }
        if(oldNum==6 && newNum==5){
            return true;
        }
        if(oldNum == newNum+1 || oldNum==newNum-1){
            return true;
        }
        return false;

    }

    private class RuleEngineC implements RuleEngine{
        Dice dice;
        WindowPatternCard window;
        DraftPool pool;
        RoundTrack roundT;
        private RuleEngineC(Dice d, WindowPatternCard w, DraftPool p,RoundTrack r) {
            dice = d;
            window = w;
            pool = p;
            roundT=r;
        }

        protected boolean InDraftPool(){
            return pool.findDice(dice);

        }

        protected boolean InPatCard(){
            return window.findDice(dice);
        }

        protected boolean InRoundTr(){
            return roundT.findDice(dice);
        }

    }
}
