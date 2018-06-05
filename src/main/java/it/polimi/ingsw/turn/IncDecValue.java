package it.polimi.ingsw.turn;


import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class IncDecValue implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContextTool inspectContextTool;

    public IncDecValue(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.inspectContextTool = turn.getInspectorContextTool();
    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(Dice toolDice, Pos toolPos) throws WrongMoveException {
        if(inspectContextTool.checkNumDice(chosenDice,posChosenDice,toolDice,toolPos)) {
            //call modifier so the value of the dice will be changed
            turn.getModifier().changeDiceValue(chosenDice,posChosenDice,toolDice);
            turn.setDynamicState(toolDice,toolPos,new Dice(),new Pos());
        } else {
            throw new WrongMoveException("Mossa sbagliata: Decrementa o aumenta il valore del dado scelto di 1. Non puoi far diventare un 1 in un 6 e viceversa.");
        }
    }
}