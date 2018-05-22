package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorContext;

public class ToolBeforeDice implements TurnState {
    private Turn turn;
    private InspectorContext inspectorContext;

    public ToolBeforeDice(Turn turn) {
        this.turn = turn;
        this.inspectorContext = turn.getInspectorContext();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice dice,Pos pos) {
        if(inspectorContext.check(dice,pos,turn.getPlayer().getDraftPool())) {
            turn.setState(new ChooseDice2(turn, dice, pos, new Dice(), new Pos()));
        } else {
            //throw wrong Dice exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
