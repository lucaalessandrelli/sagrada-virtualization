package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Move;

import java.util.ArrayList;


public class StartTurn implements TurnState {
    private Turn turn;
    private ArrayList<Integer> toolList = new ArrayList<Integer>();
    InspectorContext inspectorContext;

    public StartTurn(Turn turn) {
        this.turn = turn;
        inspectorContext = turn.getInspectorContext();
        toolList.add(2);
        toolList.add(3);
        toolList.add(4);
        toolList.add(12);
        toolList.add(7);
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
        /*int cardId = toolCard.getID();
        if(toolList.contains(cardId) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
            if ((cardId == 7 && !turn.getFirstBracket()) || (cardId != 7)) {
                //setting the list of states for the dynamic state machine
                turn.setToolStateList(toolCard.getStateList());
                //setting the check point i need to return after the user do the moves of the toolCard
                turn.setCheckPointState(new ToolBeforeDice(turn, toolCard));
                //need to set dynamic current state
                turn.setDynamicState(new Dice());
            }
        } else {
          //throw wrong toolCard exception
        }*/
    }

    @Override
    public void receiveMove(Dice dice) {
        /*if(inspectorContext.check(dice,turn.getPlayer().getDraftPool()) {
                turn.setState(new ChooseDice1(turn, dice));
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
        turn.setState(new EndTurn(turn));
    }

}
