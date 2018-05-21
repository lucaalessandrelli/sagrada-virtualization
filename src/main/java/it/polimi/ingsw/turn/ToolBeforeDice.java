package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class ToolBeforeDice implements TurnState {
    private Turn turn;
    private ToolCard toolCard;

    public ToolBeforeDice(Turn turn, ToolCard toolCard) {
        this.turn = turn;
        this.toolCard = toolCard;
    }

    @Override
    public boolean doChoice() {
        return false;
    }

    @Override
    public void viewChoice() {

    }

    //GETTING MOVE METHODS
    @Override
    public void receiveMove(ToolCard toolCard) {
        System.out.println("tool");
    }

    @Override
    public void receiveMove(Dice dice) {
        System.out.println("dice");
    }

    @Override
    public void receiveMove(Pos pos) {
        System.out.println("pos");
    }

    @Override
    public void receiveMove(String pass) {
        System.out.println(pass);
        turn.setState(new EndTurn(turn));
    }




}