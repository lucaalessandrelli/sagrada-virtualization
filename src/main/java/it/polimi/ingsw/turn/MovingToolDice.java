package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlaceTool;

public class MovingToolDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    InspectorContext inspectorContext;
    InspectorPlace inspectorPlace;
    private InspectorPlaceTool inspectorPlaceTool;

    public MovingToolDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
        this.inspectorPlaceTool = turn.getInspectorPlaceTool();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) {
        //change inspector
        //inspectorPlaceTool.check(toolDice,toolPos,turn.getToolCard());
        if(inspectorPlace.check(toolDice,toolPos,turn.getPlayer().getWindowPatternCard())) {
            //call modifier
            turn.getModifier().positionDice(toolDice,toolPos,pos);
            turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            //throw wrong placement exception
        }
    }
}