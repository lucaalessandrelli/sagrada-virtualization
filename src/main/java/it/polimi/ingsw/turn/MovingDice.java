package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

public class MovingDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    InspectorContext inspectorContext;
    InspectorPlace inspectorPlace;

    public MovingDice(Turn turn, Dice dice) {
        this.turn = turn;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
        this.chosenDice = dice;
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
        //throw wrong move exception
    }

    @Override
    public void receiveMove(Pos pos) {
        /*if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            turn.setDynamicState(new Dice());
        } else {
            //throw wrong placement exception
        }*/
    }

    @Override
    public void receiveMove(String pass) {
        //throw wrong move exception
    }
}