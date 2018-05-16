package it.polimi.ingsw.model.gameData;

public class AlreadyBeenCalledException extends Exception {
    String message;
    public  AlreadyBeenCalledException(){
        message = "This method is already been called";
    }
}