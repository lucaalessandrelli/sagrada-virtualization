package it.polimi.ingsw.model.gamedata.gametools;

class AlreadyBeenCalledException extends Exception {
    String message;
    AlreadyBeenCalledException(){
        message = "This method is already been called";
    }
}