package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Dice;
import it.polimi.ingsw.model.gameData.Player;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.ToolCard;
import it.polimi.ingsw.model.gameLogic.Move;

public class Turn {
    private Player player;
    private TurnState state;


    public Turn(Player p) {
        player = p;
    }

    public TurnState getState() {
        return state;
    }

    public void setState(TurnState state) {
        this.state = state;
    }

    public void startTurn() {
        this.state = new StartTurn(this);
    }

    public void viewChoice() {
        state.viewChoice();
    }

    //GETTING MOVE METHODS
    public void receiveMove(ToolCard toolCard) {
        state.receiveMove(toolCard);
    }

    public void receiveMove(Dice dice) {
        state.receiveMove(dice);
    }

    public void receiveMove(Pos pos) {
        state.receiveMove(pos);
    }

}
