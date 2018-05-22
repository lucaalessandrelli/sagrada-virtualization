package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectContextTool;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class SelectingDice implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;
    private InspectorContext inspectorContext;
    private InspectContextTool inspectContextTool;

    public SelectingDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectContextTool = turn.getInspectContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice chosenDice,Pos posChosenDice) {
        //change inspector
        //inspectContextTool.check(chosenDice,posChosenDice,turn.getPlayer().getToolCard())
        if(inspectorContext.check(chosenDice,posChosenDice,turn.getPlayer().getDraftPool())) {
            turn.setDynamicState(chosenDice,posChosenDice,new Dice(),new Pos());
        } else {
            //throw wrong Dice exception
        }
    }
}

