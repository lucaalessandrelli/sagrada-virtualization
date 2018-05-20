package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class SelectingDice implements TurnState {
    private Turn turn;
    InspectorContext inspectorContext;

    public SelectingDice(Turn turn) {
        this.turn = turn;
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
    public void receiveMove(Dice dice) {
        /*if(inspectorContext.check(dice,turn.getPlayer().getDraftPool()) {
                turn.setDynamicState(dice);
        } else {
          //throw wrong Dice exception
        }*/
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
