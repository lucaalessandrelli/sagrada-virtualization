package it.polimi.ingsw.turn;
import it.polimi.ingsw.model.gameData.Player;

import java.lang.reflect.*;

public class Refle {
        private Turn currTurn;

        public Refle(Turn currTurn) {
            this.currTurn = currTurn;
        }

        public void testing() {
            try {

                //prendo da file il nome dello stato  cui devo andare e con java reflection dovrei
                //crearmi una istanza di quell'oggetto in modo tale da poter chiamare sul turno
                // turn.setState(ISTANZA CREATA CON REFLECTION);
                Class cls = Class.forName("it.polimi.ingsw.turn.EndTurn");
                Class partypes = Turn.class;

                Constructor ct = cls.getConstructor(partypes);

                TurnState retobj = (TurnState)ct.newInstance(currTurn);

                currTurn.setState(retobj);
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
}