package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public interface TurnState {
    public void viewChoice();

    public boolean doChoice();

    public void receiveMove(ToolCard toolCard);

    public void receiveMove(Dice dice);

    public void receiveMove(Pos pos);
}