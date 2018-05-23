package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContextTool;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class SelectingValue implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Pos posChosenDice;
    private Dice toolDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorContextTool inspectorContextTool;

    public SelectingValue(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
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
        //chiamare inspector diversi
        //inspectContextTool.checkColourDice(chosenDice,posChosenDice,toolDice,toolPos);
        if(inspectorContext.check(toolDice,toolPos,turn.getPlayer().getDraftPool())) {
                //call modifier
                turn.getModifier().changeDiceValue(chosenDice,posChosenDice,toolDice);
                turn.setDynamicState(toolDice,toolPos,new Dice(), new Pos());
        } else {
            throw new WrongMoveException("Mossa sbagliata: selezionare un valore da assegnare al dado scelto.");
        }
    }
}
