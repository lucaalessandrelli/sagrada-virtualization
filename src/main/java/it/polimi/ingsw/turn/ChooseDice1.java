package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

import java.util.ArrayList;

public class ChooseDice1 implements TurnState {
    private Pos posDiceChosen;
    private Turn turn;
    private Dice chosenDice;
    private ArrayList<Integer> toolList = new ArrayList<Integer>();
    InspectorContext inspectorContext;
    InspectorPlace inspectorPlace;

    public ChooseDice1(Turn turn, Dice chosenDice, Pos posDiceChosen) {
        this.turn = turn;
        this.posDiceChosen = posDiceChosen;
        inspectorContext = turn.getInspectorContext();
        inspectorPlace = turn.getInspectorPlace();
        this.chosenDice = chosenDice;
        toolList.add(1);
        toolList.add(5);
        toolList.add(6);
        toolList.add(9);
        toolList.add(10);
        toolList.add(11);
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) {
        if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, turn.getPlayer().getToolCards()))) {
            //setting the toolCard used in this turn
            //setting the list of states for the dynamic state machine
            //setting the list of operations for the AutomatedOperation State
            turn.setToolCardInfo(toolCard);
            //setting the check point i need to return after the user do the moves of the toolCard
            turn.setCheckPointState(new EndTurn(turn));
            //need to set dynamic current state
            turn.setDynamicState(chosenDice,posDiceChosen, new Dice(), new Pos());
        } else {
          //throw wrong toolCard exception
        }
    }

    @Override
    public void receiveMove(Pos pos) {
        if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            //move allowed, call the model modifier passing parameters: dice, posDiceChosen, pos
            turn.getModifier().positionDice(chosenDice,posDiceChosen,pos);
            turn.setState(new PositionDice1(turn));
        } else {
            //throw wrong placement exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
