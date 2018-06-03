package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

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
    public void receiveMove(ToolCard toolCard) throws WrongMoveException {
        System.out.println("sono qui" + toolList.contains(toolCard.getID()) + "e adesso qui" + inspectorContext.check(toolCard, turn.getPlayer().getToolCards()));
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
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere la carta strumento "+ toolCard.toString() +".");
        }
    }

    @Override
    public void receiveMove(Pos pos) throws WrongMoveException {
        if(turn.getRoundNumber() == 1 && turn.isFirstBracket()) {
            if(inspectorPlace.checkFirst(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDiceFromDraft(chosenDice, posDiceChosen, pos);
                turn.setState(new PositionDice1(turn));
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole del primo piazzamento.");
            }
        } else {
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDiceFromDraft(chosenDice, posDiceChosen, pos);
                turn.setState(new PositionDice1(turn));
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole di piazzamento.");
            }
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
