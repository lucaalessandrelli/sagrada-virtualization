package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public interface TurnState {
    default void viewChoice() {
        //future use
    }
    default boolean doChoice() {
        //future use
        return true;
    }

    //receiving move methods
    default void receiveMove(ToolCard toolCard) {
        //throw wrong move exception
    }
    default void receiveMove(Dice dice,Pos pos) {
        //throw wrong move exception
    }
    default void receiveMove(Pos pos) {
        //throw wrong move exception
    }
    default void receiveMove(String pass) {
        //throw wrong move exception
    }

}