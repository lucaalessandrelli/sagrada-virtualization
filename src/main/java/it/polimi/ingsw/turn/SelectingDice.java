package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class SelectingDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorContextTool inspectorContextTool;

    public SelectingDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectorContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice chosenDice, Pos posChosenDice) throws WrongMoveException {
        //change inspector
        //inspectContextTool.check(chosenDice,posChosenDice,turn.getPlayer().getToolCard())
        if (inspectorContext.check(chosenDice, posChosenDice, turn.getPlayer().getDraftPool())) {
            turn.setDynamicState(chosenDice, posChosenDice, new Dice(), new Pos());
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere questo dado.");
        }
    }
}
