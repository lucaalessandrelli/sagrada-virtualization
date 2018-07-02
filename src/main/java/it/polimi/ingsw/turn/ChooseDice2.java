package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public class ChooseDice2 implements TurnState {
    private Turn turn;
    private Pos posDiceChosen;
    private Dice chosenDice;
    private Dice toolDice;
    private Pos toolPos;
    private InspectorPlace inspectorPlace;

    public ChooseDice2(Turn turn, Dice chosenDice,Pos posDiceChosen, Dice toolDice, Pos toolPos) {
        this.turn = turn;
        this.chosenDice = chosenDice;
        this.posDiceChosen = posDiceChosen;
        this.toolDice = toolDice;
        this.toolPos = toolPos;
        this.inspectorPlace = turn.getInspectorPlace();
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) throws WrongMoveException{
        if(turn.getRoundNumber() == 1 && turn.isFirstBracket()) {
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDiceFromDraft(chosenDice,posDiceChosen,pos);
                turn.setState(new EndTurn(turn));
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole del primo piazzamento.");
            }
        } else {
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDiceFromDraft(chosenDice, posDiceChosen, pos);
                turn.setState(new EndTurn(turn));
            } else {
                throw new WrongMoveException("Mossa sbagliata: selezionare una posizione della Vetrata che rispetti le regole di piazzamento.");
            }
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
