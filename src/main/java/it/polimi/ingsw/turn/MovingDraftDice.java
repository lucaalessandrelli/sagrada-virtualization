package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorContext;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlaceTool;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class MovingDraftDice implements TurnState {
    private Turn turn;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos posChosenDice;
    private Pos toolPos;
    private InspectorPlaceTool inspectorPlaceTool;
    private InspectorPlace inspectorPlace;

    public MovingDraftDice(Turn turn, Dice chosenDice, Pos posChosenDice, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.posChosenDice = posChosenDice;
        this.chosenDice = chosenDice;
        this.inspectorPlaceTool = turn.getInspectorPlaceTool();
        this.inspectorPlace = turn.getInspectorPlace();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) throws WrongMoveException {
        if(turn.getRoundNumber() == 1 && turn.isFirstBracket() && turn.getToolCard().getID() != 8) {
            if (inspectorPlace.checkFirst(chosenDice,pos,turn.getPlayer().getWindowPatternCard()) && inspectorPlaceTool.check(chosenDice, pos, turn.getToolCard())) {
                //call modifier
                turn.getModifier().positionDiceFromDraft(chosenDice, posChosenDice, pos);
                turn.setDynamicState(chosenDice, posChosenDice, toolDice, toolPos);
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole del primo piazzamento e relative alla carta selezionata.");
            }
        } else {
            if (inspectorPlaceTool.check(chosenDice, pos, turn.getToolCard())) {
                //call modifier
                turn.getModifier().positionDiceFromDraft(chosenDice, posChosenDice, pos);
                turn.setDynamicState(chosenDice, posChosenDice, toolDice, toolPos);
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole di piazzamento della relativa carta.");
            }
        }
    }
}