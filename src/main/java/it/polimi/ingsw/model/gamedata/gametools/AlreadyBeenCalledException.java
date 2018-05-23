package it.polimi.ingsw.model.gamedata.gametools;

public class AlreadyBeenCalledException extends Exception {
    String message;
    public  AlreadyBeenCalledException(){
        message = "This method is already been called";
    }
}