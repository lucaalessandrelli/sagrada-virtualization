package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class PositionDice2 implements TurnState {
    private Turn turn;

    public PositionDice2(Turn turn) {
        this.turn = turn;
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
