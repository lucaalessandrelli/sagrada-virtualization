package it.polimi.ingsw.model.gamelogic.checker;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.DraftPool;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;

import java.util.ArrayList;

public class InspectorContext {

    //posizione dado in draftpool; (x,0)

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
