package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

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

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) {
        int cardId = toolCard.getID();
        if(toolList.contains(cardId) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
            if ((cardId == 7 && !turn.getFirstBracket()) || (cardId != 7)) {
                //setting the toolCard used in this turn
                //setting the list of states for the dynamic state machine
                //setting the list of operations for the AutomatedOperation State
                turn.setToolCardInfo(toolCard);
                //setting the check point i need to return after the user do the moves of the toolCard
                turn.setCheckPointState(new ToolBeforeDice(turn));
                //need to set dynamic current state
                turn.setDynamicState(new Dice(), new Pos(),new Dice(), new Pos());
            }
        } else {
          //throw wrong toolCard exception
        }
    }

    @Override
    public void receiveMove(Dice dice, Pos pos) {
        if(inspectorContext.check(dice,pos,turn.getPlayer().getDraftPool())) {
                turn.setState(new ChooseDice1(turn, dice, pos));
        } else {
          //throw wrong Dice exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }

}
