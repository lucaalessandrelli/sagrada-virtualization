package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

public class IncDecValue implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    InspectorContext inspectorContext;
    InspectorPlace inspectorPlace;

    public IncDecValue(Turn turn, Dice dice) {
        this.turn = turn;
        this.chosenDice = dice;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
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
                turn.setDynamicState(new Dice());
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