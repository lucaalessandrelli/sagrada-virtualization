package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

import java.util.ArrayList;

public class ChooseDice1 implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private ArrayList<Integer> toolList = new ArrayList<Integer>();
    InspectorContext inspectorContext;
    InspectorPlace inspectorPlace;

    public ChooseDice1(Turn turn, Dice dice) {
        this.turn = turn;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
        this.chosenDice = dice;
        toolList.add(1);
        toolList.add(5);
        toolList.add(6);
        toolList.add(9);
        toolList.add(10);
        toolList.add(11);
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
        /*if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
                //setting the list of states for the dynamic state machine
                turn.setToolStateList(toolCard.getStateList());
                //setting the check point i need to return after the user do the moves of the toolCard
                turn.setCheckPointState(new ToolAfterDice(turn, toolCard));
                //need to set dynamic current state
                turn.setDynamicState(chosenDice);
        } else {
          //throw wrong toolCard exception
        }*/
    }

    @Override
    public void receiveMove(Dice dice) {
        //throw wrong move exception
    }

    @Override
    public void receiveMove(Pos pos) {
        /*if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            turn.setState(new PositionDice1(turn));
        } else {
            //throw wrong placement exception
        }*/
    }

    @Override
    public void receiveMove(String pass) {
        //throw wrong move exception
    }
}
