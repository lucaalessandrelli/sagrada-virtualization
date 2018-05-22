package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class ToolAfterStdMove implements TurnState {
    private Turn turn;


    public ToolAfterStdMove(Turn turn) {
        this.turn = turn;
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}

