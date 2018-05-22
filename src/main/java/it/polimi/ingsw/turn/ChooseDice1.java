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
    private Pos posDiceChosen;
    private ArrayList<Integer> toolList = new ArrayList<>();
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;

    public ChooseDice1(Turn turn, Dice chosenDice, Pos posDiceChosen) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posDiceChosen = posDiceChosen;

        this.inspectorContext = turn.getInspectorContext();
        this.inspectorPlace = turn.getInspectorPlace();
        this.toolList.add(1);
        this.toolList.add(5);
        this.toolList.add(6);
        this.toolList.add(9);
        this.toolList.add(10);
        this.toolList.add(11);
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
        if(turn.getRoundNumber() == 1 && turn.getFirstBracket()) {
            if(inspectorPlace.checkFirst(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDice(chosenDice, posDiceChosen, pos);
                turn.setState(new PositionDice1(turn));
            } else {
                //throw wrong placement exception
            }
        } else {
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDice(chosenDice, posDiceChosen, pos);
                turn.setState(new PositionDice1(turn));
            } else {
                //throw wrong placement exception
            }
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
