package it.polimi.ingsw.model.gamelogic;

public class NotEnoughPlayersException extends Exception {
    String message;
    public  NotEnoughPlayersException(String username){
        message = username;
    }
}
