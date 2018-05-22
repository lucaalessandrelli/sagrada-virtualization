package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.util.ArrayList;


public class StartTurn implements TurnState {
    private Turn turn;
    private ArrayList<Integer> toolList = new ArrayList<>();
    private InspectorContext inspectorContext;

    public StartTurn(Turn turn) {
        this.turn = turn;
        this.inspectorContext = turn.getInspectorContext();
        this.toolList.add(2);
        this.toolList.add(3);
        this.toolList.add(4);
        this.toolList.add(12);
        this.toolList.add(7);
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) throws WrongMoveException {
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
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere la carta strumento "+ toolCard.toString() +" in questa fase del turno");
        }
    }

    @Override
    public void receiveMove(Dice dice, Pos pos) throws WrongMoveException {
        if(inspectorContext.check(dice,pos,turn.getPlayer().getDraftPool())) {
            turn.setState(new ChooseDice1(turn, dice, pos));
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere questo dado");
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }

}
