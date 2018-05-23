package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamelogic.checker.InspectorPlace;

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
    public void receiveMove(Pos pos) {
        if(turn.getRoundNumber() == 1 && turn.isFirstBracket()) {
            if(inspectorPlace.checkFirst(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDice(chosenDice,posDiceChosen,pos);
                turn.setState(new EndTurn(turn));
            } else {
                //throw wrong placement exception
            }
        } else {
            if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
                turn.getModifier().positionDice(chosenDice, posDiceChosen, pos);
                turn.setState(new EndTurn(turn));
            } else {
                //throw wrong placement exception
            }
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
