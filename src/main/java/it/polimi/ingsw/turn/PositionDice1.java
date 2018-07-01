package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gamedata.Player;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;
import java.util.ArrayList;

public class PositionDice1 implements TurnState {
    private Turn turn;
    private ArrayList<Integer> toolList = new ArrayList<>();
    private InspectorContext inspectorContext;

    public PositionDice1(Turn turn) {
        this.turn = turn;
        this.inspectorContext = turn.getInspectorContext();
        this.toolList.add(2);
        this.toolList.add(3);
        this.toolList.add(4);
        this.toolList.add(8);
        this.toolList.add(12);
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) throws WrongMoveException {
        int cardId = toolCard.getID();
        Player currentPlayer = turn.getPlayer();
        if(toolList.contains(toolCard.getID()) && (inspectorContext.check(toolCard, currentPlayer.getToolCards(),currentPlayer)) && ((cardId == 8 && turn.isFirstBracket()) || (cardId != 8))) {
            //call the modifier method to update the cost of the toolcard and the favtokens of the player
            turn.getModifier().updateToolCardPrice(toolCard,currentPlayer);
            //setting the toolCard used in this turn, setting the list of states for the dynamic state machine, setting the list of operations for the AutomatedOperation State
            turn.setToolCardInfo(toolCard);
           //setting the check point i need to return after the user do the moves of the toolCard
           turn.setCheckPointState(new EndTurn(turn));
           //need to set dynamic current state
           turn.setDynamicState(new Dice(),new Pos(), new Dice(), new Pos());
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere la carta strumento "+ toolCard.toString() +".");
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
