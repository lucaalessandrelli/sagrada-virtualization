package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class SelectingDice implements TurnState {
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private Turn turn;
    InspectorContext inspectorContext;

    public SelectingDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        inspectorContext = turn.getInspectorContext();
    }

    @Override
    public boolean doChoice() {
        return false;
    }

    @Override
    public void viewChoice() {

    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) {
        //throw move not valid exception
    }

    @Override
    public void receiveMove(Dice toolDice,Pos toolPos) {
        //change inspector
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
          //throw wrong Dice exception
        }
    }

    @Override
    public void receiveMove(Pos pos) {
        //throw move not valid exception
    }

    @Override
    public void receiveMove(String pass) {
        //throw move not valid exception
    }
}
