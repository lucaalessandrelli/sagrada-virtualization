package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlaceTool;

public class MovingDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private InspectorPlaceTool inspectorPlaceTool;

    public MovingDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) {
        //change inspector
        //inspectorPlaceTool.check(chosenDice,pos,turn.getToolCard());
        if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            //call modifier
            turn.getModifier().positionDice(chosenDice,posChosenDice,pos);
            turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            //throw wrong placement exception
        }
    }
}