package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class SelectingValue implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;
    InspectorContext inspectorContext;

    public SelectingValue(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        inspectorContext = turn.getInspectorContext();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice,Pos toolPos) {
        //chiamare inspector diversi
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                //call modifier
                turn.getModifier().changeDiceValue(chosenDice,posChosenDice,toolDice);
                turn.setDynamicState(toolDice,toolPos,new Dice(), new Pos());
        } else {
          //throw wrong Dice exception
        }
    }
}
