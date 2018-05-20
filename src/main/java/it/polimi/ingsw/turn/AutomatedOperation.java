package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gameData.Pos;
import it.polimi.ingsw.model.gameData.gameTools.Dice;
import it.polimi.ingsw.model.gameData.gameTools.ToolCard;

public class AutomatedOperation implements TurnState {
    private Turn turn;

    /* problema: aggiungere la lista di automated operation in turno, così posso chiamare il metodo dalla classe AutomatedOperation
        che uno alla volta mi scorre la lista e mi chaima i metodi della classe CambiaModel.
        Problema: io creo lo stato di AutomatedOperation a come faccio a far partire i metodi?? utilizzo un metodo implementato sull'interfaccia
        e lo chiamo sullo stato quando la stringaa presa con la reflection è "automatedOperation?"
        problema: che parametri devo passare al metodo che mi modifica la view??? Devo passarglieli il più possibili generali così lui ha tutto ciò
        che gli serve per modificare il model
    * */
    public AutomatedOperation(Turn turn) {
        this.turn = turn;

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
        //throw wrong move exception
    }

    @Override
    public void receiveMove(Dice dice) {
        //throw wrong move exception
    }

    @Override
    public void receiveMove(Pos pos) {
        //throw wrong move exception
    }

    @Override
    public void receiveMove(String pass) {
        //throw wrong move exception
    }
}
