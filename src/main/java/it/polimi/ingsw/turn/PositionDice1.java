package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

import java.util.ArrayList;

public class PositionDice1 implements TurnState {
    private Turn turn;
    private ArrayList<Integer> toolList = new ArrayList<>();
    InspectorContext inspectorContext;

    public PositionDice1(Turn turn) {
        this.turn = turn;
        inspectorContext = turn.getInspectorContext();
        toolList.add(2);
        toolList.add(3);
        toolList.add(4);
        toolList.add(8);
        toolList.add(12);
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) {
        int cardId = toolCard.getID();
        if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
            if ((cardId == 8 && turn.getFirstBracket()) || (cardId != 8)) {
                //setting the toolCard used in this turn
                //setting the list of states for the dynamic state machine
                //setting the list of operations for the AutomatedOperation State
                turn.setToolCardInfo(toolCard);
                //setting the check point i need to return after the user do the moves of the toolCard
                turn.setCheckPointState(new EndTurn(turn));
                //need to set dynamic current state
                turn.setDynamicState(new Dice(),new Pos(), new Dice(), new Pos());
            }
        } else {
          //throw wrong toolCard exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
