package it.polimi.ingsw.model.gamedata.gametools;

public class EmptyCellException extends Exception {
    String message;
    public  EmptyCellException(){
        message = "Insert another position, this one is empty";
    }
}