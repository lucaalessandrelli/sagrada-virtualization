package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class EndTurn implements TurnState {
    private Turn turn;

    public EndTurn(Turn turn) {
        this.turn = turn;
    }

   //GETTING MOVE METHODS

    @Override
    public void receiveMove(String pass) {
    }
}
