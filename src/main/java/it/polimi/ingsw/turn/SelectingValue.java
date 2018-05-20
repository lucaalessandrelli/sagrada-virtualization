package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class SelectingValue implements TurnState {
    private Dice previousDice;
    private Turn turn;

    public SelectingValue(Turn turn, Dice dice) {
        this.turn = turn;
        this.previousDice = dice;
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
        //throw wrong move exception
    }

    @Override
    public void receiveMove(Dice dice) {
        //chiamare inspector diversi
        /*if(inspectorContext.check(dice,turn.getPlayer().getDraftPool()) {
                turn.setDynamicState(dice);
        } else {
          //throw wrong Dice exception
        }*/
    }

    @Override
    public void receiveMove(Pos pos) {
        //throw wrong move exception
    }

    @Override
    public void receiveMove(String pass) {
        //throw wrong move exception
    }
}
