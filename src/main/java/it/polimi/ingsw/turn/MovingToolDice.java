package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlaceTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class MovingToolDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private InspectorPlaceTool inspectorPlaceTool;

    public MovingToolDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posChosenDice = posChosenDice;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectorPlace = turn.getInspectorPlace();
        this.inspectorPlaceTool = turn.getInspectorPlaceTool();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) throws WrongMoveException {
        //change inspector
        //inspectorPlaceTool.check(toolDice,toolPos,turn.getToolCard());
        if(inspectorPlace.check(toolDice,toolPos,turn.getPlayer().getWindowPatternCard())) {
            //call modifier
            turn.getModifier().positionDice(toolDice,toolPos,pos);
            turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole di piazzamento della relativa carta.");
        }
    }
}