package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class SelectingDraftDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContextTool inspectorContextTool;

    public SelectingDraftDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice chosenDice, Pos posChosenDice) throws WrongMoveException {
        if (inspectorContextTool.check(chosenDice,posChosenDice,turn.getToolCard())) {
            turn.setDynamicState(chosenDice, posChosenDice, new Dice(), new Pos());
        } else {
            throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere questo dado.");
        }
    }
}
