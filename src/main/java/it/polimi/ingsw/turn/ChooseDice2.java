package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;
import it.polimi.ingsw.model.gameLogic.Checker.InspectorPlace;

public class ChooseDice2 implements TurnState {
    private Turn turn;
    private Pos posDiceChosen;
    private Dice chosenDice;
    InspectorPlace inspectorPlace;

    public ChooseDice2(Turn turn, Dice chosenDice,Pos posDiceChosen, Dice emptyDice, Pos emptyPos) {
        this.chosenDice = chosenDice;
        this.turn = turn;
        this.posDiceChosen = posDiceChosen;
    }

    //GETTING MOVE METHODS

    @Override
    public void receiveMove(Pos pos) {
        if(inspectorPlace.check(chosenDice,pos,turn.getPlayer().getWindowPatternCard())) {
            //move allowed, call the model modifier passing parameters: dice, posDiceChosen, pos
            turn.getModifier().positionDice(chosenDice,posDiceChosen,pos);
            //could end the turn here without calling another state
            turn.setState(new PositionDice2(turn));
        } else {
            //throw wrong placement exception
        }
    }

    @Override
    public void receiveMove(String pass) {
        turn.setState(new EndTurn(turn));
    }
}
