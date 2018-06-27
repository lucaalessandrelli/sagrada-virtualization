package it.polimi.ingsw.model.gamedata.gametools;

 class EmptyCellException extends Exception {
    String message;
    EmptyCellException(){
        message = "Insert another position, this one is empty";
    }
}