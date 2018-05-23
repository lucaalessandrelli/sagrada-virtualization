package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlaceTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class MovingDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorContext inspectorContext;
    private InspectorPlace inspectorPlace;
    private InspectorPlaceTool inspectorPlaceTool;

    public MovingDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.inspectorContext = turn.getInspectorContext();
        this.inspectorPlace = turn.getInspectorPlace();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) throws WrongMoveException {
        //change inspector
        //inspectorPlaceTool.check(chosenDice,pos,turn.getToolCard());
        if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            //call modifier
            turn.getModifier().positionDice(chosenDice,posChosenDice,pos);
            turn.setDynamicState(chosenDice,posChosenDice,toolDice,toolPos);
        } else {
            throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole di piazzamento della relativa carta.");
        }
    }
}