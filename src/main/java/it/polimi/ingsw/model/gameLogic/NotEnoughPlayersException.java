package it.polimi.ingsw.model.gameLogic;

public class NotEnoughPlayersException extends Exception {
    String message;
    public  NotEnoughPlayersException(){
        message = "Not enough players in current match";
    }
}
