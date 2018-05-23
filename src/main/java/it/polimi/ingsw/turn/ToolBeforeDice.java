package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class ToolBeforeDice implements TurnState {
    private Turn turn;
    private InspectorContext inspectorContext;

    public ToolBeforeDice(Turn turn) {
        this.turn = turn;
        this.inspectorContext = turn.getInspectorContext();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice dice,Pos pos) throws WrongMoveException {
        if(inspectorContext.check(dice,pos,turn.getPlayer().getDraftPool())) {
            turn.setState(new ChooseDice2(turn, dice, pos, new Dice(), new Pos()));
        } else {
            throw new WrongMoveException("Mossa sbagliata: Non è possibile scegliere questo dado.");
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
