package it.polimi.ingsw.model.gameLogic.Checker;

import it.polimi.ingsw.model.gameData.*;

import java.util.ArrayList;

public class InspectorContext {


    public boolean check(Dice d, DraftPool draft){
        return draft.findDice(d);
    }

    public boolean check (ToolCard tool, ArrayList<ToolCard> allTool){
        for (ToolCard t : allTool){
            if (t.getID()==tool.getID()){
                return true;
            }
        }
        return false;
    }

}
