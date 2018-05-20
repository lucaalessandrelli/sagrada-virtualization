package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

import java.util.ArrayList;

public class PositionDice1 implements TurnState {
    private Turn turn;
    private ArrayList<Integer> toolList = new ArrayList<Integer>();
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
        if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
            if ((cardId == 8 && turn.getFirstBracket()) || (cardId != 8)) {
                turn.setState(new ToolBeforeDice(turn, toolCard));
            }
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
        //throw wrong move exception
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
