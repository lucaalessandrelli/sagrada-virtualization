package it.polimi.ingsw.model.gameLogic.Checker;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.DraftPool;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

import java.util.ArrayList;

public class InspectorContext {


    public boolean check(Dice d, Pos pos, DraftPool draft){
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
