package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class ToolAfterDice implements TurnState {
    private Turn turn;

    public ToolAfterDice(Turn turn) {
        this.turn = turn;
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
