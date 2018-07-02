package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

import java.util.ArrayList;

/**
 * Class defining the concrete state ChooseDice1,in this state a player can chose a ToolCard, a Position, or Pass moves.
 */
public class ChooseDice1 implements TurnState {
    private static final int TOOLCARD_1 = 1;
    private static final int TOOLCARD_5 = 5;
    private static final int TOOLCARD_6 = 6;
    private static final int TOOLCARD_9 = 9;
    private static final int TOOLCARD_10 = 10;
    private static final int TOOLCARD_11 = 11;
    private Turn turn;
    private Dice chosenDice;
    private Pos posDiceChosen;
    private ArrayList<Integer> toolList = new ArrayList<>();
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;

    /**
     * Classic constructor.
     * @param turn Store the Turn object in order to call methods on it.
     * @param chosenDice The Dice the player has selected.
     * @param posDiceChosen The Position of the Dice the player has selected.
     */
    public ChooseDice1(Turn turn, Dice chosenDice, Pos posDiceChosen) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posDiceChosen = posDiceChosen;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectorPlace = turn.getInspectorPlace();
        this.toolList.add(TOOLCARD_1);
        this.toolList.add(TOOLCARD_5);
        this.toolList.add(TOOLCARD_6);
        this.toolList.add(TOOLCARD_9);
        this.toolList.add(TOOLCARD_10);
        this.toolList.add(TOOLCARD_11);
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) throws WrongMoveException {
        Player currentPlayer = turn.getPlayer();
        if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, currentPlayer.getToolCards(),currentPlayer))) {
            //call the modifier method to update the cost of the toolcard and the favtokens of the player
            turn.getModifier().updateToolCardPrice(toolCard,currentPlayer);
            //setting the toolCard used in this turn, setting the list of states for the dynamic state machine, setting the list of operations for the AutomatedOperation State
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
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
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
