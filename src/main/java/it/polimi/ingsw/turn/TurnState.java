package it.polimi.ingsw.turn;

import it.polimi.ingsw.model.gamedata.gametools.Dice;
import it.polimi.ingsw.model.gamedata.Pos;
import it.polimi.ingsw.model.gamedata.gametools.ToolCard;
import it.polimi.ingsw.turn.moveexceptions.WrongMoveException;

public interface TurnState {

    //receiving move methods
    default void receiveMove(ToolCard toolCard) throws WrongMoveException {
        throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere una Carta Strumento in questa fase del turno.");
    }

    default void receiveMove(Dice dice,Pos pos) throws WrongMoveException {
        throw new WrongMoveException("Mossa sbagliata: non è possibile scegliere un Dado in questa fase del turno.");
    }

    default void receiveMove(Pos pos) throws WrongMoveException {
        throw new WrongMoveException("Mossa sbagliata: non è possibile posizionare un dado in questa fase del turno.");
    }

    default void receiveMove(String pass) throws WrongMoveException {
        throw new WrongMoveException("Non è possibile passare il turno: finire la mossa prima di passare.");
    }

    default void automaticPass() {
    }
}