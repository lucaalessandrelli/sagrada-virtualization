package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class SelectingToolDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Pos posChosenDice;
    private Dice toolDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorContextTool inspectorContextTool;

    public SelectingToolDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posChosenDice = posChosenDice;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectorContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice,Pos toolPos) throws WrongMoveException {
        //change inspector
        //inspectContextTool.check(toolDice,toolDice,turn.getToolCard());
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere questo dado.");
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
