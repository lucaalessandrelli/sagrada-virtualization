package it.polimi.ingsw.turn.moveexceptions;

public class WrongMoveException extends Exception {
    public WrongMoveException(String message) {
        super(message);
    }
}