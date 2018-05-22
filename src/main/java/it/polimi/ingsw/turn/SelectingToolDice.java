package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectContextTool;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

public class SelectingToolDice implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;
    private InspectorContext inspectorContext;
    private InspectContextTool inspectContextTool;

    public SelectingToolDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
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
    public void receiveMove(Dice toolDice,Pos toolPos) {
        //change inspector
        //inspectContextTool.check(toolDice,toolDice,turn.getToolCard());
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
          //throw wrong Dice exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
